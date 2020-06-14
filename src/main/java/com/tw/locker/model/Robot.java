package com.tw.locker.model;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;

import java.util.List;

public class Robot {
    private List<Locker> lockers;

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        for (Locker locker : lockers) {
            if (!locker.isFullFilled()) {
                return locker.saveBag(bag);
            }
        }
        throw new NoCapacityException();
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    public Bag takeBag(Ticket invalidTicket) {
        for (Locker locker : lockers) {
            if (locker.isValidTicket(invalidTicket)) {
                return locker.takeBag(invalidTicket);
            }
        }

        throw new InvalidTicketException();
    }
}
