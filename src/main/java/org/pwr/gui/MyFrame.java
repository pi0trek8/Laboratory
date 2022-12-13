package org.pwr.gui;

import org.pwr.entity.FoodHolder;
import org.pwr.entity.LaboratoryEmployee;
import org.pwr.entity.Specimen;
import org.pwr.jlabel.LabelContainer;
import org.pwr.laboratory.Laboratory;
import org.pwr.thread.CustomThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame {

    private Integer numberLaboratoryEmployees;

    private Integer workSpeedEmployees;

    private Integer workSpeedSpecimens;

    private JLabel nourishmentLabel, staminaLabel, assistantLabel, distributorLabel;

    private JLabel assistantContainer1, assistantContainer2, assistantContainer3;
    private JLabel assistantContainer4, assistantContainer5, assistantContainer6;

    private JLabel nourishmentContainer1, nourishmentContainer2, nourishmentContainer3;
    private JLabel nourishmentContainer4, nourishmentContainer5, nourishmentContainer6;

    private JLabel staminaContainer1, staminaContainer2, staminaContainer3;
    private JLabel staminaContainer4, staminaContainer5, staminaContainer6;

    private Laboratory lab;
    JLabel distributorPlaceHolder;

    private LaboratoryEmployee laboratoryEmployeeThread1;
    private LaboratoryEmployee laboratoryEmployeeThread2;
    private LaboratoryEmployee laboratoryEmployeeThread3;
    private Specimen specimenThread1;
    private Specimen specimenThread2;
    private Specimen specimenThread3;
    private Specimen specimenThread4;
    private Specimen specimenThread5;
    private Specimen specimenThread6;

    private List<LabelContainer> labolatoryEmployeeContainers = new ArrayList<>();

    private List<LabelContainer> foodContainers = new ArrayList<>();

    private List<JLabel> specimenContainers = new ArrayList<>();

    private List<String> laboratoryContainersString = new ArrayList<>();
    private List<FoodHolder> foodHolders = new ArrayList<>();

    private List<Specimen> specimens = new ArrayList<>();

    private boolean hasRun;

    public void utworzWatki() {

        lab = new Laboratory();
        lab.setLaboratoryEmployeeContainers(laboratoryContainersString);
        lab.setFoodHolders(foodHolders);
        lab.setSpecimens(specimens);
        lab.setStateUpdater(
                () -> {
                    laboratoryContainersString = lab.getLaboratoryEmployeeContainers();

                    for (var container : labolatoryEmployeeContainers) {
                        container.getjLabel().setText(laboratoryContainersString.get(container.getPosition()));
                    }

                    foodHolders = lab.getFoodHolders();
                    for (var foodContainer : foodContainers) {
                        foodContainer.getjLabel().setText(foodHolders.get(foodContainer.getPosition()).getName());
                    }

                    distributorPlaceHolder.setText(lab.getDistributor());

                }
        );


        specimenThread1 = new Specimen("specimen1", workSpeedSpecimens, lab, 0, 10);
//        specimenThread1.setStateUpdater(
//                () -> staminaContainer1.setText("| " + specimenThread1.getHealth() + " |")
//        );
        specimenThread2 = new Specimen("specimen1", workSpeedSpecimens, lab, 1, 10);

        specimenThread3 = new Specimen("specimen2", workSpeedSpecimens, lab, 2, 10);

        specimenThread4 = new Specimen("specimen3", workSpeedSpecimens, lab, 3, 10);
        specimenThread5 = new Specimen("specimen4", workSpeedSpecimens, lab, 4, 10);
        specimenThread6 = new Specimen("specimen5", workSpeedSpecimens, lab, 5, 10);

        specimens.addAll(List.of(
                specimenThread1, specimenThread2,
                specimenThread3, specimenThread4,
                specimenThread5, specimenThread6
        ));

        for (Specimen specimen : specimens) {
            specimen.setStateUpdater(
                    () -> {
                        if (specimen.getHealth() == -1) {
                            specimenContainers.get(specimen.getPosition()).setText("| X |");

                        } else {
                            specimenContainers.get(specimen.getPosition()).setText("| " + specimen.getHealth() + " |");
                        }
                    }
            );
        }


        laboratoryEmployeeThread1 = new LaboratoryEmployee("a", workSpeedEmployees, lab, 1);
        laboratoryEmployeeThread1.setStateUpdater(
                () -> {
                    String name;
                    if (laboratoryEmployeeThread1.getAmountOfFood() > 9) {
                        name = "|__" + laboratoryEmployeeThread1.getName() + "_" + laboratoryEmployeeThread1.getAmountOfFood() + "_|";
                    } else {
                        name = "|__" + laboratoryEmployeeThread1.getName() + "_0" + laboratoryEmployeeThread1.getAmountOfFood() + "_|";
                    }

                    labolatoryEmployeeContainers.get(laboratoryEmployeeThread1.getPosition())
                            .getjLabel().setText(name);
                }
        );

        laboratoryEmployeeThread2 = new LaboratoryEmployee("b", workSpeedEmployees, lab, 2);
        laboratoryEmployeeThread2.setStateUpdater(
                () -> {
                    String name;
                    if (laboratoryEmployeeThread2.getAmountOfFood() > 9) {
                        name = "|__" + laboratoryEmployeeThread2.getName() + "_" + laboratoryEmployeeThread2.getAmountOfFood() + "_|";
                    } else {
                        name = "|__" + laboratoryEmployeeThread2.getName() + "_0" + laboratoryEmployeeThread2.getAmountOfFood() + "_|";
                    }

                    labolatoryEmployeeContainers.get(laboratoryEmployeeThread2.getPosition())
                            .getjLabel().setText(name);
                }
        );

        laboratoryEmployeeThread3 = new LaboratoryEmployee("c", workSpeedEmployees, lab, 5);
        laboratoryEmployeeThread3.setStateUpdater(
                () -> {
                    String name;
                    if (laboratoryEmployeeThread3.getAmountOfFood() > 9) {
                        name = "|__" + laboratoryEmployeeThread3.getName() + "_" + laboratoryEmployeeThread3.getAmountOfFood() + "_|";
                    } else {
                        name = "|__" + laboratoryEmployeeThread3.getName() + "_0" + laboratoryEmployeeThread3.getAmountOfFood() + "_|";
                    }

                    labolatoryEmployeeContainers.get(laboratoryEmployeeThread3.getPosition())
                            .getjLabel().setText(name);
                }
        );


        for (FoodHolder foodHolder : foodHolders) {
            foodHolder.setStateUpdater(
                    () -> {
                        String name = foodHolder.getName();
                        foodContainers.get(foodHolder.getPosition()).getjLabel().setText(name);
                    }
            );
        }

    }

    public void uruchomWatki() {
        CustomThread.simulation = true;
        if (!hasRun) {

            if (numberLaboratoryEmployees == 1) {
                laboratoryEmployeeThread1.start();
            } else if (numberLaboratoryEmployees == 2) {
                laboratoryEmployeeThread1.start();
                laboratoryEmployeeThread2.start();
            } else if (numberLaboratoryEmployees == 3) {
                laboratoryEmployeeThread1.start();
                laboratoryEmployeeThread2.start();
                laboratoryEmployeeThread3.start();
            }

            for (Specimen specimen : specimens) {
                specimen.start();
            }

        }
    }


    public void zatrzymajWatki() {
        CustomThread.simulation = false;
    }

    public void createComponents() {

        JButton buttonT = new JButton("Start");
        buttonT.setMnemonic(KeyEvent.VK_T);
        JButton buttonP = new JButton("Stop");
        buttonP.setMnemonic(KeyEvent.VK_P);

        distributorLabel = new JLabel("distributor");
        assistantLabel = new JLabel("assistant");
        nourishmentLabel = new JLabel("nourishment");
        staminaLabel = new JLabel("stamina");

        distributorLabel.setHorizontalAlignment(JLabel.CENTER);
        assistantLabel.setHorizontalAlignment(JLabel.CENTER);
        nourishmentLabel.setHorizontalAlignment(JLabel.CENTER);
        staminaLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel whiteSpaces1 = new JLabel("            ");
        JLabel whiteSpaces2 = new JLabel("            ");
        JLabel whiteSpaces3 = new JLabel("            ");
        JLabel whiteSpaces4 = new JLabel("            ");
        JLabel whiteSpaces5 = new JLabel("            ");
        JLabel whiteSpaces6 = new JLabel("            ");
        JLabel whiteSpaces7 = new JLabel("            ");
        JLabel whiteSpaces8 = new JLabel("            ");
        JLabel whiteSpaces9 = new JLabel("            ");
        JLabel whiteSpaces10 = new JLabel("            ");
        JLabel whiteSpaces11 = new JLabel("            ");
        JLabel whiteSpaces12 = new JLabel("            ");

        JLabel whiteSpaces13 = new JLabel("            ");
        JLabel whiteSpaces14 = new JLabel("            ");
        JLabel whiteSpaces15 = new JLabel("            ");
        JLabel whiteSpaces16 = new JLabel("            ");
        JLabel whiteSpaces17 = new JLabel("            ");
        JLabel whiteSpaces18 = new JLabel("            ");
        JLabel whiteSpaces19 = new JLabel("            ");
        JLabel whiteSpaces20 = new JLabel("            ");
        JLabel whiteSpaces21 = new JLabel("      ");
        JLabel whiteSpaces22 = new JLabel("            ");
        JLabel whiteSpaces23 = new JLabel("            ");
        JLabel whiteSpaces24 = new JLabel("         ");


        assistantContainer1 = new JLabel("|_______|");
        assistantContainer1.setHorizontalAlignment(JLabel.CENTER);
        laboratoryContainersString.add("|_______|");

        assistantContainer2 = new JLabel("|_______|");
        assistantContainer2.setHorizontalAlignment(JLabel.CENTER);
        laboratoryContainersString.add("|_______|");

        assistantContainer3 = new JLabel("|_______|");
        assistantContainer3.setHorizontalAlignment(JLabel.CENTER);
        laboratoryContainersString.add("|_______|");

        assistantContainer4 = new JLabel("|_______|");
        assistantContainer4.setHorizontalAlignment(JLabel.CENTER);
        laboratoryContainersString.add("|_______|");

        assistantContainer5 = new JLabel("|_______|");
        assistantContainer5.setHorizontalAlignment(JLabel.CENTER);
        laboratoryContainersString.add("|_______|");

        assistantContainer6 = new JLabel("|_______|");
        assistantContainer6.setHorizontalAlignment(JLabel.CENTER);
        laboratoryContainersString.add("|_______|");


        nourishmentContainer1 = new JLabel("|  __  |");
        nourishmentContainer1.setHorizontalAlignment(JLabel.CENTER);
        FoodHolder foodHolder0 = new FoodHolder();
        foodHolder0.setPosition(0);
        foodHolders.add(foodHolder0);
        foodContainers.add(new LabelContainer(nourishmentContainer1, 0));

        nourishmentContainer2 = new JLabel("|  __  |");
        nourishmentContainer2.setHorizontalAlignment(JLabel.CENTER);
        FoodHolder foodHolder1 = new FoodHolder();
        foodHolder1.setPosition(1);
        foodHolders.add(foodHolder1);

        foodContainers.add(new LabelContainer(nourishmentContainer2, 1));

        nourishmentContainer3 = new JLabel("|  __  |");
        nourishmentContainer3.setHorizontalAlignment(JLabel.CENTER);
        FoodHolder foodHolder2 = new FoodHolder();
        foodHolder2.setPosition(2);
        foodHolders.add(foodHolder2);
        foodContainers.add(new LabelContainer(nourishmentContainer3, 2));

        nourishmentContainer4 = new JLabel("|  __  |");
        nourishmentContainer4.setHorizontalAlignment(JLabel.CENTER);
        FoodHolder foodHolder3 = new FoodHolder();
        foodHolder3.setPosition(3);
        foodHolders.add(foodHolder3);
        foodContainers.add(new LabelContainer(nourishmentContainer4, 3));

        nourishmentContainer5 = new JLabel("|  __  |");
        nourishmentContainer5.setHorizontalAlignment(JLabel.CENTER);
        FoodHolder foodHolder4 = new FoodHolder();
        foodHolder4.setPosition(4);
        foodHolders.add(foodHolder4);
        foodContainers.add(new LabelContainer(nourishmentContainer5, 4));

        nourishmentContainer6 = new JLabel("|  __  |");
        nourishmentContainer6.setHorizontalAlignment(JLabel.CENTER);
        FoodHolder foodHolder5 = new FoodHolder();
        foodHolder5.setPosition(5);
        foodHolders.add(foodHolder5);
        foodContainers.add(new LabelContainer(nourishmentContainer6, 5));


        staminaContainer1 = new JLabel("| _ |");
        staminaContainer1.setHorizontalAlignment(JLabel.CENTER);

        staminaContainer2 = new JLabel("| _ |");
        staminaContainer2.setHorizontalAlignment(JLabel.CENTER);

        staminaContainer3 = new JLabel("| _ |");
        staminaContainer3.setHorizontalAlignment(JLabel.CENTER);

        staminaContainer4 = new JLabel("| _ |");
        staminaContainer4.setHorizontalAlignment(JLabel.CENTER);

        staminaContainer5 = new JLabel("| _ |");
        staminaContainer5.setHorizontalAlignment(JLabel.CENTER);

        staminaContainer6 = new JLabel("| _ |");
        staminaContainer6.setHorizontalAlignment(JLabel.CENTER);

        distributorPlaceHolder = new JLabel("|_______|");
        distributorPlaceHolder.setHorizontalAlignment(JLabel.CENTER);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(8, 6, 10, 10));


        jPanel.add(whiteSpaces10);
        jPanel.add(distributorLabel);
        jPanel.add(assistantLabel);
        jPanel.add(nourishmentLabel);
        jPanel.add(staminaLabel);
        jPanel.add(whiteSpaces1);

        jPanel.add(whiteSpaces11);
        jPanel.add(whiteSpaces5);
        jPanel.add(assistantContainer1);
        jPanel.add(nourishmentContainer1);
        jPanel.add(staminaContainer1);
        jPanel.add(whiteSpaces2);

        jPanel.add(whiteSpaces12);
        jPanel.add(whiteSpaces6);
        jPanel.add(assistantContainer2);
        jPanel.add(nourishmentContainer2);
        jPanel.add(staminaContainer2);
        jPanel.add(whiteSpaces3);


        jPanel.add(whiteSpaces13);
        jPanel.add(distributorPlaceHolder);
        jPanel.add(assistantContainer3);
        jPanel.add(nourishmentContainer3);
        jPanel.add(staminaContainer3);
        jPanel.add(whiteSpaces4);


        jPanel.add(whiteSpaces14);
        jPanel.add(whiteSpaces7);
        jPanel.add(assistantContainer4);
        jPanel.add(nourishmentContainer4);
        jPanel.add(staminaContainer4);
        jPanel.add(whiteSpaces19);

        jPanel.add(whiteSpaces15);
        jPanel.add(whiteSpaces8);
        jPanel.add(assistantContainer5);
        jPanel.add(nourishmentContainer5);
        jPanel.add(staminaContainer5);
        jPanel.add(whiteSpaces20);

        jPanel.add(whiteSpaces16);
        jPanel.add(whiteSpaces9);
        jPanel.add(assistantContainer6);
        jPanel.add(nourishmentContainer6);
        jPanel.add(staminaContainer6);
        jPanel.add(whiteSpaces18);

        labolatoryEmployeeContainers.addAll(List.of(
                new LabelContainer(assistantContainer1, 0),
                new LabelContainer(assistantContainer2, 1),
                new LabelContainer(assistantContainer3, 2),
                new LabelContainer(assistantContainer4, 3),
                new LabelContainer(assistantContainer5, 4),
                new LabelContainer(assistantContainer6, 5)
        ));


        specimenContainers.addAll(List.of(
                staminaContainer1, staminaContainer2,
                staminaContainer3, staminaContainer4,
                staminaContainer5, staminaContainer6
        ));


        ActionListener actionListener = e -> {
            if (((JButton) e.getSource()).getText().compareTo("Start") == 0) {
                if (!hasRun) {
                    utworzWatki();
                    uruchomWatki();
                    hasRun = true;
                } else {
                    uruchomWatki();
                }

//                ((JButton) e.getSource()).setEnabled(false);
            }

            if (((JButton) e.getSource()).getText().compareTo("Stop") == 0) {
                zatrzymajWatki();
            }
        };

        buttonT.addActionListener(actionListener);
        buttonP.addActionListener(actionListener);
        JPanel pb = new JPanel();
        pb.add(buttonT);
        pb.add(buttonP);

        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setPreferredSize(new Dimension(700, 30));
//        panel.add(whiteSpaces23);
        panel.add(new JLabel("Laboratory"));

        this.getContentPane().setLayout(new GridLayout(3, 1));

        this.getContentPane().add(panel);
        this.getContentPane().add(jPanel);
        this.getContentPane().add(pb);
    }

    public MyFrame(String arg0, Integer numberLaboratoryEmployees, Integer workSpeedEmployees, Integer workSpeedSpecimens) {
        super(arg0);
        if (numberLaboratoryEmployees > 3 || numberLaboratoryEmployees < 0) {
            throw new IllegalArgumentException("Amount of laboratory employees should be between 0 and 3");
        }
        this.numberLaboratoryEmployees = numberLaboratoryEmployees;
        this.workSpeedEmployees = workSpeedEmployees;
        this.workSpeedSpecimens = workSpeedSpecimens;
        createComponents();
        pack();
    }

}
