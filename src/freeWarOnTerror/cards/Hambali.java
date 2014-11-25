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
package freeWarOnTerror.cards;

import freeWarOnTerror.Cell;
import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.getCurrentPlayer;
import static freeWarOnTerror.Game.getTrack;
import static freeWarOnTerror.Game.getUS;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.Alignment.ALLY;
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.CountryLookup.INDONESIA;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Hambali extends Card {

    public Hambali() {
        super(CardLookup.HAMBALI);
    }

    @Override
    public Boolean getPlayable() {
        if (hasHambali(getCountry(CountryLookup.INDONESIA))) {
            return true;
        }
        for (Country c : getCountry(INDONESIA).getAdjacentCountries()) {
            if (hasHambali(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasHambali(Country c) {
        if (c.hasCells()) {
            if (c instanceof MuslimCountry) {
                return ((MuslimCountry) c).getAlignment() == ALLY;
            } else if (c instanceof NonMuslimCountry) {
                return ((NonMuslimCountry) c).getPosture() == 1;
            }
        }
        return false;
    }

    @Override
    public void playEvent() {
        ArrayList<Country> eligibleCountries = new ArrayList<>();
        if (hasHambali(getCountry(CountryLookup.INDONESIA))) {
            eligibleCountries.add(getCountry(INDONESIA));
        }
        for (Country c : getCountry(INDONESIA).getAdjacentCountries()) {
            if (hasHambali(c)) {
                eligibleCountries.add(c);
            }
        }
        if (getCurrentPlayer() == getUS()){
            ArrayList<Cell> eligibleCells = new ArrayList<>();
            for (Country country : eligibleCountries){
                for (Cell c : country.getCells()){
                    eligibleCells.add(c);
                }
            }
            inputLoop(eligibleCells).kill();
            getUS().draw();
            getUS().draw();
            setRemoved(true);
        }
        else {
            inputLoop(eligibleCountries).placePlot(inputLoop(getTrack().getPlots()));
        }
    }
}
