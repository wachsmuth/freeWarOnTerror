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
import static freeWarOnTerror.Game.isPostureHard;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.CAUCASUS;
import static freeWarOnTerror.helpers.CONSTANTS.CENTRALASIA;
import static freeWarOnTerror.helpers.CONSTANTS.EXKGB;
import static freeWarOnTerror.helpers.CONSTANTS.RUSSIA;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;

/**
 *
 * @author Emil
 */
public class ExKGB extends Card{
    
    public ExKGB(){
        super("Ex-KGB", 2, 3, false, false, EXKGB);
    }
    
    @Override
    public void playEvent(){
        if (getCountry(RUSSIA).getCTR()){
            getCountry(RUSSIA).setCTR(false);
        }
        else {
            int input = inputLoop("Choose one of the following", "Set Caucasus to opposite posture of US", "Test and shift Central Asia 1 box toward Adversary");
            if (input == 1){
                NonMuslimCountry caucasus = (NonMuslimCountry) getCountry(CAUCASUS);
                if (isPostureHard()){
                    caucasus.setPosture(-1);
                }
                else {
                    caucasus.setPosture(1);
                }
            }
            else {      
                MuslimCountry centralAsia = (MuslimCountry) getCountry(CENTRALASIA);
                centralAsia.testCountry();
                centralAsia.shiftAlignment(1);
            }
        }
    }
}
