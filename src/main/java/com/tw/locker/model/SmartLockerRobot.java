package com.tw.locker.model;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartLockerRobot {
    private List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        lockers = lockers.stream().sorted(Comparator.comparing(Locker::getRemainingCapacity).reversed()).collect(Collectors.toList());

        for (Locker locker : lockers) {
            if (locker.getRemainingCapacity() > 0) {
                return locker.saveBag(bag);
            }
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
