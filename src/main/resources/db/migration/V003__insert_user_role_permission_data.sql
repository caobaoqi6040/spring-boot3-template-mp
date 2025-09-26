INSERT INTO backend.sys_permission (id, name)
VALUES (1, 'ADD');
INSERT INTO backend.sys_permission (id, name)
VALUES (2, 'DELETE');
INSERT INTO backend.sys_permission (id, name)
VALUES (3, 'UPDATE');
INSERT INTO backend.sys_permission (id, name)
VALUES (4, 'SELECT');

INSERT INTO backend.sys_role (id, name)
VALUES (1, 'ADMIN');
INSERT INTO backend.sys_role (id, name)
VALUES (2, 'GENERAL');

INSERT INTO backend.sys_role_permission_map (id, role_id, permission_id)
VALUES (1, 1, 1);
INSERT INTO backend.sys_role_permission_map (id, role_id, permission_id)
VALUES (2, 1, 2);
INSERT INTO backend.sys_role_permission_map (id, role_id, permission_id)
VALUES (3, 1, 3);
INSERT INTO backend.sys_role_permission_map (id, role_id, permission_id)
VALUES (4, 1, 4);
INSERT INTO backend.sys_role_permission_map (id, role_id, permission_id)
VALUES (5, 2, 4);

INSERT INTO backend.sys_user (id, username, email, password, avatar, create_time, update_time, enable)
VALUES (1, 'caobaoqi6040', 'caobaoqi6040@gmail.com', '$2a$10$maCH7REN6XlgEx7iK0zRQubU2h8A4QMnmD2yJ//3OB36QU6Egehk.',
        'https://www.gravatar.com/avatar/2a1be8d4123eb685be3eb374b6588648?s=80&r=g&d=mm', '2025-09-24 11:23:26',
        '2025-09-24 11:23:29', 1);
INSERT INTO backend.sys_user (id, username, email, password, avatar, create_time, update_time, enable)
VALUES (2, 'ikun', 'ikun@gmail.com', '$2a$10$vnSmQ2.2cNcwH9u4nUKLg.P5iBj32attKoIZVMbd20nWDIpcEMuqO',
        'https://www.gravatar.com/avatar/ikun', '2025-09-24 11:23:32', '2025-09-24 11:23:31', 1);

INSERT INTO backend.sys_user_role_map (id, user_id, role_id)
VALUES (1, 1, 1);
INSERT INTO backend.sys_user_role_map (id, user_id, role_id)
VALUES (2, 1, 2);
INSERT INTO backend.sys_user_role_map (id, user_id, role_id)
VALUES (3, 2, 2);
