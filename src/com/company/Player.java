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

    Player (String playerName, String playerClass) {

        pName = playerName;
        pClass = playerClass;

        switch (pClass) {
            case "warrior" -> {
                atk = 9;
                baseAtk = 9;
                baseHp = 20;
                hp = 20;
                spell = "rage buff";
            }
            case "sorcerer" -> {
                atk = 4;
                baseAtk = 4;
                baseHp = 15;
                hp = 15;
                spell = "fireball";
            }
            case "archer" -> {
                atk = 12;
                baseAtk = 12;
                baseHp = 12;
                hp = 12;
                spell = "rapid-fire";
            }
        }
    }

    @Override
    public String toString() {
        return "player name: " + pName + "\nplayer class: " + pClass + "\nplayer base hp: " + baseHp + "\nplayer current hp: " + hp + "\nplayer atk: " + atk + "\n";
    }
}