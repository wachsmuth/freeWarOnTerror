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

import static freeWarOnTerror.Game.getUS;
import static freeWarOnTerror.Game.rollPrestige;
import static freeWarOnTerror.Game.setPostureHard;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.AXISOFEVIL;
import static freeWarOnTerror.helpers.CONSTANTS.HIZBALLAH;
import static freeWarOnTerror.helpers.CONSTANTS.IRANCARD;
import static freeWarOnTerror.helpers.CONSTANTS.JAYSHALMAHDI;

/**
 *
 * @author Emil
 */
public class AxisOfEvil extends Card {

    public AxisOfEvil() {
        super("Axis of Evil", 3, 3, false, false, AXISOFEVIL);
    }
    
    @Override
    public void playEvent(){
        getUS().discardSpecificCard(JAYSHALMAHDI);
        getUS().discardSpecificCard(IRANCARD);
        getUS().discardSpecificCard(HIZBALLAH);
        setPostureHard(true);
        rollPrestige();
    }
}
