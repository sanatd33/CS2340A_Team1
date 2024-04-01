package com.example.cs2340a_team1.model;

import java.util.HashMap;
import java.util.Set;

//Open/Closed Principle shown because classes can be added and extend off the interface
//LSP shown because superclass objects can be replaced with objects of the subclass
//Low Coupling shown because the interface is an example of the classes not be highly interconnected
public interface Sorting {
    abstract String sort(Set<String> names, HashMap<String, Integer> recipes);
}
