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
package freeWarOnTerror.Players;

import freeWarOnTerror.abClasses.Action;
import freeWarOnTerror.abClasses.Card;

/**
 *
 * @author Emil
 */
public class ActionWarOfIdeas extends Action {
    
    public ActionWarOfIdeas(){
        super("Use ops for War of Ideas");
    }
    
    @Override
    public boolean canDoAction(Card c){
        return true;
    }
    
    @Override
    public void performAction(Card c){
        //DEBUG to do
    }
}
