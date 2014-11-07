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

import freeWarOnTerror.Game;
import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.getTrack;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.abClasses.Location;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class ActionDeploy extends Action {

    public ActionDeploy() {
        super("Use ops to deploy");
    }

    @Override
    public boolean canDoAction(Card c) {
        boolean availableOrigin = false;
        boolean availableDestination = false;
        if (getTrack().hasTroops()) {
            availableOrigin = true;
        }
        if (getTrack().troopAmount() < 15) {
            availableDestination = true;
        }

        for (Country country : getAllCountries()) {
            if (country.canDeployTo(c.getOps())) {
                availableDestination = true;
            }
            if (country.canDeployFrom()) {
                availableOrigin = true;
            }
        }
        return availableOrigin && availableDestination;
    }

    @Override
    public void performAction(Card c) {
        ArrayList<Location> possibleOrigins = new ArrayList<>();
        ArrayList<Location> possibleDestinations = new ArrayList<>();
        if (getTrack().hasTroops()) {
            possibleOrigins.add(getTrack());
        }
        if (getTrack().troopAmount() < 15) {
            possibleDestinations.add(getTrack());
        }
        for (Country country : getAllCountries()) {
            if (country.canDeployTo(c.getOps())) {
                possibleDestinations.add(country);
            }
            if (country.canDeployFrom()) {
                possibleOrigins.add(country);
            }
        }
        System.out.println("Choose origin: ");
        Location origin = inputLoop(possibleOrigins);
        possibleDestinations.remove(origin);
        System.out.println("Choose destination: ");
        Location destination = inputLoop(possibleDestinations);
        System.out.println("Choose amount of troops to deploy: ");
        int[] arr = new int[origin.noCanDeployFrom()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        int amount = inputLoop(arr);
        Game.deployTroops(origin, destination, amount);
    }
}
