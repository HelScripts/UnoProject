package me.jack.uno.cards.special.wild;

import me.jack.uno.UnoGame;
import me.jack.uno.cards.special.AbstractSpecialCard;
import me.jack.uno.cards.types.ActionableCard;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.players.Player;
import me.jack.uno.players.PlayerList;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class AbstractWildCard extends AbstractSpecialCard implements ActionableCard {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public abstract void process();

    public void processPlayer(){
        int selection;
        do {
            System.out.println("What color would you like to choose?");
            CardColor[] colors = CardColor.values();
            for(int i = 0; i < colors.length; i++){
                System.out.println(i + ": " + colors[i]);
            }
            selection = scanner.nextInt();

            if(selection < 0 || selection > 3){
                System.out.println("Invalid selection!");
            }
        }while(selection < 0 || selection > 3);

        UnoGame.setColor(CardColor.values()[selection]);
    }
    public void processAI(){
        Player player = PlayerList.getCurrentPlayer();

        CardColor highest = player.getCards().stream()
                .filter(card -> card instanceof ColoredCard)
                .map(card -> ((ColoredCard) card).getColor())
                .collect(Collectors.groupingBy(color -> color, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if(highest == null){
            UnoGame.setColor(CardColor.values()[random.nextInt(CardColor.values().length)]);
        }else{
            // Set as most common card color we have
            UnoGame.setColor(highest);
        }

        System.out.println(player + " set the color to " + UnoGame.getColor());
    }

}
