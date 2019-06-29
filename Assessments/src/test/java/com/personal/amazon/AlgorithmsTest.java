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
package com.personal.amazon;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AlgorithmsTest
{

    @Test
    public void testCellCompete()
    {
        int[] value =
        { 1, 1, 1, 0, 1, 1, 1, 1 };
        List<Integer> expect = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 0));

        assertThat(Algorithms.cellCompete(value, 2), is(expect));

        value[1] = 0;
        value[2] = 0;
        value[4] = 0;
        value[6] = 0;
        value[7] = 0;

        expect = new ArrayList<>(Arrays.asList(0, 1, 0, 0, 1, 0, 1, 0));
        assertThat(Algorithms.cellCompete(value, 1), is(expect));
    }

    @Test
    public void testFindGCD()
    {

        int[] gcb =
        { 2, 4, 6, 8, 10 };

        assertEquals(Algorithms.findGCD(5, gcb), 2);
    }

    @Test
    public void testTruck()
    {
        ArrayList<Integer> packages = new ArrayList<>(Arrays.asList(20, 70, 90, 30, 60, 110));

        assertEquals(Arrays.asList(0, 4), Algorithms.IDsOfPackages(110, packages));

        ArrayList<Integer> packages2 = new ArrayList<>(Arrays.asList(100, 180, 40, 120, 10));
        assertEquals(Arrays.asList(1, 2), Algorithms.IDsOfPackages(250, packages2));

        ArrayList<Integer> packages3 = new ArrayList<>(Arrays.asList(220, 220, 170, 100, 50));
        assertEquals(Arrays.asList(0, 4), Algorithms.IDsOfPackages(300, packages3));
    }

    @Test
    public void testMemory()
    {
        ArrayList<List<Integer>> fapp = new ArrayList<>();
        fapp.add(new ArrayList<>(Arrays.asList(1, 8)));
        fapp.add(new ArrayList<>(Arrays.asList(2, 15)));
        fapp.add(new ArrayList<>(Arrays.asList(3, 9)));

        ArrayList<List<Integer>> bapp = new ArrayList<>();
        bapp.add(new ArrayList<>(Arrays.asList(1, 8)));
        bapp.add(new ArrayList<>(Arrays.asList(2, 11)));
        bapp.add(new ArrayList<>(Arrays.asList(3, 12)));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(3, 2));
        expected.add(Arrays.asList(1, 3));

        assertEquals(expected, Algorithms.memory(fapp, bapp, 20));

        expected.clear();
        fapp.clear();
        fapp.add(new ArrayList<>(Arrays.asList(1, 8)));
        fapp.add(new ArrayList<>(Arrays.asList(2, 7)));
        fapp.add(new ArrayList<>(Arrays.asList(3, 14)));

        bapp.clear();
        bapp.add(new ArrayList<>(Arrays.asList(1, 5)));
        bapp.add(new ArrayList<>(Arrays.asList(2, 10)));
        bapp.add(new ArrayList<>(Arrays.asList(3, 14)));

        expected.add(Arrays.asList(3, 1));
        assertEquals(expected, Algorithms.memory(fapp, bapp, 20));

        expected.clear();
        fapp.clear();
        fapp.add(new ArrayList<>(Arrays.asList(1, 3)));
        fapp.add(new ArrayList<>(Arrays.asList(2, 5)));
        fapp.add(new ArrayList<>(Arrays.asList(3, 7)));
        fapp.add(new ArrayList<>(Arrays.asList(4, 10)));

        bapp.clear();
        bapp.add(new ArrayList<>(Arrays.asList(1, 2)));
        bapp.add(new ArrayList<>(Arrays.asList(2, 3)));
        bapp.add(new ArrayList<>(Arrays.asList(3, 4)));
        bapp.add(new ArrayList<>(Arrays.asList(4, 5)));

        expected.add(Arrays.asList(3, 2));
        expected.add(Arrays.asList(2, 4));
        assertEquals(expected, Algorithms.memory(fapp, bapp, 10));
    }
}
