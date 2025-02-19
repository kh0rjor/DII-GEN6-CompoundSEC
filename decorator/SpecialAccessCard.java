package decorator;

import visitor.VisitorCard;

public class SpecialAccessCard extends VisitorCardDecorator {
    public SpecialAccessCard(VisitorCard decoratedCard) {
        super(decoratedCard);
    }

    @Override
    public String getType() {
        return super.getType() + " + Special Access";
    }
}
