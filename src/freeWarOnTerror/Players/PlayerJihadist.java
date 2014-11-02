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

import freeWarOnTerror.Game;
import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.getMuslimCountries;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.JIHAD;
import static freeWarOnTerror.helpers.CONSTANTS.NEUTRAL;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;

/**
 *
 * @author Emil
 */
public class PlayerJihadist extends freeWarOnTerror.abClasses.Player {

    private boolean firstPlot = false;

    public PlayerJihadist(String name) {
        super(name);
    }

    public void recruit() {

    }

    public void travel() {

    }

    public void minorJihad() {

    }

    public void majorJihad() {

    }

    public boolean canMinorJihad() {
        for (Country c : getMuslimCountries()) {
            if (c.canPlot()) {
                return true;
            }
        }
        return false;
    }

    public boolean canPlot() {
        for (Country c : getAllCountries()) {
            if (c.canPlot()) {
                return true;
            }
        }
        return false;
    }

    public boolean canMajorJihad(int ops) {
        for (Country c : getMuslimCountries()) {
            MuslimCountry x = (MuslimCountry) c;
            if (x.canMajorJihad(ops)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canPlayAsEvent(Card c) {
        return (c.getAlignment() == 1 || c.getAlignment() == 3) && c.getPlayable();
    }

    public boolean hasUsedFirstPlot() {
        return firstPlot;
    }

    public void setFirstPlot(boolean firstPlot) {
        this.firstPlot = firstPlot;
    }

    @Override
    public void playForOps(int ops) {
        //DEBUG - doesn't do jack yet
    }

    @Override
    public void drawPhase() {
        //Islamist Draw
        if (Game.getFunding() > 6) {
            draw(9);
        } else if (Game.getFunding() > 3) {
            draw(8);
        } else {
            draw(7);
        }
    }

    @Override
    public void playCard(Card c) {
        if (c.getAlignment() == JIHAD || c.getAlignment() == NEUTRAL) {
            System.out.println("Do you want to:");
            System.out.println("1: Play for ops");
            System.out.println("2: Play for event");
            int userInput = inputLoop(1, 2);
            if (userInput == 1) {
                playForOps(c.getOps());
            } else {
                c.playEvent();
            }

        } else {
            //Event and ops both happen - but which first?
            System.out.println("Do you want:");
            System.out.println("1: The event to happen first");
            System.out.println("2: To play the ops first");
            int userInput = inputLoop(1, 2);
            if (userInput == 1) {
                c.playEvent();
                playForOps(c.getOps());
            } else {
                playForOps(c.getOps());
                c.playEvent();
            }
        }
    }
}
