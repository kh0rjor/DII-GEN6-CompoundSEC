package decorator;

import access.AccessStrategy;

public class VIPAccess extends CardDecorator {
    public VIPAccess(AccessStrategy decoratedCard) {
        super(decoratedCard);
    }

    @Override
    public boolean hasAccess(String room) {
        return true;
    }

    @Override
    public boolean isAccessTimeValid() {
        return false;
    }
}
