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
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.abClasses.Player;
import static freeWarOnTerror.helpers.CONSTANTS.SOMALIA;
import static freeWarOnTerror.helpers.CONSTANTS.YEMEN;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Gustav Wengel
 */
public class Turn {

    private boolean firstPlot;
    private boolean jihadistDone;
    private boolean USADone;

    public void Turn() {
        Game.incrementTurnNumber();
        jihadistDone = false;
        USADone = false;
        firstPlot = true;
    }

    public void startTurn() {
        System.out.println("Turn: " + Game.getTurnNumber());
        System.out.println("Draw Phase");
        drawPhase();
        //Flip regime change markers.
        for (MuslimCountry c : getMuslimCountries()) {
            if (c.getRegimeChange() == 2) {
                c.setRegimeChange(1);
            }
        }
        while (USADone == false && jihadistDone == false) {
            Game.switchCurrentPlayer(Game.getJihadist());
            playCardPhase();
            playCardPhase();
            Game.switchCurrentPlayer();
            playCardPhase();
            playCardPhase();
            resolvePlots();
        }
        turnEnd();
    }

    public boolean isFirstPlot() {
        return firstPlot;
    }

    public void setFirstPlot(boolean firstPlot) {
        this.firstPlot = firstPlot;
    }

    private void drawPhase() {
        Game.getJihadist().drawPhase();
        Game.getUS().drawPhase();
    }

    private void resolvePlots() {
        for (Country c : Game.getAllCountries()) {
            c.resolvePlots();
        }
    }

    private void turnEnd() {
        //Drop funding
        if (isCardInPlay("Pirates") && (getCountry(SOMALIA).getGovernance() == 4 || getCountry(YEMEN).getGovernance() == 4)) {

        } else {
            modifyFunding(-1);
        }

        //Modify prestige
        if (anyIslamistRule()) {
            modifyPrestige(-1);
        }
        if (getGlobalPosture() > 2 && isPostureHard()) {
            modifyPrestige(1);
        } else if (getGlobalPosture() < -2 && !isPostureHard()) {
            modifyPrestige(1);
        }
        //Reset reserves.
        for (Player p : getPlayers()) {
            p.setReserves(0);
        }
        //Reset first plot
        PlayerJihadist jihadist = getJihadist();
        jihadist.setFirstPlot(false);
        startTurn();
    }

    private void playCardPhase() {
        Player cPlayer = Game.getCurrentPlayer();
        ArrayList<Card> hand = cPlayer.getHand();

        //If no cards
        if (hand.isEmpty()) {
            if (cPlayer == Game.getUS()) {
                USADone = true;
            } else {
                jihadistDone = true;
            }
            return;
        }
        //If last card and US
        if (hand.size() == 1 && cPlayer == Game.getUS()) {
            throwAwayOrKeep();
            USADone = true;
            return;
        }

        //Prints hand
        int count = 0;
        for (Card c : hand) {
            System.out.println(count++ + ": " + c);
        }

        //Starts inputLoop with correct input
        int[] okInput = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            okInput[i] = i;
        }

        int userInput = inputLoop(okInput);

        //Plays the card
        cPlayer.playCard(hand.get(userInput));
    }

    private void throwAwayOrKeep() {
        //Debug, still need to do that
    }
}
