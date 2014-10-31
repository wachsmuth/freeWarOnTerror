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

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Emil
 */
public class Game {

    private int prestige = 7;
    private int funding = 9;
    private final Boolean[] markedCards = new Boolean[22];
    private final ArrayList<Country> countries = new ArrayList<>();
    private Deck drawPile = new Deck();
    private Deck discardPile = new Deck();

    public Game() {
        Arrays.fill(markedCards, false);
    }

    public Boolean ifCardActive(int n) {
        return markedCards[n];
    }

    public void setCardInPlay(int n, Boolean bool) {
        markedCards[n] = bool;
    }

    public void connectCountries(Country c1, Country c2) {
        c1.addAdjacentCountry(c2);
        c2.addAdjacentCountry(c1);
    }

    public ArrayList<Country> getAllCountries() {
        return countries;
    }

    public Deck getDrawPile() {
        return drawPile;
    }

    public void setFunding(int funding) {
        this.funding = funding;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }
    
    public int getFunding(){
        return funding;
    }
    
    public int getPrestige(){
        return prestige;
    }

    public void modifyPrestige(int change) {
        prestige = prestige + change;
        if (prestige < 1) {
            prestige = 1;
        } else if (prestige > 12) {
            prestige = 12;
        }
    }

    public void modifyFunding(int change) {
        funding = funding + change;
        if (funding < 1) {
            funding = 1;
        } else if (funding > 9) {
            funding = 9;
        }
    }
}
