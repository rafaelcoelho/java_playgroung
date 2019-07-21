package com.personal.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//**********************************************************************
// Copyright (c) 2019 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************

public class Algorithms
{
    public static List<Integer> cellCompete(int[] states, int days)
    {
        List<Integer> values = Arrays.stream(states).boxed().collect(Collectors.toList());
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < days; ++i)
        {
            result.clear();
            values.add(0);
            values.add(0, 0);

            for (int x = 1; x < values.size() - 1; x++)
            {
                result.add(values.get(x - 1).equals(values.get(x + 1)) ? 0 : 1);
            }

            values = new ArrayList<>(result);
        }

        return result;
    }

    private static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // Function to find gcd of array of
    // numbers
    public static int findGCD(int n, int arr[])
    {
        int result = arr[0];
        for (int i = 1; i < n; i++)
            result = gcd(arr[i], result);

        return result;
    }

    public static ArrayList<Integer> IDsOfPackages(int truckSpace,
                                                   ArrayList<Integer> packagesSpace)
    {
        Integer left = truckSpace - 30;
        ArrayList<Integer> values = new ArrayList<>(packagesSpace);
        ArrayList<Integer> result = new ArrayList<>();

        Collections.sort(values);

        for (int i = values.size() - 1; i != 0; i--)
        {
            Integer base = values.get(i);

            if (base > left)
                continue;

            Integer match = left - base;
            if (values.indexOf(match) != -1)
            {
                result.add(packagesSpace.indexOf(match));
                result.add(packagesSpace.indexOf(base));
                Collections.sort(result);
                return result;
            }

        }

        return result;
    }

    public static List<List<Integer>> memory(List<List<Integer>> foreground,
                                             List<List<Integer>> background,
                                             Integer mem)
    {
        Map<Integer, String> sumMem = new HashMap<>();
        List<List<Integer>> finalResult = new ArrayList<>();
        Integer last = 0,
                sum = 0;

        for (int i = 0; i < foreground.size(); i++)
        {
            for (int x = 0; x < background.size(); x++)
            {
                sum = foreground.get(i).get(1) + background.get(x).get(1);

                if (sum > mem || sum < last)
                {
                    continue;
                }

                String key = String.valueOf(foreground.get(i).get(0) + "," + background.get(x).get(0) + "-" + sumMem.get(sum));

                sumMem.put(sum, key);
                last = sum;
            }
        }

        List<Integer> sumMap = sumMem.keySet().stream().collect(Collectors.toList());
        Collections.sort(sumMap);

        for (String v : sumMem.get(sumMap.get(sumMap.size() - 1)).split("-"))
        {
            if (!v.equals("null"))
            {
                ArrayList<Integer> list = new ArrayList<>();
                String[] split = v.split(",");
                list.add(Integer.valueOf(split[0]));
                list.add(Integer.valueOf(split[1]));
                finalResult.add(list);
            }
        }

        System.out.println("Memory: " + finalResult);
        return finalResult;

    }

}
