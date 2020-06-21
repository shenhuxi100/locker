package com.tw.locker.manger;

import com.tw.locker.exception.NoCapacityException;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;
import com.tw.locker.robot.BaseLockerRobot;
import com.tw.locker.robot.PrimaryLockerRobot;
import com.tw.locker.robot.SmartLockerRobot;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class LockerRobotManagerTest {
    /*
 Given LockerRobotManager管理2个柜子未满的SmartLockerRobot & 未管理柜子，When 让LockerRobotManager存包，Then 第一个SmartLockerRobot存包，Manager返回一张票

Given LockerRobotManager管理2个PrimaryLockerRobot & 第一个robot的柜子已满，第二个robot未满 & 未管理柜子，When 让LockerRobotManager存包，Then 第二个PrimaryLockerRobot存包，Manager返回一张票

Given LockerRobotManager管理2个柜子已满的PrimaryLockerRobot & 未管理柜子，When 让LockerRobotManager存包，Then 存包失败，提示柜子已满
     *
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
}