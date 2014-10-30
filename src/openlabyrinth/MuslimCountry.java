/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openlabyrinth;

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
    private Boolean regimeChange = false;
    private int aid = 0;
    private int governance = 0; //1 = Good, 2 = Fair, 3 = Poor, 4 = Islamist Rule
    private int alignment = 0; //1 = Ally, 2 = Neutral, 3 = Adversary

    public MuslimCountry(String name, int resources, Boolean oilCountry, Boolean shiaMix) {
        super(name);
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
    
    @Override
    public Boolean getRegimeChange(){
        return regimeChange;
    }
}
