/*
 * Copyright (C) 2014 Emil
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package freeWarOnTerror.Scenarios;

import static freeWarOnTerror.Game.deployTroops;
import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.getTrack;
import static freeWarOnTerror.Game.placeCell;
import static freeWarOnTerror.Game.setFunding;
import static freeWarOnTerror.Game.setPostureHard;
import static freeWarOnTerror.Game.setPrestige;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.abClasses.Scenario;
import static freeWarOnTerror.helpers.CONSTANTS.AFGHANISTAN;
import static freeWarOnTerror.helpers.CONSTANTS.CENTRALASIA;
import static freeWarOnTerror.helpers.CONSTANTS.GULFSTATES;
import static freeWarOnTerror.helpers.CONSTANTS.IRAQ;
import static freeWarOnTerror.helpers.CONSTANTS.LIBYA;
import static freeWarOnTerror.helpers.CONSTANTS.PAKISTAN;
import static freeWarOnTerror.helpers.CONSTANTS.SAUDIARABIA;
import static freeWarOnTerror.helpers.CONSTANTS.SOMALIA;
import static freeWarOnTerror.helpers.CONSTANTS.SYRIA;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDSTATES;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Anaconda extends Scenario {
    
    public Anaconda(){
        super("Anaconda");
    }
    
    @Override
    public void setup(){
        //Markers
        setPrestige(8);
        setPostureHard(true);
        setFunding(6);
        //Governance and alignment
        getCountry(LIBYA).setGovernanceAndAlignment(3, 3);
        getCountry(SYRIA).setGovernanceAndAlignment(2, 3);
        getCountry(IRAQ).setGovernanceAndAlignment(3, 3);
        getCountry(SAUDIARABIA).setGovernanceAndAlignment(3, 1);
        getCountry(GULFSTATES).setGovernanceAndAlignment(2, 1);
        getCountry(PAKISTAN).setGovernanceAndAlignment(3, 1);
        getCountry(AFGHANISTAN).setGovernanceAndAlignment(3, 1);
        getCountry(CENTRALASIA).setGovernanceAndAlignment(3, 1);
        ((MuslimCountry) getCountry(SOMALIA)).setBesiegedRegime(true);
        ((MuslimCountry) getCountry(AFGHANISTAN)).setRegimeChange(1);
        //Cells & troops
        placeCell(getCountry(PAKISTAN));
        placeCell(getCountry(AFGHANISTAN));
        deployTroops(getTrack(), getCountry(SAUDIARABIA), 2);
        deployTroops(getTrack(), getCountry(GULFSTATES), 2);
        deployTroops(getTrack(), getCountry(AFGHANISTAN), 6);
        
        // DEBUG add support for playing events before the game starts
        // Must put into play events Patriot Act and FATA
        // Must remove from play cards Patriot and Tora Bora
        
        ArrayList<Country> eligibleCountries = new ArrayList<>();
        for (Country c : getAllCountries()){
            if (c.getID() != UNITEDSTATES ){ 
                eligibleCountries.add(c);
            }         
        }
        for (int i = 0;i < 3; i++){
            System.out.println("Choose where to place a cell");
            Country targetCountry = inputLoop(eligibleCountries);
            placeCell(targetCountry);
            eligibleCountries.remove(targetCountry);
        }
    }
}
