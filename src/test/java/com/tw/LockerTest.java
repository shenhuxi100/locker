package com.tw;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LockerTest {
    @Test
    void should_prompt_failure_and_no_ticket_pop_when_save_bag_given_locker_is_full() {
        // given
        int surplusCapacity = 0;
        Locker locker = new Locker(surplusCapacity);

        //when
        String result = locker.saveBag();

        // then
        assertNull(result);
        assertThat(locker.getSurplusCapacity(), is(0));
    }

    @Test
    void should_save_bag_successfully_and_pop_ticket_when_save_bag_given_locker_is_not_full() {
        // given
        int surplusCapacity = 19;
        Locker locker = new Locker(surplusCapacity);
        String ticketNumber = LocalDateTime.now().toString();

        // when
        String result = locker.saveBag();

        // then
        assertThat(result, is(ticketNumber));
        assertThat(locker.getSurplusCapacity(), is(18));

    }

    @Test
    void should_prompt_failure_when_take_bag_given_ticket_is_invalid() {
        // given
        int surplusCapacity = 2;
        String ticket = "Invalid ticket";
        Locker locker = new Locker(surplusCapacity);

        // when
        boolean result = locker.takeBag(ticket);

        // then
        assertFalse(result);
        assertThat(locker.getSurplusCapacity(), is(2));

    }

    @Test
    void should_prompt_failure_when_take_bag_given_ticket_is_duplicated() {
        // given
        int surplusCapacity = 10;
        Locker locker = new Locker(surplusCapacity);
        String ticket = locker.saveBag();

        // when
        boolean result = locker.takeBag(ticket);

        // then
        assertTrue(result);
        assertFalse(locker.takeBag(ticket));
    }

    @Test
    void should_take_bag_successfully_when_take_bag_given_ticket_is_valid() {
        // given
        int surplusCapacity = 10;
        Locker locker = new Locker(surplusCapacity);
        String ticket = locker.saveBag();

        // when
        boolean result = locker.takeBag(ticket);

        // then
        assertTrue(result);
        assertThat(locker.getSurplusCapacity(), is(10));
    }
}