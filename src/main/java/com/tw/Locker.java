package com.tw;

import java.util.ArrayList;
import java.util.List;

public class Locker {
    private int surplusCapacity;
    private List<Ticket> ticketLocker = new ArrayList<>();

    public Locker(int surplusCapacity) {
        this.surplusCapacity = surplusCapacity;
    }

    public Ticket saveBag() {
        if (surplusCapacity <= 0) {
            System.out.println("出票失败");
            return null;
        }

        Ticket ticket = new Ticket(surplusCapacity);
        ticketLocker.add(ticket);
        surplusCapacity--;
        System.out.println("出票成功");
        return ticket;
    }

    public boolean takeBag(Ticket ticket) {
        if (ticketLocker.contains(ticket)) {
            ticketLocker.remove(ticket);
            System.out.println("取包成功");
            surplusCapacity++;
            return true;
        }

        System.out.println("取包失败");
        return false;
    }


    public int getSurplusCapacity() {
        return surplusCapacity;
    }
}
