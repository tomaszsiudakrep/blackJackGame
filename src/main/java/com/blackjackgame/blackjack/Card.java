package com.blackjackgame.blackjack;

import javafx.scene.image.Image;
import java.util.Objects;
import javafx.scene.image.ImageView;

public class Card {

    Rank rank;
    Suit color;

    public Card(Rank rank, Suit color) {
        this.rank = rank;
        this.color = color;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getColor() {
        return color;
    }

    public static String getFileName(Suit color, Rank rank){
        StringBuilder filePath = new StringBuilder();
        filePath.append(BlackJack.IMG_PATH + "/cards/");
        filePath.append(rank.getRank() + "_of_");
        filePath.append(color.getColor() + ".png");
        return filePath.toString();
    }

    ImageView getCardImage(){
        ImageView img = new ImageView(getFileName(getColor(), getRank()));
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setFitHeight(250.0);
        return img;
    }

    enum Suit{

        CLUBS("clubs"), DIAMONDS("diamonds"), HEARTS("hearts"), SPADES("spades");

        private String color;

        Suit(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    enum Rank{

        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("jack", 10),
        QUEEN("queen", 10),
        KING("king", 10),
        ACE("ace", 1);

        private String rank;
        private int value;

        Rank(String rank, int value) {
            this.rank = rank;
            this.value = value;
        }

        public String getRank() {
            return rank;
        }

        public int getValue() {
            return value;
        }
    }
}
