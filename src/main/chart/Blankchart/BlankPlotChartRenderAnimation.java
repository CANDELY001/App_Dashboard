/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.chart.Blankchart;

import java.awt.Graphics2D;

/**
 *
 * @author HP
 */
public interface BlankPlotChartRenderAnimation {
     public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChartAnimation chart, Graphics2D g2, SeriesSize size, int index);
}
