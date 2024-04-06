create database if not exists `evaluate-oj`;


use `evaluate-oj`;
create table if not exists question
(
    id          bigint auto_increment comment 'id'
        primary key,
    title       varchar(512)                       null comment '标题',
    content     text                               null comment '内容',
    tags        varchar(1024)                      null comment '题目标签分类（json 数组）',
    answer      text                               not null comment '题目标准答案',
    difficulty  int                                null comment '1-简单 2-中等 3-困难',
    submitNum   int      default 0                 not null comment '题目提交数',
    acceptedNum int      default 0                 not null comment '题目通过数',
    judgeCase   text                               null comment '判题用例（json 数组）',
    judgeConfig text                               null comment '判题配置（json 数组）',
    thumbNum    int      default 0                 not null comment '点赞数',
    favourNum   int      default 0                 not null comment '收藏数',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
)
    comment '题目' collate = utf8mb4_unicode_ci;

create index idx_userId
    on question (userId);

create table if not exists question_submit
(
    id          bigint auto_increment comment 'id'
        primary key,
    language    varchar(128)                       null comment '编程语言',
    backendCode text                               not null comment '经过后端转换后的可执行代码',
    frontedCode text                               null comment '用户代码',
    judgeInfo   text                               null comment '判题信息（json 数组）',
    status      int      default 0                 not null comment '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
    questionId  bigint                             not null comment '题目id',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
)
    comment '题目提交' collate = utf8mb4_unicode_ci;

create  index  idx_questionId
    on question_submit (questionId);

create index idx_userId
    on question_submit (userId);

create table if not exists user
(
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    tag          varchar(1024)                          null comment '用户标签',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    gender       int                                    null comment '0 - 女，1 - 男',
    userState    int                                    null comment '0 - 正常，1 - 注销，2 - 封号',
    phone        varchar(255)                           null comment '手机号',
    email        varchar(255)                           null comment '邮箱'
)
    comment '用户' collate = utf8mb4_unicode_ci;

