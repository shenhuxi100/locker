## 储物柜(Locker)可以存包、取包
### Tasking: 
Given locker 已满，when 存包， then 未出票，存包失败.

Given locker 未满，when 存包， then 出票，存包成功.

Given 有效票据， when 取包，then 取包成功.

Given 无效票据， when 取包， then 取包失败.

Given 重复票据， when 取包， then 取包失败.
