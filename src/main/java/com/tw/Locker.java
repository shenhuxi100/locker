package com.tw;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int surplusCapacity;
    private Map<Integer, String> ticketLocker = new HashMap<>();

    public Locker(int surplusCapacity) {
        this.surplusCapacity = surplusCapacity;
    }

    public Integer saveBag() {
        if (surplusCapacity <= 0) {
            System.out.println("出票失败");
            return null;
        }

        Integer ticketNumber = surplusCapacity;
        surplusCapacity--;
        ticketLocker.put(ticketNumber, "1");
        System.out.println("出票成功");
        return ticketNumber;
    }

    public boolean takeBag(Integer ticket) {
        String locker = ticketLocker.get(ticket);
        if (locker != null) {
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
