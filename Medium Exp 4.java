import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class Main {
    private static Map<String, List<Card>> cardMap = new HashMap<>();

    public static void addCard(String symbol, String value) {
        Card card = new Card(symbol, value);
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(card);
    }

    public static void findCardsBySymbol(String symbol) {
        List<Card> cards = cardMap.get(symbol);
        if (cards != null) {
            System.out.println("Cards with symbol " + symbol + ": " + cards);
        } else {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }

    public static void main(String[] args) {
        addCard("Hearts", "Ace");
        addCard("Hearts", "King");
        addCard("Spades", "Queen");
        addCard("Diamonds", "Jack");
        addCard("Clubs", "10");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a symbol to search for cards: ");
        String symbol = scanner.nextLine();
        findCardsBySymbol(symbol);
    }
}
