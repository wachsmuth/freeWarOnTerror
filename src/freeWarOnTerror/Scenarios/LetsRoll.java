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
import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.getTrack;
import static freeWarOnTerror.Game.placeCell;
import static freeWarOnTerror.Game.setFunding;
import static freeWarOnTerror.Game.setPostureHard;
import static freeWarOnTerror.Game.setPrestige;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Scenario;
import freeWarOnTerror.helpers.CountryLookup;

/**
 *
 * @author Emil
 */
public class LetsRoll extends Scenario {

    public LetsRoll() {
        super("Let's Roll!");
    }

    @Override
    public void setup() {
        //Markers

        setPrestige(7);
        setPostureHard(true);
        setFunding(9);

        //Countries
        getCountry(CountryLookup.LIBYA).setGovernanceAndAlignment(3, 3);
        getCountry(CountryLookup.SYRIA).setGovernanceAndAlignment(2, 3);
        getCountry(CountryLookup.IRAQ).setGovernanceAndAlignment(3, 3);
        getCountry(CountryLookup.SAUDIARABIA).setGovernanceAndAlignment(3, 1);
        getCountry(CountryLookup.GULFSTATES).setGovernanceAndAlignment(2, 1);
        getCountry(CountryLookup.PAKISTAN).setGovernanceAndAlignment(2, 2);
        getCountry(CountryLookup.AFGHANISTAN).setGovernanceAndAlignment(4, 3);
        MuslimCountry somalia = (MuslimCountry) getCountry(CountryLookup.SOMALIA);
        somalia.setBesiegedRegime(true);
        for (int i = 0; i < 4; i++) {
            placeCell(getCountry(CountryLookup.AFGHANISTAN));
        }
        deployTroops(getTrack(), getCountry(CountryLookup.GULFSTATES), 2);
        deployTroops(getTrack(), getCountry(CountryLookup.SAUDIARABIA), 2);
    }

}
