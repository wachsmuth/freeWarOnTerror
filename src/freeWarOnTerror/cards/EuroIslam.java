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
import static freeWarOnTerror.Game.modifyFunding;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.BENELUX;
import static freeWarOnTerror.helpers.CONSTANTS.EUROISLAM;
import static freeWarOnTerror.helpers.InputLoop.setCountryPosture;

/**
 *
 * @author Emil
 */
public class EuroIslam extends Card {
    
    public EuroIslam(){
        super("Euro-Islam", 2, 2, true, false, EUROISLAM);
    }
    
    @Override
    public void playEvent(){
        setCountryPosture(BENELUX);
        if (!anyIslamistRule()){
            modifyFunding(-1);
        }
    }
}
