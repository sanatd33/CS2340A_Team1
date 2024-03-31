package com.example.cs2340a_team1.viewmodels;

import com.example.cs2340a_team1.model.CookbookData;

public class CookbookViewModel {
    private static CookbookViewModel instance;
    private CookbookData data;

    private CookbookViewModel() { }

    public static synchronized CookbookViewModel getInstance() {
        if (instance == null) {
            instance = new CookbookViewModel();
            instance.data = new CookbookData();
        }
        return instance;
    }

    public CookbookData getData() {
        return data;
    }
}
