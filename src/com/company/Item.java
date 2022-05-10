package com.company;

public class Item {

    String iName;
    int dropChance;
    int atkBoost;
    int hpBoost;

    Item (String itemName) {

        iName = itemName;

        if (iName.equals("gandalf's ring")) {
            atkBoost = 4;
            dropChance = 20;
        } else if (iName.equals("knight's ring")) {
            atkBoost = 2;
            dropChance = 30;
        } else if (iName.equals("peasant's ring")) {
            atkBoost = 1;
            dropChance = 50;
        }

        if (iName.equals("commander's armor")) {
            hpBoost = 6;
            dropChance = 20;
        } else if (iName.equals("knight's armor")) {
            hpBoost = 4;
            dropChance = 30;
        } else if (iName.equals("peasant's armor")) {
            hpBoost = 2;
            dropChance = 50;
        }

    }

}
