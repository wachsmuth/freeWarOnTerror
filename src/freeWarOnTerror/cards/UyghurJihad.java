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
import static freeWarOnTerror.Game.placeCell;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.CENTRALASIA;
import static freeWarOnTerror.helpers.CONSTANTS.CHINA;
import static freeWarOnTerror.helpers.CONSTANTS.UYGHURJIHAD;

/**
 *
 * @author Emil
 */
public class UyghurJihad extends Card {
    
    public UyghurJihad(){
        super("Uyghur Jihad", 1, 3, true, false, UYGHURJIHAD);
    }
    
    @Override
    public void playEvent(){
        NonMuslimCountry china = (NonMuslimCountry) getCountry(CHINA);
        china.testCountry();
        if (china.getPosture() == 1){
            placeCell(getCountry(CENTRALASIA));
        }
        else {
            placeCell(china);
        }
        
    }
    
}
