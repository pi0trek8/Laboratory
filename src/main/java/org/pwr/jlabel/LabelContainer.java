package org.pwr.jlabel;

import javax.swing.*;

public class LabelContainer {

    private JLabel jLabel;

    private Integer position;


    public LabelContainer(JLabel jLabel, Integer position) {
        this.jLabel = jLabel;
        this.position = position;
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
