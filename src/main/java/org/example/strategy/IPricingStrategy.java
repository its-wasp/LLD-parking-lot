package org.example.strategy;
import org.example.model.Ticket;

public interface IPricingStrategy {
    double calculatePrice(Ticket ticket);
}

