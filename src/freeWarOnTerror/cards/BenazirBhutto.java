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
import static freeWarOnTerror.Game.getMuslimCountry;
import static freeWarOnTerror.Game.isCardInPlay;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.helpers.CardLookup;
import static freeWarOnTerror.helpers.CardLookup.BHUTTOSHOT;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.Governance.FAIR;
import static freeWarOnTerror.helpers.Governance.ISLAMISTRULE;
import static freeWarOnTerror.helpers.Governance.POOR;

/**
 *
 * @author Emil
 */
public class BenazirBhutto extends Card {
    
    public BenazirBhutto(){
        super(CardLookup.BENAZIRBHUTTO);
    }
    
    @Override
    public Boolean getPlayable(){
        if (getMuslimCountry(CountryLookup.PAKISTAN).getGovernance() == ISLAMISTRULE){
            return false;
        }
        for (Country c : getCountry(CountryLookup.PAKISTAN).getAdjacentCountries()){
            if (c.getGovernance() == ISLAMISTRULE){
                return false;
            }
        }
        return !isCardInPlay(BHUTTOSHOT);
    }
    
    @Override
    public void playEvent(){
        if (getCountry(CountryLookup.PAKISTAN).getGovernance() == POOR){
            getMuslimCountry(CountryLookup.PAKISTAN).setGovernance(FAIR);
        }
    }
}
