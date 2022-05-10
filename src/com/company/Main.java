package com.company;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player("jerryTheSmol", "sorcerer");
        Monster skeleton = new Monster("skeleton");
        Monster troll = new Monster("troll");
        Monster goblin = new Monster("goblin");
        Boss boss = new Boss("Malenia");

        fight(player1, troll);
        fight(player1, troll);

        heal(player1);

        spell(player1, troll);

    }

    public static void fight (Player player, Monster monster) {

        Item kingRing = new Item("gandalf's ring");
        Item knightRing = new Item("knight's ring");
        Item peasantRing = new Item("peasant's ring");
        Item kingArmor = new Item("commander's armor");
        Item knightArmor = new Item("knight's armor");
        Item peasantArmor = new Item("peasant's armor");

        Item[] allItems = {kingRing, knightRing, peasantRing, kingArmor, knightArmor, peasantArmor};

        monster.hp -= player.atk;
        player.hp -= monster.atk;

        if (monster.hp < 0) {
            monster.hp = 0;
        } else if (player.hp < 0) {
            player.hp = 0;
        }

        System.out.println("\n" + "You attack and the " + monster.mClass +  " fights back ");
        System.out.println("Your remaining hp: " + player.hp + ", the " + monster.mClass + "'s remaining hp: " + monster.hp);

        if (monster.hp == 0) {
            kill(player, monster);
            if (itemDrop(player, monster, allItems)) {
                System.out.println(player);
            }
            if (player.xp >= 15) {
                levelUp(player);
            }
        } else if (player.hp == 0) {
            System.out.println("You died!");
        }
    }

    public static void spell (Player player, Monster monster) {

        Item kingRing = new Item("gandalf's ring");
        Item knightRing = new Item("knight's ring");
        Item peasantRing = new Item("peasant's ring");
        Item kingArmor = new Item("commander's armor");
        Item knightArmor = new Item("knight's armor");
        Item peasantArmor = new Item("peasant's armor");

        Item[] allItems = {kingRing, knightRing, peasantRing, kingArmor, knightArmor, peasantArmor};

        switch(player.pClass) {

            case "warrior":
                player.atk += 8;
                player.hp -= monster.atk;

                if (monster.hp < 0) {
                    monster.hp = 0;
                } else if (player.hp < 0) {
                    player.hp = 0;
                }

                System.out.println("\n" + "You have become enraged, your atk has been increased by 4, then it attacks you");

                break;

            case "archer":
                monster.hp -= 18;
                player.hp -= monster.atk;

                if (monster.hp < 0) {
                    monster.hp = 0;
                } else if (player.hp < 0) {
                    player.hp = 0;
                }

                System.out.println("\n" + "You fire a rapid volley or arrows dealing 18 dmg to your opponent, then it attacks you back");

                break;

            case "sorcerer":
                monster.hp -= 5 + (int) ((monster.baseHp - monster.hp) * 0.7);
                player.hp -= monster.atk;

                if (monster.hp < 0) {
                    monster.hp = 0;
                } else if (player.hp < 0) {
                    player.hp = 0;
                }

                System.out.println("\n" + "You shoot a giant fireball at your enemy dealing missing health dmg, then your opponent attacks you back");

                break;
        }

        System.out.println("Your remaining hp: " + player.hp + ", the " + monster.mClass + "'s remaining hp: " + monster.hp);

        if (monster.hp == 0) {
            kill(player, monster);
            if (itemDrop(player, monster, allItems)) {
                System.out.println(player);
            }
            if (player.xp >= 15) {
                levelUp(player);
            }
        } else if (player.hp == 0) {
            System.out.println("You died!");
        }

    }

    public static void bossFight (Player player, Boss boss) {

        if (boss.passive.equals("rejuvenation") && (boss.hp + 2) <= boss.baseHp) {
            boss.hp += 2;
            System.out.println(boss.bName + " has healed 2 hp back thanks to its passive, remaining hp:" + boss.hp);
        }

        boss.hp -= player.atk;
        player.hp -= boss.atk;

        if (boss.hp < 0) {
            boss.hp = 0;
        } else if (player.hp < 0) {
            player.hp = 0;
        }

        System.out.println("\n" + "You attack and " + boss.bName +  " strikes back");
        System.out.println("Your remaining hp: " + player.hp + ", " + boss.bName + "'s remaining hp: " + boss.hp);

        if (boss.hp == 0) {
            bossKill(player, boss);
            System.out.println("Good job, you have escaped. Now go do something with your life");
        } else if (player.hp == 0) {
            System.out.println("You died!");
        }

    }

    public static void heal (Player player) {
        if (player.hp + 10 > player.baseHp) {
            player.hp = player.baseHp;
        } else {
            player.hp += 10;
        }
        System.out.println("\n" + "You drink a healing potions and gain 10 hp, current hp: " + player.hp);
    }

    static void levelUp (Player player) {
        player.level += 1;
        player.xp -= 15;
        player.atk += 2;
        player.baseHp += 4;
        System.out.println("You have leveled up, your new level is now: " + player.level);
        System.out.println("All your base stats have been boosted:\n" + player);
    }

    static void kill (Player player, Monster monster) {
        monster.hp = monster.baseHp;
        System.out.println("the " + monster.mClass + " is dead!");
        player.xp += monster.xpDrop;
        player.atk = player.baseAtk;
        System.out.println("you have gained " + monster.xpDrop + " xp");
    }

    static void bossKill (Player player, Boss boss) {
        boss.hp = boss.baseHp;
        boss.alive = false;
        player.atk = player.baseAtk;
        System.out.println("Congratulations, you have defeated " + boss.bName + "!" + "\nYou can now escape the dungeon with the boss's master key!");
        player.hasKey = true;
    }

    static boolean itemDrop (Player player, Monster monster, Item[] itemList) {

        double dropChance = ((Math.random() * (100)));
        double itemChance = ((Math.random() * (100)));
        double fiftyFifty = ((Math.random() * (100)));

        if (fiftyFifty < 50 && dropChance <= monster.itemDropChance && itemChance <= itemList[0].dropChance) {
            player.atk += itemList[0].atkBoost;
            System.out.println("You have found " + itemList[0].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty < 50 && dropChance <= monster.itemDropChance && (itemChance > itemList[0].dropChance && itemChance <= itemList[1].dropChance + itemList[0].dropChance)) {
            player.atk += itemList[1].atkBoost;
            System.out.println("You have found the " + itemList[1].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty < 50 && dropChance <= monster.itemDropChance && (itemChance > itemList[1].dropChance + itemList[0].dropChance)) {
            player.atk += itemList[2].atkBoost;
            System.out.println("You have found the " + itemList[2].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= monster.itemDropChance && itemChance <= itemList[3].dropChance) {
            player.baseHp += itemList[3].hpBoost;
            System.out.println("You have found " + itemList[3].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= monster.itemDropChance && (itemChance > itemList[3].dropChance && itemChance <= itemList[4].dropChance + itemList[3].dropChance)) {
            player.baseHp += itemList[4].hpBoost;
            System.out.println("You have found the " + itemList[4].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        } else if (fiftyFifty >= 50 && dropChance <= monster.itemDropChance && (itemChance > itemList[4].dropChance + itemList[3].dropChance)) {
            player.baseHp += itemList[5].hpBoost;
            System.out.println("You have found the " + itemList[5].iName + " on the enemy, you immediately equip it and improve your stats");
            return true;
        }

        return false;
    }

}
