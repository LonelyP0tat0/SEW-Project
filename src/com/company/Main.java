package com.company;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player("jerryTheSmol", "archer");
        Monster skeleton = new Monster("skeleton");
        Monster troll = new Monster("troll");
        Monster goblin = new Monster("goblin");
        Boss boss = new Boss("Malenia");

        fight(player1, boss);

    }

    /**
     * initates a battle between the player and the chosen enemy, hp decreases
     * based on the others atk. Can trigger the method itemDrop() and kill()
     * prints out dialogue accordingly
     * If the defeated enemy was a boss, a different dialogue will appear
     * @param player
     * @param enemy
     */

    public static void fight (Player player, Enemy enemy) {

        Item kingRing = new Item("gandalf's ring");
        Item knightRing = new Item("knight's ring");
        Item peasantRing = new Item("peasant's ring");
        Item kingArmor = new Item("commander's armor");
        Item knightArmor = new Item("knight's armor");
        Item peasantArmor = new Item("peasant's armor");

        Item[] allItems = {kingRing, knightRing, peasantRing, kingArmor, knightArmor, peasantArmor};

        if (enemy.passive.equals("rejuvenation") && (enemy.hp + 2) <= enemy.baseHp) {
            enemy.hp += 2;
            System.out.println(enemy.bName + " has healed 2 hp back thanks to its passive, remaining hp:" + enemy.hp);
        }

        enemy.hp -= player.atk;
        player.hp -= enemy.atk;

        if (enemy.hp < 0) {
            enemy.hp = 0;
        }
        if (player.hp < 0) {
            player.hp = 0;
        }

        if (enemy.mClass != "") {
            System.out.println("\n" + "You attack and the " + enemy.mClass +  " fights back ");
            System.out.println("Your remaining hp: " + player.hp + ", the " + enemy.mClass + "'s remaining hp: " + enemy.hp);
        } else {
            System.out.println("\n" + "You attack and " + enemy.bName +  " strikes back");
            System.out.println("Your remaining hp: " + player.hp + ", " + enemy.bName + "'s remaining hp: " + enemy.hp);
        }

        if (enemy.hp == 0) {
            kill(player, enemy);
            if (itemDrop(player, enemy, allItems)) {
                System.out.println(player);
            }
            if (player.xp >= 15) {
                levelUp(player);
            }
        } else if (enemy.hp == 0 && enemy.mClass == "") {
            System.out.println("Good job, you have escaped. Now go do something with your life");
        } else if (player.hp == 0) {
            System.out.println("You died!");
        }
    }

    /**
     * activates the players ability based on their class (archer, mage, knight)
     * the player uses their abilty and the monster attacks, both lose hp unless
     * it's a buff ability (knigts). Can trigger the methods itemDrop() and kill()
     * prints out dialogue accordingly
     * If the defeated enemy was a boss, a different dialogue will appear
     * @param player
     * @param enemy
     */

    public static void spell (Player player, Enemy enemy) {

        Item kingRing = new Item("gandalf's ring");
        Item knightRing = new Item("knight's ring");
        Item peasantRing = new Item("peasant's ring");
        Item kingArmor = new Item("commander's armor");
        Item knightArmor = new Item("knight's armor");
        Item peasantArmor = new Item("peasant's armor");

        Item[] allItems = {kingRing, knightRing, peasantRing, kingArmor, knightArmor, peasantArmor};

        switch(player.pClass) {

            case "warrior":

                if (enemy.passive.equals("rejuvenation") && (enemy.hp + 2) <= enemy.baseHp) {
                    enemy.hp += 2;
                    System.out.println(enemy.bName + " has healed 2 hp back thanks to its passive, remaining hp:" + enemy.hp);
                }

                player.atk += 8;
                player.hp -= enemy.atk;


                if (enemy.hp < 0) {
                    enemy.hp = 0;
                } else if (player.hp < 0) {
                    player.hp = 0;
                }

                System.out.println("\n" + "You have become enraged, your atk has been increased by 4, then it attacks you");

                break;

            case "archer":

                if (enemy.passive.equals("rejuvenation") && (enemy.hp + 2) <= enemy.baseHp) {
                    enemy.hp += 2;
                    System.out.println(enemy.bName + " has healed 2 hp back thanks to its passive, remaining hp:" + enemy.hp);
                }

                enemy.hp -= 18;
                player.hp -= enemy.atk;

                if (enemy.hp < 0) {
                    enemy.hp = 0;
                } else if (player.hp < 0) {
                    player.hp = 0;
                }

                System.out.println("\n" + "You fire a rapid volley or arrows dealing 18 dmg to your opponent, then it attacks you back");

                break;

            case "sorcerer":

                if (enemy.passive.equals("rejuvenation") && (enemy.hp + 2) <= enemy.baseHp) {
                    enemy.hp += 2;
                    System.out.println(enemy.bName + " has healed 2 hp back thanks to its passive, remaining hp:" + enemy.hp);
                }

                enemy.hp -= 5 + (int) ((enemy.baseHp - enemy.hp) * 0.7);
                player.hp -= enemy.atk;

                if (enemy.hp < 0) {
                    enemy.hp = 0;
                } else if (player.hp < 0) {
                    player.hp = 0;
                }

                System.out.println("\n" + "You shoot a giant fireball at your enemy dealing missing health dmg, then your opponent attacks you back");

                break;
        }

        System.out.println("Your remaining hp: " + player.hp + ", the " + enemy.mClass + "'s remaining hp: " + enemy.hp);

        if (enemy.hp == 0) {
            kill(player, enemy);
            if (itemDrop(player, enemy, allItems)) {
                System.out.println(player);
            }
            if (player.xp >= 15) {
                levelUp(player);
            }
        } else if (enemy.hp == 0 && enemy.mClass == "") {
            System.out.println("Congratulations, you have defeated " + enemy.bName + "!" + "\nYou can now escape the dungeon with the boss's master key!");
        } else if (player.hp == 0) {
            System.out.println("You died!");
        }

    }

    /**
     * increases the players current hp by 10
     * you can not heal more than you have max hp
     * prints out dialogue accordingly
     * @param player
     */

    public static void heal (Player player) {
        if (player.hp + 10 > player.baseHp) {
            player.hp = player.baseHp;
            System.out.println("\n" + "You drink a healing potion and heal to full");
        } else if (player.hp == player.baseHp) {
            System.out.println("\n" + "You are already at full health, you can't drink another health potion!");
        } else {
            player.hp += 10;
        }
        System.out.println("\n" + "You drink a healing potion and gain 10 hp, current hp: " + player.hp);
    }

    /**
     * players level up when they've gained enough xp from the method kill()
     * the players base stats get increased by 2 and heals them for 4 hp
     * prints out dialogue accordingly
     * @param player
     */

    static void levelUp (Player player) {
        player.level += 1;
        player.xp -= 15;
        player.atk += 2;
        player.baseHp += 4;
        System.out.println("You have leveled up, your new level is now: " + player.level);
        System.out.println("All your base stats have been boosted:\n" + player);
    }

    /**
     * kill() gets triggered by the methods fight() and spell()
     * player gains xp and can trigger the method levelUp() when the enemies hp=0
     * the enemies hp gets reset after dying
     * prints out dialogue accordingly
     * If the defeated enemy was a boss, a different dialogue will appear
     * @param player
     * @param enemy
     */

    static void kill (Player player, Enemy enemy) {
        enemy.hp = enemy.baseHp;
        if (enemy.mClass == "") {
            enemy.alive = false;
            System.out.println("Congratulations, you have defeated " + enemy.bName + "!" + "\nYou can now escape the dungeon with the boss's master key!");
        } else {
            System.out.println("the " + enemy.mClass + " is dead!");
            player.xp += enemy.xpDrop;
            player.atk = player.baseAtk;
            System.out.println("you have gained " + enemy.xpDrop + " xp");
        }
    }

    /**
     * If the enemy dies, there's a chance it'll
     * drop loot (every enemy has different drop rates), then there's a 50% chance
     * for one of the 3 armor pieces (permanent hp boost) and 50% chance for one of
     * the 3 rings (permanent atk boost). There are 3 rarities, each with their own droprate
     * Everything is calculated randomly with Math.random()
     * prints out dialogue accordingly
     * @param player
     * @param enemy
     * @param itemList      //Arraylist of all the possible items
     * @return              //returns true if an item has been dropped by the enemy
     */

    static boolean itemDrop (Player player, Enemy enemy, Item[] itemList) {

        double dropChance = ((Math.random() * (100)));
        double itemChance = ((Math.random() * (100)));
        double fiftyFifty = ((Math.random() * (100)));

        if (fiftyFifty < 50 && dropChance <= enemy.itemDropChance && itemChance <= itemList[0].dropChance) {
            player.atk += itemList[0].atkBoost;
            System.out.println("You have found " + itemList[0].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty < 50 && dropChance <= enemy.itemDropChance && (itemChance > itemList[0].dropChance && itemChance <= itemList[1].dropChance + itemList[0].dropChance)) {
            player.atk += itemList[1].atkBoost;
            System.out.println("You have found the " + itemList[1].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty < 50 && dropChance <= enemy.itemDropChance && (itemChance > itemList[1].dropChance + itemList[0].dropChance)) {
            player.atk += itemList[2].atkBoost;
            System.out.println("You have found the " + itemList[2].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= enemy.itemDropChance && itemChance <= itemList[3].dropChance) {
            player.baseHp += itemList[3].hpBoost;
            System.out.println("You have found " + itemList[3].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= enemy.itemDropChance && (itemChance > itemList[3].dropChance && itemChance <= itemList[4].dropChance + itemList[3].dropChance)) {
            player.baseHp += itemList[4].hpBoost;
            System.out.println("You have found the " + itemList[4].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= enemy.itemDropChance && (itemChance > itemList[4].dropChance + itemList[3].dropChance)) {
            player.baseHp += itemList[5].hpBoost;
            System.out.println("You have found the " + itemList[5].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        }

        return false;
    }

}
