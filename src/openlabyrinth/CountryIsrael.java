/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openlabyrinth;

/**
 *
 * @author Emil
 */
public class CountryIsrael extends NonMuslimCountry {

    public CountryIsrael(String name, int governance) {
        super(name, governance, governance);
        noLongerNeedsTesting();
    }
    
    @Override
    public int getGovernance(){
        return 1;
    }
    
    public void setGovernance(){
        
    }
}
