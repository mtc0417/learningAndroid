package com.dhoolak.learning;

/**
 * Created by prakasht on 7/22/2015.
 */
public class Card {

    public enum CardSuit { // Clubs, Spades, Hearts, Diamonds
        CHIDI, HUKUM, PAAN, EENT
    }
    public enum CardNumber {
        N1, N2, N3, N4, N5, N6, N7, N8, N9, N10, J, Q, K
    }
    public enum CardNumberTest {
        N1 {
            public String toString() {
                return "this is one";
            }
        }
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

    private CardSuit mCardSuit;
    private CardNumber mCardNumber;

    public Card(CardSuit suit, CardNumber number){
        mCardSuit = suit;
        mCardNumber = number;
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
