package freeWarOnTerror;

import static freeWarOnTerror.helpers.CONSTANTS.GOOD;
import static freeWarOnTerror.helpers.CONSTANTS.WMD;
import static freeWarOnTerror.helpers.Die.rollPosture;

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
    private int posture = 0; //soft = 1, hard = 2
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

    public int getRecruit() {
        return recruit;
    }

    @Override
    public int getGovernance() {
        return governance;
    }

    @Override
    public void testCountry() {
        if (4 >= freeWarOnTerror.helpers.Die.rollDie()) {
            posture = 1;
        } else {
            posture = 2;
        }
        noLongerNeedsTesting();
    }

    @Override
    public Boolean canWarOfIdeas(int ops) {
        return ops >= governance;
    }

    public Boolean getSchengen() {
        return schengen;
    }

    @Override
    public void resolvePlots() {
        for (Plot p : getPlots()) {
            //Posture
            setPosture(rollPosture());
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
                Game.modifyPrestige(-1);
                if (p.getType() == WMD) {
                    Game.setPrestige(1);
                }
            }
        }
    }

}
