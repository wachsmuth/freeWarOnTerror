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
package freeWarOnTerror.cards;

import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.getNonMuslimCountries;
import static freeWarOnTerror.Game.modifyPrestige;
import static freeWarOnTerror.Game.rollUSPosture;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.SAFERNOW;
import freeWarOnTerror.helpers.CountryLookup;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class SaferNow extends Card {
    
    public SaferNow(){
        super("Safer Now", 3, 2, false, false, SAFERNOW);
    }
    
    @Override
    public Boolean getPlayable(){
        for (Country c : getAllCountries()){
            if (c.getGovernance() == 4){
                return false;
            }
            else if (c.getGovernance() == 1 && (c.hasCells() || c.hasPlots())){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void playEvent(){
        rollUSPosture();
        modifyPrestige(3);
        ArrayList <NonMuslimCountry> eligibleCountries = new ArrayList<>();
        for (NonMuslimCountry c : getNonMuslimCountries()){
            if (!c.is(CountryLookup.UNITEDSTATES)){
                eligibleCountries.add(c);
            }
        }
         //setCountryPosture(inputLoop(eligibleCountries)); DEBUG
    }
}
