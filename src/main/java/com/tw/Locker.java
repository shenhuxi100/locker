package com.tw;

import sun.net.www.protocol.http.HttpURLConnection;

public class Locker {
    private int initialCapacity;
    private int surplusCapacity;

    public Locker(int initialCapacity, int surplusCapacity) {
        this.initialCapacity = initialCapacity;
        this.surplusCapacity = surplusCapacity;
    }

    public boolean saveBag() {
        if(surplusCapacity <= 0) {
            System.out.println("出票失败");
            return false;
        }
        surplusCapacity++;
        return true;
    }
}
