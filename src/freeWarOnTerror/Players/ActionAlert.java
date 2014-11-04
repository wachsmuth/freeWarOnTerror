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
package freeWarOnTerror.Players;

import static freeWarOnTerror.Game.getAllCountries;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class ActionAlert extends Action {

    public ActionAlert() {
        super("Use the ops to Alert a plot");
    }

    @Override
    public boolean canDoAction(Card c) {
        if (c.getOps() < 3) {
            return false;
        }
        for (Country country : getAllCountries()) {
            if (country.hasPlots()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void performAction(Card c) {
        ArrayList<Country> countriesWithPlots = new ArrayList<>();
        for (Country country : getAllCountries()) {
            if (country.hasPlots()) {
                countriesWithPlots.add(country);
            }
        }
        Country userInput = inputLoop("Pick a country to alert a plot in", countriesWithPlots);
        //DEBUG actually remove the plot
    }

}
