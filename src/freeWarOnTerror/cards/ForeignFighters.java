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

import static freeWarOnTerror.Game.anyRegimeChange;
import static freeWarOnTerror.Game.getMuslimCountries;
import static freeWarOnTerror.Game.placeCell;
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
public class ForeignFighters extends Card {
    
    public ForeignFighters(){
        super(CardLookup.FOREIGNFIGHTERS);
    }
    
    @Override
    public Boolean getPlayable(){
        return anyRegimeChange();
    }
    
    @Override
    public void playEvent(){
        MuslimCountry targetCountry;
        ArrayList<Country> regimeChangeCountries = new ArrayList<>();
        for (MuslimCountry c : getMuslimCountries()){
            if (c.getRegimeChange() > 0){
                regimeChangeCountries.add(c);
            }
        }
        if (regimeChangeCountries.size() == 1){
            targetCountry = (MuslimCountry) regimeChangeCountries.get(0);
        }
        else {
            targetCountry = (MuslimCountry) inputLoop("Pick a regime change country to improve governance in", regimeChangeCountries);
        }
        placeCell(targetCountry);
        placeCell(targetCountry);
        placeCell(targetCountry);
        placeCell(targetCountry);
        placeCell(targetCountry);
        if (targetCountry.hasAid()){
            targetCountry.removeAid(1);
        }
        else {
            targetCountry.setBesiegedRegime(true);
        }
    }
}
