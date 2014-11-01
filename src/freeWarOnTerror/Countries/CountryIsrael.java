/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror.Countries;

import freeWarOnTerror.NonMuslimCountry;

/**
 *
 * @author Emil
 */
public class CountryIsrael extends NonMuslimCountry {

    public CountryIsrael(String name, int id, int governance) {
        super(name, id, governance, governance, false);
        noLongerNeedsTesting();
    }

}
