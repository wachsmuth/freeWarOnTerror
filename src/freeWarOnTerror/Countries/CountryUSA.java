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
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.Plot;
import static freeWarOnTerror.helpers.CONSTANTS.WMD;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.Die.rollDie;

/**
 *
 * @author Emil
 */
public class CountryUSA extends NonMuslimCountry {
    
    public CountryUSA(CountryLookup c){
        super(c);
        noLongerNeedsTesting();
    }

    public CountryUSA(String name, int id, int governance) {
        super(name, id, governance, governance, false);
        noLongerNeedsTesting();
    }

    @Override
    public Boolean canWarOfIdeas(int ops) {
        return false;
    }

    @Override
    public void resolvePlots() {
        for (Plot p : getPlots()) {
            if (p.getType() == WMD) {
                Game.WMDinUS(); //US Loses
                return;
            }
        }
        //DEBUG! Works very differently.
        super.resolvePlots();
    }
    
    @Override
    public void rollPosture(){
        if (rollDie() > 3){
            setPosture(1);
        }
        else {
            setPosture(-1);
        }
    }
    
}
