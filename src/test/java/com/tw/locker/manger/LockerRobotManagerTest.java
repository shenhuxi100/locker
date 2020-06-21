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
import static org.junit.jupiter.api.Assertions.*;

class LockerRobotManagerTest {
    /*

Given LockerRobotManager管理1个robot & 1个locker & 无效票据，When 让LockerRobotManager取包，Then 取包失败，提示无效票
     */
    @Test
    void should_save_in_first_locker_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2_unfilled_locker_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker, new Locker(1)), asList(new PrimaryLockerRobot((emptyList()))));

        Bag bag = new Bag();
        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertEquals(bag, firstLocker.takeBag(ticket));
    }

    @Test
    void should_save_in_second_locker_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2nd_unfilled_locker_1st_filled_locker_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker, secondLocker), asList(new PrimaryLockerRobot(emptyList())));

        Bag bag = new Bag();
        lockerRobotManage.saveBag(bag);
        bag = new Bag();
        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertEquals(bag, secondLocker.takeBag(ticket));
    }

    @Test
    void should_throw_NoCapacityException_when_LockerRobotManager_save_bag_given_manage_2_filled_locker_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker, new Locker(1)), asList(new PrimaryLockerRobot(emptyList())));

        Bag bag = new Bag();
        lockerRobotManage.saveBag(bag);
        lockerRobotManage.saveBag(bag);

        assertThrows(NoCapacityException.class, () -> lockerRobotManage.saveBag(new Bag()));
    }

    @Test
    void should_save_in_first_robot_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2_unfilled_robot_not_manage_locker() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManage = new LockerRobotManager(emptyList(), asList(firstSmartLockerRobot, new SmartLockerRobot(asList(new Locker(1)))));

        Bag bag = new Bag();
        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertEquals(bag, firstSmartLockerRobot.takeBag(ticket));
    }

    @Test
    void should_save_in_first_robot_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2nd_unfilled_robot_1st_filled_robot_not_manage_locker() {
        PrimaryLockerRobot firstPrimaryLockerRobot = new PrimaryLockerRobot(asList(new Locker(1)));
        PrimaryLockerRobot secondPrimaryLockerRobot = new PrimaryLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManage = new LockerRobotManager(emptyList(), asList(firstPrimaryLockerRobot, secondPrimaryLockerRobot));

        Bag bag = new Bag();
        lockerRobotManage.saveBag(bag);
        bag = new Bag();
        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertEquals(bag, secondPrimaryLockerRobot.takeBag(ticket));
    }

    @Test
    void should_throw_NoCapacityException_when_LockerRobotManager_save_bag_given_manage_2_filled_robot_not_manage_locker() {
        PrimaryLockerRobot firstPrimaryLockerRobot = new PrimaryLockerRobot(asList(new Locker(1)));
        PrimaryLockerRobot secondPrimaryLockerRobot = new PrimaryLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManage = new LockerRobotManager(emptyList(), asList(firstPrimaryLockerRobot, secondPrimaryLockerRobot));

        Bag bag = new Bag();
        lockerRobotManage.saveBag(bag);
        bag = new Bag();
        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertThrows(NoCapacityException.class, () -> lockerRobotManage.saveBag(new Bag()));
    }

    @Test
    void should_save_bag_by_robot_when_LockerRobotManager_save_bag_given_manage_1_unfilled_robot_and_1_unfilled_locker() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker), asList(firstSmartLockerRobot));

        Bag bag = new Bag();
        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertEquals(bag, firstSmartLockerRobot.takeBag(ticket));
    }

    @Test
    void should_save_bag_by_locker_when_LockerRobotManager_save_bag_given_manage_1_filled_robot_and_1_unfilled_locker() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker), asList(firstSmartLockerRobot));

        Bag bag = new Bag();
        lockerRobotManage.saveBag(bag);
        bag = new Bag();
        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertEquals(bag, firstLocker.takeBag(ticket));
    }

    @Test
    void should_save_bag_by_locker_when_LockerRobotManager_save_bag_given_manage_1_filled_robot_and_1_filled_locker() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker), asList(firstSmartLockerRobot));

        Bag bag = new Bag();
        lockerRobotManage.saveBag(bag);
        bag = new Bag();
        lockerRobotManage.saveBag(bag);

        assertThrows(NoCapacityException.class, () -> lockerRobotManage.saveBag(new Bag()));
    }

    @Test
    void should_return_bag_when_LockerRobotManager_take_bag_given_manage_2_locker_not_manage_robot_and_valid_ticket() {
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker, new Locker(1)), asList(new PrimaryLockerRobot((emptyList()))));

        Bag firstBag = new Bag();
        Ticket firstTicket = lockerRobotManage.saveBag(firstBag);
        Bag secondBag = new Bag();
        Ticket secondTicket = lockerRobotManage.saveBag(secondBag);

        assertEquals(secondBag, lockerRobotManage.takeBag(secondTicket));
        assertEquals(firstBag, lockerRobotManage.takeBag(firstTicket));
    }

    @Test
    void should_throw_InvalidTicketException_when_LockerRobotManager_take_bag_given_manage_2_locker_not_manage_robot_and_invalid_ticket() {
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker, new Locker(1)), asList(new PrimaryLockerRobot((emptyList()))));

        Bag bag = new Bag();
        lockerRobotManage.saveBag(bag);

        assertThrows(InvalidTicketException.class, () -> lockerRobotManage.takeBag(new Ticket()));
    }

    @Test
    void should_return_bag_when_LockerRobotManager_take_bag_given_manage_2_robot_not_manage_locker_valid_ticket() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        SmartLockerRobot secondSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManage = new LockerRobotManager(emptyList(), asList(firstSmartLockerRobot, secondSmartLockerRobot));

        Bag firstBag = new Bag();
        Ticket firstTicket = lockerRobotManage.saveBag(firstBag);
