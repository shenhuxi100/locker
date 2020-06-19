package com.tw.locker.model;

import com.tw.locker.exception.InvalidTicketException;
import com.tw.locker.exception.NoCapacityException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Given SmartLockerRobot管理两个柜子，并且SmartLockerRobot拿到一张伪造票，When SmartLockerRobot取包，Then 取包失败，提示无效票
 * <p>
 * Given SmartLockerRobot、PrimaryLockerRobot同时管理两个柜子，并且PrimaryLockerRobot拿到一张由SmartLockerRobot存包获得的有效票，When PrimaryLockerRobot取包，Then PrimaryLockerRobot返回一个包
 * <p>
 * Given SmartLockerRobot、PrimaryLockerRobot同时管理两个柜子，并且SmartLockerRobot拿到一张由PrimaryLockerRobot存包获得的有效票，When SmartLockerRobot取包，Then SmartLockerRobot返回一个包
 * <p>
 * Given SmartLockerRobot管理A，B两个柜子、PrimaryLockerRobot管理C，D两个柜子，并且PrimaryLockerRobot拿到一张由SmartLockerRobot存包获得的有效票，When PrimaryLockerRobot取包，Then 取包失败，提示无效票
 * <p>
 * Given SmartLockerRobot管理A，B两个柜子、PrimaryLockerRobot管理C，D两个柜子，并且SmartLockerRobot拿到一张由PrimaryLockerRobot存包获得的有效票，When SmartLockerRobot取包，Then 取包失败，提示无效票
 */
class SmartLockerRobotTest {
    @Test
    void should_return_ticket_and_store_bag_in_1st_locker_when_save_bag_given_smart_locker_robot_manage_2_under_filled_lockers_and_1st_locker_has_1_more_capacity_2nd_locker() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(2), new Locker(1));
        Locker firstLocker = lockers.get(0);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);

        Ticket ticket = smartLockerRobot.saveBag(bag);

        assertEquals(firstLocker.takeBag(ticket), bag);
    }

    @Test
    void should_return_ticket_and_store_bag_in_2nd_locker_when_save_bag_given_smart_locker_robot_manage_2_under_filled_lockers_and_2nd_locker_has_1_more_capacity_1st_locker() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(2));
        Locker secondLocker = lockers.get(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);

        Ticket ticket = smartLockerRobot.saveBag(bag);

        assertEquals(secondLocker.takeBag(ticket), bag);
    }

    @Test
    void should_return_ticket_and_store_bag_in_1st_locker_when_save_bag_given_smart_locker_robot_manage_2_same_remaining_capacity_under_filled_lockers() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        Locker firstLocker = lockers.get(0);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);

        Ticket ticket = smartLockerRobot.saveBag(bag);

        assertEquals(firstLocker.takeBag(ticket), bag);
    }

    @Test
    void should_throw_NoCapacityException_when_save_bag_given_smart_locker_robot_manage_2_full_lockers() {
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
    void should_return_the_same_saving_bag_when_take_bag_given_valid_ticket_and_smart_locker_robot_manage_2_under_filled_lockers() {
        Bag bag = new Bag();
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
        Ticket validTicket = smartLockerRobot.saveBag(bag);

        Bag returnBag = smartLockerRobot.takeBag(validTicket);
        assertEquals(bag, returnBag);
    }

    @Test
    void should_throw_InvalidTicketException_when_take_bag_given_invalid_ticket_and_smart_locker_robot_manage_2_under_filled_lockers() {
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
        Ticket invalidTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> smartLockerRobot.takeBag(invalidTicket));
    }
}
