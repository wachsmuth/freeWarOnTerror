/*
 * Copyright (C) 2014 Gustav Wengel
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
package freeWarOnTerror;

import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.isCardInPlay;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.SOMALIA;
import static freeWarOnTerror.helpers.CONSTANTS.YEMEN;

/**
 *
 * @author Gustav Wengel
 */
public class Turn {

    private boolean firstPlot = true;

    public void drawPhase() {
        Game.getJihadist().drawPhase();
        Game.getUS().drawPhase();
    }

    public void resolvePlots() {
        for (Country c : Game.getAllCountries()) {
            c.resolvePlots();
        }
    }

    public void turnEnd() {
        if (!isCardInPlay("Pirates") && (getCountry(SOMALIA).getGovernance() == 4 || getCountry(YEMEN).getGovernance() == 4)) {
            Game.modifyFunding(-1);
        }
        for (MuslimCountry c : Game.getMuslimCountries()) {
            if (c.getRegimeChange() == 2) {
                c.setRegimeChange(1);
            }
        }
        if (Game.anyIslamistRule()) {
            Game.modifyPrestige(-1);
        }
    }

}
