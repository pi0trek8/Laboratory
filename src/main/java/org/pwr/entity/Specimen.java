package org.pwr.entity;

import org.pwr.thread.CustomThread;
import org.pwr.laboratory.Laboratory;

import java.util.Random;

public class Specimen extends CustomThread {

    private Integer position;
    private Integer health;

    private Integer threadTime;

    private final Random random = new Random();

    private boolean isDead;

    public Specimen(String specimenName, int threadTime, Laboratory laboratory, Integer position, Integer health) {
        super(specimenName, threadTime, laboratory);
        this.position = position;
        this.health = health;
        this.threadTime = threadTime;
    }

    public void run() {
        while (!isDead) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            while (simulation) {
                stateUpdater.showState();
                //TODO: null after some iteratriio
                try {
                    sleep(threadTime);
                } catch (InterruptedException e) {
                    System.err.println("Thread stopped");
                }

                if (health <= 5) {
                    health += getLaboratory().getFood(position, 10 - health);
                }
                stateUpdater.showState();

                if (health == 0) {
                    try {
                        sleep(500); //Dev note: infinite loop
                    } catch (InterruptedException e) {
                        System.err.println("Thread stopped");
                    }

                    if (getLaboratory().checkIfThereIsFood(position)) {
                        health += getLaboratory().getFood(position, 10 - health);
                        stateUpdater.showState();
                        continue;
                    }
                    health = -1;
                    stateUpdater.showState();
                    isDead = true;
                    break;

                } else {
                    health--;
                }
            }
        }
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
