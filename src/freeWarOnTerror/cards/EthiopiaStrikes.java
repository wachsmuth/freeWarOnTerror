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
import static freeWarOnTerror.Game.getMuslimCountry;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.Alignment.NEUTRAL;
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.Governance.ISLAMISTRULE;
import static freeWarOnTerror.helpers.Governance.POOR;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class EthiopiaStrikes extends Card {

    public EthiopiaStrikes() {
        super(CardLookup.ETHIOPIASTRIKES);
    }

    @Override
    public Boolean getPlayable() {
        return getCountry(CountryLookup.SOMALIA).getGovernance() == ISLAMISTRULE || getCountry(CountryLookup.YEMEN).getGovernance() == ISLAMISTRULE;
    }

    @Override
    public void playEvent() {
        if (getCountry(CountryLookup.SOMALIA).getGovernance() == ISLAMISTRULE && getCountry(CountryLookup.YEMEN).getGovernance() == ISLAMISTRULE) {
            ArrayList<MuslimCountry> somaliaAndYemen = new ArrayList<>();
            somaliaAndYemen.add(getMuslimCountry(CountryLookup.SOMALIA));
            somaliaAndYemen.add(getMuslimCountry(CountryLookup.YEMEN));
            System.out.println("Choose a country to set to Poor Neutral: ");
            inputLoop(somaliaAndYemen).setGovernanceAndAlignment(POOR, NEUTRAL);
        } else if (getCountry(CountryLookup.SOMALIA).getGovernance() == ISLAMISTRULE) {
            getMuslimCountry(CountryLookup.SOMALIA).setGovernanceAndAlignment(POOR, NEUTRAL);
        } else if (getCountry(CountryLookup.YEMEN).getGovernance() == ISLAMISTRULE) {
            getMuslimCountry(CountryLookup.YEMEN).setGovernanceAndAlignment(POOR, NEUTRAL);
        }
    }
}
