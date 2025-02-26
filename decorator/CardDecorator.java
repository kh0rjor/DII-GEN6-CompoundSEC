package decorator;

import access.AccessStrategy;

public abstract class CardDecorator implements AccessStrategy {

    protected AccessStrategy decoratedCard;

    public CardDecorator(AccessStrategy decoratedCard) {
        this.decoratedCard = decoratedCard;
    }

    public boolean hasAccess(String room) {
        return decoratedCard.hasAccess(room);
    }

}
