/**
 * @Autor Piotr Szczypior
 * @Jar mvn package in the directory of project
 * @Run-jar java -jar lab05_pop.jar arguments
 *
 * @argumets
 * @1 - number of employees - between 0 and 3
 * @2 - employee-thread wait time
 * @3 - specimen-thread wait time
 */


package org.pwr;

import org.pwr.gui.MyFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        MyFrame frame = new MyFrame("MyApplication", Integer.valueOf(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]));
        System.out.println(args[0]);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
