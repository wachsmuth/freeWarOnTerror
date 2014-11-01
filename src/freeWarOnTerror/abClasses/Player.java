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
import java.util.ArrayList;
/**
 *
 * @author Emil
 */
public abstract class Player {
    
    private final String name;
    private final ArrayList<Card> hand = new ArrayList<>();
    private final int reserves = 0;
    
    public Player(String name){
        this.name = name;
    }
    
    public void addCard(Card card){
        hand.add(card);
    }
    
    public void draw(){
        addCard(Game.draw());
    }
    
    public int cardsLeft(){
        return hand.size();
    }
    
    public void draw(int amount){
        for (int i = 0; i < amount; i++){
            addCard(Game.draw());
        }
    }
    
    public abstract void drawPhase();
    
}