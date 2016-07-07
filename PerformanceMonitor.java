/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.io.File;
import java.util.List;
import java.util.*;
import java.text.*;

/**
 *
 * @author limei
 */
public class PerformanceMonitor {

    Set<List<Integer>> premuteSet = new HashSet<>();

    private void permute(List<Integer> arr, int k) {
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permute(arr, k + 1);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            List<Integer> newObject = new ArrayList<>();
            newObject.addAll(arr);
            premuteSet.add(newObject);
        }
    }

    private List<List<Integer>> createDetaGrid(Set<List<Integer>> set) {
        List<List<Integer>> detaGridSet = new ArrayList<>();

        for (List<Integer> arr : set) {
            detaGridSet.add(createDeta(arr));
        }

        return detaGridSet;
    }

    private List<Integer> createDeta(List<Integer> arr) {
        List<Integer> newObject = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            int currentInt = arr.get(i);
            int detaValue = i + 1;
            for (int j = i - 1; j >= 0; j--) {
                int previousInt = arr.get(j);
                if (previousInt >= currentInt) {
                    detaValue = i - j;
                    break;
                }
            }
            newObject.add(detaValue);
        }

        //System.out.println("posi: " + arr);
        //System.out.println("deta: " + newObject);
        //System.out.println("\n ");
        return newObject;
    }

    private double averageDeta(List<List<Integer>> set) {
        int number = set.size();
        double sumDeta = 0.0;

        Iterator iterator = set.iterator();
        for (List<Integer> set1 : set) {
            List<Integer> arr = (List<Integer>) set1;
            sumDeta = sumDeta + sumDeta(arr);
        }

        return sumDeta / number;
    }

    private int sumDeta(List<Integer> arr) {
        int currentInt = 0;
        for (Integer arr1 : arr) {
            currentInt = currentInt + arr1;
        }
        //System.out.println("currentInt: " + currentInt);
        return currentInt;
    }

    public static void main(String[] args) {
        PerformanceMonitor pm = new PerformanceMonitor();
        pm.permute(Arrays.asList(1, 2, 3, 4, 5, 6), 0);
        System.out.println(pm.premuteSet);
        System.out.println("\n");

        List<List<Integer>> detaGridSet = pm.createDetaGrid(pm.premuteSet);
        System.out.println(detaGridSet);
        System.out.println("\n");

        double averageDeta = pm.averageDeta(detaGridSet);
        System.out.println(pm.roundTo2Decimals(averageDeta));
        //System.out.println("\n");

    }

    private double roundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
    }

    public static void main1(String[] args) {
        /* Total number of processors or cores available to the JVM */
        System.out.println("Available processors (cores): "
                + Runtime.getRuntime().availableProcessors());

        /* Total amount of free memory available to the JVM */
        System.out.println("Free memory (bytes): "
                + Runtime.getRuntime().freeMemory() / (1024 * 1024));

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        /* Maximum amount of memory the JVM will attempt to use */
        System.out.println("Maximum memory (bytes): "
                + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

        /* Total memory currently in use by the JVM */
        System.out.println("Total memory (bytes): "
                + Runtime.getRuntime().totalMemory() / (1024 * 1024));

        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            System.out.println("File system root: " + root.getAbsolutePath());
            System.out.println("Total space (bytes): " + root.getTotalSpace());
            System.out.println("Free space (bytes): " + root.getFreeSpace());
            System.out.println("Usable space (bytes): " + root.getUsableSpace());
            System.out.println("\n");
        }
    }
}