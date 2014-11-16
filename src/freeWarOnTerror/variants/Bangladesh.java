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
package freeWarOnTerror.variants;

import freeWarOnTerror.abClasses.Variant;

/**
 *
 * @author Emil
 */
public class Bangladesh extends Variant {

    @Override
    protected String getDescription() {
        return "Bangladesh";
    }

    @Override
    public void activate() {
        /*getAllCountries().add(new MuslimCountry("Bangladesh", BANGLADESH, 1, false, false));
        getCountry(BANGLADESH).addAdjacentCountry(getCountry(INDIA));
        getCountry(BANGLADESH).addAdjacentCountry(getCountry(CountryLookup.INDONESIA));
        getCountry(CountryLookup.INDONESIA).addAdjacentCountry(getCountry(BANGLADESH));
        getCountry(INDIA).addAdjacentCountry(getCountry(BANGLADESH)); DEBUG */ 
    }
}
