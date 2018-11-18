/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Mutant;
import com.sg.superherosightings.model.Org;
import com.sg.superherosightings.model.Power;
import com.sg.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author patty
 */
@Component
public class JdbcTemplateImpl implements Dao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // location CRUD SQL
    private static final String SQL_SELECT_LAST_INSERT_ID
            = "select LAST_INSERT_ID()";

    private static final String SQL_INSERT_LOCATION
            = "insert into Locations (LocationName, LocationDescription, "
            + "LocationStreet, LocationCity, LocationState, LocationZip, "
            + "Latitude, Longitude) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from Locations where LocationID = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update Locations set LocationName = ?, LocationDescription = ?, "
            + "LocationStreet = ?, LocationCity = ?, LocationState = ?, "
            + "LocationZip = ?, Latitude = ?, Longitude = ? where LocationID = ?";

    private static final String SQL_SELECT_LOCATION_BY_ID
            = "select * from Locations where LocationID = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Locations";

    // mutant CRUD SQL
    private static final String SQL_INSERT_MUTANT
            = "insert into Mutants (MutantName, MutantDescription) "
            + "values (?, ?)";

    private static final String SQL_DELETE_MUTANT
            = "delete from Mutants where MutantID = ?";

    private static final String SQL_UPDATE_MUTANT
            = "update Mutants set MutantName = ?, MutantDescription = ? where MutantID = ?";

    private static final String SQL_SELECT_MUTANT_BY_ID
            = "select * from Mutants where MutantID = ?";

    private static final String SQL_SELECT_ALL_MUTANTS
            = "select * from Mutants";

    // org CRUD SQL
    private final static String SQL_INSERT_ORG
            = "insert into Orgs (OrgName, OrgDescription, OrgStreet, OrgCity, "
            + "OrgState, OrgZip, OrgPhone, OrgEmail) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private final static String SQL_DELETE_ORG
            = "delete from Orgs where OrgID = ?";

    private final static String SQL_UPDATE_ORG
            = "update Orgs set OrgName = ?, OrgDescription = ?, OrgStreet = ?, "
            + "OrgCity = ?, OrgState = ?, OrgZip = ?, OrgPhone = ?, OrgEmail = ? where OrgID = ?";

    private final static String SQL_SELECT_ORG_BY_ID
            = "select * from Orgs where OrgID = ?";

    private final static String SQL_SELECT_ALL_ORGS
            = "select * from Orgs";

    // power CRUD SQL
    private final static String SQL_INSERT_POWER
            = "insert into Powers (PowerDescription) values (?)";

    private final static String SQL_DELETE_POWER
            = "delete from Powers where PowerID = ?";

    private final static String SQL_UPDATE_POWER
            = "update Powers set PowerDescription = ? where PowerID = ?";

    private final static String SQL_SELECT_POWER_BY_ID
            = "select * from Powers where PowerID = ?";

    private final static String SQL_SELECT_ALL_POWERS
            = "select * from Powers";

    // sighting CRUD SQL
    private final static String SQL_INSERT_SIGHTING
            = "insert into Sightings (SightingDate, LocationID) values (?, ?)";

    private final static String SQL_DELETE_SIGHTING
            = "delete from Sightings where SightingID = ?";
    
    private final static String SQL_DELETE_SIGHTING_BY_LOCATION_ID
            = "delete from Sightings where Sightings.LocationID = ?";

    private final static String SQL_UPDATE_SIGHTING
            = "update Sightings set SightingDate = ?, LocationID = ? where SightingID = ?";

    private final static String SQL_SELECT_SIGHTING_BY_ID
            = "select s.sightingid, s.sightingdate, s.locationid, l.locationname, l.locationdescription, "
            + "l.locationstreet, l.locationcity, l.locationstate, l.locationzip, l.latitude, "
            + "l.longitude from sightings s inner join "
            + "locations l on s.locationid = l.locationid "
            + "where s.sightingid = ?";

    private final static String SQL_SELECT_ALL_SIGHTINGS
            = "select s.sightingid, s.sightingdate, s.locationid, l.locationname, l.locationdescription, "
            + "l.locationstreet, l.locationcity, l.locationstate, l.locationzip, l.latitude, "
            + "l.longitude from sightings s inner join "
            + "locations l on s.locationid = l.locationid ";

    // BRIDGE TABLE STATEMENTS
    // mutantsorgs bridge CRUD
    private final static String SQL_INSERT_MUTANTS_ORGS
            = "insert into MutantsOrgs(MutantID, OrgID) "
            + "values (?, ?)";

    private final static String SQL_DELETE_MUTANTS_ORGS_BY_MUTANT_ID
            = "delete from MutantsOrgs where MutantID = ?";

    private final static String SQL_DELETE_MUTANTS_ORGS_BY_ORG_ID
            = "delete from MutantsOrgs where OrgID = ?";

    private final static String SQL_SELECT_MUTANTS_ORGS_BY_MUTANT_ID
            = "select distinct * from Orgs inner join MutantsOrgs on "
            + "Orgs.OrgID = MutantsOrgs.OrgID "
            + "where MutantsOrgs.MutantID = ?";

    private final static String SQL_SELECT_MUTANTS_ORGS_BY_ORG_ID
            = "select * from Orgs inner join MutantsOrgs "
            + "on Orgs.OrgID = MutantsOrgs.OrgID inner join Mutants "
            + "on Mutants.MutantID = MutantsOrgs.MutantID";

    // mutantspowers bridge CRUD
    private final static String SQL_INSERT_MUTANTS_POWERS
            = "insert into MutantsPowers(MutantID, PowerID) "
            + "values (?, ?)";

    private final static String SQL_DELETE_MUTANTS_POWERS_BY_MUTANT_ID
            = "delete from MutantsPowers where MutantID = ?";

    private final static String SQL_DELETE_MUTANTS_POWERS_BY_POWER_ID
            = "delete from MutantsPowers where PowerID = ?";

    private final static String SQL_SELECT_MUTANTS_POWERS
            = "select * from Mutants inner join on MutantsPowers "
            + "where Mutants.MutantID = MutantsPowers.MutantID "
            + "inner join on Powers where MutantsPowers.PowerID = Powers.PowerID";

    private final static String SQL_SELECT_POWERS_BY_MUTANT_ID
            = "select * from Powers inner join MutantsPowers on Powers.PowerID "
            + "= MutantsPowers.PowerID where MutantsPowers.MutantID = ?";
    
    private final static String SQL_SELECT_MUTANTS_BY_POWER_ID
            = "select * from mutants inner join MutantsPowers on Mutants.MutantId = "
            + "MutantsPowers.MutantId where MutantsPowers.PowerId = ?";

    // mutantssightings bridge CRUD
    private final static String SQL_INSERT_MUTANTS_SIGHTINGS
            = "insert into MutantsSightings(MutantID, SightingID) "
            + "values(?, ?)";

    private final static String SQL_DELETE_MUTANTS_SIGHTINGS_BY_MUTANT_ID
            = "delete from MutantsSightings where MutantID = ? ";

    private final static String SQL_DELETE_MUTANTS_SIGHTINGS_BY_SIGHTING_ID
            = "delete from MutantsSightings where SightingID = ?";
    
    private final static String SQL_DELETE_MUTANTS_SIGHTINGS_BY_LOCATION_ID
            = "delete from MutantsSightings where SightingID = "
            + "(select SightingID from Sightings where Sightings.LocationID = ?)";

    private final static String SQL_SELECT_MUTANTSSIGHTINGS_BY_MUTANT_ID
            = "select * from Mutants inner join on MutantsSightings where "
            + "Mutants.MutantID = MutantsSightings.MutantID inner join on "
            + "Sightings where Sightings.SightingID = MutantsSightings.SightingID "
            + "inner join on Locations where Sightings.SightingID where "
            + "Locations.LocationID = Sightings.LocationID";

    private final static String SQL_SELECT_MUTANTSSIGHTINGS_BY_SIGHTING_ID
            = "select * from Mutants inner join MutantsSightings on Mutants.MutantID "
            + "= MutantsSightings.MutantID where MutantsSightings.SightingID = ?";

    // SQL for deliverables
    private final static String SQL_SELECT_MUTANTS_BY_LOCATION_ID
            = "select * from Mutants inner join MutantsSightings on MutantsSightings.MutantID = "
            + "Mutants.MutantID inner join Sightings on MutantsSightings.SightingID = "
            + "Sightings.SightingID where Sightings.LocationID = ?";

    private final static String SQL_SELECT_LOCATIONS_BY_MUTANT_ID
            = "select * from Locations inner join Sightings on Locations.LocationID = "
            + "Sightings.LocationID inner join MutantsSightings on Sightings.SightingID = "
            + "MutantsSightings.SightingID where MutantsSightings.MutantID = ?";

    private final static String SQL_SELECT_ALL_SIGHTINGS_BY_DATE
            = "select s.sightingid, s.sightingdate, s.locationid, l.locationname, "
            + "l.locationdescription, l.locationstreet, l.locationcity, "
            + "l.locationstate, l.locationzip, l.latitude, l.longitude "
            + "from sightings s inner join locations l on s.locationid = "
            + "l.locationid where s.sightingdate = ?";

    private final static String SQL_SELECT_MUTANTS_BY_ORG_ID
            = "select * from Mutants inner join MutantsOrgs on Mutants.MutantID = "
            + "MutantsOrgs.MutantID where MutantsOrgs.OrgID = ?";

    private final static String SQL_SELECT_LAST_10_SIGHTINGS
            = "select s.sightingid, s.sightingdate, s.locationid, l.locationname, "
            + "l.locationdescription, l.locationstreet, l.locationcity, "
            + "l.locationstate, l.locationzip, l.latitude, l.longitude "
            + "from sightings s inner join locations l on s.locationid = "
            + "l.locationid order by s.sightingdate desc limit 10";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude());

        int locationId
                = jdbcTemplate.queryForObject(SQL_SELECT_LAST_INSERT_ID, Integer.class);

        location.setId(locationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocation(int id) {
        jdbcTemplate.update(SQL_DELETE_MUTANTS_SIGHTINGS_BY_LOCATION_ID, id);
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_LOCATION_ID, id);
        jdbcTemplate.update(SQL_DELETE_LOCATION, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude(),
                location.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location getLocationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ID,
                    new LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMutant(Mutant mutant) {
        jdbcTemplate.update(SQL_INSERT_MUTANT,
                mutant.getName(),
                mutant.getDescription());

        int mutantId = jdbcTemplate.queryForObject(SQL_SELECT_LAST_INSERT_ID, Integer.class);

        mutant.setId(mutantId);

        // now update the mutants_orgs table
        insertMutantsOrgs(mutant);
        // now update the mutants_powers table
        insertMutantsPowers(mutant);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteMutant(int id) {
        jdbcTemplate.update(SQL_DELETE_MUTANTS_ORGS_BY_MUTANT_ID, id);
        jdbcTemplate.update(SQL_DELETE_MUTANTS_POWERS_BY_MUTANT_ID, id);
        jdbcTemplate.update(SQL_DELETE_MUTANTS_SIGHTINGS_BY_MUTANT_ID, id);
        jdbcTemplate.update(SQL_DELETE_MUTANT, id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateMutant(Mutant mutant) {
        jdbcTemplate.update(SQL_UPDATE_MUTANT,
                mutant.getName(),
                mutant.getDescription(),
                mutant.getId());

        // delete mutants_powers relationship then reset them
        jdbcTemplate.update(SQL_DELETE_MUTANTS_POWERS_BY_MUTANT_ID, mutant.getId());
        insertMutantsPowers(mutant);

        // delete mutants_orgs relationship then reset them
        jdbcTemplate.update(SQL_DELETE_MUTANTS_ORGS_BY_MUTANT_ID, mutant.getId());
        insertMutantsOrgs(mutant);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Mutant getMutantById(int id) {
        try {
            // get the properties from the mutant table
            Mutant mutant = jdbcTemplate.queryForObject(SQL_SELECT_MUTANT_BY_ID, new MutantMapper(), id);

            // get the Powers for this mutant and set list on the mutant
            mutant.setPowers(findPowersForMutant(mutant));

            // get the Orgs for this mutant and set list on the mutant
            mutant.setOrgs(findOrgsForMutant(mutant));

            return mutant;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Mutant> getAllMutants() {
        List<Mutant> mutants = jdbcTemplate.query(SQL_SELECT_ALL_MUTANTS, new MutantMapper());

        // set the orgs for each mutant    
        mutants = findPowersForAllMutants(mutants);
        mutants = findOrgsForAllMutants(mutants);
        // set the powers for each mutant
        return mutants;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrg(Org org) {
        jdbcTemplate.update(SQL_INSERT_ORG,
                org.getName(),
                org.getDescription(),
                org.getStreet(),
                org.getCity(),
                org.getState(),
                org.getZip(),
                org.getPhone(),
                org.getEmail());

        int orgId = jdbcTemplate.queryForObject(SQL_SELECT_LAST_INSERT_ID, Integer.class);

        org.setId(orgId);

        // now update the mutants_organizations table
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrg(int id) {
        jdbcTemplate.update(SQL_DELETE_MUTANTS_ORGS_BY_ORG_ID, id);
        jdbcTemplate.update(SQL_DELETE_ORG, id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrg(Org org) {
        jdbcTemplate.update(SQL_UPDATE_ORG,
                org.getName(),
                org.getDescription(),
                org.getStreet(),
                org.getCity(),
                org.getState(),
                org.getZip(),
                org.getPhone(),
                org.getEmail(),
                org.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Org getOrgById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORG_BY_ID, new OrgMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Org> getAllOrgs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGS, new OrgMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getDescription());

        int powerId = jdbcTemplate.queryForObject(SQL_SELECT_LAST_INSERT_ID, Integer.class);

        power.setId(powerId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePower(int id) {

        jdbcTemplate.update(SQL_DELETE_MUTANTS_POWERS_BY_POWER_ID, id);
        jdbcTemplate.update(SQL_DELETE_POWER, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getDescription(),
                power.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Power getPowerById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER_BY_ID, new PowerMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getId());

        int sightingId = jdbcTemplate.queryForObject(SQL_SELECT_LAST_INSERT_ID, Integer.class);

        sighting.setId(sightingId);

        insertMutantsSightings(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int id) {

        jdbcTemplate.update(SQL_DELETE_MUTANTS_SIGHTINGS_BY_SIGHTING_ID, id);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getId(),
                sighting.getId());
        // delete mutantssightings relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_MUTANTS_SIGHTINGS_BY_SIGHTING_ID,
                sighting.getId());
        insertMutantsSightings(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting getSightingById(int id) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID,
                    new SightingMapper(), id);
            // get the mutants for this sighting and set list on sighting
            List<Mutant> mutants = findMutantsForSighting(sighting);
            mutants = findPowersForAllMutants(mutants);
            mutants = findOrgsForAllMutants(mutants);
            sighting.setMutants(mutants);
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getAllSightings() {
        //get all the sightings
        List<Sighting> sightings
                = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());

        // set the list of mutants for each sighting
        return findMutantsForAllSightings(sightings);
    }

    // extra methods for deliverables
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Mutant> getMutantsByLocationId(int id) {
        List<Mutant> mutants = jdbcTemplate.query(SQL_SELECT_MUTANTS_BY_LOCATION_ID,
                new MutantMapper(), id);
        mutants = findPowersForAllMutants(mutants);
        mutants = findOrgsForAllMutants(mutants);
        return mutants;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Location> getLocationsByMutantId(int id) {
        List<Location> locations = jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_MUTANT_ID,
                new LocationMapper(), id);
        return locations;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getSightingsByDate(LocalDate date) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_DATE,
                new SightingMapper(), date.toString());
        sightings = findMutantsForAllSightings(sightings);
        for (Sighting s : sightings) {
            List<Mutant> mutants = s.getMutants();
            mutants = findPowersForAllMutants(mutants);
            mutants = findOrgsForAllMutants(mutants);
            s.setMutants(mutants);
        }
        return sightings;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getLast10Sightings() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_LAST_10_SIGHTINGS,
                new SightingMapper());
        sightings = findMutantsForAllSightings(sightings);
        for (Sighting s : sightings) {
            List<Mutant> mutants = s.getMutants();
            mutants = findPowersForAllMutants(mutants);
            mutants = findOrgsForAllMutants(mutants);
            s.setMutants(mutants);
        }
        return sightings;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Mutant> getMutantsByOrgId(int id) {
        List<Mutant> mutants = jdbcTemplate.query(SQL_SELECT_MUTANTS_BY_ORG_ID,
                new MutantMapper(), id);
        mutants = (findPowersForAllMutants(mutants));
        mutants = findOrgsForAllMutants(mutants);
        return mutants;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Mutant> getMutantsByPowerId(int id){
        List<Mutant> mutants = jdbcTemplate.query(SQL_SELECT_MUTANTS_BY_POWER_ID, 
                new MutantMapper(), id);
        mutants = (findPowersForAllMutants(mutants));
        mutants = findOrgsForAllMutants(mutants);
        return mutants;
        
    }

    // MUTANT POWER HELPER METHODS
    private void insertMutantsPowers(Mutant mutant) {
        final int mutantId = mutant.getId();
        final List<Power> powers = mutant.getPowers();

        for (Power currentPower : powers) {
            jdbcTemplate.update(SQL_INSERT_MUTANTS_POWERS, mutantId,
                    currentPower.getId());
        }
    }

    private List<Power> findPowersForMutant(Mutant mutant) {
        return jdbcTemplate.query(SQL_SELECT_POWERS_BY_MUTANT_ID,
                new PowerMapper(), mutant.getId());
    }

    private List<Mutant> findPowersForAllMutants(List<Mutant> mutants) {

        // set the powers for each mutant in the list
        for (Mutant currentMutant : mutants) {
            currentMutant.setPowers(findPowersForMutant(currentMutant));
        }
        return mutants;
    }

    // ORGANIZATION MUTANT HELPER METHODS
    private void insertMutantsOrgs(Mutant mutant) {
        final int mutantId = mutant.getId();
        final List<Org> orgs = mutant.getOrgs();

        for (Org currentOrg : orgs) {
            jdbcTemplate.update(SQL_INSERT_MUTANTS_ORGS,
                    mutantId, currentOrg.getId());
        }
    }

    private List<Org> findOrgsForMutant(Mutant mutant) {
        return jdbcTemplate.query(SQL_SELECT_MUTANTS_ORGS_BY_MUTANT_ID,
                new OrgMapper(), mutant.getId());
    }

    private List<Mutant> findOrgsForAllMutants(List<Mutant> mutants) {
        // set the orgs for each mutant in the list
        for (Mutant currentMutant : mutants) {
            currentMutant.setOrgs(findOrgsForMutant(currentMutant));
        }
        return mutants;
    }

    // MUTANT SIGHTING HELPER METHODS
    private void insertMutantsSightings(Sighting sighting) {
        final int sightingId = sighting.getId();
        final List<Mutant> mutants = sighting.getMutants();

        // update the mutantssightings bridge table
        for (Mutant currentMutant : mutants) {
            jdbcTemplate.update(SQL_INSERT_MUTANTS_SIGHTINGS,
                    currentMutant.getId(),
                    sightingId);
        }
    }

    private List<Mutant> findMutantsForSighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_SELECT_MUTANTSSIGHTINGS_BY_SIGHTING_ID,
                new MutantMapper(), sighting.getId());
    }

    private List<Sighting> findMutantsForAllSightings(List<Sighting> sightings) {
        // set the complete list of mutants for each sighting
        for (Sighting currentSighting : sightings) {
            currentSighting.setMutants(findMutantsForSighting(currentSighting));
        }
        return sightings;
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setName(rs.getString("LocationName"));
            loc.setDescription(rs.getString("LocationDescription"));
            loc.setStreet(rs.getString("LocationStreet"));
            loc.setCity(rs.getString("LocationCity"));
            loc.setState(rs.getString("LocationState"));
            loc.setZip(rs.getString("LocationZip"));
            loc.setLatitude(rs.getBigDecimal("Latitude"));
            loc.setLongitude(rs.getBigDecimal("Longitude"));
            loc.setId(rs.getInt("LocationID"));
            return loc;
        }
    }

    private static final class MutantMapper implements RowMapper<Mutant> {

        @Override
        public Mutant mapRow(ResultSet rs, int i) throws SQLException {
            Mutant mut = new Mutant();
            mut.setName(rs.getString("MutantName"));
            mut.setDescription(rs.getString("MutantDescription"));
            mut.setId(rs.getInt("MutantID"));
            return mut;
        }
    }

    private static final class OrgMapper implements RowMapper<Org> {

        @Override
        public Org mapRow(ResultSet rs, int i) throws SQLException {
            Org org = new Org();
            org.setName(rs.getString("OrgName"));
            org.setDescription(rs.getString("OrgDescription"));
            org.setStreet(rs.getString("OrgStreet"));
            org.setCity(rs.getString("OrgCity"));
            org.setState(rs.getString("OrgState"));
            org.setZip(rs.getString("OrgZip"));
            org.setPhone(rs.getString("OrgPhone"));
            org.setEmail(rs.getString("OrgEmail"));
            org.setId(rs.getInt("OrgID"));
            return org;
        }
    }

    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power power = new Power();
            power.setDescription(rs.getString("PowerDescription"));
            power.setId(rs.getInt("PowerID"));
            return power;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setDate(rs.getTimestamp("SightingDate").toLocalDateTime().toLocalDate());

            Location location = new Location();
            location.setId(rs.getInt("LocationID"));
            location.setName(rs.getString("LocationName"));
            location.setDescription(rs.getString("LocationDescription"));
            location.setStreet(rs.getString("LocationStreet"));
            location.setCity(rs.getString("LocationCity"));
            location.setState(rs.getString("LocationState"));
            location.setZip(rs.getString("LocationZip"));
            location.setLatitude(rs.getBigDecimal("Latitude"));
            location.setLongitude(rs.getBigDecimal("Longitude"));
            sighting.setLocation(location);

            sighting.setId(rs.getInt("SightingID"));
            return sighting;
        }
    }
}
