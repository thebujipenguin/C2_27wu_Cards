//@author Bryan Wu
//@date 20/02/2025
//ACK code from Think Java 2

class Main{
    public static void main(String[] args){
        Card jackOfClubs, twoOfHearts,fourOfSpades, sixOfDiamonds;
        Card randomCard;
        Card[] cards = makeDeck(); // makes a deck of cards

        jackOfClubs = new Card(11,2);
        twoOfHearts = new Card(2,1);
        fourOfSpades = new Card(4,0);
        sixOfDiamonds = new Card(6,3);

        //printDeck(cards);

        //histogram
        int[] histogram = suitHist(cards);
        System.out.println("\nSuit Histogram:");
        for (int i = 0; i < histogram.length; i++) {
            System.out.println(Card.SUITS[i] + ": " + histogram[i]);
        }

        System.out.println("\nHas Flush: " + hasFlush(cards));

        System.out.println("Has Royal Flush: " + hasRoyal(cards));

        Deck deck = new Deck();//Deck(int size)
        System.out.println("\ntoString of Deck:\n" + deck);

        //shuffle deck
        deck.shuffle();
        System.out.println("\nShuffled Deck:\n" + deck);
        deck.selectionSort();
        System.out.println("\nSelection Sorted Deck:\n" + deck);

        deck.shuffle();
        System.out.println("\nShuffled Deck:\n" + deck);
        Deck mergeSorted = deck.mergeSort();
        System.out.println("\nMerge Sorted Deck:\n" + mergeSorted);

        deck.shuffle();
        System.out.println("\nShuffled Deck:\n" + deck);
        deck.bubbleSort();
        System.out.println("\nBubble Sorted Deck:\n" + deck);

    }

    public static void printDeck(Card[] cards) {
        System.out.println("Current deck:");
        for (Card card : cards) {
            System.out.println(card);
        }
    }


    public static int search(Card[] cards, Card target) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }


    public static int binarySearch(Card[] cards, Card target) {
        int low = 0;
        int high = cards.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int comp = cards[mid].compareTo(target);

            if (comp == 0) {
                return mid;
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static Card[] makeDeck(){
        int index = 0;
        Card[] deck = new Card[52];
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                deck[index] = new Card(rank, suit);
                index++;
            }
        }

        return deck;
    }

    public static int[] suitHist(Card[] cards){
        int[] suitCounts = new int[4];

        for (Card c : cards) {
            int suit = c.getSuit(); // Get the suit of the card (0-3)
            suitCounts[suit]++; // Increment the count for the corresponding suit
        }

        return suitCounts;
    }

    public static boolean hasFlush(Card[] cards){
        int[] suitCount = new int[4];

        for(Card c: cards){
            suitCount[c.getSuit()]++;
        }

        for(int count: suitCount){
            if(count>=5){
                return true;
            }
        }
        return false;
    }

    public static boolean hasRoyal(Card[] cards){
        if(!hasFlush(cards)){
            return false;
        }


        boolean[] ranks = new boolean[14];
        for (Card c : cards) {
            if (c.getRank() >= 10 && c.getRank() <= 13 || c.getRank()==1) {
                ranks[c.getRank()] = true;
            }
        }

        return ranks[1] && ranks[10] && ranks[11] && ranks[12] && ranks[13]; //return values
    }


}