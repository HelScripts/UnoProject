package me.jack.uno.data;

public enum CardNumber {

    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    private final int value;

    CardNumber(int value){
       this.value = value;
    }

    public static CardNumber get(int value){
        for(CardNumber cardNumber : values()){
            if(cardNumber.value == value) return cardNumber;
        }
        return null;
    }
}