//        Bag secondBag = new Bag();
//        Ticket secondTicket = lockerRobotManage.saveBag(secondBag);

        assertEquals(firstBag, lockerRobotManage.takeBag(firstTicket));
//        assertEquals(secondBag, lockerRobotManage.takeBag(secondTicket));
    }

    @Test
    void should_throw_NoCapacityException_when_LockerRobotManager_take_bag_given_manage_2_robot_not_manage_locker_invalid_ticket() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        LockerRobotManager lockerRobotManage = new LockerRobotManager(emptyList(), asList(firstSmartLockerRobot, new SmartLockerRobot(asList(new Locker(1)))));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertThrows(InvalidTicketException.class, () -> lockerRobotManage.takeBag(new Ticket()));
    }

    @Test
    void should_return_bag_when_LockerRobotManager_take_bag_given_manage_1_robot_and_1_locker_valid_ticket() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker), asList(firstSmartLockerRobot));

        Bag firstBag = new Bag();
        Ticket firstTicket = lockerRobotManage.saveBag(firstBag);
        Bag secondBag = new Bag();
        Ticket secondTicket = lockerRobotManage.saveBag(secondBag);

        assertEquals(firstBag, lockerRobotManage.takeBag(firstTicket));
        assertEquals(secondBag, lockerRobotManage.takeBag(secondTicket));
    }

    @Test
    void should_throw_InvalidTicketException_when_LockerRobotManager_take_bag_given_manage_1_locker_1_robot_and_invalid_ticket() {
        SmartLockerRobot firstSmartLockerRobot = new SmartLockerRobot(asList(new Locker(1)));
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(asList(firstLocker), asList(firstSmartLockerRobot));

        Bag bag = new Bag();
        lockerRobotManage.saveBag(bag);

        assertThrows(InvalidTicketException.class, () -> lockerRobotManage.takeBag(new Ticket()));
    }
}