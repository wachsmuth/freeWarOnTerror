/*
 * Copyright (C) 2014 Emil
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package freeWarOnTerror;

import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Scenario {
    
    private final ArrayList<Country> countries;
    
    public Scenario(ArrayList<Country> countries){
        this.countries = countries;
        createWorld();
    }
    
    private void createWorld(){ //sample code, WIP
        countries.add(new MuslimCountry("Somalia", 1, false, false));
    }
}
