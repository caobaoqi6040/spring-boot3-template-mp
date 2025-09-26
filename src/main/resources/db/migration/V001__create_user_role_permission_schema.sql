create table sys_permission
(
  id   int auto_increment comment 'id'
    primary key,
  name varchar(64) not null comment '权限名'
)
  comment '权限表';

create unique index sys_permission_name_uindex
  on sys_permission (name);

create table sys_role
(
  id   int auto_increment comment '角色 id'
    primary key,
  name varchar(64) not null comment '角色名'
)
  comment '角色表';

create unique index sys_role_name_uindex
  on sys_role (name);

create table sys_user
(
  id         bigint            not null comment '用户 id'
    primary key,
  username   varchar(32)       not null comment '用户名',
  email      varchar(64)       not null comment '邮箱',
  password   varchar(64)       not null comment '密码',
  avatar     varchar(128)      null comment '头像',
  create_time timestamp         null,
  update_time timestamp         null,
  enable     boolean default 1 not null comment '软删除字段 (1 未删除 | 0 删除)'
)
  comment '用户表';

create unique index sys_user_email_uindex
  on sys_user (email);





