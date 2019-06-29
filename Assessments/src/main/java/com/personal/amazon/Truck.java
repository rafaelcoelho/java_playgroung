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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Truck
{
    public static ArrayList<Integer> IDsOfPackages(int truckSpace, ArrayList<Integer> packages)
    {
        Integer remaning = truckSpace - 30;
        List<Integer> pkgSorted = new ArrayList<>(packages);
        List<Integer> result = new ArrayList<>();

        Collections.sort(pkgSorted);

        for (int i = pkgSorted.size() - 1; i >= 0; i--)
        {
            if (pkgSorted.get(i) > remaning)
                continue;

            for (int x = i - 1; x >= 0; x--)
            {
                if ((pkgSorted.get(x) + pkgSorted.get(i)) > remaning)
                    continue;

                if (pkgSorted.get(x) + pkgSorted.get(i) == remaning)
                {
                    result.add(packages.indexOf(pkgSorted.get(i)));
                    result.add(packages.indexOf(pkgSorted.get(x)));
                    Collections.sort(result);
                    return (ArrayList<Integer>) result;
                }
            }
        }

        return (ArrayList<Integer>) result;
    }
}
