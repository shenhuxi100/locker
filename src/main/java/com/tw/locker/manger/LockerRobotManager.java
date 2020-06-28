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
            if (robot.isValidTicket(ticket))
                return robot.takeBag(ticket);
        }

        throw new InvalidTicketException();
    }

    public int getRemainingCapacity() {
        int lockersRemainingCapacity = lockers.stream().mapToInt(Locker::getRemainingCapacity).sum();
        int robotsRemainingCapacity = robots.stream().mapToInt(BaseLockerRobot::getRemainingCapacity).sum();
        return lockersRemainingCapacity + robotsRemainingCapacity;
    }

    public int getTotalCapacity() {
        int lockersTotalCapacity = lockers.stream().mapToInt(Locker::getCapacity).sum();
        int robotsTotalCapacity = robots.stream().mapToInt(BaseLockerRobot::getTotalCapacity).sum();
        return lockersTotalCapacity + robotsTotalCapacity;
    }

    public String getReport() {
        String report = "M " + getRemainingCapacity() + " " + getTotalCapacity() + "\n";

        report = lockers.isEmpty() ? report : lockers.stream()
                .map(Locker::getReport)
                .reduce(report, (partialReport, lockerReport) -> partialReport + lockerReport);

        return robots.isEmpty() ? report : robots.stream()
                .map(BaseLockerRobot::getReport)
                .reduce(report, (partialReport, robotReport) -> partialReport + robotReport);


    }
}
