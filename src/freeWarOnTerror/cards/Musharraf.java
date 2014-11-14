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

import freeWarOnTerror.Cell;
import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.isCardInPlay;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.BENAZIRBHUTTO;
import static freeWarOnTerror.helpers.CONSTANTS.MUSHARRAF;
import static freeWarOnTerror.helpers.CONSTANTS.PAKISTAN;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;

/**
 *
 * @author Emil
 */
public class Musharraf extends Card {
    
    public Musharraf(){
        super("Musharraf", 2, 1, false, false, MUSHARRAF);
    }
    
    @Override
    public Boolean getPlayable(){
        return !isCardInPlay(BENAZIRBHUTTO) && getCountry(PAKISTAN).hasCells();
    }
    
    @Override
    public void playEvent(){
        Cell target = inputLoop(getCountry(PAKISTAN).getCells());
        target.kill();
        getCountry(PAKISTAN).setGovernanceAndAlignment(3, 1);
    }
}
