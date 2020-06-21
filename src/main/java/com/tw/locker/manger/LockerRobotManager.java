package com.tw.locker.manger;

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
        for (Locker locker : lockers) {
            if (locker.getRemainingCapacity()>0) {
                return locker.saveBag(bag);
            }
        }
        if (robots.size() > 0)
            return robots.get(0).saveBag(bag);

        throw new NoCapacityException();
    }
}
