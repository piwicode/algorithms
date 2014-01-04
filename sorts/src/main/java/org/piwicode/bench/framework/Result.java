/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.SortOrder;

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

    static class Axis {

        final String key;
        final String label;

        public Axis(String def) {
            final String[] parts = def.split(": ?", 2);
            key = parts[0];
            label = parts.length > 1 ? parts[1] : parts[0];
        }

    }

    public Plot plot(final String title, final String serieKey, final String xDef, final String yDef) {
        final Axis xAxis = new Axis(xDef), yAxis = new Axis(yDef);
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
                else if (name.equals(xAxis.key)) {
                    x = (Number) value;
                } else if (name.equals(yAxis.key)) {
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
                xAxis.label, // x axis label
                yAxis.label, // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
                );
        final XYPlot plot = (XYPlot) chart.getPlot();
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new UnitNumberFormat(TimeUnit.ns));
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setBaseStroke(new BasicStroke(2));
        ((AbstractRenderer) renderer).setAutoPopulateSeriesStroke(false);
        chart.getLegend().setBackgroundPaint(Color.lightGray);
        if (traces.size() == 1) chart.getLegend().setVisible(false);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        final JFrame frame = new JFrame("Benchmark results");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getWidth() / 2);

        return new Plot() {
            @Override
            public Plot includeZero(boolean b) {
                rangeAxis.setAutoRangeIncludesZero(b);
                return this;
            }

            @Override
            public void show() {
                frame.setVisible(true);
            }
        };
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

    public Plot plotBarChart(String title, final String serieKey, final String valueDef) {
        final Axis vAxis = new Axis(valueDef);
        // Create an ITrace:         
        final DefaultKeyedValues data = new DefaultKeyedValues();
        // Add all points, as it is static:         
        final Report report = new Report() {
            Object serie;
            Number v;

            @Override
            public void write(String name, Object value) {
                if (name.equals(serieKey))
                    serie = value;
                else if (name.equals(vAxis.key)) {
                    v = (Number) value;
                }
            }

            @Override
            public void nextRaw() {
                if (serie == null || v == null)
                    throw new IllegalStateException();
                data.addValue(nameFor(serie), v.doubleValue());
            }
        };

        for (Experiment e : experiments) {
            e.writeTo(report);
            report.nextRaw();
        }
        data.sortByValues(SortOrder.DESCENDING);
        final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(serieKey, data);
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
                title, // chart title
                serieKey, // domain axis label
                vAxis.label, // range axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true,
                false
                );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new UnitNumberFormat(TimeUnit.ns));
        domainAxis.setMaximumCategoryLabelLines(3);
        chart.removeLegend();
        final ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        final JFrame frame = new JFrame("Benchmark results");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getWidth() / 2);
        return new Plot() {
            @Override
            public Plot includeZero(boolean b) {
                rangeAxis.setAutoRangeIncludesZero(true);
                return this;
            }

            @Override
            public void show() {
                frame.setVisible(true);
            }
        };
    }

    private static class UnitNumberFormat extends NumberFormat {

        final Unit unit;

        public UnitNumberFormat(Unit unit) {
            this.unit = unit;
        }

        @Override
        public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
            return toAppendTo.append(Units.format(number, unit));
        }

        @Override
        public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
            return format(number, toAppendTo, pos);
        }

        @Override
        public Number parse(String source, ParsePosition parsePosition) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public interface Plot {

        Plot includeZero(boolean b);

        void show();
    }
}
