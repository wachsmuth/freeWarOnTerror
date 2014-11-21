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

import static freeWarOnTerror.Game.getCurrentPlayer;
import static freeWarOnTerror.Game.getMuslimCountries;
import static freeWarOnTerror.Game.getUS;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.helpers.CardLookup;

/**
 *
 * @author Emil
 */
public class IEDs extends Card {
    
    public IEDs(){
        super(CardLookup.IEDS);
    }
    
    @Override
    public Boolean getPlayable(){
        for (MuslimCountry c : getMuslimCountries()){
            if (c.getRegimeChange() > 0 && c.hasCells()){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void playEvent(){
        getCurrentPlayer().discard(getUS().getRandomCard());
    }
}
