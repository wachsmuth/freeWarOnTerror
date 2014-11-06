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

import static freeWarOnTerror.Game.discard;
import static freeWarOnTerror.Game.getUS;
import static freeWarOnTerror.Game.isPostureHard;
import static freeWarOnTerror.Game.playCard;
import static freeWarOnTerror.Game.setPostureHard;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class ActionReassessment extends Action {

    public ActionReassessment() {
        super("Use two cards for reassessment");
    }

    @Override
    public boolean canDoAction(Card c) {
        if (c.getRealOps() < 3) {
            return false;
        }
        int noOf3Ops = 0;
        for (Card card : getUS().getHand()) {
            if (card.getRealOps() > 2 && card != c) {
                noOf3Ops++;
            }
        }
        //DEBUG make it illegal to use reserves here.
        return noOf3Ops > 1;
    }

    @Override
    public void performAction(Card c) {
        ArrayList<Card> threeOps = new ArrayList<>();
        for (Card card : getUS().getHand()) {
            if (card.getRealOps() > 2 && card != c) {
                threeOps.add(card);
            }

            

        }
        Card target = (inputLoop(threeOps));
        if (target.getAlignment() == 0 || target.getAlignment() == 3){
            playCard(target);
        }
        else {
            discard(target);
        }
        setPostureHard(!isPostureHard());
    }
}
