package com.tw.locker.model;


import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LockerTest {
    @Test
    void should_throw_NoCapacityException_when_save_bag_given_locker_is_full() {
        int capacity = 1;
        Locker locker = new Locker(capacity);

        Bag bag = new Bag();
        locker.saveBag(bag);
        assertThrows(NoCapacityException.class, () -> locker.saveBag(bag));
    }

    @Test
    void should_return_ticket_when_save_bag_given_locker_is_not_full() {
        int capacity = 19;
        Locker locker = new Locker(capacity);

        Bag bag = new Bag();
        Ticket ticket = locker.saveBag(bag);
        assertTrue(ticket instanceof Ticket);
    }

    @Test
    void should_throw_InvalidTicketException_failure_when_take_bag_given_ticket_is_invalid() {
        int capacity = 10;
        Locker locker = new Locker(capacity);

        Bag bag = new Bag();
        locker.saveBag(bag);
        Ticket invalidTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> locker.takeBag(invalidTicket));
    }

    @Test
    void should_throw_InvalidTicketException_when_take_bag_given_ticket_is_duplicated() {
        int capacity = 10;
        Locker locker = new Locker(capacity);

        Bag bag = new Bag();
        Ticket ticket = locker.saveBag(bag);
        locker.takeBag(ticket);

        assertThrows(InvalidTicketException.class, () -> locker.takeBag(ticket));
    }

    @Test
    void should_take_bag_which_is_same_with_saving_bag_when_take_bag_given_ticket_is_valid() {
        int capacity = 10;
        Locker locker = new Locker(capacity);

        Bag bag = new Bag();
        Ticket ticket = locker.saveBag(bag);
        Bag responseBag = locker.takeBag(ticket);

        assertEquals(bag, responseBag);
    }
}