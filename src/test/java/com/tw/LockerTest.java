package com.tw;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LockerTest {
    @Test
    void should_save_bag_failed_when_given_locker_is_full_then_no_ticket_pop() {
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
    void should_save_bag_successful_when_given_locker_is_not_full_then_pop_a_ticket() {
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
    void should_take_bag_failed_when_given_invalid_ticket() {
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
    void should_take_bag_failed_when_given_duplicated_ticket() {
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
    void should_take_bag_successful_when_given_valid_ticket_then_bag_is_taken() {
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