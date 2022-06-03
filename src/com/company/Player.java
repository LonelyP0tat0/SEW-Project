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
    boolean alive;

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
                alive = true;
            }
            case "sorcerer" -> {
                atk = 4;
                baseAtk = 4;
                baseHp = 15;
                hp = 15;
                spell = "fireball";
                alive = true;
            }
            case "archer" -> {
                atk = 12;
                baseAtk = 12;
                baseHp = 12;
                hp = 12;
                spell = "rapid-fire";
                alive = true;
            }
        }
    }

    /**
     * initates a battle between the player and the chosen enemy, hp decreases
     * based on the others atk. Can trigger the method itemDrop() and kill()
     * prints out dialogue accordingly
     * If the defeated enemy was a boss, a different dialogue will appear
     * @param enemy
     */

    void fight (Enemy enemy) {

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
        } else {
            enemy.hp = baseHp;
        }

        enemy.hp -= atk;
        hp -= enemy.atk;

        if (enemy.hp < 0) {
            enemy.hp = 0;
        }
        if (hp < 0) {
            hp = 0;
        }

        if (enemy.mClass != "") {
            System.out.println("\n" + "You attack and the " + enemy.mClass +  " fights back ");
            System.out.println("Your remaining hp: " + hp + ", the " + enemy.mClass + "'s remaining hp: " + enemy.hp);
        } else {
            System.out.println("\n" + "You attack and " + enemy.bName +  " strikes back");
            System.out.println("Your remaining hp: " + hp + ", " + enemy.bName + "'s remaining hp: " + enemy.hp);
        }

        if (enemy.hp == 0 && hp != 0) {
            kill(enemy);
            if (itemDrop(enemy, allItems)) {
                System.out.println(this);
            }
            if (xp >= 15) {
                levelUp();
            }
        } else if (enemy.hp == 0 && enemy.mClass == "" && hp != 0) {
            System.out.println("Congratulations, you have defeated " + enemy.bName + "!" + "\nYou can now escape the dungeon with the boss's master key!");
        } else if (hp == 0) {
            System.out.println("You died!");
        }
    }

    /**
     * activates the players ability based on their class (archer, mage, knight)
     * the player uses their abilty and the monster attacks, both lose hp unless
     * it's a buff ability (knigts). Can trigger the methods itemDrop() and kill()
     * prints out dialogue accordingly
     * If the defeated enemy was a boss, a different dialogue will appear
     * @param enemy
     */

    void spell (Enemy enemy) {

        Item kingRing = new Item("gandalf's ring");
        Item knightRing = new Item("knight's ring");
        Item peasantRing = new Item("peasant's ring");
        Item kingArmor = new Item("commander's armor");
        Item knightArmor = new Item("knight's armor");
        Item peasantArmor = new Item("peasant's armor");

        Item[] allItems = {kingRing, knightRing, peasantRing, kingArmor, knightArmor, peasantArmor};

        switch(pClass) {

            case "warrior":

                if (enemy.passive.equals("rejuvenation") && (enemy.hp + 2) <= enemy.baseHp) {
                    enemy.hp += 2;
                    System.out.println(enemy.bName + " has healed 2 hp back thanks to its passive, remaining hp:" + enemy.hp);
                }

                atk += 8;
                hp -= enemy.atk;
                enemy.hp -= atk;

                if (enemy.hp < 0) {
                    enemy.hp = 0;
                } else if (hp < 0) {
                    hp = 0;
                }

                System.out.println("\n" + "You have become enraged, your atk has been increased by 4, then it attacks you");

                break;

            case "archer":

                if (enemy.passive.equals("rejuvenation") && (enemy.hp + 2) <= enemy.baseHp) {
                    enemy.hp += 2;
                    System.out.println(enemy.bName + " has healed 2 hp back thanks to its passive, remaining hp:" + enemy.hp);
                }

                enemy.hp -= 18;
                hp -= enemy.atk;

                if (enemy.hp < 0) {
                    enemy.hp = 0;
                } else if (hp < 0) {
                    hp = 0;
                }

                System.out.println("\n" + "You fire a rapid volley of arrows dealing 18 dmg to your opponent, then it attacks you back");

                break;

            case "sorcerer":

                if (enemy.passive.equals("rejuvenation") && (enemy.hp + 2) <= enemy.baseHp) {
                    enemy.hp += 2;
                    System.out.println(enemy.bName + " has healed 2 hp back thanks to its passive, remaining hp:" + enemy.hp);
                }

                enemy.hp -= 5 + (int) ((enemy.baseHp - enemy.hp) * 0.7);
                hp -= enemy.atk;

                if (enemy.hp < 0) {
                    enemy.hp = 0;
                } else if (hp < 0) {
                    hp = 0;
                }

                System.out.println("\n" + "You shoot a giant fireball at your enemy dealing missing health dmg, then your opponent attacks you back");

                break;
        }

        if (enemy.mClass.equals("")) {
            System.out.println("Your remaining hp: " + hp + ", " + enemy.bName + "'s remaining hp: " + enemy.hp);
        } else {
            System.out.println("Your remaining hp: " + hp + ", the " + enemy.mClass + "'s remaining hp: " + enemy.hp);
        }

        if (enemy.hp == 0 && hp != 0) {
            kill(enemy);
            if (itemDrop(enemy, allItems)) {
                System.out.println(this);
            }
            if (xp >= 15) {
                levelUp();
            }
        } else if (enemy.hp == 0 && enemy.mClass == "" && hp != 0) {
            System.out.println("Congratulations, you have defeated " + enemy.bName + "!" + "\nYou can now escape the dungeon with the boss's master key!");
        } else if (hp == 0) {
            System.out.println("You died!");
        }

    }

    /**
     * increases the players current hp by 10
     * you can not heal more than you have max hp
     * prints out dialogue accordingly
     */

    void heal () {
        if (hp == baseHp) {
            System.out.println("\n" + "You are already at full health, you can't drink your health potion!");
        } else if ((hp + 10) > baseHp || (hp + 10) == baseHp) {
            hp = baseHp;
            System.out.println("\n" + "You drink a healing potion and heal to full");
        } else {
            hp += 10;
            System.out.println("\n" + "You drink a healing potion and gain 10 hp, current hp: " + hp);
        }
    }

    /**
     * players level up when they've gained enough xp from the method kill()
     * the players base stats get increased by 2 and heals them for 4 hp
     * prints out dialogue accordingly
     */

    private void levelUp () {
        if (level != 5) {
            level += 1;
            xp -= 15;
            atk += 2;
            baseHp += 4;
            System.out.println("\nYou have leveled up, your new level is now: " + level);
            System.out.println("All your base stats have been boosted:\n" + "\n" + this);
        }
    }

    /**
     * kill() gets triggered by the methods fight() and spell()
     * player gains xp and can trigger the method levelUp() when the enemies hp=0
     * the enemies hp gets reset after dying
     * prints out dialogue accordingly
     * If the defeated enemy was a boss, a different dialogue will appear
     * @param enemy
     */

    private void kill (Enemy enemy) {
        enemy.alive = false;
        if (enemy.mClass == "") {
            System.out.println("Congratulations, you have defeated " + enemy.bName + "!" + "\nYou can now escape the dungeon with the boss's master key!");
        } else {
            System.out.println("the " + enemy.mClass + " is dead!");
            if (level != 5) {
                xp += enemy.xpDrop;
            } else {
                System.out.println("You have reached the max level!");
            }
            atk = baseAtk;
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
     * @param enemy
     * @param itemList      //Arraylist of all the possible items
     * @return              //returns true if an item has been dropped by the enemy
     */

    private boolean itemDrop (Enemy enemy, Item[] itemList) {

        double dropChance = ((Math.random() * (100)));
        double itemChance = ((Math.random() * (100)));
        double fiftyFifty = ((Math.random() * (100)));

        if (fiftyFifty < 50 && dropChance <= enemy.itemDropChance && itemChance <= itemList[0].dropChance) {
            atk += itemList[0].atkBoost;
            System.out.println("\nYou have found " + itemList[0].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty < 50 && dropChance <= enemy.itemDropChance && (itemChance > itemList[0].dropChance && itemChance <= itemList[1].dropChance + itemList[0].dropChance)) {
            atk += itemList[1].atkBoost;
            System.out.println("\nYou have found the " + itemList[1].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty < 50 && dropChance <= enemy.itemDropChance && (itemChance > itemList[1].dropChance + itemList[0].dropChance)) {
            atk += itemList[2].atkBoost;
            System.out.println("\nYou have found the " + itemList[2].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= enemy.itemDropChance && itemChance <= itemList[3].dropChance) {
            baseHp += itemList[3].hpBoost;
            System.out.println("\nYou have found " + itemList[3].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= enemy.itemDropChance && (itemChance > itemList[3].dropChance && itemChance <= itemList[4].dropChance + itemList[3].dropChance)) {
            baseHp += itemList[4].hpBoost;
            System.out.println("\nYou have found the " + itemList[4].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= enemy.itemDropChance && (itemChance > itemList[4].dropChance + itemList[3].dropChance)) {
            baseHp += itemList[5].hpBoost;
            System.out.println("\nYou have found the " + itemList[5].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "player name: " + pName + "\nplayer class: " + pClass + "\nplayer base hp: " + baseHp + "\nplayer current hp: " + hp + "\nplayer atk: " + atk;
    }
}