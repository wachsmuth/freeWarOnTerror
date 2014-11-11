/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror;

import static freeWarOnTerror.Game.getTrack;
import freeWarOnTerror.abClasses.Location;
import freeWarOnTerror.abClasses.Moveable;

/**
 *
 * @author Emil
 */
public class Cell extends Moveable {

    private Boolean active = false;
    private boolean idle = true;
 

    public Cell(Location l) {
        super(l);
    }

//--------------------------------GETTERS-------------------------------------------------------
    public boolean isIdle() {
        return idle;
    }

    public boolean isActive() {
        return active;
    }

    public Boolean getActive() {
        return active;
    }

//--------------------------------SETTERS-------------------------------------------------------

    public void kill() {
        move(getTrack());
    }
    
    public void disrupt(){
        setIdle(false);
        if (isActive()){
            kill();
        }
        else {
            setActive(true);
        }
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
//--------------------------------OVERRIDES-----------------------------------------------------
    @Override
    public String toString() {
        String string = "";
        if (getActive()){
            string += "Active Cell";
        }
        else {
            string += "Sleeper Cell";
        }
        return string + " Location: " + getLocation().getName();
    }

    @Override
    public void move(Location l) {
        setActive(false);
        super.move(l);
    }
}
