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
public class Cell {
    private Boolean active = false;
    private boolean idle = true;

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public void setActive(Boolean active){
        this.active = active;
    }
    
    public Boolean getActive(){
        return active;
    }
    
    
}
