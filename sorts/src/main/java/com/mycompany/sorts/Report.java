/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Pierre
 */
public class Report {

    private static class Column {

        String value, name;
        int size;
    }
    private final LinkedHashMap<String, Column> columns = new LinkedHashMap<>();

    public void write(String name, Object value) {
        Column c = columns.get(name);
        if (c == null) {
            c = new Column();
            c.name = name;
            //c.size = name.length();
            columns.put(name, c);
        }
        c.value = value.toString();
        c.size = Math.max(c.size, c.value.length());
    }

    public void nextRaw() {
        int col = 0;
        for (Column c : columns.values()) {
            if (col++ != 0) {
                System.out.print(", ");
            }
            inlineTitle(c.name);
            String strVal = c.value != null ? c.value : "";
            System.out.print(strVal);
            padding(c.size - strVal.length());
            c.value = null;
        }
        System.out.println();
    }

    void inlineTitle(String name) {
        System.out.print(name);
        System.out.print(":");
    }

    void padding(int size) {
        for (int j = 0; j < size; j++) {
            System.out.print(' ');
        }
    }

    void printHeader() {
        int col = 0;
        for (Column c : columns.values()) {
            if (col++ != 0) {
                System.out.print(", ");
            }
            System.out.print(c.name);
        }
        System.out.println();
    }

    static void writeAllAsCsv(Iterable<Session> sessions) {
        final Report report = new Report() {

            @Override
            void padding(int size) {
            }

            @Override
            void inlineTitle(String name) {
            }

        };
        for (Session session : sessions) {
            session.writeTo(report);
        }
        report.printHeader();
        for (Session session : sessions) {
            session.writeTo(report);
            report.nextRaw();
        }
    }
}
