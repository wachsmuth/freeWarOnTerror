package freeWarOnTerror;

import static freeWarOnTerror.Game.isPostureHard;
import static freeWarOnTerror.Game.modifyPrestige;
import static freeWarOnTerror.helpers.AppendToString.appendString;
import static freeWarOnTerror.helpers.CONSTANTS.GOOD;
import static freeWarOnTerror.helpers.CONSTANTS.WMD;
import freeWarOnTerror.helpers.Die;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Emil
 */
public class NonMuslimCountry extends freeWarOnTerror.abClasses.Country {

    private final int governance;
    private int posture = 0; //soft = -1, hard = 1
    private final int recruit;
    private final Boolean schengen;

    public NonMuslimCountry(String name, int id, int governance, int recruit, Boolean schengen) {
        super(name, id);
        this.governance = governance;
        this.recruit = recruit;
        this.schengen = schengen;
    }

    public void setPosture(int gwot) {
        posture = gwot;
        noLongerNeedsTesting();
    }

    public int getPosture() {
        return posture;
    }

    @Override
    public int getRecruit() {
        return recruit;
    }

    @Override
    public int getGovernance() {
        return governance;
    }

    @Override
    public void testCountry() {
        if (getNeedsTesting()){
        rollPosture();
        noLongerNeedsTesting();
        }
    }

    public void rollPosture() {
        if (4 >= Die.rollDie()) {
            posture = -1;
        } else {
            posture = 1;
        }
        noLongerNeedsTesting();
    }

    @Override
    public Boolean canWarOfIdeas(int ops) {
        return ops >= governance;
    }

    @Override
    public void warOfIdeas() {
        rollPosture();
        if (isPostureHard() && posture == 1) {
            modifyPrestige(1);
        } else if (!isPostureHard() && posture == -1) {
            modifyPrestige(1);
        }

    }

    public Boolean getSchengen() {
        return schengen;
    }

    @Override
    public void resolvePlots() {
        for (Plot p : super.getPlots()) {
            //Posture
            rollPosture();
            if (schengen == true) {
                //DEBUG, jihadist can select two schengen countries and change posture
            }
            if (p.getType() == WMD) {
                //DEBUG, jihadist can reroll twice
            }

            //Funding
            if (governance == GOOD) {
                Game.modifyFunding(p.getType() * 2);
            } else {
                Game.modifyFunding(p.getType());
            }

            //PRESTIGE
            if (troopAmount() > 0) {
                if (p.getType() == WMD) {
                    Game.setPrestige(1);
                } else {
                    Game.modifyPrestige(-1);
                }
            }
        }
    }

    @Override
    public String toString() {
        String string = super.toString();
        
            if (governance == 1){
                string += "Good";
            }
            else {
                string += "Fair";
            }
            string += " ";
            if (needsTesting()){
                string += "Untested";
            }
            else if (posture == 1){
                string += "Hard";
            }
            else {
                string += "Soft";
            }
        
        return appendString(string) + moveablesString();
    }
}
