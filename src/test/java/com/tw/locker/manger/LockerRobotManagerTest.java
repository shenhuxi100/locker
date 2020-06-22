package com.tw.locker.manger;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;
import com.tw.locker.robot.PrimaryLockerRobot;
import com.tw.locker.robot.SmartLockerRobot;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LockerRobotManagerTest {
    @Test
    void should_save_in_first_locker_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2_unfilled_locker_and_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(firstLocker, secondLocker), emptyList());

        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.saveBag(bag);

        assertEquals(bag, firstLocker.takeBag(ticket));
    }

    @Test
    void should_save_in_second_locker_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2_robot_and_2nd_unfilled_locker_and_1st_filled_locker_and_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(firstLocker, secondLocker), emptyList());

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);
        bag = new Bag();
        Ticket ticket = lockerRobotManager.saveBag(bag);

        assertEquals(bag, secondLocker.takeBag(ticket));
    }

    @Test
    void should_throw_NoCapacityException_when_LockerRobotManager_save_bag_given_manage_2_filled_locker_and_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(firstLocker, secondLocker), emptyList());

        lockerRobotManager.saveBag(new Bag());
        lockerRobotManager.saveBag(new Bag());

        assertThrows(NoCapacityException.class, () -> lockerRobotManager.saveBag(new Bag()));
    }

    @Test
    void should_save_in_first_robot_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2_unfilled_robot_and_not_manage_locker() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        SmartLockerRobot secondSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), asList(firstSmartLockerRobot, secondSmartLockerRobot));

        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.saveBag(bag);

        assertEquals(bag, firstSmartLockerRobot.takeBag(ticket));
    }

    @Test
    void should_save_in_first_robot_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2_robot_and_2nd_unfilled_robot_and_1st_filled_robot_and_not_manage_locker() {
        PrimaryLockerRobot firstPrimaryLockerRobot = new PrimaryLockerRobot(asList(new Locker(1)));
        PrimaryLockerRobot secondPrimaryLockerRobot = new PrimaryLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), asList(firstPrimaryLockerRobot, secondPrimaryLockerRobot));

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);
        bag = new Bag();
        Ticket ticket = lockerRobotManager.saveBag(bag);

        assertEquals(bag, secondPrimaryLockerRobot.takeBag(ticket));
    }

    @Test
    void should_throw_NoCapacityException_when_LockerRobotManager_save_bag_given_manage_2_filled_robot_and_not_manage_locker() {
        PrimaryLockerRobot firstPrimaryLockerRobot = new PrimaryLockerRobot(asList(new Locker(1)));
        PrimaryLockerRobot secondPrimaryLockerRobot = new PrimaryLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), asList(firstPrimaryLockerRobot, secondPrimaryLockerRobot));

        lockerRobotManager.saveBag(new Bag());
        lockerRobotManager.saveBag(new Bag());

        assertThrows(NoCapacityException.class, () -> lockerRobotManager.saveBag(new Bag()));
    }

    @Test
    void should_save_bag_by_robot_when_LockerRobotManager_save_bag_given_manage_1_unfilled_robot_and_1_unfilled_locker() {
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker locker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(locker), asList(smartLockerRobot));

        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.saveBag(bag);

        assertEquals(bag, smartLockerRobot.takeBag(ticket));
    }

    @Test
    void should_save_bag_by_locker_when_LockerRobotManager_save_bag_given_manage_1_filled_robot_and_1_unfilled_locker() {
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker locker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(locker), asList(smartLockerRobot));

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);
        bag = new Bag();
        Ticket ticket = lockerRobotManager.saveBag(bag);

        assertEquals(bag, locker.takeBag(ticket));
    }

    @Test
    void should_throw_NoCapacityException_when_LockerRobotManager_save_bag_given_manage_1_filled_robot_and_1_filled_locker() {
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker locker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(locker), asList(smartLockerRobot));

        lockerRobotManager.saveBag(new Bag());
        lockerRobotManager.saveBag(new Bag());

        assertThrows(NoCapacityException.class, () -> lockerRobotManager.saveBag(new Bag()));
    }

    @Test
    void should_return_bag_when_LockerRobotManager_take_bag_given_manage_2_locker_and_not_manage_robot_and_valid_ticket() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(firstLocker, secondLocker), emptyList());

        Bag firstBag = new Bag();
        Ticket firstTicket = lockerRobotManager.saveBag(firstBag);
        Bag secondBag = new Bag();
        Ticket secondTicket = lockerRobotManager.saveBag(secondBag);

        assertEquals(firstBag, lockerRobotManager.takeBag(firstTicket));
        assertEquals(secondBag, lockerRobotManager.takeBag(secondTicket));
    }

    @Test
    void should_throw_InvalidTicketException_when_LockerRobotManager_take_bag_given_manage_2_locker_and_not_manage_robot_and_invalid_ticket() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(firstLocker, secondLocker), emptyList());

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);
        Ticket invalidTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> lockerRobotManager.takeBag(invalidTicket));
    }

    @Test
    void should_return_bag_when_LockerRobotManager_take_bag_given_manage_2_robot_and_not_manage_locker_and_valid_ticket() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        SmartLockerRobot secondSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), asList(firstSmartLockerRobot, secondSmartLockerRobot));

        Bag firstBag = new Bag();
        Ticket firstTicket = lockerRobotManager.saveBag(firstBag);
        Bag secondBag = new Bag();
        Ticket secondTicket = lockerRobotManager.saveBag(secondBag);

        assertEquals(firstBag, lockerRobotManager.takeBag(firstTicket));
        assertEquals(secondBag, lockerRobotManager.takeBag(secondTicket));
    }

    @Test
    void should_throw_NoCapacityException_when_LockerRobotManager_take_bag_given_manage_2_robot_and_not_manage_locker_and_invalid_ticket() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        SmartLockerRobot secondSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), asList(firstSmartLockerRobot, secondSmartLockerRobot));

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);
        Ticket invalidTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> lockerRobotManager.takeBag(invalidTicket));
    }

    @Test
    void should_return_bag_when_LockerRobotManager_take_bag_given_manage_1_robot_and_1_locker_and_valid_ticket() {
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker locker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(locker), asList(smartLockerRobot));

        Bag firstBag = new Bag();
        Ticket firstTicket = lockerRobotManager.saveBag(firstBag);
        Bag secondBag = new Bag();
        Ticket secondTicket = lockerRobotManager.saveBag(secondBag);

        assertEquals(firstBag, lockerRobotManager.takeBag(firstTicket));
        assertEquals(secondBag, lockerRobotManager.takeBag(secondTicket));
    }

    @Test
    void should_throw_InvalidTicketException_when_LockerRobotManager_take_bag_given_manage_1_locker_and_1_robot_and_invalid_ticket() {
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker locker = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(asList(locker), asList(smartLockerRobot));

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);
        Ticket invalidTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> lockerRobotManager.takeBag(invalidTicket));
    }
}