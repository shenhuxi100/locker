package com.tw.locker.model;

import java.util.List;

public class Robot {
    private List<Locker> lockers;

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        return lockers.get(0).saveBag(bag);
    }

    public List<Locker> getLockers() {
        return lockers;
    }
}
