package com.company;

public class Monster extends Enemy {

    Monster (String monsterClass) {

        mClass = monsterClass;

        switch (mClass) {
            case "goblin" -> {
                atk = 5;
                baseHp = 13;
                hp = 13;
                xpDrop = 5;
                itemDropChance = 40;
                passive = "";
                alive = true;
            }
            case "troll" -> {
                atk = 7;
                baseHp = 18;
                hp = 18;
                xpDrop = 10;
                itemDropChance = 80;
                passive = "";
                alive = true;
            }
            case "skeleton" -> {
                atk = 6;
                baseHp = 10;
                hp = 10;
                xpDrop = 2;
                itemDropChance = 30;
                passive = "";
                alive = true;
            }
        }
    }

    @Override
    public String toString() {
        return "monster class: " + mClass + "\nmonster hp: " + hp + "\nmonster atk: " + atk + "\nitem drop rate: " + itemDropChance + "%\n";
    }
}
