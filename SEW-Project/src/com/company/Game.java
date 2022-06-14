package com.company;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;


public class Game extends Application {

    Stage stage;

    Scene scene1;
    VBox vbox1;
    VBox vbox2;
    VBox vbox3;
    VBox vbox4;
    VBox vbox5;
    VBox vbox6;

    Button button1;
    Button button2;

    Scene scene2;
    Scene scene3;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Menu");

        scene1 = gameMenu();
        scene2 = nameClassMenu();
        scene3 = gameStart();

        stage.setScene(scene1);

        stage.show();
    }

    public void switchScenes(Scene scene) {
        stage.setScene(scene);
    }

    public Scene gameMenu() {
        stage.setResizable(false);

        button1 = new Button("START");
        button1.setOnAction(e -> switchScenes(scene2));

        button2 = new Button("EXIT");
        button2.setOnAction(e -> stage.close());

        Label text1 = new Label();
        text1.setText("The Fox's Lair");
        text1.setFont(new Font("Book Antiqua", 40));
        text1.setTextFill(Color.WHITE);
        text1.setPadding(new Insets(50, 50, 50, 175));

        vbox1 = new VBox(10);
        vbox1.setPadding(new Insets(20, 20, 20, 20));
        vbox1.getChildren().addAll(text1);

        vbox2 = new VBox(10);
        vbox2.setPadding(new Insets(20, 20, 0, 295));
        vbox2.getChildren().addAll(button1);

        vbox5 = new VBox(10);
        vbox5.setPadding(new Insets(0, 0, 0, 300));
        vbox5.getChildren().addAll(button2);

        vbox3 = new VBox(10);
        vbox3.getChildren().addAll(vbox1, vbox2, vbox5);
        vbox3.setStyle("-fx-background-image: url('/Backgrounds/MenuBackground1.png'); -fx-background-repeat: stretch;");

        scene1 = new Scene(vbox3, 640, 360);

        return scene1;
    }

    String playerclass = "";

    public Scene nameClassMenu () {
        stage.setResizable(false);

        Label label1 = new Label();
        label1.setText("Enter your player name:");
        label1.setTextFill(Color.WHITE);
        label1.setFont(new Font("Arial", 15));
        label1.setPadding(new Insets(0, 0, 0, -27));

        TextField text1 = new TextField();
        text1.setMaxWidth(100);

        Label label2 = new Label();
        label2.setText("Choose your class: warrior, sorcerer, archer");
        label2.setTextFill(Color.WHITE);
        label2.setFont(new Font("Arial", 15));
        label2.setPadding(new Insets(0, 0, 0, -90));

        TextField text2 = new TextField();
        text2.setMaxWidth(100);
        playerclass = text2.getText();

        button1 = new Button("next");
        button1.setOnAction(e -> switchScenes(scene3));

        vbox6 = new VBox(10);
        vbox6.setPadding(new Insets(0, 0, 0, 30));
        vbox6.getChildren().addAll(button1);

        vbox4 = new VBox(10);
        vbox4.setPadding(new Insets(100, 20, 20, 265));
        vbox4.getChildren().addAll(label1, text1, label2, text2, vbox6);
        vbox4.setStyle("-fx-background-image: url('/Backgrounds/MenuBackground2.png'); -fx-background-repeat: stretch;");

        scene2 = new Scene(vbox4, 626, 340);

        return scene2;
    }

    Player player1 = new Player("player", "warrior");
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
    Enemy o1 = new Monster("orc");
    Enemy o2 = new Monster("orc");
    Enemy o3 = new Monster("orc");
    Enemy boss = new Boss("Ahri");

    ImageView archer = new ImageView(".\\Sprites\\Archer.png");
    ImageView warrior = new ImageView(".\\Sprites\\Warrior.png");
    ImageView sorcerer = new ImageView(".\\Sprites\\Sorcerer.png");
    ImageView goblin = new ImageView(".\\Sprites\\Goblin.png");
    ImageView skeleton = new ImageView(".\\Sprites\\Skeleton.png");
    ImageView orc = new ImageView(".\\Sprites\\Orc.png");
    ImageView ahri = new ImageView(".\\Sprites\\Ahri.png");

    ImageView spellIcon;

    public int enemyCounter = 0;
    public int spellCooldown = 0;
    public int healCooldown = 0;

    private Scene gameStart(){
        stage.setTitle("Game");
        stage.setResizable(false);

        ImageView fightIcon = new ImageView(".\\Icons\\FightIcon.png");
        ImageView healIcon = new ImageView(".\\Icons\\HealIcon.png");
        ImageView spellCDIcon = new ImageView(".\\Icons\\SpellCDIcon.png");

        if (player1.pClass.equals("archer")) {
            spellIcon = new ImageView(".\\Icons\\ArcherSpellIcon.png");
        } else if (player1.pClass.equals("warrior")) {
            spellIcon = new ImageView(".\\Icons\\WarriorSpellIcon.png");
        } else {
            spellIcon = new ImageView(".\\Icons\\SorcererSpellIcon.png");
        }

        archer.setFitHeight(192);
        archer.setFitWidth(192);
        warrior.setFitHeight(192);
        warrior.setFitWidth(192);
        sorcerer.setFitHeight(192);
        sorcerer.setFitWidth(192);
        goblin.setFitHeight(192);
        goblin.setFitWidth(192);
        skeleton.setFitHeight(192);
        skeleton.setFitWidth(192);
        orc.setFitHeight(192);
        orc.setFitWidth(192);
        ahri.setFitHeight(210);
        ahri.setFitWidth(210);

        Enemy[] enemies = new Enemy[]{s1, s2, s3, g1, g2, s4, s5, o1, g3, g4, g5, o2, g6, g7, o3, boss};

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 785, 550);
        stage.setScene(scene);

        gridPane.setHgap(8);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.setStyle("-fx-background-image:url('/Backgrounds/GameBackground.png'); -fx-background-repeat: stretch;");

        gridPane.add(skeleton, 11, 15);

        Button fightB = new Button("fight", fightIcon);
        fightB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (spellCooldown > 0) {
                    spellCooldown -= 1;
                } else {
                    spellCooldown = 0;
                }

                if (healCooldown > 0) {
                    healCooldown -= 1;
                } else {
                    healCooldown = 0;
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

                switch (enemyCounter) {

                    case 0:
                        break;
                    case 1:
                        if (s1.hp == 0 && s2.hp == s2.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (s2.hp == s2.baseHp) {
                            gridPane.add(skeleton, 11, 15);
                        }
                        break;
                    case 2:
                        if (s2.hp == 0 && s3.hp == s3.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (s3.hp == s3.baseHp) {
                            gridPane.add(skeleton, 11, 15);
                        }
                        break;
                    case 3:
                        if (s3.hp == 0 && g1.hp == g1.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (g1.hp == g1.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 4:
                        if (g1.hp == 0 && g2.hp == g2.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (g2.hp == g2.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 5:
                        if (g2.hp == 0 && s4.hp == s4.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (s4.hp == s4.baseHp) {
                            gridPane.add(skeleton, 11, 15);
                        }
                        break;
                    case 6:
                        if (s4.hp == 0 && s5.hp == s5.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (s5.hp == s5.baseHp) {
                            gridPane.add(skeleton, 11, 15);
                        }
                        break;
                    case 7:
                        if (s5.hp == 0 && o1.hp == o1.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (o1.hp == o1.baseHp) {
                            gridPane.add(orc, 11, 15);
                        }
                        break;
                    case 8:
                        if (o1.hp == 0 && g3.hp == g3.baseHp) {
                            gridPane.getChildren().remove(orc);
                        }
                        if (g3.hp == g3.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 9:
                        if (g3.hp == 0 && g4.hp == g4.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (g4.hp == g4.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 10:
                        if (g4.hp == 0 && g5.hp == g5.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (g5.hp == g5.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 11:
                        if (g5.hp == 0 && o2.hp == o2.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (o2.hp == o2.baseHp) {
                            gridPane.add(orc, 11, 15);
                        }
                        break;
                    case 12:
                        if (o2.hp == 0 && g6.hp == g6.baseHp) {
                            gridPane.getChildren().remove(orc);
                        }
                        if (g6.hp == g6.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 13:
                        if (g6.hp == 0 && g7.hp == g7.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (g7.hp == g7.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 14:
                        if (g7.hp == 0 && o3.hp == o3.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (o3.hp == o3.baseHp) {
                            gridPane.add(orc, 11, 15);
                        }
                        break;
                    case 15:
                        if (o3.hp == 0 && boss.hp == boss.baseHp) {
                            gridPane.getChildren().remove(orc);
                        }
                        if (boss.hp == boss.baseHp) {
                            gridPane.add(ahri, 11, 15);
                        }
                        break;
                }
            }
        });

        Button spellB = new Button("spell: " + player1.spell, spellIcon);
        spellB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (healCooldown > 0) {
                    healCooldown -= 1;
                } else {
                    healCooldown = 0;
                }

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
                    System.out.println("\nYou can't use this yet. Spell cooldown: " + spellCooldown);
                }

                switch (enemyCounter) {

                    case 0:
                        break;
                    case 1:
                        if (s1.hp == 0 && s2.hp == s2.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (s2.hp == s2.baseHp) {
                            gridPane.add(skeleton, 11, 15);
                        }
                        break;
                    case 2:
                        if (s2.hp == 0 && s3.hp == s3.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (s3.hp == s3.baseHp) {
                            gridPane.add(skeleton, 11, 15);
                        }
                        break;
                    case 3:
                        if (s3.hp == 0 && g1.hp == g1.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (g1.hp == g1.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 4:
                        if (g1.hp == 0 && g2.hp == g2.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (g2.hp == g2.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 5:
                        if (g2.hp == 0 && s4.hp == s4.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (s4.hp == s4.baseHp) {
                            gridPane.add(skeleton, 11, 15);
                        }
                        break;
                    case 6:
                        if (s4.hp == 0 && s5.hp == s5.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (s5.hp == s5.baseHp) {
                            gridPane.add(skeleton, 11, 15);
                        }
                        break;
                    case 7:
                        if (s5.hp == 0 && o1.hp == o1.baseHp) {
                            gridPane.getChildren().remove(skeleton);
                        }
                        if (o1.hp == o1.baseHp) {
                            gridPane.add(orc, 11, 15);
                        }
                        break;
                    case 8:
                        if (o1.hp == 0 && g3.hp == g3.baseHp) {
                            gridPane.getChildren().remove(orc);
                        }
                        if (g3.hp == g3.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 9:
                        if (g3.hp == 0 && g4.hp == g4.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (g4.hp == g4.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 10:
                        if (g4.hp == 0 && g5.hp == g5.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (g5.hp == g5.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 11:
                        if (g5.hp == 0 && o2.hp == o2.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (o2.hp == o2.baseHp) {
                            gridPane.add(orc, 11, 15);
                        }
                        break;
                    case 12:
                        if (o2.hp == 0 && g6.hp == g6.baseHp) {
                            gridPane.getChildren().remove(orc);
                        }
                        if (g6.hp == g6.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 13:
                        if (g6.hp == 0 && g7.hp == g7.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (g7.hp == g7.baseHp) {
                            gridPane.add(goblin, 11, 15);
                        }
                        break;
                    case 14:
                        if (g7.hp == 0 && o3.hp == o3.baseHp) {
                            gridPane.getChildren().remove(goblin);
                        }
                        if (o3.hp == o3.baseHp) {
                            gridPane.add(orc, 11, 15);
                        }
                        break;
                    case 15:
                        if (o3.hp == 0 && boss.hp == boss.baseHp) {
                            gridPane.getChildren().remove(orc);
                        }
                        if (boss.hp == boss.baseHp) {
                            gridPane.add(ahri, 11, 15);
                        }
                        break;
                }
            }
        });

        Button statsB = new Button("stats");
        statsB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("\n" + player1);
                System.out.println("\n" + enemies[enemyCounter]);
            }
        });

        Button healB = new Button("heal", healIcon);
        healB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (healCooldown == 0) {
                    healCooldown += 1;
                    player1.heal();
                } else {
                    System.out.println("\nYou can't use this yet. Heal cooldown: " + healCooldown);
                }
            }
        });

        Button spellCDB = new Button("spell Cooldown", spellCDIcon);
        spellCDB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (spellCooldown == 0) {
                    System.out.println("\n" + player1.spell + " is ready to use!");
                } else {
                    System.out.println("\nRemaining cooldown: " + spellCooldown);
                }
            }
        });

        Button exitB = new Button("exit");
        exitB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("\nYou fall to your demise due to your lack of will!");
                stage.close();
            }
        });

        gridPane.add(fightB, 0, 25);
        gridPane.add(healB, 1, 25);
        gridPane.add(spellB, 10, 25);
        gridPane.add(spellCDB, 10, 26);
        gridPane.add(statsB, 12, 25);
        gridPane.add(exitB, 12, 26);

        if (player1.pClass.equals("archer")) {
            gridPane.add(archer, 0, 15);
        } else if (player1.pClass.equals("warrior")) {
            gridPane.add(warrior, 0, 15);
        } else {
            gridPane.add(sorcerer, 0, 15);
        }

        gridPane.setVisible(true);
        stage.show();
        return scene;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
