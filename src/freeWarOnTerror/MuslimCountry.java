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
import freeWarOnTerror.helpers.Alignment;
import static freeWarOnTerror.helpers.Alignment.ADVERSARY;
import static freeWarOnTerror.helpers.Alignment.ALLY;
import static freeWarOnTerror.helpers.Alignment.NEUTRAL;
import static freeWarOnTerror.helpers.AppendToString.appendString;
import static freeWarOnTerror.helpers.CONSTANTS.WMD;
import freeWarOnTerror.helpers.CountryLookup;
import freeWarOnTerror.helpers.Die;
import static freeWarOnTerror.helpers.Die.rollDie;
import freeWarOnTerror.helpers.Governance;
import static freeWarOnTerror.helpers.Governance.FAIR;
import static freeWarOnTerror.helpers.Governance.GOOD;
import static freeWarOnTerror.helpers.Governance.ISLAMISTRULE;
import static freeWarOnTerror.helpers.Governance.POOR;
import static freeWarOnTerror.helpers.Governance.UNASSIGNED;
import java.util.Iterator;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    private Governance governance = UNASSIGNED; //DEBUG WHY WE DONT GET THIS??
    private Alignment alignment = NEUTRAL; //1 = Ally, 2 = Neutral, 3 = Adversary

    public MuslimCountry(CountryLookup c) {
        super(c);
        this.resources = c.getResources();
        this.oilCountry = c.isOilCountry();
        this.shiaMix = c.isShiaMix();
        if (!c.isMuslimCountry()){
            throw new RuntimeException("Wrong countryLookup used in init");
        } //Debug remove later
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
    @Override
    public boolean canDisrupt(int ops){
        return (hasTroops() || getAlignment() == ALLY) && super.canDisrupt(ops);
    }

//--------------------------------SETTERS-------------------------------------------------------
    public void rollGovernance() {
        alignment = Alignment.NEUTRAL;
        int die = Die.rollDie();
        if (die >= 5) {
            governance = FAIR;
        } else {
            governance = POOR;
        }
        noLongerNeedsTesting();
    }

    public void setGovernance(Governance governance) {
        this.governance = governance;
        if (governance == GOOD || governance == ISLAMISTRULE) {
            setBesiegedRegime(false);
            this.removeAid(50); //REMOVE IT ALL
            setRegimeChange(0);
        }
        noLongerNeedsTesting();
    }

    public void setAlignment(Alignment a) {
        this.alignment = a;
        noLongerNeedsTesting();
    }

    public void shiftAlignment(int change) {
            //Check boundaries.
            if (alignment.id()+change < 1){
                this.alignment = Alignment.ALLY;
            } else if (alignment.id()+change > 3) {
                this.alignment = Alignment.ADVERSARY;
            } else {
                alignment = alignment.values()[alignment.id()+change];
        }
    }

    public void shiftGovernance(int change) {
        throw new NotImplementedException(); //DEBUG FIX
        /*if (governance != ISLAMISTRULE) {
            setGovernance(governance + change);
            if (governance < 1) {
                setGovernance(1);
            } else if (governance > 3) {
                setGovernance(3);
            }
        }*/
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
        if (getGovernance().getValue() < 4 && hasCells()) {
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
        if (rollDie() <= getGovernance().getValue()) {
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
        setAlignment(ADVERSARY);
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
    public boolean canRegimeChangeFrom() {
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
        setAlignment(ALLY);
        rollPrestige();
    }

    public void withdraw() {
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
        for (MuslimCountry c : getAdjacentMuslimCountries()) {
            if (c.getAlignment() == ALLY && c.getGovernance().getValue() == 1) {
                dieRoll++;
                break;
            }
        }
        if (alignment == ALLY && governance == FAIR) {
            dieRoll--;
        }
        dieRoll += getPrestigeModifier();
        dieRoll += getPosturePenalty();
        if (dieRoll == 4 && aid == 0) {
            aid++;
        } else if (dieRoll > 4) {
            if (alignment == Alignment.NEUTRAL) {
                setAlignment(ALLY);
            } else if (alignment == ADVERSARY) {
                shiftGovernance(-1);
                if (governance == GOOD) {
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
            if (p.isBacklash()) {
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

    public void setGovernanceAndAlignment(Governance governance, Alignment alignment) {
        this.governance = governance;
        this.alignment = alignment;
        noLongerNeedsTesting();
    }

    @Override
    public Governance getGovernance() {
        return governance;
    }

    @Override
    public Boolean canDeployTo(int ops) {
        return alignment == ALLY && governance.getValue() < 4 && ops >= governance.getValue();
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

    public Alignment getAlignment() {
        testCountry();
        return alignment;
    }

    @Override
    public Boolean canWarOfIdeas(int ops) {
        if (governance == GOOD && alignment == ALLY) {
            return false;
        } else if (needsTesting() && ops == 1) {
            return false;
        }
        return ops >= governance.getValue() && alignment.id() < 3;
    }

    @Override
    public String toString() {
        String string = super.toString();
        if (needsTesting()) {
            string = string + "Untested";
        } else {
            String gov = "";
            if (governance == GOOD) {
                gov += "Good";
            } else if (governance == FAIR) {
                gov += "Fair";
            } else if (governance == POOR) {
                gov += "Poor";
            } else if (governance == ISLAMISTRULE) {
                gov += "Islamist";
            }
            gov += " ";
            if (alignment == ALLY) {
                gov += "Ally";
            } else if (alignment == NEUTRAL) {
                gov += "Neutral";
            } else if (alignment == ADVERSARY) {
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
            return governance.getValue();
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
