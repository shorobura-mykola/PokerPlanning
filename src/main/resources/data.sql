INSERT INTO POKER_PLANNING_SESSION(id, deck_type, title)
VALUES ('aa7ac5b9-9928-4b6f-9678-d516b0f3c6e4', 'STANDARD', 'session one'),
       ('93fde82c-8d43-417c-8f40-bfc64afa55df', 'BIG', 'session two');

INSERT INTO MEMBER(id, name, session_id)
VALUES ('2e243f5a-c640-4313-8385-9ae0a25800f5', 'user 1', 'aa7ac5b9-9928-4b6f-9678-d516b0f3c6e4'),
       ('c1702967-e67e-4986-922b-4f4f0a0c0aab', 'user 2', 'aa7ac5b9-9928-4b6f-9678-d516b0f3c6e4'),
       ('cb66b493-9c0b-4be2-874a-5087283a5aa1', 'user 3', '93fde82c-8d43-417c-8f40-bfc64afa55df'),
       ('2c014abb-8105-45a5-97ff-cbe224947602', 'user 4', '93fde82c-8d43-417c-8f40-bfc64afa55df');

INSERT INTO USER_STORY(id, description, status, title, session_id)
VALUES ('5a9fa967-e774-47d9-b47e-bc1bb7dd0791', 'story desc 1', 'PENDING', 'story title 1', 'aa7ac5b9-9928-4b6f-9678-d516b0f3c6e4'),
       ('13f7d639-6759-4ff7-a881-4eec0a1924ae', 'story desc 2', 'VOTED', 'story title 2', 'aa7ac5b9-9928-4b6f-9678-d516b0f3c6e4');

INSERT INTO VOTE(id, vote, member_id, user_story_id)
VALUES ('2393113b-746e-4004-bff4-a4595f74d1e6', '5', '2e243f5a-c640-4313-8385-9ae0a25800f5', '13f7d639-6759-4ff7-a881-4eec0a1924ae'),
       ('fd878f4b-3957-471c-812e-9503840437a1', '3', 'c1702967-e67e-4986-922b-4f4f0a0c0aab', '13f7d639-6759-4ff7-a881-4eec0a1924ae');