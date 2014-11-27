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
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import static freeWarOnTerror.helpers.Posture.HARD;
import static freeWarOnTerror.helpers.Posture.SOFT;

/**
 *
 * @author Emil
 */
public class ExKGB extends Card{
    
    public ExKGB(){
        super(CardLookup.EXKGB);
    }
    
    @Override
    public void playEvent(){
        if (getCountry(CountryLookup.RUSSIA).getCTR()){
            getCountry(CountryLookup.RUSSIA).setCTR(false);
        }
        else {
            int input = inputLoop("Choose one of the following", "Set Caucasus to opposite posture of US", "Test and shift Central Asia 1 box toward Adversary");
            if (input == 1){
                NonMuslimCountry caucasus = (NonMuslimCountry) getCountry(CountryLookup.CAUCASUS);
                if (isPostureHard()){
                    caucasus.setPosture(SOFT);
                }
                else {
                    caucasus.setPosture(HARD);
                }
            }
            else {      
                MuslimCountry centralAsia = (MuslimCountry) getCountry(CountryLookup.CENTRALASIA);
                centralAsia.testCountry();
                centralAsia.shiftTowardsAdversary();
            }
        }
    }
}
