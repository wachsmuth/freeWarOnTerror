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
package freeWarOnTerror;

import freeWarOnTerror.Countries.CountryUSA;
import freeWarOnTerror.Players.PlayerJihadist;
import freeWarOnTerror.Players.PlayerUS;
import freeWarOnTerror.Scenarios.LetsRoll;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.abClasses.Player;
import static freeWarOnTerror.helpers.CONSTANTS.FAIR;
import static freeWarOnTerror.helpers.CONSTANTS.GOOD;
import static freeWarOnTerror.helpers.CONSTANTS.ISLAMISTRULE;
import static freeWarOnTerror.helpers.CONSTANTS.POOR;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDSTATES;
import static freeWarOnTerror.helpers.Die.prestigeRoll;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
//Static Class
public class Game {

    private static int decksLeft = 0; //DEBUG - add support for additional decks in menu
    private static int turnNumber = 0;
    private static int prestige = 7;
    private static int funding = 9;
    private static int globalPosture = 0;
    private static int goodResources = 0;
    private static int islamistResources = 0;
    private static int goodFairCountries = 0;
    private static int poorIslamistCountries = 0;
    private static int oilPriceSpike = 0;
    private static Turn currentTurn;
    private static List<Turn> turnList = new ArrayList<>();
    private static final ArrayList<String> persistentEffects = new ArrayList<>();
    private static final ArrayList<Country> allCountries = new ArrayList<>();
    private static final ArrayList<MuslimCountry> muslimCountries = new ArrayList<>();
    private static final ArrayList<NonMuslimCountry> schengenCountries = new ArrayList<>();
    private static final ArrayList<NonMuslimCountry> nonMuslimCountries = new ArrayList<>();
    private static Deck drawPile = new Deck();
    private static Deck discardPile = new Deck();
    private static final Track track = new Track();
    private static final Deck removedCards = new Deck();
    private static final PlayerJihadist playerJihadist = new PlayerJihadist("JihadPlayer");
    private static final PlayerUS playerUS = new PlayerUS("USPlayer");
    private static final List<Player> players = new ArrayList<>();
    private static Player currentPlayer = playerJihadist;

    static {
        players.add(playerJihadist);
        players.add(playerUS);
    }

    public Game() {

    }

    public static void reshuffleDeck(){
        if (decksLeft == 0){
            noCardsLeft();
            return;
        }
        decksLeft--;
        drawPile = discardPile;
        discardPile = new Deck();
        drawPile.shuffle();
    }
    
    private static void noCardsLeft(){
        int americaRes = 0;
        int jihadRes = 0;
        //count resources
        for (MuslimCountry c : muslimCountries){
            if (c.getGovernance() == GOOD){
                americaRes += c.getResources();
            }
            else if (c.getGovernance() == ISLAMISTRULE || c.getRegimeChange() == 2){
                jihadRes += c.getResources();
            }
        }
        
        if (americaRes > jihadRes * 2){
            victoryUS();
        } else {
            victoryJihad();
        }
    }
    
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void switchCurrentPlayer() {
        if (currentPlayer == playerJihadist) {
            currentPlayer = playerUS;
        } else {
            currentPlayer = playerJihadist;
        }
    }

    public static void switchCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    public static void connectCountries(Country c1, Country c2) {
        c1.addAdjacentCountry(c2);
        c2.addAdjacentCountry(c1);
    }

    public static ArrayList<Country> getAllCountries() {
        return allCountries;
    }

    public static ArrayList<MuslimCountry> getMuslimCountries() {
        return muslimCountries;
    }

    public static ArrayList<NonMuslimCountry> getSchengenCountries() {
        return schengenCountries;
    }

    public static ArrayList<NonMuslimCountry> getNonMuslimCountries() {
        return nonMuslimCountries;
    }

    public static Deck getDrawPile() {
        return drawPile;
    }

    public static void setFunding(int f) {
        funding = f;
    }

    public static void setPrestige(int p) {
        prestige = p;
    }

    public static int getFunding() {
        return funding;
    }

    public static int getPrestige() {
        return prestige;
    }

