package com.tw.locker.model;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrimaryLockerRobotTest {
    private PrimaryLockerRobot primaryLockerRobot;
    private List<Locker> lockers;

    @BeforeEach
    void getRobotWith2LockersWith1Cell() {
        lockers = Arrays.asList(new Locker(1), new Locker(1));
        primaryLockerRobot = new PrimaryLockerRobot(lockers);
    }

    @Test
    void should_return_ticket_and_store_bag_in_1st_locker_when_save_bag_given_robot_manage_2_under_filled_lockers() {
        Bag bag = new Bag();
        Locker firstLocker = lockers.get(0);

        Ticket ticket = primaryLockerRobot.saveBag(bag);

        // assertNotNull(ticket); //looks duplicated test here, so comment here
        assertEquals(firstLocker.takeBag(ticket), bag);
    }

    @Test
    void should_return_ticket_and_store_bag_in_2nd_locker_when_save_bag_given_robot_manage_1st_full_locker_and_2nd_under_filled_locker() {
        Bag firstBag = new Bag();
        primaryLockerRobot.saveBag(firstBag);
        Bag secondBag = new Bag();
        Locker secondLocker = lockers.get(1);

        Ticket secondTicket = primaryLockerRobot.saveBag(secondBag);

        assertEquals(secondLocker.takeBag(secondTicket), secondBag);
    }

    @Test
    void should_throw_NoCapacityException_when_save_bag_given_robot_manage_2_full_lockers() {
        Bag bag = new Bag();
        primaryLockerRobot.saveBag(bag);
        bag = new Bag();
        primaryLockerRobot.saveBag(bag);

        Bag thirdBag = new Bag();
        assertThrows(NoCapacityException.class, () -> primaryLockerRobot.saveBag(thirdBag));
    }

    @Test
    void should_return_the_same_saving_bag_when_take_bag_given_valid_ticket_and_robot_manage_2_under_filled_lockers() {
        Bag bag = new Bag();
        Ticket validTicket = primaryLockerRobot.saveBag(bag);

        Bag returnBag = primaryLockerRobot.takeBag(validTicket);
        assertEquals(bag, returnBag);
    }

    @Test
    void should_throw_InvalidTicketException_when_take_bag_given_invalid_ticket_and_robot_manage_2_under_filled_lockers() {
        Ticket invalidTicket = new Ticket();
        assertThrows(InvalidTicketException.class, () -> primaryLockerRobot.takeBag(invalidTicket));
    }
}
