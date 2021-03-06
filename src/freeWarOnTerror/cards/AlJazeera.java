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
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class AlJazeera extends Card {
    
    public AlJazeera(){
        super(CardLookup.ALJAZEERA);
    }
    
    @Override
    public Boolean getPlayable(){
        if (getCountry(CountryLookup.SAUDIARABIA).hasTroops()){
            return true;
        }
        for (Country c : getCountry(CountryLookup.SAUDIARABIA).getAdjacentCountries()){
            if (c.hasTroops()){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void playEvent(){
        ArrayList<Country> eligibleCountries = new ArrayList<>();
        if (getCountry(CountryLookup.SAUDIARABIA).hasTroops()){
            eligibleCountries.add(getCountry(CountryLookup.SAUDIARABIA));
        }
        for (Country c : getCountry(CountryLookup.SAUDIARABIA).getAdjacentCountries()){
            if (c.hasTroops()){
                eligibleCountries.add(c);
            }
        }
        System.out.println("Choose a country to shift alignment of");
        ((MuslimCountry) inputLoop(eligibleCountries)).shiftTowardsAdversary();
    }
}
