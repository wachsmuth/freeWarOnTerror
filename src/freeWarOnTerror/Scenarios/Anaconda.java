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
package freeWarOnTerror.Scenarios;

import static freeWarOnTerror.Game.deployTroops;
import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.getTrack;
import static freeWarOnTerror.Game.placeCell;
import static freeWarOnTerror.Game.setFunding;
import static freeWarOnTerror.Game.setPostureHard;
import static freeWarOnTerror.Game.setPrestige;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.abClasses.Scenario;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Anaconda extends Scenario {

    public Anaconda() {
        super("Anaconda");
    }

    @Override
    public void setup() {
        //Markers
        setPrestige(8);
        setPostureHard(true);
        setFunding(6);
        //Governance and alignment
        getCountry(CountryLookup.LIBYA).setGovernanceAndAlignment(3, 3);
        getCountry(CountryLookup.SYRIA).setGovernanceAndAlignment(2, 3);
        getCountry(CountryLookup.IRAQ).setGovernanceAndAlignment(3, 3);
        getCountry(CountryLookup.SAUDIARABIA).setGovernanceAndAlignment(3, 1);
        getCountry(CountryLookup.GULFSTATES).setGovernanceAndAlignment(2, 1);
        getCountry(CountryLookup.PAKISTAN).setGovernanceAndAlignment(3, 1);
        getCountry(CountryLookup.AFGHANISTAN).setGovernanceAndAlignment(3, 1);
        getCountry(CountryLookup.CENTRALASIA).setGovernanceAndAlignment(3, 1);
        ((MuslimCountry) getCountry(CountryLookup.SOMALIA)).setBesiegedRegime(true);
        ((MuslimCountry) getCountry(CountryLookup.AFGHANISTAN)).setRegimeChange(1);
        //Cells & troops
        placeCell(getCountry(CountryLookup.PAKISTAN));
        placeCell(getCountry(CountryLookup.AFGHANISTAN));
        deployTroops(getTrack(), getCountry(CountryLookup.SAUDIARABIA), 2);
        deployTroops(getTrack(), getCountry(CountryLookup.GULFSTATES), 2);
        deployTroops(getTrack(), getCountry(CountryLookup.AFGHANISTAN), 6);

        // DEBUG add support for playing events before the game starts
        // Must put into play events Patriot Act and FATA
        // Must remove from play cards Patriot and Tora Bora
        ArrayList<Country> eligibleCountries = new ArrayList<>();
        for (Country c : getAllCountries()) {
            if (c.is(CountryLookup.UNITEDSTATES)) {
                eligibleCountries.add(c);
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("Choose where to place a cell");
            Country targetCountry = inputLoop(eligibleCountries);
            placeCell(targetCountry);
            eligibleCountries.remove(targetCountry);
        }
    }
}
