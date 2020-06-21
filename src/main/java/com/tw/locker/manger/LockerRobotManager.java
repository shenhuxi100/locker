package com.tw.locker.manger;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;
import com.tw.locker.robot.BaseLockerRobot;

import java.util.List;

public class LockerRobotManager {
    private List<Locker> lockers;
    private List<BaseLockerRobot> robots;

    public LockerRobotManager(List<Locker> lockers, List<BaseLockerRobot> robots) {
        this.lockers = lockers;
        this.robots = robots;
    }

    public Ticket saveBag(Bag bag) {
        for (BaseLockerRobot robot : robots) {
            if (robot.getRemainingCapacity() > 0) {
                return robot.saveBag(bag);
            }
        }

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

        for (BaseLockerRobot robot : robots) {
            return robot.takeBag(ticket);
        }

        throw new InvalidTicketException();
    }
}
