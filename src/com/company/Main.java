package com.company;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player("jerryTheSmol", "archer");
        Monster skeleton = new Monster("skeleton");
        Monster troll = new Monster("troll");
        Monster goblin = new Monster("goblin");
        Boss boss = new Boss("Malenia");

        player1.fight(troll);

    }

}
