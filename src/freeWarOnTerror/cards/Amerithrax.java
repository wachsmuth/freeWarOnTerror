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

import static freeWarOnTerror.Game.discard;
import static freeWarOnTerror.Game.getUS;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.AMERITHRAX;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Amerithrax extends Card {

    public Amerithrax() {
        super("Amerithrax", 2, 3, false, false, AMERITHRAX);
    }

    @Override
    public void playEvent() {
        ArrayList<Card> usEvents = new ArrayList<>();
        int highestOps = 0;
        for (Card c : getUS().getHand()) {
            if (c.getAlignment() == 2) {
                usEvents.add(c);
                if (c.getOps() > highestOps) {
                    highestOps = c.getOps();
                }
            }

        }
        if (usEvents.size() > 0) {
            for (int i = 0;i < usEvents.size();i++){
                if (usEvents.get(i).getOps() < highestOps) {
                    usEvents.remove(usEvents.get(i));
                }
            }
            System.out.println("Choose a card to discard");
            discard(inputLoop(usEvents));
        }
    }
}
