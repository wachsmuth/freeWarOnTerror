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
import static freeWarOnTerror.Game.isCardInPlay;
import static freeWarOnTerror.Game.removeCardFromPlay;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.PAKISTAN;

/**
 *
 * @author Emil
 */
public class PakistaniOffensive extends Card {
    
    public PakistaniOffensive(){
        super("Pakistani Offensive", 3, 2, false);
    }
    
    @Override
    public Boolean getPlayable(){
        MuslimCountry pakistan = (MuslimCountry) getCountry(PAKISTAN);
        return pakistan.getAlignment() == 1 && isCardInPlay("FATA");
    }
    
    @Override
    public void play(){
        removeCardFromPlay("FATA");
    }
}