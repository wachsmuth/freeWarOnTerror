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
package freeWarOnTerror.cards;

import static freeWarOnTerror.Game.getMuslimCountries;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.abClasses.Country;
import freeWarOnTerror.helpers.CardLookup;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Sistani extends Card {
    
    public Sistani(){
        super(CardLookup.SISTANI);
    }
    
    @Override
    public Boolean getPlayable(){
        for (MuslimCountry c : getMuslimCountries()){
            if (c.hasCells() && c.getRegimeChange() > 0 && c.getShiaMix()){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void playEvent(){
         MuslimCountry targetCountry;
        ArrayList<Country> regimeChangeCountries = new ArrayList<>();
        for (MuslimCountry c : getMuslimCountries()){
            if (c.getRegimeChange() > 0 && c.hasCells() && c.getShiaMix()){
                regimeChangeCountries.add(c);
            }
        }
        if (regimeChangeCountries.size() == 1){
            targetCountry = (MuslimCountry) regimeChangeCountries.get(0);
        }
        else {
            targetCountry = (MuslimCountry) inputLoop("Pick a regime change country to improve governance in", regimeChangeCountries);
        }
        targetCountry.shiftGovernance(-1);
    }
}
