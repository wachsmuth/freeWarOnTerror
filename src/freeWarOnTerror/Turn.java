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

import static freeWarOnTerror.Game.anyIslamistRule;
import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.getGlobalPosture;
import static freeWarOnTerror.Game.getJihadist;
import static freeWarOnTerror.Game.getMuslimCountries;
import static freeWarOnTerror.Game.getPlayers;
import static freeWarOnTerror.Game.isCardInPlay;
import static freeWarOnTerror.Game.isPostureHard;
import static freeWarOnTerror.Game.modifyFunding;
import static freeWarOnTerror.Game.modifyPrestige;
import freeWarOnTerror.Players.PlayerJihadist;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.abClasses.Player;
import static freeWarOnTerror.helpers.CONSTANTS.SOMALIA;
import static freeWarOnTerror.helpers.CONSTANTS.YEMEN;

/**
 *
 * @author Gustav Wengel
 */
public class Turn {

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
        //Drop funding
        if (!isCardInPlay("Pirates") && (getCountry(SOMALIA).getGovernance() == 4 || getCountry(YEMEN).getGovernance() == 4)) {
            modifyFunding(-1);
        }
        //Flip regime change markers.
        for (MuslimCountry c : getMuslimCountries()) {
            if (c.getRegimeChange() == 2) {
                c.setRegimeChange(1);
            }
        }
        //Modify prestige
        if (anyIslamistRule()) {
            modifyPrestige(-1);
        }
        if (getGlobalPosture() > 2 && isPostureHard()){
            modifyPrestige(1);
        }
        else if (getGlobalPosture() < -2 && !isPostureHard()){
            modifyPrestige(1);
        }
        //Reset reserves.
        for (Player p : getPlayers()){
           p.setReserves(0);
        }
        //Reset first plot
        PlayerJihadist jihadist = (PlayerJihadist) getJihadist();
        jihadist.setFirstPlot(false);
        drawPhase();
    }

}
