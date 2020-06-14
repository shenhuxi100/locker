## 储物柜(Locker)可以存包、取包
### Tasking: 
Given locker 已满，when 存包， then 未出票，存包失败.

Given locker 未满，when 存包， then 出票，存包成功.

Given 有效票据， when 取包，then 取包成功.

Given 无效票据， when 取包， then 取包失败.

Given 重复票据， when 取包， then 取包失败.

## Robot
### Tasking: 
Given: 一个机器人有2的柜子，第一个柜子已满，第二个柜子有空， When: 存包 Then: 存第二个柜子，返回一张票

Given: 一个机器人有2的柜子，两个柜子都未满， When: 存包 Then: 存第一个柜子，返回一张票

Given: 一个机器人有2的柜子，两个柜子都满， When: 存包 Then: 存包失败，提示柜子已满

Given: 一张无效票， When: 取包 Then: 提示无效票据

Given: 一张有效票， When: 取包 Then: 机器人返回一个包