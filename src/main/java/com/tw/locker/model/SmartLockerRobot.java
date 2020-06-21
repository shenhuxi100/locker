package com.tw.locker.model;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot {
    private List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        lockers.sort(Comparator.comparing(Locker::getRemainingCapacity).reversed());
        if (!lockers.isEmpty() && lockers.get(0).getRemainingCapacity() > 0) {
            return lockers.get(0).saveBag(bag);
        }

        throw new NoCapacityException();
    }

    public Bag takeBag(Ticket ticket) {
        for (Locker locker : lockers) {
            if (locker.isValidTicket(ticket)) {
                return locker.takeBag(ticket);
            }
        }

        throw new InvalidTicketException();
    }
}
