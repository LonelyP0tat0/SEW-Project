package com.company;

public class Boss extends Enemy {

    Boss (String bossName) {

        bName = bossName;
        bCLass = "boss";
        mClass = "";
        atk = 5;
        hp = 50;
        baseHp = 50;
        passive = "rejuvenation";

    }

    @Override
    public String toString() {
        return "boss: " + bName + "\nboss hp: " + hp + "\nboss atk: " + atk + "\npasive: " + passive + "\n";
    }

}
