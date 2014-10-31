package freeWarOnTerror;

import freeWarOnTerror.helpers.Die;
import freeWarOnTerror.abClasses.Country;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Emil
 */
public class NonMuslimCountry extends Country {

    private final int governance;
    private int posture = 0; //soft = 1, hard = 2
    private final int recruit;
    private final Boolean schengen;

    public NonMuslimCountry(String name, int governance, int recruit, Boolean schengen) {
        super(name);
        this.governance = governance;
        this.recruit = recruit;
        this.schengen = schengen;
    }
    
    public void setPosture(int gwot){
        posture = gwot;
        noLongerNeedsTesting();
    }
    
    public int getPosture(){
        return posture;
    }
    
    public int getRecruit(){
        return recruit;
    }
    
    @Override
    public int getGovernance(){
        return governance;
    }
    
    @Override
    public void testCountry(){
        if (4 >= Die.rollDie()){
            posture = 1;
        }
        else {
            posture = 2;
        }
        noLongerNeedsTesting();
    }
    
    @Override
    public Boolean canWarOfIdeas(int ops){
        return ops >= governance;
    }
    
    public Boolean getSchengen(){
        return schengen;
    }
    
}
