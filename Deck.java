import javax.swing.*;
import java.util.Random;

public class Deck{
    private Card[] cards;
    private static Random random = new Random();

    public Deck() {
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                this.cards[index] = new Card(rank, suit);
                index++;
            }
        }
    }
    public Deck(int n) {
        this.cards = new Card[n];
    }

    public Card[] getCards() {
        return this.cards;
    }

    public void print() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
    }

    public String toString(){
        String result = "";
        for(Card c: this.cards){
            result += c.toString() + "\n";
        }
        return result;
    }

    public void shuffle(){
        for(int i = 0; i < this.cards.length; i++){
            // shuffle with i and len-1
            int rand = randomInt(0, this.cards.length);
            //swap to shuffle
            swapCards(i,rand);
        }
    }

    private int randomInt(int low, int high) {
        int result = this.random.nextInt(high-low)+low;
        return result;
    }

    private void swapCards(int i, int j) {
        Card temp = this.cards[i];
        this.cards[i] = this.cards[j];
        this.cards[j] = temp;
    }

    public void selectionSort() {
        for (int i = 0; i < this.cards.length - 1; i++) {
            int indexLow = indexLowest(i, this.cards.length - 1); // Find the index of the lowest card in the range
            if (i != indexLow) { // Only swap if the lowest card is not already at the current position
                swapCards(indexLow, i); // Swap the cards
            }
        }
    }

    private int indexLowest(int low, int high){
        //for finding lowest card between two
        int index = low;
        for(int i = low; i <=high; i++){
            if(cards[i].compareTo(this.cards[index]) < 0){
                index = i;
            }
        }
        return index;
    }

    public Deck mergeSort(){
        if (this.cards.length <= 1) {
            return this;
        }

        int mid = this.cards.length / 2;
        Deck left = this.subdeck(0, mid - 1); // First half of the deck
        Deck right = this.subdeck(mid, this.cards.length - 1); // Second half of the deck

        // Recursively sort each subdeck
        Deck sortedLeft = left.mergeSort();
        Deck sortedRight = right.mergeSort();

        // Merge the two sorted subdecks and return the result
        return merge(sortedLeft, sortedRight);
    }

    public Deck subdeck(int low, int high) {
        Deck sub = new Deck(high - low + 1);
        for (int i = 0; i < sub.cards.length; i++) {
            sub.cards[i] = this.cards[low + i];
        }
        return sub;
    }

    private static Deck merge(Deck d1, Deck d2) {
        int size1 = d1.cards.length;
        int size2 = d2.cards.length;
        Deck d3 = new Deck(size1 + size2);

        int i = 0, j = 0, k = 0;
        while (i < size1 && j < size2) {
            if (d1.cards[i].compareTo(d2.cards[j]) < 0) {
                d3.cards[k++] = d1.cards[i++];
            } else {
                d3.cards[k++] = d2.cards[j++];
            }
        }

        while (i < size1) {
            d3.cards[k++] = d1.cards[i++];
        }
        while (j < size2) {
            d3.cards[k++] = d2.cards[j++];
        }

        return d3;
    }

    public void bubbleSort() {//bryan wu
        Card temp;//temporary variable to swap things around
        boolean swapped= false;
        for(int i = 0;i<this.cards.length; i++){
            for(int j = 0; j<this.cards.length-i-1; j++){
                if(this.cards[j].compareTo(this.cards[j+1]) < 0){ // if the first card is lower than the second card, then swap cards
                    temp = cards[j]; // creates a temp variable to hold the data
                    cards[j] = cards[j+1];
                    cards[j+1] = temp;
                    swapped= true;
                }
            }

            if(!swapped)//conditional for when the deck is finally sorted, ie once the algorithm stops swapping it breaks the loop
                break;
        }
    }

    public void insertionSort() {//raihan bair
        for (int i = 1; i < this.cards.length; i++) {
            Card key = this.cards[i]; // variable saves the value of card 1
            int j = i - 1;

            while (j >= 0 && cards[j].compareTo(key)>0) {  // if card 2 is lower than card 1
                cards[j + 1] = cards[j]; // replace it
                j = j - 1;// iterate down
            }
            cards[j + 1] = key; // card 2 becomes card 1
        }
    }


}