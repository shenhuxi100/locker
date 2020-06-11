package com.tw;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LockerTest {
    @Test
    void should_no_pop_ticket_when_save_bag_given_locker_is_full() {
        int surplusCapacity = 0;
        Locker locker = new Locker(surplusCapacity);

        assertFalse(locker.saveBag());
    }

    @Test
    void should_pop_ticket_when_save_bag_given_locker_is_not_full() {
        int surplusCapacity = 19;
        Locker locker = new Locker(surplusCapacity);

        assertTrue(locker.saveBag());
    }

    @Test
    void should_take_bag_failed_when_save_bag_given_invalid_ticket() {
        int surplusCapacity = 2;
        String ticket = "Invalid ticket";
        Locker locker = new Locker(surplusCapacity);

        assertFalse(locker.takeBag(ticket));
    }

    @Test
    void should_take_bag_success_when_save_bag_given_valid_ticket() {
        int surplusCapacity = 10;
        String ticket = "123456";
        Locker locker = new Locker(surplusCapacity);

        assertTrue(locker.takeBag(ticket));
    }
}