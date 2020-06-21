## 储物柜(Locker)可以存包、取包
### Tasking: 
Given locker 已满，when 存包， then 未出票，存包失败.

Given locker 未满，when 存包， then 出票，存包成功.

Given 有效票据， when 取包，then 取包成功.

Given 无效票据， when 取包， then 取包失败.

Given 重复票据， when 取包， then 取包失败.

## Primary Locker Robot
### Tasking: 
Given: PrimaryLockerRobot管理两个柜子，两个柜子都未满， When: 存包，Then: 存第一个柜子，返回一张票

Given: PrimaryLockerRobot管理两个柜子，第一个柜子已满，第二个柜子有空， When: 存包， Then: 存第二个柜子，返回一张票

Given: PrimaryLockerRobot管理两个柜子，两个柜子都满， When: 存包， Then: 存包失败，提示柜子已满

Given: 一张有效票， When: 取包，Then: PrimaryLockerRobot返回一个包

Given: 一张无效票， When: 取包，Then: 取包失败，提示无效票

## Smart Locker Robot
### Tasking: 
Given SmartLockerRobot管理两个柜子，第一个柜子比第二个柜子可用容量多1，When SmartLockerRobot存包，Then 存第一个柜子，返回一张票

Given SmartLockerRobot管理两个柜子，第二个柜子比第一个柜子可用容量多1，When SmartLockerRobot存包，Then 存第二个柜子，返回一张票

Given SmartLockerRobot管理两个柜子，两个柜子可用容量相同，When SmartLockerRobot存包，Then 存第一个柜子，返回一张票

Given SmartLockerRobot管理两个柜子，两个柜子容量均已存满，When SmartLockerRobot存包，Then 存包失败，提示柜子已满

Given SmartLockerRobot管理两个柜子，并且SmartLockerRobot拿到一张由SmartLockerRobot存包获得的有效票，When SmartLockerRobot取包，Then SmartLockerRobot返回一个包

Given SmartLockerRobot管理两个柜子，并且SmartLockerRobot拿到一张伪造票，When SmartLockerRobot取包，Then 取包失败，提示无效票

Given SmartLockerRobot、PrimaryLockerRobot同时管理两个柜子，并且PrimaryLockerRobot拿到一张由SmartLockerRobot存包获得的有效票，When PrimaryLockerRobot取包，Then PrimaryLockerRobot返回一个包

Given SmartLockerRobot、PrimaryLockerRobot同时管理两个柜子，并且SmartLockerRobot拿到一张由PrimaryLockerRobot存包获得的有效票，When SmartLockerRobot取包，Then SmartLockerRobot返回一个包

Given SmartLockerRobot管理A，B两个柜子、PrimaryLockerRobot管理C，D两个柜子，并且PrimaryLockerRobot拿到一张由SmartLockerRobot存包获得的有效票，When PrimaryLockerRobot取包，Then 取包失败，提示无效票

Given SmartLockerRobot管理A，B两个柜子、PrimaryLockerRobot管理C，D两个柜子，并且SmartLockerRobot拿到一张由PrimaryLockerRobot存包获得的有效票，When SmartLockerRobot取包，Then 取包失败，提示无效票

## Locker Robot Manager
### Tasking: 
Given LockerRobotManager管理2个locker & 两个locker均有空间 & 未管理robot，When 让LockerRobotManager存包，Then 成功存入第一个柜子，Manager返回票据

Given LockerRobotManager管理2个locker & 第一个柜子已满，第二个未满 & 未管理robot，When 让LockerRobotManager存包，Then 成功存入第二个柜子，Manager返回票据

Given LockerRobotManager管理2个已满的locker & 未管理robot，When 让LockerRobotManager存包，Then 存包失败，提示柜子已满


Given LockerRobotManager管理2个柜子未满的SmartLockerRobot & 未管理柜子，When 让LockerRobotManager存包，Then 第一个SmartLockerRobot存包，Manager返回一张票

Given LockerRobotManager管理2个PrimaryLockerRobot & 第一个robot的柜子已满，第二个robot未满 & 未管理柜子，When 让LockerRobotManager存包，Then 第二个PrimaryLockerRobot存包，Manager返回一张票

Given LockerRobotManager管理2个柜子已满的PrimaryLockerRobot & 未管理柜子，When 让LockerRobotManager存包，Then 存包失败，提示柜子已满


Given LockerRobotManager管理均有空间的1个SmartLockerRobot & 一个柜子，When 让LockerRobotManager存包，Then robot成功存入，Manager返回票据

Given LockerRobotManager管理1个已满的SmartLockerRobot & 一个未满的柜子，When 让LockerRobotManager存包，Then locker成功存入，Manager返回票据

Given LockerRobotManager管理均已满的1个SmartLockerRobot & 一个柜子，When 让LockerRobotManager存包，Then 存包失败，提示柜子已满


Given LockerRobotManager管理2个locker & 未管理robot & 有效票据，When 让LockerRobotManager取包，Then 返回一个包

Given LockerRobotManager管理2个locker & 未管理robot & 无效票据，When 让LockerRobotManager取包，Then 取包失败，提示无效票

Given LockerRobotManager管理2个robot & 未管理locker & 有效票据，When 让LockerRobotManager取包，Then 返回一个包

Given LockerRobotManager管理2个robot & 未管理locker & 无效票据，When 让LockerRobotManager取包，Then 取包失败，提示无效票

Given LockerRobotManager管理1个robot & 1个locker & 有效票据，When 让LockerRobotManager取包，Then 返回一个包

Given LockerRobotManager管理1个robot & 1个locker & 无效票据，When 让LockerRobotManager取包，Then 取包失败，提示无效票
