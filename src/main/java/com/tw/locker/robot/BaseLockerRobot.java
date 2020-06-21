package com.tw.locker.robot;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;

import java.util.List;

public class BaseLockerRobot {
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
}
