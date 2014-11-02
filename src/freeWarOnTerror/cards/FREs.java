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
import static freeWarOnTerror.Game.placeCell;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.IRAQ;

/**
 *
 * @author Emil
 */
public class FREs extends Card {
    
    public FREs(){
        super("FREs", 1, 3, false);
    }
    
    @Override
    public Boolean getPlayable(){
        return getCountry(IRAQ).hasTroops();
    }
    
    @Override
    public void playEvent(){
        int cells;
        if (isCardInPlay("Saddam Captured")){
            cells = 2;
        }
        else {
            cells = 4;
        }
        for (int i = 0;i < cells;i++){
            placeCell(getCountry(IRAQ));
        }
    }
}
