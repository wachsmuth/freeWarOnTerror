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
public class Cell {
    private Boolean active = false;

    public void setActive(Boolean active){
        this.active = active;
    }
    
    public Boolean getActive(){
        return active;
    }
}
