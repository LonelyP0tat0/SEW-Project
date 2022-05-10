package com.company;

public class Boss {

    String bName;
    String bCLass;
    String passive;
    int atk;
    int hp;
    int baseHp;
    boolean alive;

    Boss (String bossName) {

        bName = bossName;
        bCLass = "boss";
        atk = 20;
        hp = 100;
        baseHp = 100;
        alive = true;
        passive = "rejuvenation";

    }

    @Override
    public String toString() {
        return "\nboss: " + bName + "\n" + "boss hp: " + hp + "\n" + "boss atk: " + atk + "\n" + "pasive: " + passive;
    }

}
