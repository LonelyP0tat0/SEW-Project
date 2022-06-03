package com.company;

public class Boss extends Enemy {

    public Boss (String bossName) {
        bName = bossName;
        mClass = "";
        atk = 5;
        hp = 50;
        baseHp = 50;
        passive = "rejuvenation";
        alive = true;

    }

    @Override
    public String toString() {
        return "boss: " + bName + "\nboss hp: " + hp + "\nboss atk: " + atk + "\npasive: " + passive + "\n";
    }

}
