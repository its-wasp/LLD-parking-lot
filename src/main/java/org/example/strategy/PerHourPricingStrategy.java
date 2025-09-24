package org.example.strategy;

import org.example.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class PerHourPricingStrategy implements IPricingStrategy {
    private static final double HOURLY_RATE = 10.0; // $10 per hour

    @Override
    public double calculatePrice(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = ticket.getExitTime();

        if (exitTime == null) {
            throw new IllegalStateException("Exit time has not been set for the ticket.");
        }

        Duration duration = Duration.between(entryTime, exitTime);
        long hours = duration.toHours();
        if (duration.toMinutesPart() > 0) {
            hours++; // rounding up
        }

        return hours * HOURLY_RATE;
    }
}
