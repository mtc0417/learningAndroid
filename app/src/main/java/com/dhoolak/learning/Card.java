package com.dhoolak.learning;

/**
 * Created by prakasht on 7/22/2015.
 */
public class Card  implements Comparable<Card>{

    public enum CardSuit { // Clubs, Spades, Hearts, Diamonds
        UNKNOWN, CHIDI, HUKUM, PAAN, EENT
    }
    public enum CardNumber {
        N2, N3, N4, N5, N6, N7, N8, N9, N10, J, Q, K, N1
    }

    public CardSuit getCardSuit() {
        return mCardSuit;
    }

    public CardNumber getCardNumber() {
        return mCardNumber;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.mCardSuit = cardSuit;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.mCardNumber = cardNumber;
    }
    public boolean isTrump()
    {
        Game game = Game.getInstance();
        return game.isTrumpSet() && game.getTrump().equals(mCardSuit);
    }

    private CardSuit mCardSuit;
    private CardNumber mCardNumber;

    public Card(CardSuit suit, CardNumber number){
        mCardSuit = suit;
        mCardNumber = number;
    }

    public boolean equals(Card card)
    {
        return (mCardSuit.equals(card.getCardSuit()) && mCardNumber.equals(card.getCardNumber()));
    }

    public int compareTo(Card card)
    {
        if(mCardSuit.equals(card.getCardSuit()))
        {
            return mCardNumber.compareTo(card.getCardNumber());
        }
        if(this.isTrump())
        {
            return 1;
        }
        if(card.isTrump())
        {
            return -1;
        }
        return mCardSuit.compareTo(card.getCardSuit());
    }

    public String getDrawableName()
    {
        String name = "";
        switch(mCardSuit)
        {
            case CHIDI: // Clubs
                name += 'c';
                break;
            case HUKUM: // Spades
                name += 's';
                break;
            case EENT: // Diamonds
                name += 'd';
                break;
            case PAAN: // Hearts
                name += 'h';
                break;
            default:
                return "ec";
        }

        switch(mCardNumber)
        {
            case N1:
                name += '1';
                break;
            case N2:
                name += '2';
                break;
            case N3:
                name += '3';
                break;
            case N4:
                name += '4';
                break;
            case N5:
                name += '5';
                break;
            case N6:
                name += '6';
                break;
            case N7:
                name += '7';
                break;
            case N8:
                name += '8';
                break;
            case N9:
                name += '9';
                break;
            case N10:
                name += "10";
                break;
            case J:
                name += 'j';
                break;
            case Q:
                name += 'q';
                break;
            case K:
                name += 'k';
                break;
        }
        return name;
    }
}
