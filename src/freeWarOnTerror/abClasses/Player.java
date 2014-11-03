package freeWarOnTerror.abClasses;

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
import freeWarOnTerror.Game;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;
/**
 *
 * @author Emil
 */
public abstract class Player {
    
    private final String name;
    private final ArrayList<Card> hand = new ArrayList<>();
    private int reserves = 0;
    private final ArrayList<Action> actions = new ArrayList<>();
    
    
    
    public Player(String name){
        this.name = name;
    }
    
    public void howToPlay(Card c){
        ArrayList<String> possibleActions = new ArrayList<>();
        for (Action a : actions){
            a.setValue(-1);
            if (a.canDoAction(c)){
                possibleActions.add(a.getDescription());
                a.setValue(possibleActions.size());
            }
        }
        String[] stringArray = new String[possibleActions.size()];
        stringArray = possibleActions.toArray(stringArray);
        int userInput = inputLoop("Choose how to play the card", stringArray);
        for (Action a : actions){
            if (a.getValue() == userInput){
                a.performAction(c);
                break;
            }
        }
    }
    
    public String getName(){
        return name;
    }
    
    public void addCard(Card card){
        hand.add(card);
    }
    
    public void removeCard(Card card){
        hand.remove(card);
    }
    
    public void draw(){
        addCard(Game.draw());
    }
    
    public ArrayList<Card> getHand(){
        return hand;
    }
    
    public int cardsLeft(){
        return hand.size();
    }
    
    public void draw(int amount){
        for (int i = 0; i < amount; i++){
            addCard(Game.draw());
        }
    }
    
    public Card getRandomCard(){
        Card c = null;
        if (hand.size() > 0){
            c = hand.get((int) (Math.floor(Math.random()*hand.size())));
            hand.remove(c);
        }
        return c;
    }
    
    public void addAction(Action a){
        actions.add(a);
    }
    
    public abstract void drawPhase();
    
    public abstract boolean canPlayAsEvent(Card c);

    public int getReserves() {
        return reserves;
    }

    public void setReserves(int reserves) {
        this.reserves = reserves;
    }
    
    public boolean canUseReserves(int ops){
        return ops < 3 && reserves > 0;
    }
    
    public boolean canIncreaseReserves(int ops){
        return ops < 3 && reserves < 2;
    }
    
    public void modifyReserves(int change){
        reserves += change;
        if (reserves > 2){
            reserves = 2;
        }
    }
    
    public void chooseEventOrOps(Card c){
        int userInput = inputLoop("Do you want", "The event to happen first", "To play ops first");
            if (userInput == 1) {
                Game.playCard(c);
                howToPlay(c);
            } else {
                howToPlay(c);
                Game.playCard(c);
            }
    }
    
    public abstract void playCard(Card c);
    
    public abstract void playForOps(int ops);
}
