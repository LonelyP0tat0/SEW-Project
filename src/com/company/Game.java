package com.company;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Game extends Application {

    Player player1 = new Player("player", "archer");
    Monster skeleton = new Monster("skeleton");
    Monster troll = new Monster("troll");
    Monster goblin = new Monster("goblin");
    Boss boss = new Boss("Malenia");

    public int iFight = 0;
    public int iSPell = 0;

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
        button1 = new Button("Click to start game");
        button1.setOnAction(e -> switchScenes(scene2));
        vbox1 = new VBox(button1);
        scene1 = new Scene(vbox1, 800, 500);

        return scene1;
    }

    private Scene gameStart() {
        stage.setTitle("Game");

        Enemy[] enemies = new Enemy[]{skeleton, troll, goblin};

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 1280, 720);
        stage.setScene(scene);

        gridPane.setHgap(8);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Button fightB = new Button("fight");
        fightB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (enemies[iFight].alive) {
                    player1.fight(enemies[iFight]);
                    if (player1.hp == 0) {
                        stage.close();
                    }
                }
                if (!enemies[iFight].alive) {
                    iFight++;
                }
                if (iFight == enemies.length - 1 && !enemies[iFight].alive) {
                    stage.close();
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
                if (enemies[iSPell].alive) {
                    player1.spell(enemies[iSPell]);
                    if (player1.hp == 0) {
                        stage.close();
                    }
                }
                if (!enemies[iSPell].alive) {
                    iSPell++;
                }
                if (iSPell == enemies.length - 1 && !enemies[iSPell].alive) {
                    stage.close();
                }
            }
        });

        Button statsB = new Button("stats");
        statsB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println(player1);
                System.out.println(troll);
            }
        });

        gridPane.add(fightB, 0, 40);
        gridPane.add(healB, 15, 40);
        gridPane.add(spellB, 30, 40);
        gridPane.add(statsB, 45, 40);

        gridPane.setGridLinesVisible(false);

        stage.show();
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

