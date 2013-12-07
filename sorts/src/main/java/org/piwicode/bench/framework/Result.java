/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.piwicode.bench.framework;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Pierre
 */
public class Result {

    private final List<Experiment> experiments;

    public Result(List<Experiment> measurements) {
        this.experiments = measurements;
    }

    public void showBests() {
        System.out.println("------------------------------------------------------------------------");
        final ArrayList<Experiment> al = new ArrayList<>(experiments);
        Collections.sort(al);
        PrettyPrint.printAll(al, "-");
    }

    public void showCSV() {
        System.out.println("------------------------------------------------------------------------");
        CSVReport.writeAllAsCsv(experiments);
    }

    public void plot(final String title, final String serieKey, final String xKey, final String yKey) {
        // Create an ITrace:         
        final XYSeriesCollection dataset = new XYSeriesCollection();
        final Map<Object, XYSeries> traces = new HashMap<>();
        // Add all points, as it is static:         
        final Report report = new Report() {
            Object serie;
            Number x, y;

            @Override
            public void write(String name, Object value) {
                if (name.equals(serieKey))
                    serie = value;
                else if (name.equals(xKey)) {
                    x = (Number) value;
                } else if (name.equals(yKey)) {
                    y = (Number) value;
                }
            }

            @Override
            public void nextRaw() {
                if (x == null || y == null || serie == null)
                    throw new IllegalStateException();
                XYSeries trace = traces.get(serie);
                if (trace == null) {
                    trace = new XYSeries(nameFor(serie));
                    traces.put(serie, trace);
                    dataset.addSeries(trace);
                }
                trace.add(x.doubleValue(), y.doubleValue());
            }

        };

        for (Experiment e : experiments) {
            e.writeTo(report);
            report.nextRaw();
        }

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title, // chart title
                xKey, // x axis label
                yKey, // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
                );
        XYItemRenderer renderer = ((XYPlot)chart.getPlot()).getRenderer();
        renderer.setBaseStroke( new BasicStroke( 2 ) );
        ((AbstractRenderer)renderer).setAutoPopulateSeriesStroke(false);
        chart.getLegend().setBackgroundPaint(Color.lightGray);
        final ChartPanel chartPanel = new ChartPanel(chart);        
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        final JFrame frame = new JFrame("Benchmark results");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();        
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getWidth() / 2);
        frame.setVisible(true);

    }

    private static Comparable nameFor(Object serie) {
        if (serie instanceof Class) {
            String pattern = String.format("%s|%s|%s",
                    "(?<=[A-Z])(?=[A-Z][a-z])",
                    "(?<=[^A-Z])(?=[A-Z])",
                    "(?<=[A-Za-z])(?=[^A-Za-z])"
                    );
            return ((Class) serie).getSimpleName().replaceAll(pattern, " ");
        } else return serie.toString();
    }
}
