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
import static freeWarOnTerror.Game.getAllCountries;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;

/**
 *
 * @author Emil
 */
public class PlayerUS extends freeWarOnTerror.abClasses.Player {

    public PlayerUS(String name) {
        super(name);
    }

    public void disrupt() {

    }

    public void warOfIdeas() {

    }

    public void reassessment() {

    }

    public void regimeChange() {

    }

    public void withdraw() {

    }

    public void deploy() {

    }
    
    public boolean canAlert(int ops){
        if (ops < 3){
            return false;
        }
        for (Country c : getAllCountries()){
            if (c.hasPlots()){
                return true;
            }
        }
        return false;
    }
    
    public boolean canReassess(int ops){
        if (ops < 3){
            return false;
        }
        int noOf3Ops = 0;
        for (Card c : getHand()){
            if (c.getOps() > 2){
                noOf3Ops++;
            }
        }
        //DEBUG make it illegal to use reserves here.
        return noOf3Ops > 2;
    }

    @Override
    public void drawPhase() {
        //US draw
        if (Game.getPrestige() > 7) {
            draw(9); //DEBUG - right numbers?
        } else if (Game.getPrestige() > 4) {
            draw(8);
        } else {
            draw(7);
        }
    }
}
