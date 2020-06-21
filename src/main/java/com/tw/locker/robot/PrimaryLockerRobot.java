package com.tw.locker.robot;

import com.tw.locker.exception.NoCapacityException;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;

import java.util.List;

public class PrimaryLockerRobot extends BaseLockerRobot{
    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    public Ticket saveBag(Bag bag) {
        for (Locker locker : lockers) {
            if (locker.getRemainingCapacity()>0) {
                return locker.saveBag(bag);
            }
        }
        throw new NoCapacityException();
    }
}
