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
    
    
    static public int rollDie(){
        return (int) Math.floor(Math.random()*6+1);
    }
}
