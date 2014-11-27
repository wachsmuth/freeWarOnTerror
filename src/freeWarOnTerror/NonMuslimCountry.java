package freeWarOnTerror;

import static freeWarOnTerror.Game.isPostureHard;
import static freeWarOnTerror.Game.modifyPrestige;
import static freeWarOnTerror.helpers.AppendToString.appendString;
import static freeWarOnTerror.helpers.CONSTANTS.WMD;
import freeWarOnTerror.helpers.CountryLookup;
import freeWarOnTerror.helpers.Die;
import freeWarOnTerror.helpers.Governance;
import static freeWarOnTerror.helpers.Governance.GOOD;
import freeWarOnTerror.helpers.Posture;
import static freeWarOnTerror.helpers.Posture.HARD;
import static freeWarOnTerror.helpers.Posture.SOFT;
import static freeWarOnTerror.helpers.Posture.UNASSIGNED;
import java.util.Iterator;

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

    private final Governance governance;
    private final int recruit;
    private final Boolean schengen;
    private Posture posture = UNASSIGNED;

    public NonMuslimCountry(CountryLookup c) {
        super(c);
        this.governance = c.getGovernance();
        this.recruit = c.getRecruit();
        this.schengen = c.isSchengen();
        if (c.isMuslimCountry()){
            throw new RuntimeException("Wrong countryLookup used in init");
        } //Debug remove later
    }
//--------------------------------GETTERS-------------------------------------------------------

    public Posture getPosture() {
        return posture;
    }

    public Boolean getSchengen() {
        return schengen;
    }

//--------------------------------SETTERS-------------------------------------------------------
    public void setPosture(Posture posture) {
        this.posture = posture;
        noLongerNeedsTesting();
    }

    public void rollPosture() {
        if (4 >= Die.rollDie()) {
            posture = SOFT;
        } else {
            posture = HARD;
        }
        noLongerNeedsTesting();
    }

//--------------------------------OVERRIDES-----------------------------------------------------
    @Override
    public int getRecruit() {
        return recruit;
    }

    @Override
    public Governance getGovernance() {
        return governance;
    }

    @Override
    public void testCountry() {
        if (getNeedsTesting()) {
            rollPosture();
            noLongerNeedsTesting();
        }
    }

    @Override
    public Boolean canWarOfIdeas(int ops) {
        return ops >= governance.getValue();
    }

    @Override
    public void warOfIdeas() {
        rollPosture();
        if (isPostureHard() && posture == HARD) {
            modifyPrestige(1);
        } else if (!isPostureHard() && posture == SOFT) {
            modifyPrestige(1);
        }

    }

    @Override
    public void resolvePlots() {
        Iterator i = getPlots().iterator();
        while (i.hasNext()) {
            Plot p = (Plot) i.next();
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
            //Move plot back to track
            System.out.println("Plot succeeded in: " + getName());
            Plot tempReference = p;
            i.remove();
            tempReference.move(Game.getTrack());
        }
    }

    @Override
    public String toString() {
        String string = super.toString();

        if (governance == GOOD) {
            string += "Good";
        } else {
            string += "Fair";
        }
        string += " ";
        if (needsTesting()) {
            string += "Untested";
        } else if (posture == HARD) {
            string += "Hard";
        } else {
            string += "Soft";
        }

        return appendString(string) + moveablesString() + eventsToString();
    }
}
