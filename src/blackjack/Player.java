/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Heba Raslan
 */
public class Player {
    private String Name;
    private int score = 0;
    private Card hand[] = new Card[11];
    private int cardCounter = 0;

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public String getName() {
        return Name;
    }

    public int getScore() {
        return score;
    }

    public Card[] getHand() {
        return hand;
    }
     
    public void add_card(Card card)
    {
        if(cardCounter < 11)
        {
            hand[cardCounter] = card;
            cardCounter++;
            score += card.getValue();
        }
    }
}
