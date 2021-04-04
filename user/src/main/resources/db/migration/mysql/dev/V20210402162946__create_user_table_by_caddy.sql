SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS secu_permission;
create table secu_permission
(
    id int auto_increment comment '主键',
    name varchar(32) not null comment '菜单',
    constraint secu_permission_pk
        primary key (id)
)
    comment '菜单表';

DROP TABLE IF EXISTS secu_role;
create table secu_role
(
    id int auto_increment comment '主键',
    name varchar(32) not null comment '菜单',
    constraint secu_role_pk
        primary key (id)
)
    comment '角色表';

DROP TABLE IF EXISTS secu_user;
create table secu_user
(
    id int auto_increment comment '主键',
    name varchar(32) not null comment '菜单',
    constraint secu_user_pk
        primary key (id)
)
    comment '用户表';

DROP TABLE IF EXISTS secu_role_perm;
create table secu_role_perm
(
    id int auto_increment comment '主键',
    cid int not null comment '菜单',
    rid int not null comment '角色',
    constraint secu_role_perm_pk
        primary key (id)
)
    comment '角色菜单关系表';

DROP TABLE IF EXISTS secu_user_role;
create table secu_user_role
(
    id int auto_increment comment '主键',
    rid int not null comment '角色',
    uid int not null comment '用户',
    constraint secu_user_role_pk
        primary key (id)
)
    comment '角色用户关系表';