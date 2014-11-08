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

import static freeWarOnTerror.Game.getMuslimCountries;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.BACKLASH;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Backlash extends Card {
    
    public Backlash(){
        super("Backlash", 1, 2, false, false, BACKLASH);
    }
    
    @Override
    public Boolean getPlayable(){
        for (Country c : getMuslimCountries()){
            if (c.hasPlots()){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void playEvent(){
        ArrayList<MuslimCountry> options = new ArrayList<>();
        for (MuslimCountry c : getMuslimCountries()){
            if (c.hasPlots()){
                options.add(c);
            }
        }
        System.out.println("Choose country to backlash plot in");
        inputLoop(options).getRandomPlot().setBacklash(true);
    
    }
}
