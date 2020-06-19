package com.tw.locker.model;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SmartLockerRobotTest {
    @Test
    void should_return_ticket_and_store_bag_in_1st_locker_when_smart_locker_robot_save_bag_given_smart_locker_robot_manage_2_under_filled_lockers_and_1st_locker_has_1_more_capacity_2nd_locker() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(2), new Locker(1));
        Locker firstLocker = lockers.get(0);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);

        Ticket ticket = smartLockerRobot.saveBag(bag);

        assertEquals(firstLocker.takeBag(ticket), bag);
    }

    @Test
    void should_return_ticket_and_store_bag_in_2nd_locker_when_smart_locker_robot_save_bag_given_smart_locker_robot_manage_2_under_filled_lockers_and_2nd_locker_has_1_more_capacity_1st_locker() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(2));
        Locker secondLocker = lockers.get(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);

        Ticket ticket = smartLockerRobot.saveBag(bag);

        assertEquals(secondLocker.takeBag(ticket), bag);
    }

    @Test
    void should_return_ticket_and_store_bag_in_1st_locker_when_smart_locker_robot_save_bag_given_smart_locker_robot_manage_2_same_remaining_capacity_under_filled_lockers() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        Locker firstLocker = lockers.get(0);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);

        Ticket ticket = smartLockerRobot.saveBag(bag);

        assertEquals(firstLocker.takeBag(ticket), bag);
    }

    @Test
    void should_throw_NoCapacityException_when_smart_locker_robot_save_bag_given_smart_locker_robot_manage_2_full_lockers() {
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
        Bag bag = new Bag();
        smartLockerRobot.saveBag(bag);
        bag = new Bag();
        smartLockerRobot.saveBag(bag);

        Bag thirdBag = new Bag();
        assertThrows(NoCapacityException.class, () -> smartLockerRobot.saveBag(thirdBag));
    }

    @Test
    void should_return_the_same_saving_bag_when_smart_locker_robot_take_bag_given_valid_ticket_and_smart_locker_robot_manage_2_under_filled_lockers() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
        Ticket validTicket = smartLockerRobot.saveBag(bag);

        Bag returnBag = smartLockerRobot.takeBag(validTicket);
        assertEquals(bag, returnBag);
    }

    @Test
    void should_throw_InvalidTicketException_when_smart_locker_robot_take_bag_given_invalid_ticket_and_smart_locker_robot_manage_2_under_filled_lockers() {
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
        Ticket invalidTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> smartLockerRobot.takeBag(invalidTicket));
    }

    @Test
    void should_return_the_same_saving_bag_when_smart_locker_robot_take_bag_given_valid_ticket_provided_by_primary_locker_robot_and_2_robots_manage_2_same_lockers() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
        Ticket validTicketFromPrimaryLockerRobot = primaryLockerRobot.saveBag(bag);

        Bag returnBag = smartLockerRobot.takeBag(validTicketFromPrimaryLockerRobot);
        assertEquals(bag, returnBag);
    }

    @Test
    void should_throw_InvalidTicketException_when_smart_locker_robot_take_bag_given_valid_ticket_provided_by_primary_locker_robot_and_2_robots_manage_2_different_under_filled_lockers() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        List<Locker> lockers2 = Arrays.asList(new Locker(1), new Locker(1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers2);
        Ticket validTicketFromPrimaryLockerRobot = primaryLockerRobot.saveBag(bag);

        assertThrows(InvalidTicketException.class, () -> smartLockerRobot.takeBag(validTicketFromPrimaryLockerRobot));
    }
}
