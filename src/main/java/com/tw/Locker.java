package com.tw;

import java.util.regex.Pattern;

public class Locker {
    private int surplusCapacity;

    public Locker(int surplusCapacity) {
        this.surplusCapacity = surplusCapacity;
    }

    public boolean saveBag() {
        if(surplusCapacity <= 0) {
            System.out.println("出票失败");
            return false;
        }
        surplusCapacity++;
        System.out.println("出票成功");
        return true;
    }

    public boolean takeBag(String ticket) {
        boolean result = checkTicket(ticket);
        System.out.println(result ? "取包成功" :"取包失败");
        return result;
    }

    private boolean checkTicket(String ticket) {
        return Pattern.matches("^\\d+", ticket);
    }
}
