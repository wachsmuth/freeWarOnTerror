/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openlabyrinth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Emil
 */
public class Game {
    
    private int prestige = 7;
    private int funding = 9;
    private final Boolean[] markedCards = new Boolean[22];
    private final ArrayList<Country> countries = new ArrayList<>();
    
    public Game(){
        Arrays.fill(markedCards, false);
    }
    
    public Boolean ifCardActive(int n){
        return markedCards[n];
    }
    
    public void setCardInPlay(int n, Boolean bool){
        markedCards[n] = bool;
    }
    
    public void connectCountries(Country c1, Country c2){
        c1.addAdjacentCountry(c2);
        c2.addAdjacentCountry(c1);
    }
    
    private void createWorld(){
        
    }
    
    public void modifyPrestige(int change){
        prestige = prestige + change;
        if (prestige < 1){
            prestige = 1;
        }
        else if (prestige > 12){
            prestige = 12;
        }
    }
    
    public void modifyFunding(int change){
        funding = funding + change;
        if (funding < 1){
            funding = 1;
        }
        else if (funding > 9){
            funding = 9;
        }
    }
}
