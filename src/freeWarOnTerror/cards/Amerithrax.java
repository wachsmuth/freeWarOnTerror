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

import static freeWarOnTerror.Game.getUS;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CardAlignment.USA;
import freeWarOnTerror.helpers.CardLookup;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Amerithrax extends Card {

    public Amerithrax() {
        super(CardLookup.AMERITHRAX);
    }

    @Override
    public void playEvent() {
        ArrayList<Card> usEvents = new ArrayList<>();
        int highestOps = 0;
        for (Card c : getUS().getHand()) {
            if (c.getAlignment() == USA && c.getOps() > highestOps) {
                    highestOps = c.getOps();
            }
        }
        for (Card c : getUS().getHand()) {
            if (c.getAlignment() == USA && c.getOps() == highestOps) {
                usEvents.add(c);

            }

        }
        if (usEvents.size() > 0) {
            System.out.println("Choose a card to discard");
            getUS().discard(inputLoop(usEvents));
        }
    }
}
