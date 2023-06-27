package me.jack.uno.data;

public enum CardType {

    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    REVERSE(10),
    SKIP(11),
    DRAW_TWO(12);

    private final int value;

    CardType(int value) {
       this.value = value;
    }

    public static CardType get(int value) {
        for(CardType cardNumber : values()){
            if(cardNumber.value == value) return cardNumber;
        }
        return null;
    }
}
