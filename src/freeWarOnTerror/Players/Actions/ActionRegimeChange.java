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
package freeWarOnTerror.Players.Actions;

import static freeWarOnTerror.Game.deployTroops;
import static freeWarOnTerror.Game.getMuslimCountries;
import static freeWarOnTerror.Game.getTrack;
import static freeWarOnTerror.Game.isPostureHard;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Location;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class ActionRegimeChange extends Action {
    
    public ActionRegimeChange(){
        super("Use ops for regime change");
    }
    
    @Override
    public boolean canDoAction(Card c){
        
        if (c.getOps() < 3){
            return false;
        }
        if (!isPostureHard()){
            return false;
        }
        boolean origin = false;
        boolean destination = false;
        if (getTrack().troopAmount() > 5){
            origin = true;
        }
        for (MuslimCountry country : getMuslimCountries()){
            if (country.canRegimeChangeFrom()){
                origin = true;
            }
            if (country.canRegimeChange()){
                destination = true;
            }
        }
        return origin && destination;
    }
    
    @Override
    public void performAction(Card c){
        ArrayList<MuslimCountry> candidates = new ArrayList<>();
        ArrayList<Location> origins = new ArrayList<>();
        if (getTrack().troopAmount() > 5){
            origins.add(getTrack());
        }
        for (MuslimCountry country : getMuslimCountries()){
            if (country.canRegimeChange()){
                candidates.add(country);
            }
            if (country.canRegimeChangeFrom()){
                origins.add(country);
            }
        }
        System.out.println("Choose where to regime change from:");
        Location origin = inputLoop(origins);
        System.out.println("Choose country to regime change: ");
        MuslimCountry destination = inputLoop(candidates);
        int[] arr = new int[origin.noCanDeployFrom()-5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+6;
        }
        System.out.println("Choose amount of troops to move in: ");
        int amount = inputLoop(arr);
        deployTroops(origin, destination, amount);
        destination.regimeChange();
        
    }
    
    
    
}
