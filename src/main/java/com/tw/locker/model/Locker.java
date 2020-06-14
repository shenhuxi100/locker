package com.tw.locker.model;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;

import java.util.HashMap;
import java.util.Map;

class Locker {
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
}
