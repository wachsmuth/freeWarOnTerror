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
import static freeWarOnTerror.Game.getJihadist;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CardLookup.FSB;
import static freeWarOnTerror.helpers.CardLookup.HEU;
import static freeWarOnTerror.helpers.CardLookup.KAZAKHSTRAIN;
import static freeWarOnTerror.helpers.CardLookup.LOOSENUKE;
import static freeWarOnTerror.helpers.CountryLookup.CENTRALASIA;
import static freeWarOnTerror.helpers.CountryLookup.RUSSIA;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class FSB extends Card {
    
    public FSB(){
        super(FSB);
    }
    
    @Override
    public void playEvent(){
        boolean jihadistHasCard = getJihadist().discardSpecificCard(LOOSENUKE) || getJihadist().discardSpecificCard(KAZAKHSTRAIN) || getJihadist().discardSpecificCard(HEU);
        if (!jihadistHasCard){
            ArrayList<Cell> eligibleCells = new ArrayList<>();
            getCountry(RUSSIA).getCells().stream().forEach((c) -> {
                eligibleCells.add(c);
            });
            getCountry(CENTRALASIA).getCells().stream().forEach((c) -> {
                eligibleCells.add(c);
            });
            inputLoop(eligibleCells).kill();
        }
    }
}
