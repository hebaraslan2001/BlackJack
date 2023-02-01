/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Heba Raslan
 */
public class Game {
    Player players[] = new Player[4];
    Card deck[] = new Card[52];
    int high_scores[] = new int[4];
    
    public void Generator()
    {
        int counter = 0;
        for(int s = 0; s<4; s++)
        {
            for(int r = 0; r<13; r++)
            {
                if(r<10)
                {
                    Card card = new Card(s,r,r+1);
                    deck[counter] = card;
                }
                else
                {
                    Card card = new Card(s,r,10);
                    deck[counter] = card;
                }
                counter++;
            }
        }
    }
    
    public Card Drawer()
    {
        Random rand = new Random();
        Card card = null;
        do{
            int randomChoice = rand.nextInt(51);
            card = deck[randomChoice];
            deck[randomChoice] = null;
        }while(card == null);
        return card;
    }
    
    public void Set_players()
    {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < players.length - 1; i++) 
        {
            System.out.println("Enter name of player " + (i+1) + ":");
            players[i] = new Player();
            players[i].setName(input.next());
            players[i].add_card(this.Drawer());
            players[i].add_card(this.Drawer());
        }
        System.out.println("the Fourth player is the dealer");
            players[3] = new Player();
            players[3].setName("dealer");
            players[3].add_card(this.Drawer());
            players[3].add_card(this.Drawer());
    }
    
    public void update_score()
    {
        for(int i = 0; i < high_scores.length; i++)
        {
            if(players[i].getScore() <= 21)
            {
               high_scores[i] = players[i].getScore(); 
            }
            else
            {
                high_scores[i] = 0;
                System.out.println("The player " + (i+1) + " BUSTED");
            }
        }
    }
}
