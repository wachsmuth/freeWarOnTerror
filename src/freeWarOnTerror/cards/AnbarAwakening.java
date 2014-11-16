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

import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.modifyPrestige;
import static freeWarOnTerror.Game.removeCardFromPlay;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.ALANBAR;
import static freeWarOnTerror.helpers.CONSTANTS.ANBARAWAKENING;
import static freeWarOnTerror.helpers.CONSTANTS.IRAQ;
import static freeWarOnTerror.helpers.CONSTANTS.SYRIA;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class AnbarAwakening extends Card {
    
    public AnbarAwakening(){
        super("Anbar Awakening", 2, 2, false, true, ANBARAWAKENING, IRAQ, SYRIA);
    }
    
    @Override
    public Boolean getPlayable(){
        return getCountry(CountryLookup.IRAQ).hasTroops() || getCountry(CountryLookup.SYRIA).hasTroops();
    }
    
    @Override
    public void playEvent(){
        if (getCountry(CountryLookup.IRAQ).hasTroops() && getCountry(CountryLookup.SYRIA).hasTroops()){
            System.out.println("Choose a country for aid");
            ArrayList<MuslimCountry> iraqAndSyria = new ArrayList<>();
            iraqAndSyria.add((MuslimCountry) getCountry(CountryLookup.IRAQ));
            iraqAndSyria.add((MuslimCountry) getCountry(CountryLookup.SYRIA));
            inputLoop(iraqAndSyria).addAid();
        }
        else if (getCountry(CountryLookup.IRAQ).hasTroops()){
            MuslimCountry iraq = (MuslimCountry) getCountry(CountryLookup.IRAQ);
            iraq.addAid();
        }
        else {
            MuslimCountry syria = (MuslimCountry) getCountry(CountryLookup.SYRIA);
            syria.addAid();
        }
        modifyPrestige(1);
        removeCardFromPlay(ALANBAR);
    }
}
