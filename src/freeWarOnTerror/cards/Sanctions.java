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

import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.Game;

/**
 *
 * @author Emil
 */
public class Sanctions extends Card {

    public Sanctions() {
        super(1, 2, true);
    }
    
    @Override
    public Boolean getPlayable(){
        return Game.ifCardActive(10);
    }

    @Override
    public void play() {
        Game.modifyFunding(-2);
    }
}
