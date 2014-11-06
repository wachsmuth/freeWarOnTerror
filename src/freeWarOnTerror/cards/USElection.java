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

import static freeWarOnTerror.Game.getPosturePenalty;
import static freeWarOnTerror.Game.modifyPrestige;
import static freeWarOnTerror.Game.rollUSPosture;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.USELECTION;

/**
 *
 * @author Emil
 */
public class USElection extends Card {
    
    public USElection(){
        super("US Election", 3, 1, false, false, USELECTION);
    }
    
    @Override
    public void playEvent(){
        rollUSPosture();
        if (getPosturePenalty() == 0){
            modifyPrestige(1);
        }
        else {
            modifyPrestige(-1);
        }
    }
}
