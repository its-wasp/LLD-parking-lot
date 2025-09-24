package org.example.model;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private final String id;
    private final List<Floor> floors;
    private final List<EntryGate> entryGates;
    private final List<ExitGate> exitGates;
    private final Map<String, Ticket> activeTickets;

    public ParkingLot(String id, List<Floor> floors, List<EntryGate> entryGates, List<ExitGate> exitGates) {
        this.id = id;
        this.floors = floors;
        this.entryGates = entryGates;
        this.exitGates = exitGates;
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addTicket(Ticket ticket) {
        activeTickets.put(ticket.getTicketId(), ticket);
    }

    public Ticket getTicket(String ticketId) {
        return activeTickets.get(ticketId);
    }

    public void removeTicket(String ticketId) {
        activeTickets.remove(ticketId);
    }
}