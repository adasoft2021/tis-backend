-- BASE_QUALIFICATIONS
INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('1', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Cumplimiento de especificaciones del proponente', '15');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('2', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Claridad en la organización de la empresa del proponente', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('3', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Cumplimiento de especificaciones técnicas', '30');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('4', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Claridad en el proceso de desarrollo', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('5', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Plazo de ejecución', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('6', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Precio total', '15');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('7', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Uso de herramientas en el proceso de desarrollo', '10');

INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES
    ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','A','/files/ADASOFTParteA.pdf','1'), ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','B','/files/ADASOFTParteB.pdf','1'));

INSERT INTO observations (ID, CREATED_AT, DELETED, UPDATED_AT, FK_PROPOSAL_ID, TITLE, DESCRIPTION) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','seccion 1','esta es una observacion');
INSERT INTO reviews VALUES (1, TIMESTAMP '2021-10-11 12:36:22.213012', FALSE, TIMESTAMP '2021-10-11 12:36:22.213012', NULL, 1, NULL);
INSERT INTO qualifications VALUES (1, TIMESTAMP '2021-10-11 12:36:22.213012', FALSE, TIMESTAMP '2021-10-11 12:36:22.213012', NULL, 1, 1);
INSERT INTO qualifications VALUES (2, TIMESTAMP '2021-10-11 12:36:22.213012', FALSE, TIMESTAMP '2021-10-11 12:36:22.213012', NULL, 2, 1);
INSERT INTO qualifications VALUES (3, TIMESTAMP '2021-10-11 12:36:22.213012', FALSE, TIMESTAMP '2021-10-11 12:36:22.213012', NULL, 3, 1);
INSERT INTO qualifications VALUES (4, TIMESTAMP '2021-10-11 12:36:22.213012', FALSE, TIMESTAMP '2021-10-11 12:36:22.213012', NULL, 4, 1);
INSERT INTO qualifications VALUES (5, TIMESTAMP '2021-10-11 12:36:22.213012', FALSE, TIMESTAMP '2021-10-11 12:36:22.213012', NULL, 5, 1);
INSERT INTO qualifications VALUES (6, TIMESTAMP '2021-10-11 12:36:22.213012', FALSE, TIMESTAMP '2021-10-11 12:36:22.213012', NULL, 6, 1);
INSERT INTO qualifications VALUES (7, TIMESTAMP '2021-10-11 12:36:22.213012', FALSE, TIMESTAMP '2021-10-11 12:36:22.213012', NULL, 7, 1);
INSERT INTO users VALUES (1, TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047', 'pass', 'leticiablanco');
INSERT INTO advisers VALUES ('Maria Leticia', 'Blanco Coca', 1);
INSERT INTO users VALUES (2, TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047', 'pass', 'ada');
INSERT INTO companies VALUES ('ADA SOFTWARE', 2);