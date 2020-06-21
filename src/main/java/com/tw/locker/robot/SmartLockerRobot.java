package com.tw.locker.robot;

import com.tw.locker.exception.NoCapacityException;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot extends BaseLockerRobot{
    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    public Ticket saveBag(Bag bag) {
        lockers.sort(Comparator.comparing(Locker::getRemainingCapacity).reversed());
        if (!lockers.isEmpty() && lockers.get(0).getRemainingCapacity() > 0) {
            return lockers.get(0).saveBag(bag);
        }

        throw new NoCapacityException();
    }
}
