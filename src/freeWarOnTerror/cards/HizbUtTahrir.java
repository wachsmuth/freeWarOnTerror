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

import static freeWarOnTerror.Game.getTroops;
import static freeWarOnTerror.Game.modifyFunding;
import freeWarOnTerror.abClasses.Card;

/**
 *
 * @author Emil
 */
public class HizbUtTahrir extends Card {
    
    public HizbUtTahrir(){
        super("Hizb Ut-Tahrir", 1, 1, false);
    }
    
    @Override
    public void play(){
        if (getTroops() > 10){
            modifyFunding(-2);
        }
        else if(getTroops() <= 5){
            modifyFunding(2);
        }
    }
}
