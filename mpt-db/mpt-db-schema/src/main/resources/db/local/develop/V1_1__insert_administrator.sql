--
-- insert user Administrator
--
INSERT INTO T_USER (ID, SINCE, NAME, NICK, PASSWORD) VALUES (nextval('SEQ_ID_USER'), '2017-01-01 01:00:00.000', 'Administrator', 'admin', 'Password');
INSERT INTO T_GROUP (ID, SINCE, NAME, MANAGER_ID) VALUES (nextval('SEQ_ID_GROUP'), '2017-01-01 01:00:00.000', 'Administrator', (SELECT ID FROM T_USER WHERE NICK = 'admin'));
INSERT INTO T_MEMBER_OF (ID, USER_ID, GROUP_ID) VALUES (nextval('SEQ_ID_MEMBER_OF'), (SELECT ID FROM T_USER WHERE NICK = 'admin'), (SELECT ID FROM T_GROUP WHERE NAME = 'Administrator'));
