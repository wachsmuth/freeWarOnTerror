/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror;

/**
 *
 * @author Emil
 */
public class Die {
    
    
    public static int rollDie(){
        return (int) Math.floor(Math.random()*6+1);
    }
    
    public static int rollD6(int amount){
        long reVal = 0;
        for (int i = 0; i < amount; i++){
            reVal += Math.floor(Math.random()*6+1);
        }
        return (int) reVal;
        }
    }
