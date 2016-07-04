package com.netty.chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by frinder_liu on 2016/4/18.
 */
public class App {
    public static void main(String[] args) {

        try (
                Reader reader = new InputStreamReader(System.in);
                BufferedReader in = new BufferedReader(reader)) {
            String line;
            while (true) {
                System.out.print("input: ");
                line = in.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


