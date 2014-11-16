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
import static freeWarOnTerror.Game.getTroops;
import static freeWarOnTerror.Game.isCardInPlay;
import static freeWarOnTerror.Game.modifyFunding;
import static freeWarOnTerror.Game.modifyPrestige;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.ABBAS;
import static freeWarOnTerror.helpers.CONSTANTS.QUARTET;
import freeWarOnTerror.helpers.CountryLookup;

/**
 *
 * @author Emil
 */
public class Quartet extends Card {
    
    public Quartet(){
        super("Quartet", 2, 2, false, false, QUARTET);
    }
    
    @Override
    public Boolean getPlayable(){

        if (getTroops() > 5){
            return false;
        }
        else if (!isCardInPlay(ABBAS)){
            return false;
        }
        else {
            for (Country c : getCountry(CountryLookup.ISRAEL).getAdjacentCountries()){
                if (c.getGovernance() == 4){
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void playEvent(){
        modifyPrestige(2);
        modifyFunding(-3);
    }
}
