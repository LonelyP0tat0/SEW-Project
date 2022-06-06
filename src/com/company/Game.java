package com.company;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Game extends Application {

    Player player1 = new Player("player", "archer");
    Enemy s1 = new Monster("skeleton");
    Enemy s2 = new Monster("skeleton");
    Enemy s3 = new Monster("skeleton");
    Enemy s4 = new Monster("skeleton");
    Enemy s5 = new Monster("skeleton");
    Enemy g1 = new Monster("goblin");
    Enemy g2 = new Monster("goblin");
    Enemy g3 = new Monster("goblin");
    Enemy g4 = new Monster("goblin");
    Enemy g5 = new Monster("goblin");
    Enemy g6 = new Monster("goblin");
    Enemy g7 = new Monster("goblin");
    Enemy t1 = new Monster("troll");
    Enemy t2 = new Monster("troll");
    Enemy t3 = new Monster("troll");
    Enemy boss = new Boss("Malenia");

    public int enemyCounter = 0;
    public int spellCooldown = 0;

    Stage stage;

    Scene scene1;
    VBox vbox1;
    Button button1;

    Scene scene2;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Menu");

        scene1 = gameMenu();
        scene2 = gameStart();

        stage.setScene(scene1);

        stage.show();
    }

    public void switchScenes(Scene scene) {
        stage.setScene(scene);
    }

    public Scene gameMenu() {
        button1 = new Button("START");
        button1.setOnAction(e -> switchScenes(scene2));
        vbox1 = new VBox(button1);
        scene1 = new Scene(vbox1, 800, 500);

        return scene1;
    }

    private Scene gameStart() {
        stage.setTitle("Game");

        Enemy[] enemies = new Enemy[]{s1, s2, s3, g1, g2, s4, s5, t1, g3, g4, g5, t2, g6, g7, t3, boss};

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 600, 500);
        stage.setScene(scene);

        gridPane.setHgap(8);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Button fightB = new Button("fight");
        fightB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (spellCooldown > 0) {
                    spellCooldown -= 1;
                } else {
                    spellCooldown = 0;
                }

                if (enemies[enemyCounter].alive) {
                    player1.fight(enemies[enemyCounter]);
                    if (player1.hp == 0) {
                        stage.close();
                    }
                }

                if (enemyCounter == enemies.length - 1 && !enemies[enemyCounter].alive) {
                    stage.close();
                } else if (!enemies[enemyCounter].alive) {
                    enemyCounter++;
                }
            }
        });

        Button healB = new Button("heal");
        healB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                player1.heal();
            }
        });

        Button spellB = new Button("spell: " + player1.spell);
        spellB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (spellCooldown == 0) {
                    spellCooldown += 3;
                    if (enemies[enemyCounter].alive) {
                        player1.spell(enemies[enemyCounter]);
                        if (player1.hp == 0) {
                            stage.close();
                        }
                        if (enemies[enemyCounter].mClass.equals("") && enemies[enemyCounter].hp == 0) {
                            stage.close();
                        }
                    }
                    if (!enemies[enemyCounter].alive) {
                        enemyCounter++;
                    }
                    if (enemyCounter == enemies.length - 1 && !enemies[enemyCounter].alive) {
                        stage.close();
                    }
                } else {
                    System.out.println("\nYou can't use this yet. spell cooldown: " + spellCooldown);
                }
            }
        });

        Button statsB = new Button("stats");
        statsB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println(player1);
                System.out.println(enemies[enemyCounter]);
            }
        });

        Button spellCDB = new Button("spell Cooldown");
        spellCDB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (spellCooldown == 0) {
                    System.out.println("\n" + player1.spell + " is ready to use!");
                } else {
                    System.out.println("Remaining cooldown: " + spellCooldown);
                }
            }
        });

        gridPane.add(fightB, 0, 40);
        gridPane.add(healB, 15, 40);
        gridPane.add(spellB, 30, 40);
        gridPane.add(statsB, 45, 40);
        gridPane.add(spellCDB, 30, 41);

        gridPane.setGridLinesVisible(false);

        stage.show();
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

