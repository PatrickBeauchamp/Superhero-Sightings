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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author patty
 */
public class DaoTest {

    Dao dao;

    public DaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationcontext.xml");

        dao = ctx.getBean("dao", Dao.class);

        //delete all Mutants
        List<Mutant> mutants = dao.getAllMutants();
        for (Mutant currentMutant : mutants) {
            dao.deleteMutant(currentMutant.getId());
        }

        //delete all Powers
        List<Power> powers = dao.getAllPowers();
        for (Power currentPower : powers) {
            dao.deletePower(currentPower.getId());
        }

        //delete all Organizations
        List<Org> orgs = dao.getAllOrgs();
        for (Org currentOrg : orgs) {
            dao.deleteOrg(currentOrg.getId());
        }

        //delete all Sightings
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getId());
        }

        // delete all Locations
        List<Location> locations = dao.getAllLocations();
        for (Location currentLocation : locations) {
            dao.deleteLocation(currentLocation.getId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addLocation method, of class Dao.
     */
    @Test
    public void testAddLocation() {
        Location loc = new Location();
        loc.setName("Mutant Grotto");
        loc.setDescription("A wet cave");
        loc.setStreet("123 Fake Street");
        loc.setCity("Bismarck");
        loc.setState("Durango");
        loc.setZip("42534");
        loc.setLatitude(new BigDecimal("90.01010101"));
        loc.setLongitude(new BigDecimal("50.01010101"));

        dao.addLocation(loc);

        Location fromDao = dao.getLocationById(loc.getId());
        assertEquals(fromDao, loc);

    }

    /**
     * Test of deleteLocation method, of class Dao.
     */
    @Test
    public void testDeleteLocation() {
        Location loc = new Location();
        loc.setName("Mutant Grotto");
        loc.setDescription("A wet cave");
        loc.setStreet("123 Fake Street");
        loc.setCity("Bismarck");
        loc.setState("Durango");
        loc.setZip("42534");
        loc.setLatitude(new BigDecimal("90.01010101"));
        loc.setLongitude(new BigDecimal("50.01010101"));

        dao.addLocation(loc);

        Location fromDao = dao.getLocationById(loc.getId());
        assertEquals(fromDao, loc);
        dao.deleteLocation(loc.getId());
        assertNull(dao.getLocationById(loc.getId()));
    }

    /**
     * Test of updateLocation method, of class Dao.
     */
    @Test
    public void testUpdateLocation() {
        Location loc = new Location();
        loc.setName("Mutant Grotto");
        loc.setDescription("A wet cave");
        loc.setStreet("123 Fake Street");
        loc.setCity("Bismarck");
        loc.setState("Durango");
        loc.setZip("42534");
        loc.setLatitude(new BigDecimal("90.01010101"));
        loc.setLongitude(new BigDecimal("50.01010101"));

        dao.addLocation(loc);

        Location fromDao = dao.getLocationById(loc.getId());
        assertEquals(fromDao, loc);

        loc.setCity("Guerney");
        dao.updateLocation(loc);
        Location fromDb = dao.getLocationById(loc.getId());
        assertEquals(loc, fromDb);
    }

    /**
     * Test of getLocationById method, of class Dao.
     */
    @Test
    public void testGetLocationById() {
        Location loc = new Location();
        loc.setName("Mutant Grotto");
        loc.setDescription("A wet cave");
        loc.setStreet("123 Fake Street");
        loc.setCity("Bismarck");
        loc.setState("Durango");
        loc.setZip("42534");
        loc.setLatitude(new BigDecimal("90.01010101"));
        loc.setLongitude(new BigDecimal("50.01010101"));

        dao.addLocation(loc);

        Location fromDao = dao.getLocationById(loc.getId());
        assertEquals(fromDao, loc);
    }

    /**
     * Test of getAllLocations method, of class Dao.
     */
    @Test
    public void testGetAllLocations() {
        Location loc = new Location();
        loc.setName("Mutant Grotto");
        loc.setDescription("A wet cave");
        loc.setStreet("123 Fake Street");
        loc.setCity("Bismarck");
        loc.setState("Durango");
        loc.setZip("42534");
        loc.setLatitude(new BigDecimal("90.01010101"));
        loc.setLongitude(new BigDecimal("50.01010101"));

        dao.addLocation(loc);
        List<Location> locations = dao.getAllLocations();
        assertEquals(locations.size(), 1);
    }

    /**
     * Test of addMutant method, of class Dao.
     */
    @Test
    public void testAddMutant() {
        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);
        mutant.setPowers(powers);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);

        dao.addMutant(mutant);
        Mutant fromDao = dao.getMutantById(mutant.getId());
        assertEquals(mutant, fromDao);
    }

    /**
     * Test of deleteMutant method, of class Dao.
     */
    @Test
    public void testDeleteMutant() {
        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);
        mutant.setPowers(powers);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);

        dao.addMutant(mutant);
        Mutant fromDao = dao.getMutantById(mutant.getId());
        assertEquals(mutant, fromDao);

        dao.deleteMutant(mutant.getId());
        assertNull(dao.getMutantById(mutant.getId()));

    }

    /**
     * Test of updateMutant method, of class Dao.
     */
    @Test
    public void testUpdateMutant() {
        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);
        mutant.setPowers(powers);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);

        dao.addMutant(mutant);
        Mutant fromDao = dao.getMutantById(mutant.getId());
        assertEquals(mutant, fromDao);

        mutant.setName("Javier");
        dao.updateMutant(mutant);

        fromDao = dao.getMutantById(mutant.getId());
        assertEquals(mutant, fromDao);
    }

    /**
     * Test of getMutantById method, of class Dao.
     */
    @Test
    public void testGetMutantById() {
        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);
        mutant.setPowers(powers);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);

        dao.addMutant(mutant);
        Mutant fromDao = dao.getMutantById(mutant.getId());
        assertEquals(mutant, fromDao);

    }

    /**
     * Test of getAllMutants method, of class Dao.
     */
    @Test
    public void testGetAllMutants() {
        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);
        mutant.setPowers(powers);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);

        dao.addMutant(mutant);

        List<Mutant> mutants = dao.getAllMutants();
        assertEquals(mutants.size(), 1);
    }

    /**
     * Test of addOrg method, of class Dao.
     */
    @Test
    public void testAddOrg() {
        Org org = createOrg();
        dao.addOrg(org);

        Org fromDao = dao.getOrgById(org.getId());
        assertEquals(fromDao, org);
    }

    /**
     * Test of deleteOrg method, of class Dao.
     */
    @Test
    public void testDeleteOrg() {
        Org org = createOrg();
        dao.addOrg(org);

        Org fromDao = dao.getOrgById(org.getId());
        assertEquals(org, fromDao);

        dao.deleteOrg(org.getId());
        assertNull(dao.getOrgById(org.getId()));
    }

    /**
     * Test of updateOrg method, of class Dao.
     */
    @Test
    public void testUpdateOrg() {
        Org org = createOrg();
        dao.addOrg(org);

        org.setName("Dunderbunnies");
        dao.updateOrg(org);

        Org fromDao = dao.getOrgById(org.getId());
        assertEquals(org, fromDao);
    }

    /**
     * Test of getOrgById method, of class Dao.
     */
    @Test
    public void testGetOrgById() {
        Org org = createOrg();
        dao.addOrg(org);

        Org fromDao = dao.getOrgById(org.getId());
        assertEquals(org, fromDao);
    }

    /**
     * Test of getAllOrgs method, of class Dao.
     */
    @Test
    public void testGetAllOrgs() {
        Org org = createOrg();
        dao.addOrg(org);

        List<Org> orgs = dao.getAllOrgs();
        assertEquals(orgs.size(), 1);
    }

    /**
     * Test of addPower method, of class Dao.
     */
    @Test
    public void testAddPower() {
        Power power = createPower();
        dao.addPower(power);

        Power fromDao = dao.getPowerById(power.getId());

        assertEquals(power, fromDao);
    }

    /**
     * Test of deletePower method, of class Dao.
     */
    @Test
    public void testDeletePower() {
        Power power = createPower();
        dao.addPower(power);

        Power fromDao = dao.getPowerById(power.getId());
        assertEquals(fromDao, power);

        dao.deletePower(power.getId());
        assertNull(dao.getPowerById(power.getId()));
    }

    /**
     * Test of updatePower method, of class Dao.
     */
    @Test
    public void testUpdatePower() {
        Power power = createPower();
        dao.addPower(power);
        Power fromDao = dao.getPowerById(power.getId());

        assertEquals(power, fromDao);
        power.setDescription("X-ray vision");
        dao.updatePower(power);
        fromDao = dao.getPowerById(power.getId());
        assertEquals(power, fromDao);
    }

    /**
     * Test of getPowerById method, of class Dao.
     */
    @Test
    public void testGetPowerById() {
        Power power = createPower();
        dao.addPower(power);
        Power fromDao = dao.getPowerById(power.getId());
        assertEquals(power, fromDao);
    }

    /**
     * Test of getAllPowers method, of class Dao.
     */
    @Test
    public void testGetAllPowers() {
        Power power = createPower();
        dao.addPower(power);
        List<Power> powers = dao.getAllPowers();
        assertEquals(powers.size(), 1);
    }

    /**
     * Test of addSighting method, of class Dao.
     */
    @Test
    public void testAddSighting() {
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);

        List<Power> powers = new ArrayList();
        powers.add(power);

        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");
        mutant.setPowers(powers);
        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);
        dao.addMutant(mutant);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting.setLocation(loc);
        sighting.setMutants(mutants);

        dao.addSighting(sighting);
        Sighting fromDao = dao.getSightingById(sighting.getId());
        assertEquals(sighting, sighting);
    }

    /**
     * Test of deleteSighting method, of class Dao.
     */
    @Test
    public void testDeleteSighting() {
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);

        List<Power> powers = new ArrayList();
        powers.add(power);

        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");
        mutant.setPowers(powers);
        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);
        dao.addMutant(mutant);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting.setLocation(loc);
        sighting.setMutants(mutants);

        dao.addSighting(sighting);
        Sighting fromDao = dao.getSightingById(sighting.getId());
        assertEquals(sighting, sighting);

        dao.deleteSighting(sighting.getId());
        assertNull(dao.getSightingById(sighting.getId()));
    }

    /**
     * Test of updateSighting method, of class Dao.
     */
    @Test
    public void testUpdateSighting() {
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);

        List<Power> powers = new ArrayList();
        powers.add(power);

        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");
        mutant.setPowers(powers);
        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);
        dao.addMutant(mutant);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting.setLocation(loc);
        sighting.setMutants(mutants);

        dao.addSighting(sighting);

        sighting.setDate(LocalDate.parse("2019-09-09",
                DateTimeFormatter.ISO_DATE));
        dao.updateSighting(sighting);
        Sighting fromDao = dao.getSightingById(sighting.getId());
        assertEquals(fromDao, sighting);
    }

    /**
     * Test of getSightingById method, of class Dao.
     */
    @Test
    public void testGetSightingById() {
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);

        List<Power> powers = new ArrayList();
        powers.add(power);

        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");
        mutant.setPowers(powers);
        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);
        dao.addMutant(mutant);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting.setLocation(loc);
        sighting.setMutants(mutants);

        dao.addSighting(sighting);

        Sighting fromDao = dao.getSightingById(sighting.getId());
        assertEquals(fromDao, sighting);
    }

    /**
     * Test of getAllSightings method, of class Dao.
     */
    @Test
    public void testGetAllSightings() {
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Stabbing");
        dao.addPower(power);

        List<Power> powers = new ArrayList();
        powers.add(power);

        Mutant mutant = new Mutant();
        mutant.setName("Creep");
        mutant.setDescription("Tall and pointy");
        mutant.setPowers(powers);
        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);
        mutant.setOrgs(orgs);
        dao.addMutant(mutant);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting.setLocation(loc);
        sighting.setMutants(mutants);

        dao.addSighting(sighting);

        List<Sighting> sightings = dao.getAllSightings();
        assertEquals(sightings.size(), 1);
    }

    @Test
    public void testGetMutantsByLocationId() {
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Pewpew");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);

        Mutant mutant1 = new Mutant();
        mutant1.setName("Han");
        mutant1.setDescription("Dusty");
        mutant1.setPowers(powers);
        mutant1.setOrgs(orgs);
        dao.addMutant(mutant1);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant1);

        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting1.setLocation(loc);
        sighting1.setMutants(mutants);
        dao.addSighting(sighting1);

        List<Mutant> mutantsByLocation = dao.getMutantsByLocationId(loc.getId());

        assertEquals(mutantsByLocation.size(), 1);
    }

    @Test
    public void testGetLocationsByMutantId() {
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Pewpew");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);

        Mutant mutant1 = new Mutant();
        mutant1.setName("Han");
        mutant1.setDescription("Dusty");
        mutant1.setPowers(powers);
        mutant1.setOrgs(orgs);
        dao.addMutant(mutant1);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant1);

        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting1.setLocation(loc);
        sighting1.setMutants(mutants);
        dao.addSighting(sighting1);

        List<Location> locationsByMutantId = dao.getLocationsByMutantId(mutant1.getId());
        assertEquals(locationsByMutantId.size(), 1);
    }

    @Test
    public void testGetSightingsByDate() {
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Pewpew");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);

        Mutant mutant1 = new Mutant();
        mutant1.setName("Han");
        mutant1.setDescription("Dusty");
        mutant1.setPowers(powers);
        mutant1.setOrgs(orgs);
        dao.addMutant(mutant1);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant1);

        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting1.setLocation(loc);
        sighting1.setMutants(mutants);
        dao.addSighting(sighting1);

        List<Sighting> sightingsByDate = dao.getSightingsByDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));

