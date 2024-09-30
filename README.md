# 订阅/发送风格的事件管理系统

## 指令系统

请使用空格分隔！
[]内部允许除了[]之外的任何字符

* 创建类指令
  * 创建部门：create dep [部门名]
  * 创建学生：create stu [学生名]
  * 创建老师：create tea [教师名]
  * 创建订阅者名单：create subs [订阅者名单名]
* 添加类指令
  * 向订阅者名单中添加订阅者：add [订阅者名单] [订阅者] 
* 注册类指令
  * 在部门中注册订阅者名单：register [部门] [订阅者名单]
* 展示类指令
  * 展示订阅者名单中的所有订阅者：show subs [订阅者名单]
  * 展示订阅者的所有消息：show submes [订阅者]
* 查看类指令
  * 订阅者查看序号为i的消息：check [订阅者] [消息序号]
* 发送类指令
  * 部门发送消息：send [部门] [消息标题] [消息内容]				

## 使用示例

请输入以下指令进行测试：

```command
// 创建各订阅者
create tea [Master Lee]
create tea [Doctor Wang]
create stu [Jack]
create stu [Jane]
create stu [Mary]
create stu [Lucy]

// 创建部门
create dep [CS]
create dep [TC]
create dep [AO]

// 创建部门对应的订阅者名单
create subs [CS_List]
add [CS_List] [Master Lee]
add [CS_List] [Jack]
add [CS_List] [Mary]

create subs [TC_List]
add [TC_List] [Doctor Wang]
add [TC_List] [Jane]
add [TC_List] [Lucy]

// 向部门订阅
register [CS] [CS_List]
register [TC] [TC_List]

//教务处AO比较特别，其消息会发送给所有人，所以都要订阅
register [AO] [CS_List]
register [AO] [TC_List]

//开始模拟场景
//CS学院发送通知
send [CS] [ICPC Registration] [Requirements: Strong programming skills and teamwork abilities. Interested students, please register in Room 501 of the Science Building on October 8, 2024!]

//TC学院发送通知
send [TC] [National Day Celebration] [Our college will hold a National Day gala on the evening of October 1 at 19:00. Stay tuned!]

//教务处发送通知
send [AO] [Course Selection System Failure] [We apologize for the issues with our course selection system, which is experiencing network delays again. Students and faculty encountering problems should contact the Academic Affairs Office as soon as possible!]

// 接收到消息后，订阅者开始查看
//Lee老师查看自己的消息列表并选择消息查看
show submes [Master Lee]
check [Master Lee] 1

//Wang老师查看自己的消息列表并选择消息查看
show submes [Doctor Wang]
check [Doctor Wang] 2

//Jack同学查看自己的消息列表并选择消息查看
show submes [Jack]
check [Jack] 1
Check [Jack] 2
```

纯指令版本

``````Command
create tea [Master Lee]
create tea [Doctor Wang]
create stu [Jack]
create stu [Jane]
create stu [Mary]
create stu [Lucy]
create dep [CS]
create dep [TC]
create dep [AO]
create subs [CS_List]
add [CS_List] [Master Lee]
add [CS_List] [Jack]
add [CS_List] [Mary]
create subs [TC_List]
add [TC_List] [Doctor Wang]
add [TC_List] [Jane]
add [TC_List] [Lucy]
register [CS] [CS_List]
register [TC] [TC_List]
register [AO] [CS_List]
register [AO] [TC_List]

send [CS] [ICPC Registration] [Requirements: Strong programming skills and teamwork abilities. Interested students, please register in Room 501 of the Science Building on October 8, 2024!]
send [TC] [National Day Celebration] [Our college will hold a National Day gala on the evening of October 1 at 19:00. Stay tuned!]
send [AO] [Course Selection System Failure] [We apologize for the issues with our course selection system, which is experiencing network delays again. Students and faculty encountering problems should contact the Academic Affairs Office as soon as possible!]

show submes [Master Lee]
check [Master Lee] 1

show submes [Doctor Wang]
check [Doctor Wang] 2

show submes [Jack]
check [Jack] 1
check [Jack] 2

``````

