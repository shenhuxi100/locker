package com.tw;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LockerTest {
/**
 * Given locker 已满，when 存包， then 未出票，存包失败
 * Given locker 未满，when 存包， then 出票，存包成功
 * Given 有效票据， when 取包，then 取包成功
 * Given 无效票据， when 取包， then 取包失败
 */
    @Test
    void should_no_pop_ticket_when_save_bag_given_locker_is_full() {
        int initialCapacity = 20;
        int surplusCapacity = 0;

        Locker locker = new Locker(initialCapacity, surplusCapacity);
        assertFalse(locker.saveBag());
    }
}