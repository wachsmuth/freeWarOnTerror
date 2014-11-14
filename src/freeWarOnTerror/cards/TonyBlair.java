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
import static freeWarOnTerror.Game.getSchengenCountries;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.TONYBLAIR;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDKINGDOM;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDSTATES;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;

/**
 *
 * @author Emil
 */
public class TonyBlair extends Card {

    public TonyBlair() {
        super("Tony Blair", 2, 2, true, false, TONYBLAIR);
    }
    
    @Override
    public void playEvent(){
        ((NonMuslimCountry) getCountry(UNITEDKINGDOM)).setPosture(((NonMuslimCountry) getCountry(UNITEDSTATES)).getPosture());
        for (int i = 0; i < 3; i++){
            System.out.println("Choose a Schengen country to roll War of Ideas in.");
            inputLoop(getSchengenCountries()).warOfIdeas();
        }
    }
}
