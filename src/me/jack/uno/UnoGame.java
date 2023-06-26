package me.jack.uno;

import me.jack.uno.cards.AbstractCard;
import me.jack.uno.cards.CardPile;
import me.jack.uno.cards.StandardCard;
import me.jack.uno.cards.special.DrawTwoCard;
import me.jack.uno.cards.special.ReverseCard;
import me.jack.uno.cards.special.SkipCard;
import me.jack.uno.cards.special.wild.DrawFourCard;
import me.jack.uno.cards.special.wild.WildCard;
import me.jack.uno.cards.types.ActionableCard;
import me.jack.uno.cards.types.ColoredCard;
import me.jack.uno.cards.special.AbstractSpecialCard;
import me.jack.uno.data.CardColor;
import me.jack.uno.data.CardType;
import me.jack.uno.players.Player;
import me.jack.uno.players.PlayerList;

public class UnoGame {

    public static final CardPile deck = new CardPile();
    private static final CardPile pile = new CardPile();

    private static CardColor lastColor;

    public static AbstractCard getLastPlayed(){
        return pile.getTop();
    }

    public static AbstractCard drawFromDeck(){
        AbstractCard drawn;

        //Prevent playing special card as first card
        if(pile.getSize() == 0){
            do {
                drawn = deck.draw();
            }while((drawn instanceof AbstractSpecialCard));
        }else{
            drawn = deck.draw();
        }

        if(deck.getSize() == 0){
            AbstractCard topCard = pile.draw();
            deck.addAll(pile.getCards());
            deck.shuffle();

            pile.clear();
            pile.add(topCard);
        }

        return drawn;
    }

    public static CardColor getColor(){
        return lastColor;
    }

    public static void setColor(CardColor color){
        lastColor = color;
    }

    public static void playCard(AbstractCard card){

        pile.add(card);

        if(card instanceof ColoredCard coloredCard){
            lastColor = coloredCard.getColor();
        }

        if(card instanceof AbstractSpecialCard specialCard){
            specialCard.process();
        }

        if(card instanceof ActionableCard actionableCard){
            Player player = UnoGame.getCurrentPlayer();
            if(player.isAI()){
                actionableCard.processAI();
            } else {
                System.out.println("Actionable card detected!");
                actionableCard.processPlayer();
            }
        }
    }

    public static void reset(){
        deck.clear();
        for(int i = 0; i < 5; i++){
            //setup colored cards
            if(i < 4){
                CardColor color = CardColor.values()[i];
                for(int j = 0; j < 10; j++){
                    deck.add(new StandardCard(CardType.get(j), color));
                }

                for(int j = 1; j < 10; j++){
                    deck.add(new StandardCard(CardType.get(j), color));
                }

                for(int j = 0; j < 2; j++){
                    deck.add(new SkipCard(color));
                    deck.add(new ReverseCard(color));
                    deck.add(new DrawTwoCard(color));
                }
            }else{
                //setup wild cards
                for(int j = 0; j < 4; j++){
                    deck.add(new DrawFourCard());
                    deck.add(new WildCard());
                }
            }
        }

        deck.shuffle();
    }

    public static Player getCurrentPlayer(){
        return PlayerList.getCurrentPlayer();
    }
}
