package com.company;

public class Boss extends Enemy {

    Boss (String bossName) {

        bName = bossName;
        bCLass = "boss";
        mClass = "";
        atk = 5;
        hp = 10;
        baseHp = 10;
        alive = true;
        passive = "rejuvenation";

    }

    @Override
    public String toString() {
        return "\nboss: " + bName + "\n" + "boss hp: " + hp + "\n" + "boss atk: " + atk + "\n" + "pasive: " + passive;
    }

}
