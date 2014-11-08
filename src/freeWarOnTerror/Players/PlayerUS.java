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
package freeWarOnTerror.Players;

import static freeWarOnTerror.Game.getTroops;
import freeWarOnTerror.Players.Actions.ActionAlert;
import freeWarOnTerror.Players.Actions.ActionDeploy;
import freeWarOnTerror.Players.Actions.ActionEventUS;
import freeWarOnTerror.Players.Actions.ActionReassessment;
import freeWarOnTerror.Players.Actions.ActionRegimeChange;
import freeWarOnTerror.Players.Actions.ActionReservesAdd;
import freeWarOnTerror.Players.Actions.ActionReservesUse;
import freeWarOnTerror.Players.Actions.ActionWarOfIdeas;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.AUTO;
import static freeWarOnTerror.helpers.CONSTANTS.JIHAD;

/**
 *
 * @author Emil
 */
public class PlayerUS extends freeWarOnTerror.abClasses.Player {

    public PlayerUS(String name) {
        super(name);
        addAction(new ActionEventUS());
        addAction(new ActionWarOfIdeas());
        addAction(new ActionDeploy());
        addAction(new ActionReassessment());
        addAction(new ActionAlert());
        addAction(new ActionRegimeChange());
        addAction(new ActionReservesUse(this));
        addAction(new ActionReservesAdd(this));

    }

//--------------------------------OVERRIDES-----------------------------------------------------
    @Override
    public void drawPhase() {
        //US draw
        if (getTroops() > 10) {
            draw(9);
        } else if (getTroops() > 5) {
            draw(8);
        } else {
            draw(7);
        }
    }

    @Override
    public void playCard(Card c) {
        if (c.getAlignment() == JIHAD || c.getAlignment() == AUTO) {
            chooseEventOrOps(c);
        } else {
            howToPlay(c);
        }
    }
}
