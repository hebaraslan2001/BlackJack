/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

public class BlackJack {

static Game game = new Game();
    public static void main(String[] args) {
        // TODO code application logic here
        GUI gui = new GUI();
        game.Generator();
        game.Set_players();
        
        
        gui.runGUI(game.deck,
                game.players[0].getHand(),
                game.players[1].getHand(),
                game.players[2].getHand(),
                game.players[3].getHand());
        
        player_turn(gui);
        
        game.update_score();
        
        dealer_turn(gui);
        
        game.update_score();
        
        checker();
    }
    
     public static void player_turn( GUI gui)
     {
         Scanner input = new Scanner(System.in);
         for(int i = 0; i < game.players.length - 1; i++)
         {
             String choice = "";
             int count = 0;
                while(!choice.toLowerCase().equals("stand") || count<9)
                {
                   System.out.println("Player no " + (i+1) + " Hit or Stand");
                   choice = input.next();
                   if(choice.toLowerCase().equals("hit"))
                   {
                        Card card = game.Drawer();
                        game.players[i].add_card(card);
                        gui.updatePlayerHand(card,i);
                        count++;
                   }
                   else if(!choice.toLowerCase().equals("hit") && !choice.toLowerCase().equals("stand"))
                   {
                       System.out.println("Please enter Hit Or Stand");
                   }
                   else
                   {
                       break;
                   }
                }
         }
     }
     
    public static void dealer_turn(GUI gui)
    {
        boolean dealer_win = true;
        int highscore = 0;
        for(int i = 0; i < game.players.length - 1; i++)
        {
            if(game.high_scores[i] > game.players[3].getScore())
            {
                dealer_win = false;
            }
            if(game.high_scores[i] > highscore)
            {
                highscore = game.high_scores[i];
            }
        }
        if(!dealer_win)
        {
            while(game.players[3].getScore() < highscore)
            {
                Card card = game.Drawer();
                game.players[3].add_card(card);
                gui.updateDealerHand(card, game.deck);
            }
        }
        else
        {
            return;
        }
     }
     
     public static void checker()
     {
         int highscore = 0;
         String playername1 = "";
         int playerscore1 = 0;
         String playername2 = "";
         int playerscore2 = 0;
         int winner = -1;
         for(int i = 0; i < game.players.length; i++)
         {
            if(game.high_scores[i] == highscore)
            {
                playername2 = game.players[i].getName();
                playerscore2 = game.players[i].getScore();
            }
            else if(game.high_scores[i] > highscore)
            {
                highscore = game.high_scores[i];
                winner = i;
                playername1 = game.players[i].getName();
                playerscore1 = game.players[i].getScore();
            }
         }
         if(playerscore1 == playerscore2 && playerscore1 <= 21)
            {
                System.out.println("________________________________________");
                System.out.println("PUSH");
                System.out.println(playername1 + " and " + playername2 + " have the same SCORE =  " + playerscore1);
                System.out.println("________________________________________");
            }
            else if(winner >= 0)
            {
                System.out.println("________________________________________");
                System.out.println("BLACKJACK");
                System.out.println("The WINNER is " + game.players[winner].getName() + " which has SCORE " + highscore);
                System.out.println("________________________________________");
            }
     }
}
