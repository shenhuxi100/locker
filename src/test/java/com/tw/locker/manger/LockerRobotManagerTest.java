package com.tw.locker.manger;

import com.tw.locker.exception.NoCapacityException;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;
import com.tw.locker.robot.BaseLockerRobot;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LockerRobotManagerTest {
    /*
     * Given LockerRobotManager管理2个已满的locker & 未管理robot，When 让LockerRobotManager存包，Then 存包失败，提示柜子已满
     *
     */
    @Test
    void should_save_in_first_locker_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2_unfilled_locker_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(Arrays.asList(firstLocker, new Locker(1)), Arrays.asList(new BaseLockerRobot(Collections.emptyList())));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertEquals(bag, firstLocker.takeBag(ticket));
    }

    @Test
    void should_save_in_second_locker_and_return_ticket_when_LockerRobotManager_save_bag_given_manage_2nd_unfilled_locker_1st_filled_locker_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(new BaseLockerRobot(Collections.emptyList())));
        Bag bag = new Bag();

        lockerRobotManage.saveBag(bag);
        bag = new Bag();
        Ticket ticket = lockerRobotManage.saveBag(bag);

        assertEquals(bag, secondLocker.takeBag(ticket));
    }

    @Test
    void should_throw_NoCapacityException_when_LockerRobotManager_save_bag_given_manage_2_filled_locker_not_manage_robot() {
        Locker firstLocker = new Locker(1);
        LockerRobotManager lockerRobotManage = new LockerRobotManager(Arrays.asList(firstLocker, new Locker(1)), Arrays.asList(new BaseLockerRobot(Collections.emptyList())));
        Bag bag = new Bag();

        lockerRobotManage.saveBag(bag);
        lockerRobotManage.saveBag(bag);

        assertThrows(NoCapacityException.class, () -> lockerRobotManage.saveBag(new Bag()));
    }
}