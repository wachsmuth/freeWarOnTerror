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
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;

import java.util.ArrayList;

import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.getCurrentPlayer;
import static freeWarOnTerror.Game.getJihadist;
import freeWarOnTerror.MuslimCountry;
import static freeWarOnTerror.helpers.Alignment.NEUTRAL;
import static freeWarOnTerror.helpers.CardLookup.HIZBALLAH;
import static freeWarOnTerror.helpers.CountryLookup.GULFSTATES;
import static freeWarOnTerror.helpers.CountryLookup.IRAQ;
import static freeWarOnTerror.helpers.CountryLookup.LEBANON;
import static freeWarOnTerror.helpers.CountryLookup.SAUDIARABIA;
import static freeWarOnTerror.helpers.CountryLookup.TURKEY;
import static freeWarOnTerror.helpers.Governance.POOR;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;

/**
 * Created by Emil on 30-11-2014.
 */
public class Hizballah extends Card {

    public Hizballah() {
        super(HIZBALLAH);
    }

    @Override
    public void playEvent() {
        if (getCurrentPlayer() == getJihadist()) {
            ((MuslimCountry) getCountry(LEBANON)).setGovernanceAndAlignment(POOR, NEUTRAL);
        } else {
            ArrayList<Country> countries = new ArrayList<>();
            countries.add(getCountry(LEBANON));
            countries.add(getCountry(TURKEY));
            countries.add(getCountry(IRAQ));
            countries.add(getCountry(SAUDIARABIA));
            countries.add(getCountry(GULFSTATES));
            ArrayList<Cell> eligibleCells = new ArrayList<>();
            for (Country c : countries) {
                for (Cell ce : c.getCells()) {
                    eligibleCells.add(ce);
                }
            }
            inputLoop(eligibleCells).kill();
        }
    }
}
