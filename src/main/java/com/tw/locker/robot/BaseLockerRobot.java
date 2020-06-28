package com.tw.locker.robot;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;

import java.util.List;

public abstract class BaseLockerRobot {
    protected List<Locker> lockers;

    public BaseLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Bag takeBag(Ticket ticket) {
        for (Locker locker : lockers) {
            if (locker.isValidTicket(ticket)) {
                return locker.takeBag(ticket);
            }
        }

        throw new InvalidTicketException();
    }

    public int getRemainingCapacity() {
        return lockers.stream().mapToInt(Locker::getRemainingCapacity).sum();
    }

    public int getTotalCapacity() {
        return lockers.stream().mapToInt(Locker::getCapacity).sum();
    }

    public boolean isValidTicket(Ticket ticket) {
        return lockers.stream().anyMatch(locker -> locker.isValidTicket(ticket));
    }

    public String getReport() {
        String report = "\tR " + getRemainingCapacity() + " " + getTotalCapacity() + "\n";
        return lockers.stream()
                .map(Locker::getReport)
                .reduce(report, (partialReport, lockerReport) -> partialReport+ "\t" + lockerReport);
    }

    public abstract Ticket saveBag(Bag bag);
}
