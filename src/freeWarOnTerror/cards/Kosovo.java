/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror.cards;

import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.Game;

/**
 *
 * @author Emil
 */
public class Kosovo extends Card {
        
        Game game;
        public Kosovo(Game game) {
        super(1,1, false); 
        this.game = game;
    }
        
        @Override
        public void play(){
            game.modifyPrestige(1);
        }
}
