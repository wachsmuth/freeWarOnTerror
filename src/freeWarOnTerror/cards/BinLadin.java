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

import static freeWarOnTerror.Game.anyIslamistRule;
import static freeWarOnTerror.Game.getCurrentPlayer;
import static freeWarOnTerror.Game.getJihadist;
import static freeWarOnTerror.Game.isCardInPlay;
import static freeWarOnTerror.Game.modifyFunding;
import static freeWarOnTerror.Game.modifyPrestige;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.ALANBAR;
import static freeWarOnTerror.helpers.CONSTANTS.BINLADIN;
import static freeWarOnTerror.helpers.CONSTANTS.FATA;

/**
 *
 * @author Emil
 */
public class BinLadin extends Card {
        
    public BinLadin(){
        super("Bin Ladin", 3, 1, false, false, BINLADIN);
    }
    
    @Override
    public Boolean getPlayable(){
        if (getCurrentPlayer() == getJihadist()){
            return true;
        }
        return !anyIslamistRule() && !isCardInPlay(FATA) && isCardInPlay(ALANBAR);
    }
    
    @Override
    public void playEvent(){
        if (getCurrentPlayer() == getJihadist()){
            if (anyIslamistRule()){
                modifyPrestige(-4);
            }
            else {
                modifyPrestige(-2);
            }
        }
        else {
            modifyFunding(-4);
            setRemoved(true);
        }
    }
}