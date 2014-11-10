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
import static freeWarOnTerror.Game.getMuslimCountries;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.PREDATOR;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Predator extends Card {
    
    public Predator(){
        super("Predator", 2, 2, false, false, PREDATOR);
    }
    
    @Override
    public void playEvent(){
        ArrayList<Cell> cells = new ArrayList<>();
        for (MuslimCountry m : getMuslimCountries()){
            if (m.hasCells()){
                for (Cell c : m.getCells()){
                    cells.add(c);
                }
            }
        }
        if (cells.size() > 0){
            System.out.println("Choose a cell to kill");
            inputLoop(cells).kill();
        }
    }
}
