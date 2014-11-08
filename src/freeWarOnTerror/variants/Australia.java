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
package freeWarOnTerror.variants;

import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.getCountry;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Variant;
import static freeWarOnTerror.helpers.CONSTANTS.AUSTRALIA;
import static freeWarOnTerror.helpers.CONSTANTS.INDONESIA;
import static freeWarOnTerror.helpers.CONSTANTS.PHILIPPINES;

/**
 *
 * @author Emil
 */
public class Australia extends Variant {
    
    @Override
    public String getDescription(){
        return "Australia";
    }
    
    @Override
    public void activate(){
        getAllCountries().add(new NonMuslimCountry("Australia", AUSTRALIA, 1, 1, false));
        getCountry(PHILIPPINES).addAdjacentCountry(getCountry(AUSTRALIA));
        getCountry(AUSTRALIA).addAdjacentCountry(getCountry(PHILIPPINES));
        getCountry(INDONESIA).addAdjacentCountry(getCountry(AUSTRALIA));
        getCountry(AUSTRALIA).addAdjacentCountry(getCountry(INDONESIA));
    }
}
