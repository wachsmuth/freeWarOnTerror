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

import freeWarOnTerror.Players.PlayerJihadist;
import freeWarOnTerror.Players.PlayerUS;
import freeWarOnTerror.Countries.CountryUSA;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.abClasses.Player;
import static freeWarOnTerror.helpers.Die.prestigeRoll;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
//Static Class
public class Game {

    private static int prestige = 7;
    private static int funding = 9;
    private static boolean postureHard = true;
    private static int globalPosture = 0;
    private static int goodResources = 0;
    private static int islamistResources = 0;
    private static int goodFairCountries = 0;
    private static int poorIslamistCountries = 0;
    private static int oilPriceSpike = 0;
    private static final ArrayList<String> persistentEffects = new ArrayList<>();
    private static final ArrayList<Country> allCountries = new ArrayList<>();
    private static final ArrayList<MuslimCountry> muslimCountries = new ArrayList<>();
    private static final ArrayList<NonMuslimCountry> schengenCountries = new ArrayList<>();
    private static final ArrayList<NonMuslimCountry> nonMuslimCountries = new ArrayList<>();
    private static Deck drawPile = new Deck();
    private static Deck discardPile = new Deck();
    private static final Deck removedCards = new Deck();
    private static PlayerJihadist playerJihadist = new PlayerJihadist("JihadPlayer");
    private static PlayerUS playerUS = new PlayerUS("USPlayer");
    private static final List<Player> players = new ArrayList<Player>()
    {
        {
            players.add(playerJihadist);
            players.add(playerUS);
        }
    };

    public Game() {
        
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
        return postureHard;
    }

    public static void setPostureHard(boolean posture) {
        postureHard = posture;
    }

    public static Country getCountry(int id) {
        for (Country c : allCountries) {
            if (c.getID() == id) {
                return c;
            }
        }
        return null;
    }
    
    public static void rollPrestige(){
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

    public static Player getJihadist() {
        return playerJihadist;
    }

    public static Player getUS() {
        return playerUS;
    }

    public static Card draw() {
        return drawPile.draw();
    }
    
    public static void playCard(Card card){
      boolean remove = false;  
        if (card.getPlayable()){
            card.play();
            
            if (card.getRemoved()){
            removedCards.addCard(card);
            remove = true;
            }
        }
        if (!remove){
            discardPile.addCard(card);
        }
    }
    
    public static void discard(Card card){
        discardPile.addCard(card);
    }
    
    public static Boolean isCardInPlay(String card){
        for (String s : persistentEffects){
            if (s.equals(card)){
                return true;
            }
        }
        return false;
    }
    
    public static void removeCardFromPlay(String card){
        for (String s : persistentEffects){
            if (s.equals(card)){
                persistentEffects.remove(s);
            }
        }
    }
    
    public static void addCardToPlay(String card){
        persistentEffects.add(card);
    }

    public static int getGlobalPosture() {
        calculateGlobalPosture();
        return globalPosture;
    }
    
    public static int getPosturePenalty(){
        //Get penalty from GWOT relations
        int penalty = 0;
        if (isPostureHard()){
            penalty = penalty + getGlobalPosture();
        }
        else {
            penalty = penalty - getGlobalPosture();
        }
        if (penalty > 0){
            penalty = 0;
        }
        else if(penalty < -3){
            penalty = -3;
        }
        return penalty;
    }
    
    public static int getPrestigeModifier(){
        return (int) (Math.floor(prestige/3) - 1);
    }
    
    public static int getOilPriceSpike(){
        return oilPriceSpike;
    }
    
    public static void updateScoreboard(){
        goodResources = 0;
        islamistResources = 0;
        goodFairCountries = 0;
        poorIslamistCountries = 0;
        for (MuslimCountry c : muslimCountries){
            if (c.getGovernance() == 1){
                goodResources = goodResources + c.getResources();
                goodFairCountries++;
            }
            else if (c.getGovernance() == 2){
                goodFairCountries++;
            }
            else if (c.getGovernance() == 3){
                poorIslamistCountries++;
            }
            else if (c.getGovernance() == 4){
                poorIslamistCountries++;
                islamistResources = islamistResources + c.getResources();
            }
            
        }
        
    }
}
