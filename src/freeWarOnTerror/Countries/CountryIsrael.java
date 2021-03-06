/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror.Countries;

import static freeWarOnTerror.Game.isPostureHard;
import static freeWarOnTerror.Game.modifyPrestige;
import static freeWarOnTerror.Game.removeCardFromPlay;
import freeWarOnTerror.NonMuslimCountry;
import static freeWarOnTerror.helpers.CardLookup.ABBAS;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.Die.rollDie;
import static freeWarOnTerror.helpers.Posture.HARD;

/**
 *
 * @author Emil
 */
public class CountryIsrael extends NonMuslimCountry {
    
    public CountryIsrael(CountryLookup c){
        super(c);
        noLongerNeedsTesting();
        setPosture(HARD);
    }


    
    @Override
    public void rollPosture(){
        setPosture(HARD);
    }
    
    @Override
    public void warOfIdeas(){
        int die = rollDie();
        if (isPostureHard() && die > 4){
            modifyPrestige(1);
        }
        else if (!isPostureHard() && die < 5){
            modifyPrestige(1);
        }
    }
    
    @Override
    public void resolvePlots(){
        if (hasPlots()){
            removeCardFromPlay(ABBAS);
        }
        super.resolvePlots();
    }

}
