package com.company;

public class Player {

    String pName;
    String pClass;
    String spell;
    int atk;
    int baseAtk;
    int hp;
    int baseHp;
    int xp = 0;
    int level = 1;
    boolean hasKey = false;

    Player (String playerName, String playerClass) {

        pName = playerName;
        pClass = playerClass;

        if (pClass.equals("warrior")) {
            atk = 9;
            baseAtk = 9;
            baseHp = 20;
            hp = 20;
            spell = "rage buff";
        } else if (pClass.equals("sorcerer")) {
            atk = 4;
            baseAtk = 4;
            baseHp = 15;
            hp = 15;
            spell = "fireball";
        } else if (pClass.equals("archer")) {
            atk = 12;
            baseAtk = 12;
            baseHp = 12;
            hp = 12;
            spell = "rapid-fire";
        }
    }

    @Override
    public String toString() {
        return "\nplayer name: " + pName + "\n" + "player class: " + pClass + "\n" + "player base hp: " + baseHp + "\n" + "player current hp: " + hp + "\n" + "player atk: " + atk;
    }
}