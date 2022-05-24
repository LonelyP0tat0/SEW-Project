package com.company;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {

    Player player1 = new Player("player", "archer");
    Monster skeleton = new Monster("skeleton");
    Monster troll = new Monster("troll");
    Monster goblin = new Monster("goblin");
    Boss boss = new Boss("Malenia");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game");
        stage.setMinWidth(600);
        stage.setMinHeight(500);

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 550, 500);
        stage.setScene(scene);

        gridPane.setHgap(8);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label stats = new Label(player1 + "");
        Label enemyStats = new Label(troll + "");

        Button fightB = new Button("fight");
        fightB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Main.fight(player1, troll);
            }
        });

        Button healB = new Button("heal");
        healB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Main.heal(player1);
            }
        });

        Button spellB = new Button("spell: " + player1.spell);
        spellB.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Main.spell(player1, troll);
            }
        });

        gridPane.add(fightB, 0, 30);
        gridPane.add(healB, 15, 30);
        gridPane.add(spellB, 30, 30);

        gridPane.add(stats, 0, 0);
        gridPane.add(enemyStats, 30, 0);

        gridPane.setGridLinesVisible(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

