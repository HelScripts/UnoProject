package me.jack.uno;

import me.jack.uno.cards.AbstractCard;
import me.jack.uno.cards.special.wild.AbstractWildCard;
import me.jack.uno.players.Player;
import me.jack.uno.players.PlayerList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //Random used for AI card selection
    private static final  Random random = new Random();
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        UnoGame.reset();

        do {
            System.out.println("Welcome! How many players would you like to vs?");

            int playerCount = scanner.nextInt();

            if(!PlayerList.setupPlayers(playerCount)) {
                System.out.println("Invalid player count, please choose between 1 and 8");
                continue;
            }

            // Add 7 cards to each player
            Player player = PlayerList.getCurrentPlayer();
            do {
                for(int i = 0; i < 7; i++) {
                    player.addCard(UnoGame.drawFromDeck());
                }
                player = player.getNext();
            }while(!player.equals(PlayerList.getCurrentPlayer()));

            //Setup start of game
            AbstractCard topCard = UnoGame.drawFromDeck();
            topCard.play();
            System.out.println("Starting card is " + topCard);

            do {
                player = PlayerList.getCurrentPlayer();

                //Human check
                if(!player.isAI()) {

                    //Log cards
                    ArrayList<AbstractCard> cards = player.getCards();
                    StringBuilder builder = new StringBuilder();
                    builder.append("\nYou have ").append(cards.size()).append(" cards: ");
                    for(AbstractCard card : cards) {
                        builder.append(card).append(", ");
                    }

                    //Clean the last comma
                    builder.replace(builder.lastIndexOf(", "), builder.length(), "");

                    System.out.println(builder);

                    if(UnoGame.getLastPlayed() instanceof AbstractWildCard) {
                        System.out.println("\nThe color is " + UnoGame.getColor());
                    }else {
                        System.out.println("\nThe top facing card is " + UnoGame.getLastPlayed());
                    }

                    //Playable cards
                    List<AbstractCard> playable = cards.stream()
                            .filter(AbstractCard::canPlay)
                            .toList();

                    System.out.println("What would you like to do?");
                    System.out.println("0: Draw from deck");

                    //Print cards
                    for(int i = 0; i < playable.size(); i++) {
                        System.out.println(i + 1 + ": Play " + playable.get(i));
                    }

                    //Accept turn input
                    int selection = scanner.nextInt();
                    if(selection < 0 || selection > playable.size()) {
                        System.out.println("Invalid selection, try again.");
                        continue;
                    }

                    //draw selection
                    if(selection == 0) {
                        player.getCards().add(UnoGame.drawFromDeck());
                    }else {
                        //play card from selection
                        AbstractCard card = playable.get(selection - 1);
                        player.removeCard(card);
                        card.play();
                    }
                }else {
                    // get all playable cards
                    List<AbstractCard> cards = player.getCards().stream()
                            .filter(AbstractCard::canPlay)
                            .toList();

                    // if no cards to play, draw
                    if(cards.size() == 0) {
                        player.getCards().add(UnoGame.drawFromDeck());
                        System.out.println(player + " drew from deck. They now have " + player.getCards().size() + " cards");
                    }else {
                        // play random playable card
                        AbstractCard card = cards.get(random.nextInt(cards.size()));
                        player.removeCard(card);
                        System.out.println(player + " played " + card + ". They now have " + player.getCards().size() + " cards");
                        card.play();
                    }
                }

                //Game over
                if(player.getCards().size() == 0) {
                    System.out.println(player + " has won the game!");
                    break;
                }

                //Go to next player
                PlayerList.processNextPlayer();
            }while(true);

            //Post game input
            int selection;
            do {

                System.out.println("Would you like to play again?");
                System.out.println("0: NO");
                System.out.println("1: YES");

                selection = scanner.nextInt();

                if(selection == 0) {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }else if(selection > 1) {
                    System.out.println("Invalid selection!");
                }
            }while(selection < 0 || selection > 1);
        }while(true);
    }
}
