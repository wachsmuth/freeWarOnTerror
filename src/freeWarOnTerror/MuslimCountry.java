/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror;

import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.CONSTANTS.GOOD;
import static freeWarOnTerror.helpers.CONSTANTS.WMD;
import freeWarOnTerror.helpers.Die;

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
        if (getNeedsTesting()) {
            rollGovernance();
        }
    }

    public void rollGovernance() {
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

    public void shiftAlignment(int change) {
        alignment = alignment + change;
        if (alignment < 1) {
            alignment = 0;
        } else if (alignment > 3) {
            alignment = 3;
        }
    }

    public void shiftGovernance(int change) {
        if (governance != 4) {
            governance = governance + change;
            if (governance < 1) {
                governance = 1;
            } else if (governance > 3) {
                governance = 3;
            }
        }
    }
    
    @Override
    public void setGovernanceAndAlignment(int governance, int alignment){
        this.governance = governance;
        this.alignment = alignment;
    }

    @Override
    public int getGovernance() {
        return governance;
    }

    @Override
    public Boolean canDeploy() {
        return alignment == 1 && governance < 4;
    }

    public int getAlignment() {
        return alignment;
    }

    public Boolean getShiaMix() {
        return shiaMix;
    }

    public Boolean getBesiegedRegime() {
        return besiegedRegime;
    }

    public void setBesiegedRegime(Boolean bool) {
        besiegedRegime = bool;
    }

    public int getRegimeChange() {
        return regimeChange;
    }

    public void setRegimeChange(int regimeChange) {
        this.regimeChange = regimeChange;
    }

    @Override
    public Boolean canWarOfIdeas(int ops) {
        return ops >= governance && alignment < 3;
    }

    @Override
    public void resolvePlots() {
        for (Plot p : getPlots()) {

            //Funding
            if (governance == GOOD) {
                Game.modifyFunding(2);
            } else {
                Game.modifyFunding(1);
            }

            //prestige
            if (troopAmount() > 0) {
                if (p.getType() == WMD) {
                    Game.setPrestige(1);
                } else {
                    Game.modifyPrestige(-1);
                }
            }

            //Governance and Aid
            //DEBUG
        }
    }

    public void addAid() {
        aid++;
    }
    
    public Boolean isAid(){
        return aid > 0;
    }
}