//        List<Sighting> sightingsByDate = dao.getSightingsByDate(sighting1.getDate());
//        List<Sighting> sightings = dao.getAllSightings();

//        assertNotNull(sightingsByDate);
        assertEquals(1, sightingsByDate.size());
    }
    
    @Test
    public void testGetMutantsByOrgId(){
        Location loc = createLocation();
        dao.addLocation(loc);

        Power power = new Power();
        power.setDescription("Pewpew");
        dao.addPower(power);
        List<Power> powers = new ArrayList();
        powers.add(power);

        Org org = createOrg();
        dao.addOrg(org);
        List<Org> orgs = new ArrayList();
        orgs.add(org);

        Mutant mutant1 = new Mutant();
        mutant1.setName("Han");
        mutant1.setDescription("Dusty");
        mutant1.setPowers(powers);
        mutant1.setOrgs(orgs);
        dao.addMutant(mutant1);

        List<Mutant> mutants = new ArrayList();
        mutants.add(mutant1);

        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        sighting1.setLocation(loc);
        sighting1.setMutants(mutants);
        dao.addSighting(sighting1);
        
        List<Mutant> mutantsByOrgId = new ArrayList();
        mutantsByOrgId = dao.getMutantsByOrgId(org.getId());
        assertEquals(mutantsByOrgId.size(), 1);
    }

    public Mutant createMutant() {
        Mutant mutant = new Mutant();
        mutant.setName("Eccho");
        mutant.setDescription("Annoyingly repetetive");
        Power power = createPower();
        List<Power> powers = new ArrayList();
        powers.add(power);
        mutant.setPowers(powers);
        return mutant;
    }

    public Org createOrg() {
        Org org = new Org();
        org.setName("Bad Guys");
        org.setDescription("A bunch of scofflaws");
        org.setStreet("893 Trouble AVe.");
        org.setCity("Rome");
        org.setState("Georgia");
        org.setZip("87654");
        org.setPhone("908-987-3636");
        org.setEmail("happy@killer.org");
        return org;
    }

    public Location createLocation() {
        Location loc = new Location();
        loc.setName("Mutant Grotto");
        loc.setDescription("A wet cave");
        loc.setStreet("123 Fake Street");
        loc.setCity("Bismarck");
        loc.setState("Durango");
        loc.setZip("42534");
        loc.setLatitude(new BigDecimal("90.01010101"));
        loc.setLongitude(new BigDecimal("50.01010101"));
        return loc;
    }

    public Power createPower() {
        Power power = new Power();
        power.setDescription("Flight");
        return power;
    }

    public Sighting createSighting() {
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2010-01-01",
                DateTimeFormatter.ISO_DATE));
        return sighting;
    }

}
