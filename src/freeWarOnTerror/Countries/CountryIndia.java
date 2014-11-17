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
package freeWarOnTerror.Countries;

import static freeWarOnTerror.Game.removeCardFromPlay;
import freeWarOnTerror.NonMuslimCountry;
import static freeWarOnTerror.helpers.CONSTANTS.INDOPAKISTANITALKS;
import freeWarOnTerror.helpers.CountryLookup;

/**
 *
 * @author Emil
 */
public class CountryIndia extends NonMuslimCountry {
    
    public CountryIndia(CountryLookup c){
        super(c);  
    }
    
    public CountryIndia(String name, int id, int governance, int recruit, boolean schengen){
        super(name, id, governance, recruit, schengen);
    }
    
        @Override
    public void resolvePlots(){
        if (hasPlots()){
            removeCardFromPlay(INDOPAKISTANITALKS);
        }
        super.resolvePlots();
    }
}
