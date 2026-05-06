import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FreeCellDealer {
    public static List<List<String>> dealFreeCell(int seed) {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        String[] suits = {"C", "D", "H", "S"};
        
        List<String> cards = new ArrayList<>();
        for (String r : ranks) {
            for (String s : suits) {
                cards.add(r + s);
            }
        }
        
        long state = seed;  // Use long to avoid overflow during multiplication
        List<String> dealOrder = new ArrayList<>();
        
        for (int length = 52; length > 0; length--) {
            state = (214013L * state + 2531011L) & 0x7fffffffL;
            int randVal = (int) (state >>> 16);
            int j = randVal % length;
            
            // Swap
            String temp = cards.get(j);
            cards.set(j, cards.get(length - 1));
            cards.set(length - 1, temp);
            
            // Take the chosen card
            dealOrder.add(cards.remove(length - 1));
        }
        
        // Group into rows (7 rows, last one with 4 cards)
        List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < dealOrder.size(); i += 8) {
            int end = Math.min(i + 8, dealOrder.size());
            board.add(new ArrayList<>(dealOrder.subList(i, end)));
        }
        
        return board;
    }
}
