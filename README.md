## 储物柜(Locker)可以存包、取包
### Tasking: 
Given 储物柜 已满，when 存包， then 未出票，存包失败.

Given 储物柜 未满，when 存包， then 出票，存包成功.

Given 有效票据， when 取包，then 取包成功.

Given 无效票据， when 取包， then 取包失败.

Given 重复票据， when 取包， then 取包失败.

## Primary Locker Robot
### Tasking: 
Given: PrimaryLockerRobot管理两个储物柜，两个储物柜都未满， When: 存包，Then: 存第一个储物柜，返回一张票

Given: PrimaryLockerRobot管理两个储物柜，第一个储物柜已满，第二个储物柜有空， When: 存包， Then: 存第二个储物柜，返回一张票

Given: PrimaryLockerRobot管理两个储物柜，两个储物柜都满， When: 存包， Then: 存包失败，提示储物柜已满

Given: 一张有效票， When: 取包，Then: PrimaryLockerRobot返回一个包

Given: 一张无效票， When: 取包，Then: 取包失败，提示无效票

## Smart Locker Robot
### Tasking: 
Given SmartLockerRobot管理两个储物柜，第一个储物柜比第二个储物柜可用容量多1，When SmartLockerRobot存包，Then 存第一个储物柜，返回一张票

Given SmartLockerRobot管理两个储物柜，第二个储物柜比第一个储物柜可用容量多1，When SmartLockerRobot存包，Then 存第二个储物柜，返回一张票

Given SmartLockerRobot管理两个储物柜，两个储物柜可用容量相同，When SmartLockerRobot存包，Then 存第一个储物柜，返回一张票

Given SmartLockerRobot管理两个储物柜，两个储物柜容量均已存满，When SmartLockerRobot存包，Then 存包失败，提示储物柜已满

Given SmartLockerRobot管理两个储物柜，并且SmartLockerRobot拿到一张由SmartLockerRobot存包获得的有效票，When SmartLockerRobot取包，Then SmartLockerRobot返回一个包

Given SmartLockerRobot管理两个储物柜，并且SmartLockerRobot拿到一张伪造票，When SmartLockerRobot取包，Then 取包失败，提示无效票

Given SmartLockerRobot、PrimaryLockerRobot同时管理两个储物柜，并且PrimaryLockerRobot拿到一张由SmartLockerRobot存包获得的有效票，When PrimaryLockerRobot取包，Then PrimaryLockerRobot返回一个包

Given SmartLockerRobot、PrimaryLockerRobot同时管理两个储物柜，并且SmartLockerRobot拿到一张由PrimaryLockerRobot存包获得的有效票，When SmartLockerRobot取包，Then SmartLockerRobot返回一个包

Given SmartLockerRobot管理A，B两个储物柜、PrimaryLockerRobot管理C，D两个储物柜，并且PrimaryLockerRobot拿到一张由SmartLockerRobot存包获得的有效票，When PrimaryLockerRobot取包，Then 取包失败，提示无效票

Given SmartLockerRobot管理A，B两个储物柜、PrimaryLockerRobot管理C，D两个储物柜，并且SmartLockerRobot拿到一张由PrimaryLockerRobot存包获得的有效票，When SmartLockerRobot取包，Then 取包失败，提示无效票

## Locker Robot Manager
### Tasking: 
Given LockerRobotManager管理2个储物柜 & 两个储物柜均有空间 & 未管理robot，When 让LockerRobotManager存包，Then 成功存入第一个储物柜，Manager返回票据

Given LockerRobotManager管理2个储物柜 & 第一个储物柜已满，第二个未满 & 未管理robot，When 让LockerRobotManager存包，Then 成功存入第二个储物柜，Manager返回票据

Given LockerRobotManager管理2个已满的储物柜 & 未管理robot，When 让LockerRobotManager存包，Then 存包失败，提示储物柜已满


Given LockerRobotManager管理2个储物柜未满的SmartLockerRobot & 未管理储物柜，When 让LockerRobotManager存包，Then 第一个SmartLockerRobot存包，Manager返回一张票

Given LockerRobotManager管理2个PrimaryLockerRobot & 第一个robot的储物柜已满，第二个robot未满 & 未管理储物柜，When 让LockerRobotManager存包，Then 第二个PrimaryLockerRobot存包，Manager返回一张票

Given LockerRobotManager管理2个储物柜已满的PrimaryLockerRobot & 未管理储物柜，When 让LockerRobotManager存包，Then 存包失败，提示储物柜已满


Given LockerRobotManager管理均有空间的1个SmartLockerRobot & 一个储物柜，When 让LockerRobotManager存包，Then robot成功存入，Manager返回票据

Given LockerRobotManager管理1个已满的SmartLockerRobot & 一个未满的储物柜，When 让LockerRobotManager存包，Then 储物柜成功存入，Manager返回票据

Given LockerRobotManager管理均已满的1个SmartLockerRobot & 一个储物柜，When 让LockerRobotManager存包，Then 存包失败，提示储物柜已满


Given LockerRobotManager管理2个储物柜 & 未管理robot & 有效票据，When 让LockerRobotManager取包，Then 返回一个包

Given LockerRobotManager管理2个储物柜 & 未管理robot & 无效票据，When 让LockerRobotManager取包，Then 取包失败，提示无效票

Given LockerRobotManager管理2个robot & 未管理储物柜 & 有效票据，When 让LockerRobotManager取包，Then 返回一个包

Given LockerRobotManager管理2个robot & 未管理储物柜 & 无效票据，When 让LockerRobotManager取包，Then 取包失败，提示无效票

Given LockerRobotManager管理1个robot & 1个储物柜 & 有效票据，When 让LockerRobotManager取包，Then 返回一个包

Given LockerRobotManager管理1个robot & 1个储物柜 & 无效票据，When 让LockerRobotManager取包，Then 取包失败，提示无效票
