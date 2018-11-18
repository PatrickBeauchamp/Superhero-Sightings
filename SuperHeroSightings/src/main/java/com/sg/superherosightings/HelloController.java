package com.sg.superherosightings;

import com.sg.superherosightings.dao.Dao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Mutant;
import com.sg.superherosightings.model.Org;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    Dao dao;

    @Inject
    public HelloController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/lastTenSightings"}, method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("last10Sightings", dao.getLast10Sightings());
        return "lastTenSightings";
    }

    @RequestMapping(value = "/powers", method = RequestMethod.GET)
    public String powers(Map<String, Object> model) {
        model.put("powersList", dao.getAllPowers());
        return "powers";
    }

    @RequestMapping(value = "/addPowerPage", method = RequestMethod.GET)
    public String addPowerPage() {
        return "addPowerPage";
    }

    @RequestMapping(value = "/detailsPowerPage", method = RequestMethod.GET)
    public String detailsPowerPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsPowerId");
        int powerId = parseInt(input);
        Power power = dao.getPowerById(powerId);
        List<Mutant> mutants = dao.getMutantsByPowerId(powerId);
        model.addAttribute("mutants", mutants);
        model.addAttribute("power", power);
        return "detailsPowerPage";
    }

    @RequestMapping(value = "/editPowerPage", method = RequestMethod.POST)
    public String editPowerPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("editPowerId");
        model.addAttribute("power", dao.getPowerById(parseInt(input)));
        return "editPowerPage";
    }

    @RequestMapping(value = "/editPower", method = RequestMethod.POST)
    public String editPower(HttpServletRequest request, Model model) {
        String input = request.getParameter("powerId");
        Power power = dao.getPowerById(parseInt(input));
        String description = request.getParameter("powerDescription");
        if (description.length() != 0) {
            power.setDescription(description);
            dao.updatePower(power);
            dao.getPowerById(parseInt(input));
        }
        model.addAttribute("powersList", dao.getAllPowers());
        return "powers";
    }

    @RequestMapping(value = "/addPower", method = RequestMethod.POST)
    public String addPower(HttpServletRequest request, Model model) {
        String input = request.getParameter("powerDescription");
        Power power = new Power();
        power.setDescription(input);
        dao.addPower(power);
        model.addAttribute("powersList", dao.getAllPowers());
        return "powers";
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.POST)
    public String deletePower(HttpServletRequest request, Model model) {
        String input = request.getParameter("deletePower");
        int powerId = parseInt(input);
        dao.deletePower(powerId);
        model.addAttribute("powersList", dao.getAllPowers());
        return "powers";
    }

    @RequestMapping(value = "/mutants", method = RequestMethod.GET)
    public String mutants(Map<String, Object> model) {
        model.put("mutantsList", dao.getAllMutants());
        return "mutants";
    }

    @RequestMapping(value = "/addMutantPage", method = RequestMethod.GET)
    public String addMutantPage(Model model) {
        model.addAttribute("orgs", dao.getAllOrgs());
        model.addAttribute("powers", dao.getAllPowers());
        return "addMutantPage";
    }

    @RequestMapping(value = "/addMutant", method = RequestMethod.POST)
    public String addMutant(HttpServletRequest request, Model model) {
        Mutant mutant = new Mutant();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] powers = request.getParameterValues("powers");
        String[] orgs = request.getParameterValues("orgs");
        List<Power> mutantPowers = new ArrayList();
        List<Org> mutantOrgs = new ArrayList();
        mutant.setName(name);
        mutant.setDescription(description);
        for (int i = 0; i < powers.length; i++) {
            mutantPowers.add(dao.getPowerById(parseInt(powers[i])));
        }
        mutant.setPowers(mutantPowers);
        for (int i = 0; i < orgs.length; i++) {
            mutantOrgs.add(dao.getOrgById(parseInt(orgs[i])));
        }
        mutant.setOrgs(mutantOrgs);
        dao.addMutant(mutant);
        model.addAttribute("mutantsList", dao.getAllMutants());
        return "mutants";

    }

    @RequestMapping(value = "/editMutantPage", method = RequestMethod.POST)
    public String editMutantPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("editMutantId");
        int mutantId = parseInt(input);
        Mutant mutant = dao.getMutantById(mutantId);
        model.addAttribute("mutant", mutant);
        model.addAttribute("powers", dao.getAllPowers());
        model.addAttribute("orgs", dao.getAllOrgs());

        return "editMutantPage";
    }

    @RequestMapping(value = "/editMutant", method = RequestMethod.POST)
    public String editMutant(HttpServletRequest request, Model model) {
        String input = request.getParameter("editMutantId");
        int mutantId = parseInt(input);
        Mutant mutant = dao.getMutantById(mutantId);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] powers = request.getParameterValues("powers");
        String[] orgs = request.getParameterValues("orgs");
        List<Power> mutantPowers = new ArrayList();
        List<Org> mutantOrgs = new ArrayList();
        if (name.length() != 0) {
            mutant.setName(name);
        }
        if (description.length() != 0) {
            mutant.setDescription(description);
        }
        if (powers != null && powers.length != 0) {
            for (int i = 0; i < powers.length; i++) {
                mutantPowers.add(dao.getPowerById(parseInt(powers[i])));
            }
            mutant.setPowers(mutantPowers);
        }
        if (orgs != null && orgs.length != 0) {
            for (int i = 0; i < orgs.length; i++) {
                mutantOrgs.add(dao.getOrgById(parseInt(orgs[i])));
            }
            mutant.setOrgs(mutantOrgs);
        }
        dao.updateMutant(mutant);
        model.addAttribute("mutantsList", dao.getAllMutants());
        return "mutants";

    }

    @RequestMapping(value = "/deleteMutant", method = RequestMethod.POST)
    public String deleteMutant(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteMutant");
        int mutantId = parseInt(input);
        dao.deleteMutant(mutantId);
        model.addAttribute("mutantsList", dao.getAllMutants());
        return "mutants";
    }

    @RequestMapping(value = "/detailsMutant", method = RequestMethod.GET)
    public String detailsMutant(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsMutant");
        int mutantId = parseInt(input);
        List<Location> locations = dao.getLocationsByMutantId(mutantId);
        model.addAttribute("locations", locations);

        model.addAttribute("mutant", dao.getMutantById(mutantId));
        return "detailsMutant";
    }

    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    public String organizations(Map<String, Object> model) {
        model.put("orgsList", dao.getAllOrgs());
        return "organizations";
    }

    @RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
    public String deleteOrg(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteOrg");
        int orgId = parseInt(input);
        dao.deleteOrg(orgId);
        model.addAttribute("orgsList", dao.getAllOrgs());
        return "organizations";
    }

    @RequestMapping(value = "/editOrgPage", method = RequestMethod.GET)
    public String editOrgPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("editOrgId");
        int orgId = parseInt(input);

        model.addAttribute("org", dao.getOrgById(orgId));
        return "editOrgPage";
    }

    @RequestMapping(value = "/editOrg", method = RequestMethod.POST)
    public String editOrg(HttpServletRequest request, Model model) {
        String orgId = request.getParameter("orgId");
        Org org = dao.getOrgById(parseInt(orgId));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        if (name.length() != 0) {
            org.setName(name);
        }
        if (description.length() != 0) {
            org.setDescription(description);
        }
        if (street.length() != 0) {
            org.setStreet(street);
        }
        if (city.length() != 0) {
            org.setCity(city);
        }
        if (state.length() != 0) {
            org.setState(state);
        }
        if (zip.length() != 0) {
            org.setZip(zip);
        }
        if (phone.length() != 0) {
            org.setPhone(phone);
        }
        if (email.length() != 0) {
            org.setEmail(email);
        }
        dao.updateOrg(org);
        model.addAttribute("orgsList", dao.getAllOrgs());
        return "organizations";

    }

    @RequestMapping(value = "/addOrgPage", method = RequestMethod.GET)
    public String addOrgPage() {
        return "addOrgPage";
    }

    @RequestMapping(value = "/addOrg", method = RequestMethod.POST)
    public String addOrg(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Org org = new Org();
        org.setName(name);
        org.setDescription(description);
        org.setStreet(street);
        org.setCity(city);
        org.setState(state);
        org.setZip(zip);
        org.setPhone(phone);
        org.setEmail(email);
        dao.addOrg(org);
        model.addAttribute("orgsList", dao.getAllOrgs());
        return "organizations";
    }

    @RequestMapping(value = "/detailsOrg", method = RequestMethod.GET)
    public String detailsOrg(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsOrg");
        int orgId = parseInt(input);
        List<Mutant> mutants = dao.getMutantsByOrgId(orgId);
        model.addAttribute("mutants", mutants);
        model.addAttribute("org", dao.getOrgById(orgId));
        return "detailsOrganization";
    }

    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    public String sightings(Map<String, Object> model) {
        model.put("sightingsList", dao.getAllSightings());
        return "sightings";
    }

    @RequestMapping(value = "/addSightingPage", method = RequestMethod.GET)
    public String addSightingPage(Model model) {
        model.addAttribute("mutants", dao.getAllMutants());
        model.addAttribute("locations", dao.getAllLocations());
        return "addSightingPage";
    }

    @RequestMapping(value = "/editSightingPage", method = RequestMethod.GET)
    public String editSightingPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("editSightingId");
        int sightingId = parseInt(input);
        model.addAttribute("sighting", dao.getSightingById(sightingId));
        model.addAttribute("mutants", dao.getAllMutants());
        model.addAttribute("locations", dao.getAllLocations());
        return "editSightingPage";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, Model model) {
        String input = request.getParameter("sightingId");
        Sighting sighting = dao.getSightingById(parseInt(input));
        String date = request.getParameter("date");
        String[] mutants = request.getParameterValues("mutants");
        String location = request.getParameter("location");
        List<Mutant> sightingMutants = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (date.length() != 0) {
            LocalDate sightingDate = LocalDate.parse(date, formatter);
            sighting.setDate(sightingDate);
        }
        if (mutants != null && mutants.length != 0) {
            for (int i = 0; i < mutants.length; i++) {
                sightingMutants.add(dao.getMutantById(parseInt(mutants[i])));
            }
            sighting.setMutants(sightingMutants);
        }
        if (location.length() != 0) {
            sighting.setLocation(dao.getLocationById(parseInt(location)));
        }
        dao.updateSighting(sighting);
        model.addAttribute("sightingsList", dao.getAllSightings());
        return "sightings";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.POST)
    public String deleteSighting(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteSighting");
        dao.deleteSighting(parseInt(input));
        model.addAttribute("sightingsList", dao.getAllSightings());
        return "sightings";
    }

    @RequestMapping(value = "addSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest request, Model model) {
        Sighting sighting = new Sighting();
        String date = request.getParameter("date");
        String[] mutants = request.getParameterValues("mutants");
        String location = request.getParameter("location");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sightingDate = LocalDate.parse(date, formatter);
        sighting.setDate(sightingDate);
        Location loc = dao.getLocationById(parseInt(location));
        sighting.setLocation(loc);

        List<Mutant> sightingMutants = new ArrayList();
        for (int i = 0; i < mutants.length; i++) {
            sightingMutants.add(dao.getMutantById(parseInt(mutants[i])));
        }
        sighting.setMutants(sightingMutants);
        dao.addSighting(sighting);
        model.addAttribute("sightingsList", dao.getAllSightings());
        return "sightings";
    }

    @RequestMapping(value = "/detailsSighting", method = RequestMethod.GET)
    public String detailsSighting(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsSighting");
        int sightingId = parseInt(input);
        model.addAttribute("sighting", dao.getSightingById(sightingId));
        return "detailsSighting";
    }

    @RequestMapping(value = "/sightingsByDate", method = RequestMethod.GET)
    public String sightingsByDate(HttpServletRequest request, Model model) {
        String date = request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Sighting> sightings = new ArrayList();
        LocalDate sightingDate;
        if (date.length() != 0) {
            sightingDate = LocalDate.parse(date, formatter);
            sightings = dao.getSightingsByDate(sightingDate);
            if (sightings.isEmpty()) {
                model.addAttribute("message", "No sightings matched that date.");

                return "sightingsByDate";
            }
            model.addAttribute("sightingsList", sightings);
            return "sightingsByDate";
        }
        model.addAttribute("message", "No sightings matched that date.");

        return "sightingsByDate";
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String locations(Map<String, Object> model) {
        model.put("locationsList", dao.getAllLocations());
        return "locations";
    }

    @RequestMapping(value = "/addLocationPage", method = RequestMethod.GET)
    public String addLocationPage() {
        return "addLocationPage";
    }

    @RequestMapping(value = "/editLocationPage", method = RequestMethod.GET)
    public String editLocationPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("editLocation");
        model.addAttribute("location", dao.getLocationById(parseInt(input)));
        return "editLocationPage";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, Model model) {
        String message = "";
        String input = request.getParameter("locationId");
        Location loc = dao.getLocationById(parseInt(input));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String lat = request.getParameter("latitude");
        String lon = request.getParameter("longitude");

        if (name.length() != 0) {
            loc.setName(name);
        }
        if (description.length() != 0) {
            loc.setDescription(description);
        }
        if (street.length() != 0) {
            loc.setStreet(street);
        }
        if (city.length() != 0) {
            loc.setCity(city);
        }
        if (state.length() != 0) {
            loc.setState(state);
        }
        if (zip.length() != 0) {
            loc.setZip(zip);
        }
        if (lat.length() != 0) {
            try {
                loc.setLatitude(new BigDecimal(lat));
            } catch (NumberFormatException ex) {
                message = "Latitude and Longitude must be in proper numeric form.";
                model.addAttribute("message", message);
                model.addAttribute("location", dao.getLocationById(parseInt(input)));
                return "editLocationPage";
            }
        }
        if (lon.length() != 0) {
            try {
                loc.setLongitude(new BigDecimal(lon));
            } catch (NumberFormatException ex) {
                message = "Latitude and Longitude must be in proper numeric form.";
                model.addAttribute("message", message);
                model.addAttribute("location", dao.getLocationById(parseInt(input)));
                return "editLocationPage";
            }
        }
        dao.updateLocation(loc);
        model.addAttribute("locationsList", dao.getAllLocations());

        return "locations";
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request, Model model) {
        Location location = new Location();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String message = "";
        try {
            BigDecimal lat = new BigDecimal(latitude);
            BigDecimal lon = new BigDecimal(longitude);

            location.setName(name);
            location.setDescription(description);
            location.setStreet(street);
            location.setCity(city);
            location.setState(state);
            location.setZip(zip);
            location.setLatitude(lat);
            location.setLongitude(lon);
            dao.addLocation(location);
            model.addAttribute("locationsList", dao.getAllLocations());
            return "locations";
        } catch (NumberFormatException ex) {
            message = "Latitude and Longitude must be in proper numeric form.";
        }
        model.addAttribute("message", message);
        return "addLocationPage";
    }

    @RequestMapping(value = "/detailsLocation", method = RequestMethod.GET)
    public String detailsLocation(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsLocation");
        int locationId = parseInt(input);
        List<Mutant> mutants = dao.getMutantsByLocationId(locationId);
        model.addAttribute("mutants", mutants);
        model.addAttribute("location", dao.getLocationById(locationId));
        return "detailsLocation";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.POST)
    public String deleteLocation(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteLocation");
        int locationId = parseInt(input);
        dao.deleteLocation(locationId);
        model.addAttribute("locationsList", dao.getAllLocations());
        return "locations";
    }

}
