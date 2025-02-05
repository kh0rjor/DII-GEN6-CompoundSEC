package controller;

import model.Card;
import model.AuditLog;

import java.util.HashMap;
import java.util.Map;

public class EmployeeController {
    private Map<String, Card> cardDatabase = new HashMap<>();

    public void issueCard(String cardID, String owner) {
        cardDatabase.put(cardID, new Card(cardID, owner));
        AuditLog.log("Issued card " + cardID + " to " + owner);
    }

    public void revokeCard(String cardID) {
        if (cardDatabase.containsKey(cardID)) {
            cardDatabase.get(cardID).deactivate();
            AuditLog.log("Revoked card " + cardID);
        }
    }

    public Map<String, Card> getCards() {
        return cardDatabase;
    }
}
