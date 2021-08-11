package com.blackjackgame.blackjack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BlackJack extends Application {

    public static final String IMG_PATH = "file:src/main/resources";
    public static final FlowPane PLAYER_CARDS = new FlowPane(Orientation.VERTICAL);
    public static final FlowPane DEALER_CARDS = new FlowPane(Orientation.VERTICAL);
    public static final Label WHOWINS = new Label("Wygral...");
    public static final Label PLAYER_INFO = new Label("PLAYER POINTS");
    public static final Label DEALER_INFO = new Label("DEALER POINTS");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        PLAYER_CARDS.setMaxHeight(250);
        DEALER_CARDS.setMaxHeight(250);
        WHOWINS.setAlignment(Pos.CENTER);
        WHOWINS.setPrefWidth(400);
        WHOWINS.setFont(Font.font(25));
        WHOWINS.setStyle("-fx-background-color: #C9FFE5; -fx-text-alignment: center;");

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        System.out.println("jdk:" + javaVersion + " fx:" + javafxVersion);

        ImageView imageback = new ImageView(IMG_PATH + "/table.jpg");
        imageback.setPreserveRatio(true);
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setVgap(10);
        grid.setBackground(background);
        grid.setGridLinesVisible(true);

        Button drawCardButton = new Button("Wyloz kolejna karte");
        Button enoughtCardButton = new Button("Koncze kolejke");
        Button newGameButton = new Button("Nowa gra");
        Button endGameButton = new Button("Koniec gry");

        PLAYER_INFO.setAlignment(Pos.CENTER);
        PLAYER_INFO.setPrefWidth(800);
        PLAYER_INFO.setFont(Font.font(25));
        PLAYER_INFO.setStyle("-fx-background-color: #C9FFE5; -fx-text-alignment: center;");

        DEALER_INFO.setAlignment(Pos.CENTER);
        DEALER_INFO.setPrefWidth(800);
        DEALER_INFO.setFont(Font.font(25));
        DEALER_INFO.setStyle("-fx-background-color: #C9FFE5; -fx-text-alignment: center;");

        grid.add(WHOWINS,0,0);
        grid.add(PLAYER_CARDS, 0, 1);
        grid.add(PLAYER_INFO,0,2);
        grid.add(DEALER_CARDS, 0, 3);
        grid.add(DEALER_INFO,0,4);

        grid.add(drawCardButton, 0, 5);
        grid.add(enoughtCardButton, 0, 6);
        grid.add(newGameButton, 0, 7);
        grid.add(endGameButton, 0, 8);

        GridPane.setHalignment(WHOWINS, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(PLAYER_CARDS, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(PLAYER_INFO, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(DEALER_CARDS, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(DEALER_INFO, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(drawCardButton, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(enoughtCardButton, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(newGameButton, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(endGameButton, javafx.geometry.HPos.CENTER);

        Scene scene = new Scene(grid, 1597, 898, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BlackJack");
        primaryStage.setResizable(false);
        primaryStage.show();

        GameController gc = new GameController();
        drawCardButton.setOnAction(e -> gc.playerMove());
        enoughtCardButton.setOnAction(e -> gc.discardMove());
        newGameButton.setOnAction(e -> gc.newGame());
        endGameButton.setOnAction(e -> gc.endGame());
        gc.startGame();
    }
}