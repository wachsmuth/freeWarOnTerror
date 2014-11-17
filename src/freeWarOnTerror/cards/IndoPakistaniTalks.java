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
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.INDIA;
import static freeWarOnTerror.helpers.CONSTANTS.INDOPAKISTANITALKS;
import static freeWarOnTerror.helpers.CONSTANTS.PAKISTAN;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.InputLoop.setCountryPosture;

/**
 *
 * @author Emil
 */
public class IndoPakistaniTalks extends Card {
    
    public IndoPakistaniTalks(){
        super("Indo-Pakistani Talks", 3, 2, true, true, INDOPAKISTANITALKS, INDIA, PAKISTAN);
    }
    
    @Override
    public Boolean getPlayable(){
        return getCountry(CountryLookup.PAKISTAN).getGovernance() > 0 && getCountry(CountryLookup.PAKISTAN).getGovernance() < 3;
    }
    
    @Override
    public void playEvent(){
        getCountry(CountryLookup.PAKISTAN).setAlignment(1);
        setCountryPosture(CountryLookup.INDIA);
        //TO DO: Remove this card if a plot resolved in India.
    }
}
