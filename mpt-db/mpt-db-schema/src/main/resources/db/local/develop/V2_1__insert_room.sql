--
-- insert starting room
--
INSERT INTO T_ROOM (ID, OPENING, CLOSURE, NAME, DESCRIPTION, PUBLIC, MANAGER_ID) VALUES (nextval('SEQ_ID_ROOM'), '2017-01-01 01:00:00.000', null, 'entry hall', 'all user start here', TRUE, (SELECT ID FROM T_USER WHERE NICK = 'admin'));
