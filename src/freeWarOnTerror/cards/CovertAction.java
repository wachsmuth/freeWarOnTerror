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

import static freeWarOnTerror.Game.getMuslimCountries;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.Alignment.ADVERSARY;
import static freeWarOnTerror.helpers.Alignment.NEUTRAL;
import freeWarOnTerror.helpers.CardLookup;
import static freeWarOnTerror.helpers.Die.rollDie;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class CovertAction extends Card {

    public CovertAction() {
        super(CardLookup.COVERTACTION);
    }

    @Override
    public Boolean getPlayable() {
        for (MuslimCountry c : getMuslimCountries()) {
            if (c.getAlignment() == ADVERSARY) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void playEvent() {
        MuslimCountry targetCountry;
        ArrayList<Country> adversaries = new ArrayList<>();
        for (MuslimCountry c : getMuslimCountries()) {
            if (c.getAlignment() == ADVERSARY) {
                adversaries.add(c);
            }
        }
        if (adversaries.size() == 1) {
            targetCountry = (MuslimCountry) adversaries.get(0);
        } else {
            targetCountry = (MuslimCountry) inputLoop("Pick an adversary to attempt shift to neutral", adversaries);
        }
        if (3 < rollDie()) {
            targetCountry.setAlignment(NEUTRAL);
        }
    }
}
