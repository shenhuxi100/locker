package com.tw;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int surplusCapacity;
    private Map<String, String> ticketLocker = new HashMap<>();

    public Locker(int surplusCapacity) {
        this.surplusCapacity = surplusCapacity;
    }

    public String saveBag() {
        if(surplusCapacity <= 0) {
            System.out.println("出票失败");
            return null;
        }

        surplusCapacity++;
        String ticketNumber = LocalDateTime.now().toString();
        ticketLocker.put(ticketNumber, "1");
        System.out.println("出票成功");
        return ticketNumber;
    }

    public boolean takeBag(String ticket) {
        String locker = ticketLocker.get(ticket);
        if (locker != null){
            ticketLocker.remove(ticket);
            System.out.println("取包成功");
            return true;
        }

        System.out.println("取包失败");
        return false;
    }

}
