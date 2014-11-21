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
import static freeWarOnTerror.Game.getCurrentPlayer;
import static freeWarOnTerror.Game.getJihadist;
import static freeWarOnTerror.Game.getUS;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.Alignment.ALLY;
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.Governance.ISLAMISTRULE;

/**
 *
 * @author Emil
 */
public class Saleh extends Card {
    
    public Saleh(){
        super(CardLookup.SALEH);
    }
    
    @Override
    public void playEvent(){
        MuslimCountry yemen = (MuslimCountry) getCountry(CountryLookup.YEMEN);
        yemen.testCountry();
        if (getCurrentPlayer() == getUS() && yemen.getGovernance() != ISLAMISTRULE){
            yemen.setAlignment(ALLY);
            yemen.addAid();
        }
        else if(getCurrentPlayer() == getJihadist()){
            yemen.shiftAlignment(1);
            yemen.setBesiegedRegime(true);
        }
    }
}
