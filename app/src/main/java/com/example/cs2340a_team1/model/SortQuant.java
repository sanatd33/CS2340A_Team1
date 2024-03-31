package com.example.cs2340a_team1.model;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SortQuant implements Sorting {
    //sort by quant
    @Override
    public String sort(Set<String> names, HashMap<String, Integer> recipes) {
        names = names.stream().sorted((o1, o2) -> recipes.get(o1) - recipes.get(o2))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        String list = "";
        for (String name : names) {
            list += name + "\t\t-\t\t" + recipes.get(name) + "\n";
        }
        return list;
    }
}
