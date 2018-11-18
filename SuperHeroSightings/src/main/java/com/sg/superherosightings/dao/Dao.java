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
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author patty
 */
public interface Dao {

    public void addLocation(Location location);

    public void deleteLocation(int id);

    public void updateLocation(Location location);

    public Location getLocationById(int id);

    public List<Location> getAllLocations();

    public void addMutant(Mutant mutant);

    public void deleteMutant(int id);

    public void updateMutant(Mutant mutant);

    public Mutant getMutantById(int id);

    public List<Mutant> getAllMutants();

    public void addOrg(Org org);

    public void deleteOrg(int id);

    public void updateOrg(Org org);

    public Org getOrgById(int id);

    public List<Org> getAllOrgs();

    public void addPower(Power power);

    public void deletePower(int id);

    public void updatePower(Power power);

    public Power getPowerById(int id);

    public List<Power> getAllPowers();

    public void addSighting(Sighting sighting);

    public void deleteSighting(int id);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int id);

    public List<Sighting> getAllSightings();
    
    public List<Mutant> getMutantsByPowerId(int id);

    public List<Mutant> getMutantsByLocationId(int id);

    public List<Location> getLocationsByMutantId(int id);

    public List<Sighting> getSightingsByDate(LocalDate date);
    
    public List<Sighting> getLast10Sightings();

    public List<Mutant> getMutantsByOrgId(int id);

}
