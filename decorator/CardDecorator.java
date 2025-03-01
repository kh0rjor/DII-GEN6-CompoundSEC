package decorator;

import access.AccessStrategy;

public abstract class CardDecorator implements AccessStrategy {
    protected AccessStrategy decoratedCard;

    public CardDecorator(AccessStrategy decoratedCard) {
        this.decoratedCard = decoratedCard;
    }

    @Override
    public boolean hasAccess(String room) {
        return decoratedCard.hasAccess(room);  // Default to the decorated card's hasAccess
    }

    @Override
    public boolean isAccessTimeValid() {
        return decoratedCard.isAccessTimeValid();  // Default to the decorated card's isAccessTimeValid
    }
}
