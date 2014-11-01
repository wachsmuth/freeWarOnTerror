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
        public Kosovo(Game game) {
        super("Kosovo", 1,1, false); 
    }
        
        @Override
        public void play(){
            Game.modifyPrestige(1);
        }
}
