/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror;

import static freeWarOnTerror.Game.getPosturePenalty;
import static freeWarOnTerror.Game.getPrestigeModifier;
import static freeWarOnTerror.Game.modifyFunding;
import static freeWarOnTerror.Game.rollPrestige;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.AppendToString.appendString;
import static freeWarOnTerror.helpers.CONSTANTS.A_ADVERSARY;
import static freeWarOnTerror.helpers.CONSTANTS.GOOD;
import static freeWarOnTerror.helpers.CONSTANTS.ISLAMISTRULE;
import static freeWarOnTerror.helpers.CONSTANTS.POOR;
import static freeWarOnTerror.helpers.CONSTANTS.WMD;
import freeWarOnTerror.helpers.CountryLookup;
import freeWarOnTerror.helpers.Die;
import static freeWarOnTerror.helpers.Die.rollDie;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Emil
 *
 *
 */
public class MuslimCountry extends Country {

    private final int resources;
    private final boolean oilCountry;
    private final Boolean shiaMix;
    private boolean besiegedRegime = false;
    private int regimeChange = 0; //0 is no change, 1 is tan change, 2 is green change
    private int aid = 0;
    private int governance = 0; //1 = Good, 2 = Fair, 3 = Poor, 4 = Islamist Rule
    private int alignment = 0; //1 = Ally, 2 = Neutral, 3 = Adversary

    public MuslimCountry(CountryLookup c){
        super(c);
        this.resources = c.getResources();
        this.oilCountry = c.isOilCountry();
        this.shiaMix = c.isShiaMix();
    }
    public MuslimCountry(String name, int id, int resources, boolean oilCountry, boolean shiaMix) {
        super(name, id);
        this.resources = resources;
        this.oilCountry = oilCountry;
        this.shiaMix = shiaMix;
    }

//--------------------------------GETTERS-------------------------------------------------------
    public int getResources() {
        return resources + Game.getCurrentTurn().isOilSpike();
    }

    public Boolean getShiaMix() {
        return shiaMix;
    }

    public int getRegimeChange() {
        return regimeChange;
    }

    public Boolean hasAid() {
        return aid > 0;
    }

    public Boolean getBesiegedRegime() {
        return besiegedRegime;
    }

//--------------------------------SETTERS-------------------------------------------------------
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

    @Override
    public void setGovernance(int governance) {
        this.governance = governance;
        if (governance == GOOD || governance == ISLAMISTRULE) {
            setBesiegedRegime(false);
            this.removeAid(50); //REMOVE IT ALL
            setRegimeChange(0);
        }
        noLongerNeedsTesting();
    }

