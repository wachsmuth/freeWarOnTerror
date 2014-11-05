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

import freeWarOnTerror.Game;
import static freeWarOnTerror.Game.getMuslimCountries;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.Players.Actions.ActionEventJihadist;
import freeWarOnTerror.Players.Actions.ActionMajorJihad;
import freeWarOnTerror.Players.Actions.ActionMinorJihad;
import freeWarOnTerror.Players.Actions.ActionPlot;
import freeWarOnTerror.Players.Actions.ActionRecruit;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.AUTO;
import static freeWarOnTerror.helpers.CONSTANTS.USA;

/**
 *
 * @author Emil
 */
public class PlayerJihadist extends freeWarOnTerror.abClasses.Player {

    public PlayerJihadist(String name) {
        super(name);
        addAction(new ActionPlot());
        addAction(new ActionEventJihadist());
        addAction(new ActionMajorJihad());
        addAction(new ActionMinorJihad());
        addAction(new ActionRecruit());
    }

//--------------------------------GETTERS-------------------------------------------------------
    public boolean canMinorJihad() {
        for (MuslimCountry c : getMuslimCountries()) {
            if (c.canMinorJihad()) {
                return true;
            }
        }
        return false;
    }

//--------------------------------ACTIONS-----------------------------------------------------
    public void travel() {

    }

//--------------------------------OVERRIDES-----------------------------------------------------
    @Override
    public void drawPhase() {
        //Jihadist Draw
        if (Game.getFunding() > 6) {
            draw(9);
        } else if (Game.getFunding() > 3) {
            draw(8);
        } else {
            draw(7);
        }
    }

    @Override
    public void playCard(Card c) {
        if (c.getAlignment() == USA || c.getAlignment() == AUTO) {
            chooseEventOrOps(c);
        } else {
            howToPlay(c);
        }
    }
}
