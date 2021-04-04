insert into secu_role(id,name) values (1,'administrator');
insert into secu_role(id,name) values (2,'user');

-- administrator
insert into secu_user(id,name) values (1,'jack');
insert into secu_user_role(id, rid, uid) values (1,1,1);
insert into secu_user_role(id, rid, uid) values (2,2,1);

-- user
insert into secu_user(id,name) values (2,'caddy');
insert into secu_user_role(id, rid, uid) values (3,2,2);

-- 菜单
insert into secu_permission(id,name) values (1,'/index/menu');
insert into secu_permission(id,name) values (2,'/index/info');
insert into secu_permission(id,name) values (3,'/index/logout');

-- administrator
insert into secu_role_perm(id, cid, rid) values (1,1,1);
insert into secu_role_perm(id, cid, rid) values (2,2,1);
insert into secu_role_perm(id, cid, rid) values (3,3,1);
-- user
insert into secu_role_perm(id, cid, rid) values (4,3,2);
insert into secu_role_perm(id, cid, rid) values (5,2,2);