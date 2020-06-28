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

    @Test
    void should_return_report_with_ML_when_LockerRobotDirector_view_report_given_1st_manager_which_only_manage_1_locker_2nd_manager_which_is_not_manage_by_this_director() {
        Locker firstLocker = new Locker(2);
        LockerRobotManager lockerRobotManager1 = new LockerRobotManager(singletonList(firstLocker), emptyList());
        Locker secondLocker = new Locker(2);
        LockerRobotManager lockerRobotManager2 = new LockerRobotManager(singletonList(secondLocker), emptyList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(singletonList(lockerRobotManager1));

        Bag bag = new Bag();
        lockerRobotManager1.saveBag(bag);

        String report = lockerRobotDirector.viewReport();

        String expectReport = "M 1 2\n" +
                "\tL 1 2\n";
        assertEquals(expectReport, report);
    }

    @Test
    void should_return_report_with_ML_when_LockerRobotDirector_view_report_given_1_manager_which_only_manage_1_locker() {
        Locker locker = new Locker(2);
        LockerRobotManager lockerRobotManager1 = new LockerRobotManager(singletonList(locker), emptyList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(singletonList(lockerRobotManager1));

        Bag bag = new Bag();
        lockerRobotManager1.saveBag(bag);

        String report = lockerRobotDirector.viewReport();

        String expectReport = "M 1 2\n" +
                "\tL 1 2\n";
        assertEquals(expectReport, report);
    }

    @Test
    void should_return_report_with_MLRLRL_when_LockerRobotDirector_view_report_given_1_manager_which_manage_1_locker_and_2_robots_each_with_1_locker() {
        Locker locker = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(singletonList(new Locker(1)));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(2)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(locker), asList(smartLockerRobot, primaryLockerRobot));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(singletonList(lockerRobotManager));

        Bag bag = new Bag();
        lockerRobotManager.saveBag(bag);
        bag = new Bag();
        lockerRobotManager.saveBag(bag);


        String report = lockerRobotDirector.viewReport();

        String expectReport = "M 3 5\n" +
                "\tL 2 2\n" +
                "\tR 0 1\n" +
                "\t\tL 0 1\n" +
                "\tR 1 2\n" +
                "\t\tL 1 2\n";
        assertEquals(expectReport, report);
    }

    @Test
    void should_return_report_with_MRLRLML_when_LockerRobotDirector_view_report_given_1st_manager_which_manage_2_robots_each_with_1_locker_2nd_manager_only_manage_1_locker() {
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(singletonList(new Locker(1)));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(2)));
        LockerRobotManager lockerRobotManager1 = new LockerRobotManager(emptyList(), asList(smartLockerRobot, primaryLockerRobot));
        Locker locker = new Locker(2);
        LockerRobotManager lockerRobotManager2 = new LockerRobotManager(singletonList(locker), emptyList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(asList(lockerRobotManager1, lockerRobotManager2));

        Bag bag = new Bag();
        lockerRobotManager1.saveBag(bag);
        bag = new Bag();
        lockerRobotManager1.saveBag(bag);
        bag = new Bag();
        lockerRobotManager2.saveBag(bag);

        String report = lockerRobotDirector.viewReport();

        String expectReport = "M 1 3\n" +
                "\tR 0 1\n" +
                "\t\tL 0 1\n" +
                "\tR 1 2\n" +
                "\t\tL 1 2\n" +
                "M 1 2\n" +
                "\tL 1 2\n";
        assertEquals(expectReport, report);
    }
}