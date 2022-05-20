package com.company;

public class Item {

    String iName;
    int dropChance;
    int atkBoost;
    int hpBoost;

    Item (String itemName) {

        iName = itemName;

        switch (iName) {
            case "gandalf's ring" -> {
                atkBoost = 4;
                dropChance = 20;
            }
            case "knight's ring" -> {
                atkBoost = 2;
                dropChance = 30;
            }
            case "peasant's ring" -> {
                atkBoost = 1;
                dropChance = 50;
            }
            case "commander's armor" -> {
                hpBoost = 6;
                dropChance = 20;
            }
            case "knight's armor" -> {
                hpBoost = 4;
                dropChance = 30;
            }
            case "peasant's armor" -> {
                hpBoost = 2;
                dropChance = 50;
            }
        }

    }

}
