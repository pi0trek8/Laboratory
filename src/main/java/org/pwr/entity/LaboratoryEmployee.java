package org.pwr.entity;

import org.pwr.thread.CustomThread;
import org.pwr.laboratory.Laboratory;

public class LaboratoryEmployee extends CustomThread {

    private Integer threadTime;
    private String direction;
    private Integer position;
    private Integer amountOfFood = 50;

    public LaboratoryEmployee(String name, int time, Laboratory laboratory, Integer position) {
        super(name, time, laboratory);
        this.position = position;
        this.threadTime = time;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getAmountOfFood() {
        return amountOfFood;
    }

    public void setAmountOfFood(Integer amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    private synchronized void fillUpBag() {
        if (amountOfFood <= 0) {
            stateUpdater.showState();

            stateUpdater.showState();

            getLaboratory().goToDistributor(position, getName());
            amountOfFood = 50;
            stateUpdater.showState();

        }
    }

    public void run() {

        direction = "down";

        while (true){
            stateUpdater.showState();
            while (simulation) {
                stateUpdater.showState();

                synchronized (LaboratoryEmployee.class) {
                    fillUpBag();
                }

                try {
                    sleep(threadTime);
                } catch (InterruptedException e) {
                    System.err.println("Thread stopped.");
                }


                Integer lackingFood = getLaboratory().checkLackingFood(position);
                if (lackingFood > 0) {
                    if (lackingFood < amountOfFood) {
                        if (getLaboratory().isSpecimenAlive(position)) {
                            getLaboratory().giveFoodToContainer(position, lackingFood, getName(), amountOfFood);
                            amountOfFood -= lackingFood;
                        }
                    } else {
                        getLaboratory().giveFoodToContainer(position, amountOfFood, getName(), amountOfFood);
                        amountOfFood = 0;
                    }
                }

                try {
                    sleep(threadTime);
                } catch (InterruptedException e) {
                    System.err.println("Thread stopped.");
                }

                synchronized (LaboratoryEmployee.class) {
                    if (direction.equals("down")) {
                        tryToGoDown();
                    }

                    if (direction.equals("up")) {
                        tryToGoUp();
                    }
                }
            }
        }
    }


    private synchronized void tryToGoUp() {
        if (getLaboratory().isNextOccupied(position, direction)) {
            direction = "down";
            return;
        }
        // TODO: dont go to the same direction as friend
//        if (getLaboratory().isNextNextAnotherEmployee(position, direction)) {
//            direction = "down";
//            return;
//        }
        getLaboratory().refresh(position);
        position--;
        stateUpdater.showState();
        String name = getNameWithFoodValue();
        getLaboratory().move(position, name);
    }

    private String getNameWithFoodValue() {
        String name;
        if(getAmountOfFood() > 9) {
            name = getName() + "_" + getAmountOfFood();
        } else {
            name = getName() + "_0" + getAmountOfFood();
        }
        return name;
    }

    private synchronized void tryToGoDown() {
        if (getLaboratory().isNextOccupied(position, direction)) {
            direction = "up";
            return;
        }
        getLaboratory().refresh(position);
        position++;
        stateUpdater.showState();
        String name = getNameWithFoodValue();
        getLaboratory().move(position, name);
    }
}


