package com.blackjackgame.blackjack;

import javafx.application.Platform;

public class GameController {

    private Deck deck;
    private Hand player;
    private Hand dealer;

    public GameController() {
        this.deck = new Deck(1);
        this.player = new Hand("Gracz");
        this.dealer = new Hand("Krupier");
    }

    public void startGame() {
        System.out.println("Zaczynasz gre");
        for (int i = 0; i < 2; i++) {
            player.addCard(deck);
            dealer.addCard(deck);
        }
        updateCardView();
    }

    private void updateCardView() {
        BlackJack.PLAYER_CARDS.getChildren().clear();
        BlackJack.DEALER_CARDS.getChildren().clear();

        for (int i = 0; i < player.getCardList().size(); i++) {
            BlackJack.PLAYER_CARDS.getChildren().add(player.getCardList().get(i).getCardImage());
        }
        for (int i = 0; i < dealer.getCardList().size(); i++) {
            BlackJack.DEALER_CARDS.getChildren().add(dealer.getCardList().get(i).getCardImage());
        }

    }

    public void playerMove() {
        if (player.getHandValue() < 21) {
            player.addCard(deck);
            updateCardView();
        } else {
            if (player.isBusted()) {
                evaluateWinner();
            } else {
                discardMove();
            }
        }
    }

    public void dealerMove() {
        dealer.evaluateHand();
        if (dealer.getHandValue() > player.getHandValue() && !player.isBusted()) {
            BlackJack.WHOWINS.setText( "Wygral " + evaluateWinner().getName());
        }
        if (dealer.getHandValue() < player.getHandValue()) {
            dealer.addCard(deck);
            dealer.evaluateHand();
            updateCardView();
            dealerMove();
        }
        else if (dealer.getHandValue() < 16) {
            dealer.addCard(deck);
            dealer.evaluateHand();
            updateCardView();
            dealerMove();
        } else {
            BlackJack.WHOWINS.setText( "Wygral " + evaluateWinner().getName());
        }
    }

    public void discardMove() {
        player.discardHand();
        dealerMove();
    }

    public void endGame() {
        Platform.exit();
        System.exit(0);
    }

    private Hand evaluateWinner() {
        System.out.println(player.getName() + ": " + player.getHandValue() + " vs " + dealer.getName() + ": " + dealer.getHandValue());
        System.out.println(player.getName() + ": " + player.isBusted() + " vs " + dealer.getName() + ": " + dealer.isBusted());
        if (player.isBusted()) {
            return dealer;
        } else if (dealer.isBusted()) {
            return player;
        } else if (player.getHandValue() == dealer.getHandValue()) {
            return dealer;
        } else if (player.getHandValue() < dealer.getHandValue()) {
            return dealer;
        } else if (player.getHandValue() > dealer.getHandValue()) {
            return player;
        }
        return dealer;
    }

    public void newGame() {
        player.clearHand();
        dealer.clearHand();
        updateCardView();
        this.deck = new Deck(1);
        startGame();
        BlackJack.WHOWINS.setText("Wygral...");
    }
}
