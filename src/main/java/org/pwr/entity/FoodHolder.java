package org.pwr.entity;

import org.pwr.updater.StateUpdater;

public class FoodHolder {
    private StateUpdater stateUpdater;
    private Integer food = 0;

    private Integer position;

    public void setStateUpdater(StateUpdater stateUpdater) {
        this.stateUpdater = stateUpdater;
    }
    public synchronized Integer eatFood(Integer requieredFood) {
        if (requieredFood > food) {
            int all = food;
            food = 0;
            stateUpdater.showState();
            return all;
        }
        food -= requieredFood;
        stateUpdater.showState();

        return requieredFood;

    }

    public void setFood(Integer food) {
        this.food = food;
    }

    public String getName() {
        if (food <= 9) {
            return "|  0" + food + "  |";
        }
        return "|  " + food + "  |";
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public StateUpdater getStateUpdater() {
        return stateUpdater;
    }

    public Integer getCurrentFood() {
        return food;
    }

    public synchronized void addFood(Integer lackingFood) {
        food += lackingFood;
    }
}
