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

import static freeWarOnTerror.Game.anyRegimeChange;
import static freeWarOnTerror.Game.deployTroops;
import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.getMuslimCountries;
import static freeWarOnTerror.Game.getTrack;
import static freeWarOnTerror.Game.isPostureHard;
import freeWarOnTerror.MuslimCountry;
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
public class ActionWithdraw extends Action {

    public ActionWithdraw() {
        super("Use ops to withdraw");
    }

    @Override
    public boolean canDoAction(Card c) {
        if (isPostureHard()) {
            return false;
        }
        if (c.getOps() < 3){
            return false;
        }
        return anyRegimeChange();
    }

    @Override
    public void performAction(Card c) {
        ArrayList<Country> regimeChangeCountries = new ArrayList<>();
        for (MuslimCountry country : getMuslimCountries()) {
            if (country.getRegimeChange() > 0) {
                regimeChangeCountries.add(country);
            }
        }
        System.out.println("Choose a country to withdraw from");
        MuslimCountry targetCountry = (MuslimCountry) inputLoop(regimeChangeCountries);
        targetCountry.withdraw();
        ArrayList<Location> possibleDestinations = new ArrayList<>();
        possibleDestinations.add(getTrack());
        for (Country country : getAllCountries()) {
            if (country.canDeployTo(c.getOps())) {
                possibleDestinations.add(country);
            }
        }
        System.out.println("Choose destination: ");
        Location destination = inputLoop(possibleDestinations);
        System.out.println("Choose amount of troops to deploy: ");
        int[] arr = new int[targetCountry.noCanDeployFrom()];
        for (int i = 0; i < arr.length+1; i++) {
            arr[i] = i;
        }
        int amount = inputLoop(arr);
        deployTroops(targetCountry, destination, amount);
    }
}
