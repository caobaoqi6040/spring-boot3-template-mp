create table sys_role_permission_map
(
  id            int auto_increment comment 'id'
    primary key,
  role_id       int not null comment '角色 id',
  permission_id int not null comment '权限 id',
  constraint sys_role_permission_map_sys_permission_id_fk
    foreign key (permission_id) references sys_permission (id),
  constraint sys_role_permission_map_sys_role_id_fk
    foreign key (role_id) references sys_role (id)
)
  comment '角色-权限 映射表';

create unique index sys_role_permission_map_role_id_permission_id_uindex
    on sys_role_permission_map (role_id, permission_id);

create table sys_user_role_map
(
  id      int auto_increment comment 'id'
    primary key,
  user_id bigint not null comment '用户 id',
  role_id int    not null comment '角色 id',
  constraint sys_user_role_map_sys_role_id_fk
    foreign key (role_id) references sys_role (id),
  constraint sys_user_role_map_sys_user_id_fk
    foreign key (user_id) references sys_user (id)
)
  comment '用户-角色 映射表';

create unique index sys_user_role_map_role_id_user_id_uindex
  on sys_user_role_map (role_id, user_id);
