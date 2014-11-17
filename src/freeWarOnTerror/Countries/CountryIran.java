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
package freeWarOnTerror.Countries;

import freeWarOnTerror.Game;
import freeWarOnTerror.Plot;
import static freeWarOnTerror.helpers.AppendToString.appendString;
import static freeWarOnTerror.helpers.CONSTANTS.GOOD;
import freeWarOnTerror.helpers.CountryLookup;
import java.util.Iterator;

/**
 *
 * @author Emil
 */
public class CountryIran extends freeWarOnTerror.abClasses.Country {

    int governance = 2;

    public CountryIran(CountryLookup c){
        super(c);
        noLongerNeedsTesting();
    }
    
    public CountryIran(String name, int id) {
        super(name, id);
        noLongerNeedsTesting();
    }

    @Override
    public String toString() {
        return appendString(getName()) + appendString("Fair") + moveablesString();
    }

    @Override
    public void testCountry() {
    }

    @Override
    public int getGovernance() {
        return governance;
    }

    @Override
    public Boolean canWarOfIdeas(int ops) {
        return false;
    }

    @Override
    public void resolvePlots() {
        Iterator i = getPlots().iterator();
        while (i.hasNext()) {
            Plot p = (Plot) i.next();
            if (governance == GOOD) {
                Game.modifyFunding(2);
            } else {
                Game.modifyFunding(1);
            }
            //Move plot back to track
            System.out.println("Plot succeeded in: " + getName());
            Plot tempReference = p;
            i.remove();
            tempReference.move(Game.getTrack());
        }
    }

    @Override
    public void warOfIdeas() {
    }

}
