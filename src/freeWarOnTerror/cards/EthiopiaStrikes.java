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
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.ETHIOPIASTRIKES;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class EthiopiaStrikes extends Card {

    public EthiopiaStrikes() {
        super("Ethiopia Strikes", 2, 2, true, false, ETHIOPIASTRIKES);
    }

    @Override
    public Boolean getPlayable() {
        return getCountry(CountryLookup.SOMALIA).getGovernance() == 4 || getCountry(CountryLookup.YEMEN).getGovernance() == 4;
    }

    @Override
    public void playEvent() {
        if (getCountry(CountryLookup.SOMALIA).getGovernance() == 4 && getCountry(CountryLookup.YEMEN).getGovernance() == 4) {
            ArrayList<Country> somaliaAndYemen = new ArrayList<>();
            somaliaAndYemen.add(getCountry(CountryLookup.SOMALIA));
            somaliaAndYemen.add(getCountry(CountryLookup.YEMEN));
            System.out.println("Choose a country to set to Poor Neutral: ");
            inputLoop(somaliaAndYemen).setGovernanceAndAlignment(3, 2);
        } else if (getCountry(CountryLookup.SOMALIA).getGovernance() == 4) {
            getCountry(CountryLookup.SOMALIA).setGovernanceAndAlignment(3, 2);
        } else if (getCountry(CountryLookup.YEMEN).getGovernance() == 4) {
            getCountry(CountryLookup.YEMEN).setGovernanceAndAlignment(3, 2);
        }
    }
}
