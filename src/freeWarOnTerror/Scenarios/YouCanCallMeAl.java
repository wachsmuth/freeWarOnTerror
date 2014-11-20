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
import static freeWarOnTerror.Game.getMuslimCountry;
import static freeWarOnTerror.Game.getTrack;
import static freeWarOnTerror.Game.placeCell;
import static freeWarOnTerror.Game.setFunding;
import static freeWarOnTerror.Game.setPostureHard;
import static freeWarOnTerror.Game.setPrestige;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Scenario;
import static freeWarOnTerror.helpers.Alignment.ADVERSARY;
import static freeWarOnTerror.helpers.Alignment.ALLY;
import static freeWarOnTerror.helpers.Alignment.NEUTRAL;
import freeWarOnTerror.helpers.CountryLookup;

/**
 *
 * @author Emil
 */
public class YouCanCallMeAl extends Scenario {

    public YouCanCallMeAl() {
        super("You Can Call Me Al");
    }

    @Override
    public void setup() {
        //Markers
        setPrestige(7);
        setPostureHard(false);
        setFunding(9);
        //Countries
        getMuslimCountry(CountryLookup.LIBYA).setGovernanceAndAlignment(3, ADVERSARY);
        getMuslimCountry(CountryLookup.SYRIA).setGovernanceAndAlignment(2, ADVERSARY);
        getMuslimCountry(CountryLookup.IRAQ).setGovernanceAndAlignment(3, ADVERSARY);
        getMuslimCountry(CountryLookup.SAUDIARABIA).setGovernanceAndAlignment(3, ALLY);
        getMuslimCountry(CountryLookup.GULFSTATES).setGovernanceAndAlignment(2, ALLY);
        getMuslimCountry(CountryLookup.PAKISTAN).setGovernanceAndAlignment(2, NEUTRAL);
        getMuslimCountry(CountryLookup.AFGHANISTAN).setGovernanceAndAlignment(4, ADVERSARY);
        MuslimCountry somalia = getMuslimCountry(CountryLookup.SOMALIA);
        somalia.setBesiegedRegime(true);
        for (int i = 0; i < 4; i++) {
            placeCell(getMuslimCountry(CountryLookup.AFGHANISTAN));
        }
        deployTroops(getTrack(), getCountry(CountryLookup.GULFSTATES), 2);
        deployTroops(getTrack(), getCountry(CountryLookup.SAUDIARABIA), 2);
    }
}
