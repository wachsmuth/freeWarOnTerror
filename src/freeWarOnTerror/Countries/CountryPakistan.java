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
import freeWarOnTerror.MuslimCountry;

/**
 *
 * @author Emil
 */
public class CountryPakistan extends MuslimCountry {
    
    public CountryPakistan(String name, int id, int resources, boolean oilCountry, boolean shiaMix){
        super(name, id, resources, oilCountry, shiaMix);
    }
    
    @Override
    public boolean canMinorJihad(){
        if (isCardInPlay("Benazir Bhutto")){
            return false;
        }
        return super.canMinorJihad();
    }
    
    @Override
    public boolean canMajorJihad(int ops){
        if (isCardInPlay("Benazir Bhutto")){
            return false;
        }
        return super.canMajorJihad(ops);
    }
    
    @Override
    public boolean canDisrupt(int ops){
        if (getRegimeChange() == 0 && isCardInPlay("FATA")){
            return false;
        }
        return super.canDisrupt(ops);
    }
}