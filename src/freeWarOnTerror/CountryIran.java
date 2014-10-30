/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror;

/**
 *
 * @author Emil
 */
public class CountryIran extends Country {
    
    int governance = 2;
        public CountryIran(String name) {
        super(name);
        noLongerNeedsTesting();
    }
     
    @Override
     public void testCountry(){
         
     }   
        
    @Override
      public int getGovernance(){
          return 2;
      }
    
}
