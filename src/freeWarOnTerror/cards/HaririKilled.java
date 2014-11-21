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
import static freeWarOnTerror.helpers.Alignment.ADVERSARY;
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;

/**
 *
 * @author Emil
 */
public class HaririKilled extends Card {
    
    public HaririKilled(){
        super(CardLookup.HARIRIKILLED);
    }
    
    @Override
    public void playEvent(){
        getCountry(CountryLookup.LEBANON).testCountry();
        MuslimCountry syria = (MuslimCountry) getCountry(CountryLookup.SYRIA);
        syria.setAlignment(ADVERSARY);
        syria.shiftGovernance(1);
    }
}
