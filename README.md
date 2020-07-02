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

## Locker Robot Director
### Tasking:
Given LockerRobotDirector管理一个LockerRobotManager， LockerRobotManager管理一个primaryLockerRobot，自身不管理locker，
primaryLockerRobot管理一个剩余容量为1，总容量为2的locker
When LockerRobotDirector查看报表
Then 报表内容为
M 1 2
	R 1 2
		L 1 2

Given LockerRobotDirector管理一个LockerRobotManager，LockerRobotManager先管理一个smartLockerRobot，再管理一个primaryLockerRobot，自身不管理locker，
primaryLockerRobot管理一个剩余容量为1，总容量为2的locker，smartLockerRobot管理一个剩余容量为0，总容量为1的locker，
When LockerRobotDirector查看报表
Then 报表内容为
M 1 3
    R 0 1
	    L 0 1
	R 1 2
		L 1 2

Given LockerRobotDirector只管理一个LockerRobotManager，LockerRobotManager管理一个剩余容量为1，总容量为2的locker，
同时存在一个不被此LockerRobotManager管理的locker2，locker2的剩余容量为4，总容量为5，
When LockerRobotDirector查看报表
Then 报表内容为
M 1 2
	L 1 2

Given LockerRobotDirector管理一个LockerRobotManager， LockerRobotManager管理一个locker，自身不管理LockerRobot，
locker的剩余容量为1，总容量为2
When LockerRobotDirector查看报表
Then 报表内容为
M 1 2
	L 1 2

Given LockerRobotDirector管理一个LockerRobotManager， LockerRobotManager先管理一个smartLockerRobot，再管理一个primaryLockerRobot，并管理剩余容量为2，总容量为2的一个locker，
primaryLockerRobot管理一个剩余容量为1，总容量为2的locker，smartLockerRobot管理一个剩余容量为0，总容量为1的locker，
When LockerRobotDirector查看报表
Then 报表内容为
M 3 5
    L 2 2
    R 0 1
        L 0 1
	R 1 2
		L 1 2
		
Given LockerRobotDirector管理一个LockerRobotManager， LockerRobotManager先管理一个smartLockerRobot，再管理一个primaryLockerRobot，并管理剩余容量为2，总容量为2的一个locker，
primaryLockerRobot管理两个locker，第一个locker剩余容量为0，总容量为1，第二个locker剩余容量为1，总容量为1，
smartLockerRobot管理一个剩余容量为0，总容量为1的locker，
When LockerRobotDirector查看报表
Then 报表内容为
M 3 5
    L 2 2
    R 0 1
        L 0 1
	R 1 2
		L 0 1
		L 1 1

Given LockerRobotDirector管理两个LockerRobotManager，
LockerRobotManager1先管理一个smartLockerRobot，再管理一个primaryLockerRobot，自身不管理locker，
primaryLockerRobot管理一个剩余容量为1，总容量为2的locker，smartLockerRobot管理一个剩余容量为0，总容量为1的locker，
LockerRobotManager2管理一个Locker，自身不管理LockerRobot，Locker的剩余容量为1，总容量为2
When LockerRobotDirector查看报表
Then 报表内容为
M 1 3
    R 0 1
	    L 0 1
	R 1 2
		L 1 2
M 1 2
    L 1 2