    public static boolean isPostureHard() {
        NonMuslimCountry usa = (NonMuslimCountry) getCountry(UNITEDSTATES);

        return usa.getPosture() == 1;
    }

    public static void setPostureHard(boolean posture) {
        NonMuslimCountry usa = (NonMuslimCountry) getCountry(UNITEDSTATES);
        if (posture) {
            usa.setPosture(1);
        } else {
            usa.setPosture(-1);
        }
    }

    public static Country getCountry(int id) {
        for (Country c : allCountries) {
            if (c.getID() == id) {
                return c;
            }
        }
        return null;
    }

    public static void rollPrestige() {
        modifyPrestige(prestigeRoll());
    }

    public static void modifyPrestige(int change) {
        prestige = prestige + change;
        if (prestige < 1) {
            prestige = 1;
        } else if (prestige > 12) {
            prestige = 12;
        }
    }

    public static void modifyFunding(int change) {
        funding = funding + change;
        if (funding < 1) {
            funding = 1;
        } else if (funding > 9) {
            funding = 9;
        }
    }

    public static void calculateGlobalPosture() {
        int calcValue = 0;
        for (NonMuslimCountry c : nonMuslimCountries) {
            if (c instanceof CountryUSA) {
                continue;
            }
            calcValue += c.getPosture();
        }
        globalPosture = calcValue;
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static boolean anyIslamistRule() {
        for (MuslimCountry c : muslimCountries) {
            if (c.getGovernance() == 4) {
                return true;
            }
        }
        return false;
    }

    public static boolean anyRegimeChange() {
        for (MuslimCountry c : muslimCountries) {
            if (c.getRegimeChange() > 0) {
                return true;
            }
        }
        return false;
    }

    public static PlayerJihadist getJihadist() {
        return playerJihadist;
    }

    public static PlayerUS getUS() {
        return playerUS;
    }

    public static Track getTrack() {
        return track;
    }

    public static int getTroops() {
        return getTrack().troopAmount();
    }

    public static void placeCell(Country country) {
        if (canPlaceCell()) {
            track.getCells().get(0).move(country);
        }
    }

    public static boolean canPlaceCell() {
        return track.hasCells();
    }

    public static boolean canRecruit() {
        if (!track.hasCells()) {
            return false;
        } else if (funding > 6) {
            return true;
        } else if (funding > 3 && track.cellAmount() < 6) {
            return false;
        } else {
            return !(funding < 4 && track.cellAmount() < 11);
        }
    }

    public static Card draw() {
        return drawPile.draw();
    }

    public static void playCard(Card card) {
        for (Player p : players) {
            p.removeCard(card);
        }
        boolean remove = false;
        if (card.getPlayable()) {
            card.playEvent();

            if (card.getRemoved()) {
                removedCards.addCard(card);
                remove = true;
                if (discardPile.hasCard(card)){
                    discardPile.removeCard(card);
                }
            }
        }
        if (!remove && !discardPile.hasCard(card)) {
            discardPile.addCard(card);
        }
    }

    public static void discard(Card card) {
        for (Player p : players) {
            p.removeCard(card);
        }
        if (!removedCards.hasCard(card) && !discardPile.hasCard(card)){
            discardPile.addCard(card);
        }
        
    }

    public static Boolean isCardInPlay(String card) {
        for (String s : persistentEffects) {
            if (s.equals(card)) {
                return true;
            }
        }
        return false;
    }

    public static void removeCardFromPlay(String card) {
        for (String s : persistentEffects) {
            if (s.equals(card)) {
                persistentEffects.remove(s);
            }
        }
    }

    public static void addCardToPlay(String card) {
        persistentEffects.add(card);
    }

    public static int getGlobalPosture() {
        calculateGlobalPosture();
        return globalPosture;
    }

    public static int getPosturePenalty() {
        //Get penalty from GWOT relations
        int penalty = 0;
        if (isPostureHard()) {
            penalty = penalty + getGlobalPosture();
        } else {
            penalty = penalty - getGlobalPosture();
        }
        if (penalty > 0) {
            penalty = 0;
        } else if (penalty < -3) {
            penalty = -3;
        }
        return penalty;
    }

    public static int getPrestigeModifier() {
        return (int) (Math.floor(prestige / 3) - 1);
    }

    public static int getOilPriceSpike() {
        return oilPriceSpike;
    }

    public static void updateScoreboard() {
        goodResources = 0;
        islamistResources = 0;
        goodFairCountries = 0;
        poorIslamistCountries = 0;
        for (MuslimCountry c : muslimCountries) {
            if (c.getGovernance() == 1) {
                goodResources = goodResources + c.getResources();
                goodFairCountries++;
            } else if (c.getGovernance() == 2) {
                goodFairCountries++;
            } else if (c.getGovernance() == 3) {
                poorIslamistCountries++;
            } else if (c.getGovernance() == 4) {
                poorIslamistCountries++;
                islamistResources = islamistResources + c.getResources();
            }

        }

    }

    public static int getTurnNumber() {
        return turnNumber;
    }

    public static void incrementTurnNumber() {
        Game.turnNumber++;
    }

    public static void startGame(int scenario) {
        if (scenario == 1) {
            Scenario letsRoll = new LetsRoll();
            System.out.println("Chosen scenario is Let's Roll.");
        }
        for (Country c : allCountries) {
            if (c instanceof MuslimCountry) {
                muslimCountries.add((MuslimCountry) c);
            }
            if (c instanceof NonMuslimCountry) {
                nonMuslimCountries.add((NonMuslimCountry) c);
            }
        }
        drawPile.shuffle();
        currentTurn = new Turn();
        currentTurn.startTurn();
    }

    public static void printStatus() {
        System.out.println("Prestige: " + prestige + " Funding: " + funding);
        int count = 0;
        for (Country c : allCountries) {
            String counter = "";
            if (count < 10) {
                counter = " ";
            }
            counter = counter + count++;
            System.out.println(counter + ": " + c);
        }
    }

    public static void checkForVictory() {
        checkForVictoryUS();
        checkForVictoryJihad();
    }

    public static void WMDinUS() {
        victoryJihad();
    }
    
    public static void newTurn(){
        turnList.add(currentTurn);
        currentTurn = new Turn();
        currentTurn.startTurn();
    }

    private static void checkForVictoryUS() {
        //Check for US Victory

        //By resources
        int USresources = 0;
        for (MuslimCountry c : muslimCountries) {
            //Total resources
            if (c.getGovernance() == GOOD) {
                USresources += c.getResources();
            }
        }
        if (USresources >= 12) {
            victoryUS();
            return;
        }
        //By governance
        int goodGovernances = 0;
        for (MuslimCountry c : muslimCountries) {
            //Total resources
            if (c.getGovernance() == GOOD || c.getGovernance() == FAIR) {
                goodGovernances++;
            }
        }
        if (goodGovernances >= 15) {
            victoryUS();
            return;
        }

        //By No jihadist cells
        boolean cellsFound = false;
        for (Country c : getAllCountries()) {
            if (c.hasCells()) {
                cellsFound = true;
            }
        }
        if (cellsFound == false) {
            victoryUS();
        }
    }

    private static void checkForVictoryJihad() {
        //By islamist rule resources
        int islamistRuleResources = 0;
        for (MuslimCountry c : getMuslimCountries()) {
            if (c.getGovernance() == ISLAMISTRULE) { //Do only muslime countries have resources? DEBUG
                islamistRuleResources += c.getResources();
            }
        }
        if (islamistRuleResources >= 6) {
            victoryJihad();
            return;
        }

        //By US Prestige and poor government
        if (getPrestige() == 1) {
            int shittyCountries = 0;
            for (MuslimCountry c : getMuslimCountries()) {
                if (c.getGovernance() == POOR || c.getGovernance() == ISLAMISTRULE) {
                    shittyCountries++;
                }
            }
            if (shittyCountries >= 15) {
                victoryJihad();
            }
        }
    }

    private static void victoryUS() {

    }

    private static void victoryJihad() {

    }
}
