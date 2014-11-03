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

import static freeWarOnTerror.Game.canPlaceCell;
import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.placeCell;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.ANSARALISLAM;
import static freeWarOnTerror.helpers.CONSTANTS.IRANCOUNTRY;
import static freeWarOnTerror.helpers.CONSTANTS.IRAQ;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;

/**
 *
 * @author Emil
 */
public class AnsarAlIslam extends Card {
    
    public AnsarAlIslam(){
        super("Ansar al-Islam", 1, 3, true, false, ANSARALISLAM);
    }
    
    @Override
    public Boolean getPlayable(){
        return getCountry(IRAQ).getGovernance() > 1 || getCountry(IRAQ).needsTesting();
    }
    
    @Override
    public void playEvent(){
        if (canPlaceCell()){
            if (inputLoop("Choose where to place a cell", "Iraq", "Iran") == 1){
                placeCell(getCountry(IRAQ));
            }
            else {
                placeCell(getCountry(IRANCOUNTRY));
            }
        }
    }
}
