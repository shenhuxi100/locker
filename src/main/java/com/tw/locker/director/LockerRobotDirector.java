package com.tw.locker.director;

import com.tw.locker.manger.LockerRobotManager;

import java.util.List;

public class LockerRobotDirector {
    private List<LockerRobotManager> managers;

    public LockerRobotDirector(List<LockerRobotManager> managers) {
        this.managers = managers;
    }

    public String viewReport() {
        return managers.stream()
                .map(LockerRobotManager::getReport)
                .reduce("", (partialReport, managerReport) -> partialReport + managerReport);
    }
}
