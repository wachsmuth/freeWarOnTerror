/*
 * Copyright (C) 2014 Wengel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY, without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package freeWarOnTerror.helpers;

import static freeWarOnTerror.helpers.Governance.FAIR;
import static freeWarOnTerror.helpers.Governance.GOOD;

/**
 *
 * @author Wengel
 */
public enum CountryLookup {

    UNITEDSTATES("United States"),
    CANADA("Canada", GOOD, 1, false),
    UNITEDKINGDOM("United Kingdom", GOOD, 3, false),
    SCANDINAVIA("Scandinavia", GOOD, 1, false),
    BENELUX("Benelux", GOOD, 1, true),
    GERMANY("Germany", GOOD, 1, true),
    EASTERNEUROPE("Easterm Europe", GOOD, 1, true),
    FRANCE("France", GOOD, 2, true),
    ITALY("Italy", GOOD, 1, true),
    SPAIN("Spain", GOOD, 2, true),
    SERBIA("Serbia", GOOD, 1, false),
    RUSSIA("Russia", FAIR, 2, false),
    CAUCASUS("Caucasus", FAIR, 2, false),
    ISRAEL("Israel", 1),
    INDIA("India", GOOD, 1, false),
    CHINA("China", FAIR, 2, false),
    THAILAND("Thailand", FAIR, 2, false),
    PHILIPPINES("Philippines", FAIR, 3, false),
    KENYA("Kenya/Tanzania", FAIR, 2, false),
    MOROCCO("Morocco", 2, false, false),
    ALGERIA("Algeria/Tunisia", 1, true, false),
    LIBYA("Libya", 1, true, false),
    EGYPT("EGYPT", 3, false, false),
    SUDAN("Sudan", 1, true, false),
    SOMALIA("Somalia", 1, false, false),
    YEMEN("Yemen", 1, false, true),
    SAUDIARABIA("Saudi Arabia", 3, true, true),
    JORDAN("Jordan", 1, false, false),
    SYRIA("Syria", 2, false, false),
    LEBANON("Lebanon", 1, false, true),
    TURKEY("Turkey", 2, false, true),
    IRAQ("Iraq", 3, true, true),
    GULFSTATES("Gulf States", 3, true, true),
    PAKISTAN("Pakistan", 2, false, true),
    AFGHANISTAN("Afghanistan", 1, false, true),
    CENTRALASIA("Central Asia", 2, false, false),
    INDONESIA("Indonesia/Malaysia", 3, true, false),
    IRAN("Iran")
    ;
    
    private String name;
    private int resources;
    private boolean oilCountry;
    private boolean shiaMix;
    private boolean schengen;
    private Governance governance;
    private int alignment;
    private int recruit;
    private boolean muslimCountry;

    public boolean isMuslimCountry() {
        return muslimCountry;
    }
    
    private CountryLookup(String name, int DEBUG, int DEBUG2){
        
    } //Philipines
    
    private CountryLookup(String name, int DEBUG){
        this.name = name;
    }
    
    private CountryLookup(String name) {
        this.name = name;
        muslimCountry = false;
    } //United states only

    private CountryLookup(String name, Governance governance, int recruit, boolean schengen) {
        this.name = name;
        this.governance = governance;
        this.recruit = recruit;
        this.schengen = schengen;
        this.muslimCountry = false;
    }//NonMuslimCountries

    private CountryLookup(String name, int resources, boolean oilCountry, boolean shiaMix) {
        this.name = name;
        this.resources = resources;
        this.oilCountry = oilCountry;
        this.shiaMix = shiaMix;
        this.muslimCountry = true;
    } //MuslimCountries

    //GETTERS
    public String getName() {
        return name;
    }

    public int getResources() {
        return resources;
    }

    public boolean isOilCountry() {
        return oilCountry;
    }

    public boolean isShiaMix() {
        return shiaMix;
    }

    public boolean isSchengen() {
        return schengen;
    }

    public Governance getGovernance() {
        return governance;
    }

    public int getAlignment() {
        return alignment;
    }

    public int getRecruit() {
        return recruit;
    }


}

    
