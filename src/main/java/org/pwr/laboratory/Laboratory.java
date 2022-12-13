package org.pwr.laboratory;

import org.pwr.updater.StateUpdater;
import org.pwr.entity.FoodHolder;
import org.pwr.entity.Specimen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;

public class Laboratory {
    private StateUpdater stateUpdater;

    private List<String> laboratoryEmployeeContainers = new ArrayList<>();

    private List<Specimen> specimens = new ArrayList<>();

    private List<FoodHolder> foodHolders = new CopyOnWriteArrayList<>();

    private String distributor = "|_______|";

    public void setStateUpdater(StateUpdater stateUpdater) {
        this.stateUpdater = stateUpdater;
    }

    public List<String> getLaboratoryEmployeeContainers() {
        return laboratoryEmployeeContainers;
    }

    public void setLaboratoryEmployeeContainers(List<String> laboratoryEmployeeContainers) {
        this.laboratoryEmployeeContainers = laboratoryEmployeeContainers;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public Integer getFood(Integer position, Integer food) {
        FoodHolder container = foodHolders.get(position);
        stateUpdater.showState();
        return container.eatFood(food);
    }

    public boolean checkIfThereIsFood(Integer position) {
        FoodHolder container = foodHolders.get(position);
        return container.getCurrentFood() > 0;
    }

    public List<FoodHolder> getFoodHolders() {
        return foodHolders;
    }

    public List<Specimen> getSpecimens() {
        return specimens;
    }

    public void setSpecimens(List<Specimen> specimens) {
        this.specimens = specimens;
    }

    public void setFoodHolders(List<FoodHolder> foodHolders) {
        this.foodHolders = foodHolders;
    }

    public Integer checkLackingFood(Integer position) {
        FoodHolder container = foodHolders.get(position);
        stateUpdater.showState();
        return 15 - container.getCurrentFood();
    }

    public void giveFoodToContainer(Integer position, Integer lackingFood, String name, Integer foodValue) {

        FoodHolder container = foodHolders.get(position);



        String employeeName;
        if (foodValue > 9) {
            employeeName = "|___" + name + "_" + foodValue;
        } else {
            employeeName = "|___" + name + "_0" + foodValue;
        }
        laboratoryEmployeeContainers.set(position, employeeName);
        stateUpdater.showState();

        container.addFood(lackingFood);
        stateUpdater.showState();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        if ((foodValue - lackingFood) > 9) {
            employeeName = "|__" + name + "_" + (foodValue - lackingFood) + "_|";
        } else {
            employeeName = "|__" + name + "_0" + (foodValue - lackingFood) + "_|";
        }

        laboratoryEmployeeContainers.set(position, employeeName);
        stateUpdater.showState();
    }

    public synchronized boolean isNextOccupied(Integer position, String direction) {

        if (direction.equals("up")) {
            if (position == 0) {
                return true;
            }

            if (position == 1) {
                if (specimens.get(0).isDead()) {
                    return true;
                }
            }

            if (position == 2) {
                if (specimens.get(1).isDead() && specimens.get(0).isDead()) {
                    return true;
                }
            }
            if (position == 3) {
                if (specimens.get(2).isDead() && specimens.get(1).isDead() && specimens.get(0).isDead()) {
                    return true;
                }
            }
            if (position == 4) {
                if (specimens.get(3).isDead() && specimens.get(2).isDead() && specimens.get(1).isDead() && specimens.get(0).isDead()) {
                    return true;
                }
            }
            if (position == 5) {
                if (specimens.get(4).isDead() && specimens.get(3).isDead() && specimens.get(2).isDead() && specimens.get(1).isDead() && specimens.get(0).isDead()) {
                    return true;
                }
            }
            String nextPosition = laboratoryEmployeeContainers.get(position - 1);
            return !nextPosition.equals("|_______|");
        }

        if (position == 5) {
            return true;
        }
        if (position == 4) {
            if (specimens.get(5).isDead()) {
                return true;
            }
        }
        if (position == 3) {
            if (specimens.get(4).isDead() && specimens.get(5).isDead()) {
                return true;
            }
        }
        if (position == 2) {
            if (specimens.get(3).isDead() && specimens.get(4).isDead() && specimens.get(5).isDead()) {
                return true;
            }
        }
        if (position == 1) {
            if (specimens.get(2).isDead() && specimens.get(3).isDead() && specimens.get(4).isDead() && specimens.get(5).isDead()) {
                return true;
            }
        }
        if (position == 0) {
            if (specimens.get(5).isDead() && specimens.get(4).isDead() && specimens.get(3).isDead() && specimens.get(2).isDead() && specimens.get(1).isDead()) {
                return true;
            }
        }

        String nextPosition = laboratoryEmployeeContainers.get(position + 1);
        return !nextPosition.equals("|_______|");

    }

    public synchronized void refresh(Integer position) {
        laboratoryEmployeeContainers.set(position, "|_______|");
        stateUpdater.showState();

    }

    public synchronized void move(Integer position, String name) {
        name = "|__" + name + "_|";
        this.laboratoryEmployeeContainers.set(position, name);
        stateUpdater.showState();

    }

    public synchronized void goToDistributor(Integer position, String name) {

        distributor = "|__" + name + "_00_|";
        this.laboratoryEmployeeContainers.set(position, "|___" + name + "___|");
        stateUpdater.showState();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Thread stopped.");
        }

        distributor = "|__" + name + "_" + 50 + "_|";
        stateUpdater.showState();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Thread stopped.");
        }

        distributor = "|_______|";
        this.laboratoryEmployeeContainers.set(position, "|__" + name + "_" + 50 + "_|");
        stateUpdater.showState();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Thread stopped.");
        }
    }

    public boolean isSpecimenAlive(Integer position) {
        return specimens.get(position).getHealth() > -1;
    }
}