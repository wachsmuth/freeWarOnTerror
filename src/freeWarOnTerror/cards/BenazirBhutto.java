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

import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.isCardInPlay;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.BENAZIRBHUTTO;
import static freeWarOnTerror.helpers.CONSTANTS.BHUTTOSHOT;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.CountryLookup.PAKISTAN;

/**
 *
 * @author Emil
 */
public class BenazirBhutto extends Card {
    
    public BenazirBhutto(){
        super("Benazir Bhutto", 3, 2, true, true, BENAZIRBHUTTO, PAKISTAN);
    }
    
    @Override
    public Boolean getPlayable(){
        if (getCountry(CountryLookup.PAKISTAN).getGovernance() == 4){
            return false;
        }
        for (Country c : getCountry(CountryLookup.PAKISTAN).getAdjacentCountries()){
            if (c.getGovernance() == 4){
                return false;
            }
        }
        return !isCardInPlay(BHUTTOSHOT);
    }
    
    @Override
    public void playEvent(){
        if (getCountry(CountryLookup.PAKISTAN).getGovernance() == 3){
            getCountry(CountryLookup.PAKISTAN).setGovernance(2);
        }
    }
}
