/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.chart;

import java.awt.Color;

/**
 *
 * @author HP
 */
public class ModelLegendAnimation {
      public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ModelLegendAnimation(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public ModelLegendAnimation() {
    }

    private String name;
    private Color color;
}
