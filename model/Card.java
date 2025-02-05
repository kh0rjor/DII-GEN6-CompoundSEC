package model;

public class Card {
    private String cardID;
    private String owner;
    private boolean isActive;

    public Card(String cardID, String owner) {
        this.cardID = cardID;
        this.owner = owner;
        this.isActive = true;
    }

    public String getCardID() {
        return cardID;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }
}
