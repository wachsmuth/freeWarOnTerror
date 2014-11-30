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

import freeWarOnTerror.Cell;
import freeWarOnTerror.Game;
import static freeWarOnTerror.Game.getAllCountries;
import freeWarOnTerror.Plot;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.Die.rollLessThanGovernance;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public class ActionPlot extends Action {

    public ActionPlot(){
        super("Use ops to plot");
    }
    
    @Override
    public boolean canDoAction(Card c) {
        if (plotsLeft() == false ) {
            return false;
        }
        for (Country country : getAllCountries()) {
            if (country.canPlot()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void performAction(Card c){
        for (int i = 0; i < c.getOps(); i++){
            List<Country> eligibleCountries = getEligibles();
            if (eligibleCountries.isEmpty()){
                System.out.println("No more places to plot");
                return;
            }
            System.out.println("Plot in which country?");
            Country plotPlace = inputLoop(eligibleCountries);
            Cell terrorCell = plotPlace.pickIdleCell();
            placePlot(plotPlace, terrorCell);
        }
        
        //For each plot
        
        //DEBUG make cells not idle!
        
    }
    
    public List<Country> getEligibles(){
        ArrayList<Country> reList = new ArrayList<>();
        for (Country country : getAllCountries()) {
            if (country.canPlot()) {
                reList.add(country);
            }
        }
        return reList;
    }
    
    public void placePlot(Country place, Cell c){
        //Choose plot
        Plot p = inputLoop(availablePlots());
        if (rollLessThanGovernance(place)){
            place.placePlot(p);
            System.out.println("Plot placed!");
        }
        else {
            System.out.println("Failed!");
        }
        
    }
    
    public List<Plot> availablePlots(){
        return Game.getTrack().getPlots();
    }
    
    public boolean plotsLeft(){
        //Debug, are there any plots left to place?
        return (!availablePlots().isEmpty());
    }
}
