package com.blackjackgame.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cardList;
    private String name;
    private int handValue;
    private boolean busted;

    public Hand(String name) {
        cardList = new ArrayList<>();
        this.name = name;
        this.handValue = 0;
        this.busted = false;
    }

    void addCard(Deck deck){
        cardList.add(deck.dealCard());
        evaluateHand();
    }

    void discardHand() {
        System.out.println("Ruch krupiera");
    }

    public void evaluateHand() {
        handValue = 0;
        for(Card c: cardList){
            if (!c.rank.getRank().equals(Card.Rank.ACE.getRank())) {
                handValue += c.rank.getValue();
            } else {
                if(handValue <= 10){
                    handValue +=11;
                } else {
                    handValue += 1;
                }
            }
        }

        if (this.handValue > 21){
            busted = true;
        }
        if (name.equals("Krupier")){
            BlackJack.DEALER_INFO.setText("" + handValue);
        } else {
            BlackJack.PLAYER_INFO.setText("" + handValue);
        }
    }

    public void clearHand() {
        cardList.clear();
        busted = false;
        handValue = 0;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public String getName() {
        return name;
    }

    public int getHandValue() {
        return handValue;
    }

    public boolean isBusted() {
        return busted;
    }
}
