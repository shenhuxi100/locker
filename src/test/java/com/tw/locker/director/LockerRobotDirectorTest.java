package com.tw.locker.director;

import com.tw.locker.manger.LockerRobotManager;
import com.tw.locker.model.Bag;
import com.tw.locker.model.Locker;
import com.tw.locker.robot.PrimaryLockerRobot;
import com.tw.locker.robot.SmartLockerRobot;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

/*
 * Given director管理一个manager，manager管理两个robot，不管理locker，每个robot管理一个locker
 * Given director管理一个manager，manager不管理robot，manager管理一个locker，同时存在一个被其他director管理的manager
 * Given director管理一个manager，manager不管理robot，只管理locker
 * Given director管理一个manager，manager既管理locker，也管理robot
 * Given director管理两个manager，manager1管理两个robot，不管理locker；manager2不管理robot，只管理locker
 */
class LockerRobotDirectorTest {
    @Test
    void should_return_report_with_MRL_when_LockerRobotDirector_view_report_given_1_manager_which_manage_1_robot_with_1_locker() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(2)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), singletonList(primaryLockerRobot));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(singletonList(lockerRobotManager));

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);

        String report = lockerRobotDirector.viewReport();

        String expectReport = "M 1 2\n" +
                "\tR 1 2\n" +
                "\t\tL 1 2\n";
        assertEquals(expectReport, report);
    }

    @Test
    void should_return_report_with_MRLRL_when_LockerRobotDirector_view_report_given_1_manager_which_manage_2_robots_each_with_1_locker() {
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(singletonList(new Locker(1)));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(2)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), asList(smartLockerRobot, primaryLockerRobot));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(singletonList(lockerRobotManager));

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);
        bag = new Bag();
        lockerRobotManager.saveBag(bag);

        String report = lockerRobotDirector.viewReport();

        String expectReport = "M 1 3\n" +
                "\tR 0 1\n" +
                "\t\tL 0 1\n" +
                "\tR 1 2\n" +
                "\t\tL 1 2\n";
        assertEquals(expectReport, report);
    }
}