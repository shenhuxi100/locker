package com.tw;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LockerTest {
    @Test
    void should_prompt_failure_and_no_ticket_pop_when_save_bag_given_locker_is_full() {
        int surplusCapacity = 0;
        Locker locker = new Locker(surplusCapacity);

        Integer result = locker.saveBag();

        assertNull(result);
        assertThat(locker.getSurplusCapacity(), is(0));
    }

    @Test
    void should_save_bag_successfully_and_pop_ticket_when_save_bag_given_locker_is_not_full() {
        int surplusCapacity = 19;
        Locker locker = new Locker(surplusCapacity);
        Integer ticketNumber = surplusCapacity;

        Integer result = locker.saveBag();

        assertThat(result, is(ticketNumber));
        assertThat(locker.getSurplusCapacity(), is(18));

    }

    @Test
    void should_prompt_failure_when_take_bag_given_ticket_is_invalid() {
        int surplusCapacity = 2;
        Integer ticket = 3;
        Locker locker = new Locker(surplusCapacity);

        boolean result = locker.takeBag(ticket);

        assertFalse(result);
        assertThat(locker.getSurplusCapacity(), is(2));

    }

    @Test
    void should_prompt_failure_when_take_bag_given_ticket_is_duplicated() {
        int surplusCapacity = 10;
        Locker locker = new Locker(surplusCapacity);
        Integer ticket = locker.saveBag();

        boolean result = locker.takeBag(ticket);

        assertTrue(result);
        assertFalse(locker.takeBag(ticket));
    }

    @Test
    void should_take_bag_successfully_when_take_bag_given_ticket_is_valid() {
        int surplusCapacity = 10;
        Locker locker = new Locker(surplusCapacity);
        Integer ticket = locker.saveBag();

        boolean result = locker.takeBag(ticket);

        assertTrue(result);
        assertThat(locker.getSurplusCapacity(), is(10));
    }
}