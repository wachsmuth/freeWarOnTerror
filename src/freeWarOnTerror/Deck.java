/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror;

import freeWarOnTerror.abClasses.Card;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Emil
 */
public class Deck {

    private final ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        //Empty constructor, remove DEBUG
    }

    public void addCard(Card c) {
        cards.add( c);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.size() > 0) {
            Card c = (Card) cards.get(0);
            cards.remove(c);
            return c;
        } else {
            Game.reshuffleDeck();
            return Game.getDrawPile().draw();
        }
    }

    public int getSize() {
        return cards.size();
    }
}
