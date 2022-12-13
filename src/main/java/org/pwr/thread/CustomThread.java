package org.pwr.thread;

import org.pwr.updater.StateUpdater;
import org.pwr.laboratory.Laboratory;

abstract public class CustomThread extends Thread {
    private Laboratory laboratory;
    protected int time;
    protected StateUpdater stateUpdater;

    public static boolean simulation;

    public static void setIsSimulation(boolean isSimulation) {
        isSimulation = isSimulation;
    }

    public boolean getIsSimulation() {
        return simulation;
    }

    public CustomThread(String name, int time, Laboratory laboratory) {
        super(name);
        this.time = time;
        this.laboratory = laboratory;
    }

    public void setStateUpdater(StateUpdater stateUpdater) {
        this.stateUpdater = stateUpdater;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

}
