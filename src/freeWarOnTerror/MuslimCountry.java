/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror;

/**
 *
 * @author Emil
 *
 *
 */
public class MuslimCountry extends Country {

    private final int resources;
    private final Boolean oilCountry;
    private final Boolean shiaMix;
    private Boolean besiegedRegime = false;
    private int regimeChange = 0;
    private int aid = 0;
    private int governance = 0; //1 = Good, 2 = Fair, 3 = Poor, 4 = Islamist Rule
    private int alignment = 0; //1 = Ally, 2 = Neutral, 3 = Adversary

    public MuslimCountry(String name, int id, int resources, Boolean oilCountry, Boolean shiaMix) {
        super(name, id);
        this.resources = resources;
        this.oilCountry = oilCountry;
        this.shiaMix = shiaMix;
    }

    @Override
    public void testCountry() {
        alignment = 2;
        int die = Die.rollDie();
        if (die >= 5) {
            governance = 2;
        } else {
            governance = 3;
        }
        noLongerNeedsTesting();
    }

    public void setGovernance(int governance) {
        this.governance = governance;
        noLongerNeedsTesting();
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
        noLongerNeedsTesting();
    }
    
    @Override
    public int getGovernance(){
        return governance;
    }
    
    @Override
    public Boolean canDeploy(){
        return alignment == 1 && governance < 4;
    }
    
    public int getAlignment(){
        return alignment;
    }

    public Boolean getShiaMix(){
        return shiaMix;
    }
    
    public Boolean getBesiegedRegime(){
        return besiegedRegime;
    }
    
    public void setBesiegedRegime(Boolean bool){
        besiegedRegime = bool;
    }
    
    public int getRegimeChange(){
        return regimeChange;
    }
    
    @Override
    public Boolean canWarOfIdeas(int ops){
        return ops >= governance && alignment < 3;
    }
}
