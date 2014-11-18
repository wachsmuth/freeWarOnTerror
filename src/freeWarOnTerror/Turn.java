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
import static freeWarOnTerror.Game.getMuslimCountries;
import static freeWarOnTerror.Game.getPlayers;
import static freeWarOnTerror.Game.isCardInPlay;
import static freeWarOnTerror.Game.isPostureHard;
import static freeWarOnTerror.Game.modifyFunding;
import static freeWarOnTerror.Game.modifyPrestige;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.abClasses.Player;
import static freeWarOnTerror.helpers.CONSTANTS.PIRATES;
import freeWarOnTerror.helpers.CountryLookup;
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
    private int oilSpike;
    private boolean biometrics;
    private boolean itjihad;
    private boolean gtmo;

    public Turn() {
        Game.incrementTurnNumber();
        jihadistDone = false;
        USADone = false;
        firstPlot = true;
        oilSpike = 0;
        biometrics = false;
        itjihad = false;
        gtmo = false;
    }

//--------------------------------PUBLIC-------------------------------------------------------
    private void DEBUG() { 
        Country c = getCountry(CountryLookup.IRAQ);
        if (c.is(CountryLookup.IRAQ)){
            System.out.println("yay");
        }
    //Function for cfhanging parameters of a turn to test faster. Should not be in production code
        /*System.out.println("DEBUG START");
        //System.out.println(Game.getUS().getHand());
        while (true) {
            System.out.println("Discarding " + getUS().getHand().get(0));
            getUS().discard(getUS().getHand().get(0));
            if (Game.getUS().getHand().size() == 1) {
                break;
            }
        }
        switchCurrentPlayer(getUS());
        playCardPre();
        System.out.println("Debug done");*/
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
        DEBUG(); //Debug
        while (USADone == false || jihadistDone == false) {
            Game.switchCurrentPlayer(Game.getJihadist());
            System.out.println("Jihadists turn to play cards");
            playCardPre();
            playCardPre();
            Game.switchCurrentPlayer();
            System.out.println("US's turn to play cards");
            playCardPre();
            playCardPre();
            System.out.println("Resolving plots");
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
    
    public void oilSpike(){
        oilSpike += 1;
    }
    
    public int isOilSpike(){
        return oilSpike;
    }

    public boolean isBiometrics() {
        return biometrics;
    }
    
    public void biometrics(){
        biometrics = true;
    }

    public boolean isItjihad() {
        return itjihad;
    }
    
    public void itjihad(){
        itjihad = true;
    }

    public boolean isGtmo() {
        return gtmo;
    }
    
    public void gtmo(){
        gtmo = true;
    }
    
    
//--------------------------------PRIVATE-------------------------------------------------------

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
        if (isCardInPlay(PIRATES) && (getCountry(CountryLookup.SOMALIA).getGovernance() == 4 || getCountry(CountryLookup.YEMEN).getGovernance() == 4)) {

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
        Game.checkForVictory();

        Game.newTurn(); //All over again
    }

    private void playCardPre() {
        Player cPlayer = Game.getCurrentPlayer();
        ArrayList<Card> hand = cPlayer.getHand();

        //If no cards
        if (hand.isEmpty()) {
            if (cPlayer == Game.getUS()) {
                System.out.println("US is out of cards");
                USADone = true;
            } else {
                System.out.println("Jihad is out of cards");
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
        pickCard();
    }

    private void pickCard() {
        //Prints hand
        Player cPlayer = Game.getCurrentPlayer();
        ArrayList<Card> hand = cPlayer.getHand();

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
        this.playCardMain(hand.get(userInput));
    }

    private void playCardMain(Card c) {
        Player cPlayer = Game.getCurrentPlayer();

        cPlayer.playCard(c);

        Game.checkForVictory();
    }

    private void throwAwayOrKeep() {
        Player cPlayer = Game.getCurrentPlayer();
        ArrayList<Card> hand = cPlayer.getHand();
        System.out.println("Your last card is: " + hand.get(0));
        System.out.println("Do you want to: ");
        System.out.println("0: Throw it out");
        System.out.println("1: Save it");
        System.out.println("2: Play is like normal");
        int userInput = inputLoop(0, 1, 2);
        switch (userInput) {
            case 0:
                cPlayer.discard(hand.get(0));
                System.out.println("Discarded");
                break;
            case 1:
                System.out.println("Savin it!");
                break;//Do nothing
            case 2:
                System.out.println("Playing it");
                playCardMain(hand.get(0)); //Business as usual
                break;
            default:
                System.out.println("error invalid input");
                break;
        }
    }
}
