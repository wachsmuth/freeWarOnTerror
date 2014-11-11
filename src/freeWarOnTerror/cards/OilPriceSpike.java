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

import static freeWarOnTerror.Game.getCurrentPlayer;
import static freeWarOnTerror.Game.getDiscardPile;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.OILPRICESPIKE;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class OilPriceSpike extends Card {
    
    public OilPriceSpike(){
        super("Oil Price Spike", 3, 1, false, false, OILPRICESPIKE);
    }
    
    @Override
    public void playEvent(){
        ArrayList<Card> possibleDiscards = new ArrayList<>();
        for (Card c : getDiscardPile().getDeck()){
            if (c.getId() != OILPRICESPIKE){
                possibleDiscards.add(c);
            }
        }
        if (possibleDiscards.size() > 0){
            System.out.println("Choose a card to pick from the discard pile");
            Card card = inputLoop(possibleDiscards);
            getDiscardPile().removeCard(card);
            getCurrentPlayer().addCard(card);
        }
        //DEBUG put lapsing effect into play
    }
}
