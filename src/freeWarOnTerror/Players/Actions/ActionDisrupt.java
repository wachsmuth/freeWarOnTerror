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
package freeWarOnTerror.Players.Actions;

import freeWarOnTerror.Cell;
import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.isCardInPlay;
import static freeWarOnTerror.Game.modifyPrestige;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CardLookup.ALANBAR;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import static freeWarOnTerror.helpers.Posture.HARD;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class ActionDisrupt extends Action {

    public ActionDisrupt() {
        super("Use ops to disrupt cells");
    }

    @Override
    public boolean canDoAction(Card c) {
        for (Country country : getAllCountries()) {
            if (country.canDisrupt(c.getOps())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void performAction(Card c) {
        ArrayList<Country> eligibleCountries = new ArrayList<>();
        for (Country country : getAllCountries()) {
            if (country.canDisrupt(c.getOps())) {
                eligibleCountries.add(country);
            }
        }
        System.out.println("Choose where to disrupt");
        Country targetCountry = inputLoop(eligibleCountries);
        int iterations = 1;
        if (isCardInPlay(ALANBAR) && (targetCountry.is(CountryLookup.SYRIA) || targetCountry.is(CountryLookup.IRAQ))){
            
        }
        else if (targetCountry.troopAmount() > 1) {
            iterations = 2;
      
        } else if (targetCountry instanceof NonMuslimCountry) {
            NonMuslimCountry tc = (NonMuslimCountry) targetCountry;
            if (tc.getPosture() == HARD) {
                iterations = 2;
            }
        }
        if (targetCountry.hasCells()) {
            for (int i = 0; i < iterations; i++) {
                if (targetCountry.hasCells()) {
                    System.out.println("Choose a cell to disrupt");
                    ArrayList<Cell> cells = new ArrayList<>();
                    for (Cell cell1 : targetCountry.getCells()){
                        if (cell1.isIdle()){
                            cells.add(cell1);
                        }
                    }
                    inputLoop(cells).disrupt();
                }
            }
        }
        else {
            targetCountry.setCadre(false);
        }
        if (targetCountry.troopAmount() > 1) {
            modifyPrestige(1);
        }
    }
}