    @Override
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
            setGovernance(governance + change);
            if (governance < 1) {
                setGovernance(1);
            } else if (governance > 3) {
                setGovernance(3);
            }
        }
    }

    public void setBesiegedRegime(Boolean bool) {
        besiegedRegime = bool;
    }

    public void setRegimeChange(int regimeChange) {
        this.regimeChange = regimeChange;
    }

    public void addAid() {
        aid++;
    }

    public void removeAid(int n) {
        aid += -n;
        if (aid < 0) {
            aid = 0;
        }
    }

    //--------------------------------JIHAD-------------------------------------------------------
    public boolean canMajorJihad(int ops) {
        if (besiegedRegime) {
            return governance == POOR && troopAmount() + 5 <= cellAmount();
        } else if (ops > 1) {
            return governance == POOR && troopAmount() + 5 <= cellAmount();
        }
        return false;
    }

    public boolean canMinorJihad() {
        if (getGovernance() < 4 && hasCells()) {
            for (Cell c : getCells()) {
                if (c.isIdle()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void attemptMinorJihad(Cell c) {
        System.out.println("Attempting Jihad in " + getName());
        c.setActive(true);
        if (rollDie() <= getGovernance()) {
            minorJihad();
            System.out.println("Succes!");
        } else {
            System.out.println("Failure..");
            c.kill();
        }
    }//returns succes or failure

    public void attemptMajorJihad(List<Cell> cells, int ops) {
        for (Cell c : getCells()) {
            c.setActive(true);
        }
        int succeses = 0;
        for (int i = 0; i < ops; i++) {
            if (rollDie() < 3) {
                ++succeses;
                removeAid(1);
            } else {
                cells.remove(0); //remove first added cell on failed roll
            }
        }
        if (succeses >= 2 || (succeses >= 1 && getBesiegedRegime() == true)) {
            majorJihad();
        } else if (ops >= 3) {
            majorJihadFailure();
            System.out.println("Major failure");
        } else {
            System.out.println("Failure");
        }

    }

    private void majorJihad() {
        //WE DID IT GUYS
        System.out.println("Success!");
        setGovernance(ISLAMISTRULE);
        setAlignment(A_ADVERSARY);
        if (this.hasTroops()) {
            Game.setPrestige(1);
        }
        Game.modifyFunding(getResources());
    }

    private void minorJihad() {
        shiftGovernance(1);
        removeAid(1);
    }

    private void majorJihadFailure() {
        setBesiegedRegime(true);
        shiftAlignment(1);
    }

//-------------------------------REGIME-CHANGE-------------------------------------------------
    public boolean canRegimeChange() {
        return governance == ISLAMISTRULE;
    }
    
    @Override
    public boolean canRegimeChangeFrom(){
        if (!hasTroops()) {
            return false;
        } else if (regimeChange > 0 && troopAmount() <= cellAmount() + 11) {
            return false;
        }
        return super.canRegimeChangeFrom();
    }

    public void regimeChange() {
        for (Cell c : getCells()) {
            c.setActive(true);
        }
        regimeChange = 2;
        rollGovernance();
        setAlignment(1);
        rollPrestige();
    }
    
    public void withdraw(){
        removeAid(100);
        setBesiegedRegime(true);
        rollPrestige();
    }

//--------------------------------OVERRIDES-----------------------------------------------------
    @Override
    public void warOfIdeas() {
        testCountry();
        //Add and subtract modifiers.
        int dieRoll = rollDie();
        dieRoll += aid;
        for (Country c : getAdjacentCountries()) {
            if (c.getAlignment() == 1 && c.getGovernance() == 1) {
                dieRoll++;
                break;
            }
        }
        if (alignment == 1 && governance == 2) {
            dieRoll--;
        }
        dieRoll += getPrestigeModifier();
        dieRoll += getPosturePenalty();
        if (dieRoll == 4 && aid == 0) {
            aid++;
        } else if (dieRoll > 4) {
            if (alignment == 2) {
                setAlignment(1);
            } else if (alignment == 1) {
                shiftGovernance(-1);
                if (governance == 1) {
                    regimeChange = 0;
                    aid = 0;
                    besiegedRegime = false;
                }
            }
        }
    }

    @Override
    public void resolvePlots() {
        Iterator i = getPlots().iterator();
        while (i.hasNext()) {
            Plot p = (Plot) i.next();
            int change = 1;
            if (governance == GOOD) {
                change = 2;
            }
            if (p.isBacklash()){
                change = -change;
            }
            modifyFunding(change);
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
    public void setGovernanceAndAlignment(int governance, int alignment) {
        this.governance = governance;
        this.alignment = alignment;
        noLongerNeedsTesting();
    }

    @Override
    public int getGovernance() {
        return governance;
    }

    @Override
    public Boolean canDeployTo(int ops) {
        return alignment == 1 && governance < 4 && ops >= governance;
    }

    @Override
    public boolean canDeployFrom() {
        if (!hasTroops()) {
            return false;
        } else if (regimeChange > 0 && troopAmount() <= cellAmount() + 5) {
            return false;
        }
        return true;
    }

    @Override
    public int getAlignment() {
        return alignment;
    }

    @Override
    public Boolean canWarOfIdeas(int ops) {
        if (governance == 1 && alignment == 1) {
            return false;
        } else if (needsTesting() && ops == 1) {
            return false;
        }
        return ops >= governance && alignment < 3;
    }

    @Override
    public String toString() {
        String string = super.toString();
        if (needsTesting()) {
            string = string + "Untested";
        } else {
            String gov = "";
            if (governance == 1) {
                gov += "Good";
            } else if (governance == 2) {
                gov += "Fair";
            } else if (governance == 3) {
                gov += "Poor";
            } else if (governance == 4) {
                gov += "Islamist";
            }
            gov += " ";
            if (alignment == 1) {
                gov += "Ally";
            } else if (alignment == 2) {
                gov += "Neutral";
            } else if (alignment == 3) {
                gov += "Adversary";
            }
            string = string + gov;
        }

        string = appendString(string) + moveablesString();

        if (regimeChange == 2) {
            string += "Green Regime Change ";
        } else if (regimeChange == 1) {
            string += "Tan Regime Change ";
        }
        if (aid > 0) {
            string += aid + " Aid ";
        }
        if (besiegedRegime) {
            string += "Besieged Regime ";
        }
        return string + eventsToString();
    }

    @Override
    public int getRecruit() {
        if (governance == ISLAMISTRULE) {
            return 10;
        } else {
            return governance;
        }
    }

    @Override
    public void testCountry() {
        if (getNeedsTesting()) {
            rollGovernance();
            noLongerNeedsTesting();
        }
    }

}
