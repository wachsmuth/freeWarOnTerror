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
package freeWarOnTerror.Players.Actions;

import static freeWarOnTerror.Game.canRecruit;
import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.placeCell;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.Die.rollDie;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class ActionRecruit extends Action {

    public ActionRecruit() {
        super("Use ops to recruit");
    }

    @Override
    public boolean canDoAction(Card c) {
        return canRecruit();
    }

    @Override
    public void performAction(Card c) {
        ArrayList<Country> targetCountries = new ArrayList<>();
        for (int i = 0; i < c.getOps(); i++) {
            ArrayList<Country> eligibleCountries = new ArrayList<>();
            for (Country country : getAllCountries()) {
                if (country.canRecruit()) {
                    eligibleCountries.add(country);
                }
            }

            targetCountries.add(inputLoop("Choose a country for a recruit attempt", eligibleCountries));
        }
        for (Country country : targetCountries){
            if ( country.getRecruit() >= rollDie()){
                if(canRecruit()){
                    placeCell(country);
                }
            }
        }
    }
}