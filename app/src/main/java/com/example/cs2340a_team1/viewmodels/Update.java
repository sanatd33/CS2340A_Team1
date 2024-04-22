package com.example.cs2340a_team1.viewmodels;

import android.util.Pair;

import com.example.cs2340a_team1.model.IngredientData;

import java.util.HashMap;

public class Update {
    static public String updateList(HashMap<String, Pair<IngredientData, Integer>> map) {
        String list = "";
        for (String s : map.keySet()) {
            int count = map.get(s).second;
            list += s + "\t\t-\t\t" + count + "\n";
        }
        return list;
    }
}
