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

import static freeWarOnTerror.Game.getAllCountries;
import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class ActionWarOfIdeas extends Action {
    
    public ActionWarOfIdeas(){
        super("Use ops for War of Ideas");
    }
    
    @Override
    public boolean canDoAction(Card c){
        return true;
    }
    
    @Override
    public void performAction(Card c){
       ArrayList<Country> validCountries = new ArrayList<>();
            for (Country country : getAllCountries()) {
                if (country.canWarOfIdeas(c.getOps())) {
                    validCountries.add(country);
                }
            }
            System.out.println("Choose target for War of Ideas");
            inputLoop(validCountries).warOfIdeas();
            
    }
}
