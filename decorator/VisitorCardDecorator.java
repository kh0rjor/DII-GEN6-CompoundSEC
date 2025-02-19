package decorator;

import visitor.VisitorCard;

public abstract class VisitorCardDecorator extends VisitorCard {
    protected VisitorCard decoratedCard;

    public VisitorCardDecorator(VisitorCard decoratedCard) {
        super(decoratedCard.getName(), decoratedCard.getId(), decoratedCard.getType(), decoratedCard.getFloor(), decoratedCard.getRoom());
        this.decoratedCard = decoratedCard;
    }

    @Override
    public String getType() {
        return decoratedCard.getType();
    }
}
