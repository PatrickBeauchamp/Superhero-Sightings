/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author patty
 */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString @EqualsAndHashCode
public class Power {
    
    private int id;
    private String description;

}
