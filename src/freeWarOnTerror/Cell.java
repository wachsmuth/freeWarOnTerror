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
    private boolean backlash = false;

    public Cell(Location l) {
        super(l);
    }

    @Override
    public void move(Location l) {
        setActive(false);
        super.move(l);
    }

    public void kill() {
        move(getTrack());
    }

    public boolean isIdle() {
        return idle;
    }

    public boolean isActive() {
        return active;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public boolean isBacklash() {
        return backlash;
    }

    public void setBacklash(boolean backlash) {
        this.backlash = backlash;
    }
}
