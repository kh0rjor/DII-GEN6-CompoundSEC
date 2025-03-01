package decorator;

import access.AccessStrategy;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendAccessDecorator extends CardDecorator {

    public WeekendAccessDecorator(AccessStrategy decoratedCard) {
        super(decoratedCard);
    }

    @Override
    public boolean hasAccess(String room) {
        // Check if today is Saturday or Sunday
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return decoratedCard.hasAccess(room);
        }

        // Otherwise, proceed with the regular access check from the decorated card
        return decoratedCard.hasAccess(room);
    }

    @Override
    public boolean isAccessTimeValid() {
        // Optionally, you can override the time validation for weekends here if needed.
        // For weekends, always return true (valid access time)
        return true; // Always valid for weekends, modify this if specific time checks are needed
    }
}
