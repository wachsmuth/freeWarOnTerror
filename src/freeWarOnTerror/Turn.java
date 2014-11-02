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
        if (isCardInPlay("Pirates") && (getCountry(SOMALIA).getGovernance() == 4 || getCountry(YEMEN).getGovernance() == 4)) {
            
        }
        else {
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
        drawPhase();
    }
    
    public void playCardPhase(){
        Player cPlayer = Game.getCurrentPlayer();
        ArrayList<Card> hand = cPlayer.getHand();
        //Prints hand
        int count = 0;
        for (Card c : hand){
            System.out.println(count + ": " + c);
        }
        
        //Starts inputLoop with correct input
        int[] okInput = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++){
            okInput[i] = i;
        }
        
        int userInput = inputLoop(okInput);
        
        //Plays the card
        hand.get(userInput).playEvent();
    }
}
