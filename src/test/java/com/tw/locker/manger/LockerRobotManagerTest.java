package com.tw.locker.manger;

import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.model.Ticket;
import com.tw.locker.robot.BaseLockerRobot;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LockerRobotManagerTest {
    /**
     *Given LockerRobotManager管理2个locker & 两个locker均有空间 & 未管理robot，When 让LockerRobotManager存包，Then 成功存入第一个柜子，Manager返回票据
     *
     * Given LockerRobotManager管理2个locker & 第一个柜子已满，第二个未满 & 未管理robot，When 让LockerRobotManager存包，Then 成功存入第二个柜子，Manager返回票据
     *
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
}