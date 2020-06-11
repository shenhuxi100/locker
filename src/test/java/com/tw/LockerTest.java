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

    @Test
    void should_pop_ticket_when_save_bag_given_locker_is_not_full() {
        int initialCapacity = 20;
        int surplusCapacity = 19;

        Locker locker = new Locker(initialCapacity, surplusCapacity);
        assertTrue(locker.saveBag());
    }

    @Test
    void should_take_bag_failed_when_save_bag_given_invalid_ticket() {
        int initialCapacity = 20;
        int surplusCapacity = 19;
        String ticket = "Invalid ticket";

        Locker locker = new Locker(initialCapacity, surplusCapacity);
        assertFalse(locker.takeBag(ticket));
    }

    @Test
    void should_take_bag_success_when_save_bag_given_valid_ticket() {
        int initialCapacity = 20;
        int surplusCapacity = 19;
        String ticket = "123456";

        Locker locker = new Locker(initialCapacity, surplusCapacity);
        assertTrue(locker.takeBag(ticket));
    }
}