/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror.cards;

import freeWarOnTerror.Card;
import freeWarOnTerror.Game;

/**
 *
 * @author Emil
 */
public class Sanctions extends Card {

    Game game;

    public Sanctions(Game game) {
        super(1, 1);
        this.game = game;
    }
    
    @Override
    public Boolean getPlayable(){
        return game.ifCardActive(10);
    }

    @Override
    public void play() {
        game.modifyFunding(-2);
    }
}
