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

import static freeWarOnTerror.Game.isCardInPlay;
import static freeWarOnTerror.Game.modifyPrestige;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.Plot;
import static freeWarOnTerror.helpers.CardLookup.ABUSAYYAF;
import freeWarOnTerror.helpers.CountryLookup;

/**
 *
 * @author Emil
 */
public class CountryPhilippines extends NonMuslimCountry {
    
    public CountryPhilippines(CountryLookup c){
        super(c);
    }



    @Override
    public Boolean canDeployTo(int ops) {
        return isCardInPlay(ABUSAYYAF) && ops >= getGovernance().getValue();
    }
    
    @Override
    public boolean canDeployFrom(){
        return hasTroops();
    }
    
    @Override
    public void placePlot(Plot p){
        if (isCardInPlay(ABUSAYYAF) && cellAmount() + 1 > troopAmount()){
            modifyPrestige(-1);
        }
    }
}
