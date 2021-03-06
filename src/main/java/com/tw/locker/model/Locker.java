package com.tw.locker.model;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int capacity;

    private Map<Ticket, Bag> lockerMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket saveBag(Bag bag) {
        if (lockerMap.size() >= capacity) {
            throw new NoCapacityException();
        }

        Ticket ticket = new Ticket();
        lockerMap.put(ticket, bag);
        return ticket;
    }

    public Bag takeBag(Ticket ticket) {
        Bag bag = lockerMap.get(ticket);
        if (lockerMap.get(ticket) != null) {
            lockerMap.remove(ticket);
            return bag;
        }

        throw new InvalidTicketException();
    }

    public int getRemainingCapacity() {
        return capacity - lockerMap.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isValidTicket(Ticket ticket) {
        return lockerMap.get(ticket) != null;
    }

    public String getReport() {
        return MessageFormat.format("\tL {0} {1}\n", getRemainingCapacity(), capacity);
    }
}
