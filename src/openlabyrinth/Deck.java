/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openlabyrinth;

import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Emil
 */
public class Deck {

    private final ArrayList<Card> cards = new ArrayList<>();

    public Deck() {

    }

    public void addCard(Card c) {

        cards.add( c);
    }


    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card takeTopCard() {
        if (cards.size() > 0) {
            Card c = (Card) cards.get(0);
            cards.remove(c);
            return c;
        } else {
            return null;
        }
    }

    public int getSize() {
        return cards.size();
    }
}
