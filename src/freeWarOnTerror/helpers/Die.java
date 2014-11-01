/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror.helpers;

/**
 *
 * @author Emil
 */
public class Die {

    public static int rollDie() {
        return (int) Math.floor(Math.random() * 6 + 1);
    }

    public static int prestigeRoll() {
        int smallestDie = smallestDie();
        if (Math.floor(Math.random() * 6 + 1) >= 5) {
            return smallestDie;
        }
        return -smallestDie;
    }

    //Smallest die of 2
    public static int smallestDie() {
        int smallestDie = (int) Math.floor(Math.random() * 6 + 1);
        int newDie = (int) Math.floor(Math.random() * 6 + 1);
        if (newDie > smallestDie) {
            smallestDie = newDie;
        }
        return smallestDie;
    }
    
    public static int rollPosture(){
        //DEBUG add it
        return 0;
    }

}
