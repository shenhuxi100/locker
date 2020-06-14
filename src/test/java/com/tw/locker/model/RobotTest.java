package com.tw.locker.model;

import com.tw.locker.exception.NoCapacityException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Given: 一个机器人有2的柜子，两个柜子都满， When: 存包 Then: 存包失败，提示柜子已满
 * Given: 一张无效票， When: 取包 Then: 提示无效票据
 * Given: 一张有效票， When: 取包 Then: 机器人返回一个包
 */
public class RobotTest {
    @Test
    void should_return_ticket_and_store_bag_in_1st_locker_when_save_bag_given_robot_have_2_under_filled_lockers() {
        Robot robot = new Robot(Arrays.asList(new Locker(5), new Locker(6)));
        Bag bag = new Bag();
        Ticket ticket = robot.saveBag(bag);

        // assertNotNull(ticket); //looks duplicated test here, so comment here
        assertEquals(robot.getLockers().get(0).takeBag(ticket), bag);
    }

    @Test
    void should_return_ticket_and_store_bag_in_2nd_locker_when_save_bag_given_robot_have_2nd_under_filled_and_1st_full_filled_locker() {
        Robot robot = new Robot(Arrays.asList(new Locker(1), new Locker(6)));
        Bag bag = new Bag();
        robot.saveBag(bag);
        Ticket secondTicket = robot.saveBag(bag);

        assertEquals(robot.getLockers().get(1).takeBag(secondTicket), bag);
    }

    @Test
    void should_throw_NoCapacityException_when_save_bag_given_robot_have_two_full_filled_lockers() {
        Robot robot = new Robot(Arrays.asList(new Locker(1), new Locker(1)));
        Bag bag = new Bag();
        robot.saveBag(bag);
        bag = new Bag();
        robot.saveBag(bag);

        Bag thirdBag = new Bag();
        assertThrows(NoCapacityException.class, () -> robot.saveBag(thirdBag));
    }
}
