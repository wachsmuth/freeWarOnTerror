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
import static freeWarOnTerror.Game.modifyPrestige;
import static freeWarOnTerror.Game.placeCell;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.AFGHANISTAN;
import static freeWarOnTerror.helpers.CONSTANTS.PAKISTAN;
import static freeWarOnTerror.helpers.CONSTANTS.TALIBAN;

/**
 *
 * @author Emil
 */
public class Taliban extends Card {
    
    public Taliban(){
        super("Taliban", 3, 3, false, false, TALIBAN);
    }
    
    @Override
    public void playEvent(){
        MuslimCountry afghanistan = (MuslimCountry) getCountry(AFGHANISTAN);
        afghanistan.setBesiegedRegime(true);
        placeCell(afghanistan);
        placeCell(getCountry(PAKISTAN));
        if (afghanistan.getGovernance() == 4 || getCountry(PAKISTAN).getGovernance() == 4){
            modifyPrestige(-3);
        }
        else {
            modifyPrestige(-1);
        }
    }
}
