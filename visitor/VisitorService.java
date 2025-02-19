package visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorService {
    private List<VisitorCard> visitorCards;

    public VisitorService() {
        this.visitorCards = new ArrayList<>();
    }

    public void addVisitorCard(VisitorCard card) {
        visitorCards.add(card);
    }

    public void listAllVisitors() {
        for (VisitorCard card : visitorCards) {
            System.out.println(card.getName() + " - " + card.getType());
        }
    }
}
