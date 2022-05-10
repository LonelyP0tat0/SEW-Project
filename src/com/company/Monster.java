package com.company;

public class Monster {

    String mClass;
    int atk;
    int hp;
    int baseHp;
    int xpDrop;
    int itemDropChance;

    Monster (String monsterClass) {

        mClass = monsterClass;

        if (mClass.equals("goblin")) {
            atk = 5;
            baseHp = 13;
            hp = 13;
            xpDrop = 5;
            itemDropChance = 40;
        } else if (mClass.equals("troll")) {
            atk = 7;
            baseHp = 18;
            hp = 18;
            xpDrop = 10;
            itemDropChance = 80;
        } else if (mClass.equals("skeleton")) {
            atk = 6;
            baseHp = 10;
            hp = 10;
            xpDrop = 2;
            itemDropChance = 30;
        }
    }

    @Override
    public String toString() {
        return "\nmonster class: " + mClass + "\n" + "monster hp: " + hp + "\n" + "monster atk: " + atk + "\n";
    }
}
