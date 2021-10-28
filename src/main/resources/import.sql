-- BASE_QUALIFICATIONS
INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('1', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Cumplimiento de especificaciones del proponente', '15');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('2', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Claridad en la organización de la empresa del proponente', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('3', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Cumplimiento de especificaciones técnicas', '30');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('4', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Claridad en el proceso de desarrollo', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('5', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Plazo de ejecución', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('6', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Precio total', '15');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('7', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Uso de herramientas en el proceso de desarrollo', '10');

-- USERS
INSERT INTO users (ID, CREATED_AT, DELETED, UPDATED_AT, USER_NAME, EMAIL, PASSWORD) VALUES ('1', TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047','leticiablanco','leticia@email', 'pass');
INSERT INTO users (ID, CREATED_AT, DELETED, UPDATED_AT, USER_NAME, EMAIL, PASSWORD) VALUES ('2', TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047', 'ada','adasoftsrl@gmail.com', 'pass');

-- ADVISERS
INSERT INTO advisers (FIRST_NAME, LAST_NAME,ID)VALUES ('Maria Leticia', 'Blanco Coca', '1');

--COMPANIES
INSERT INTO companies(ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS, EMAIL,  CREATED_AT, DELETED, UPDATED_AT) VALUES ('1','ADASOFT','ADA SOFTWARE', 'SRL', 'Jordan y Oquendo', 'adasoftsrl@gmail.com', TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047');
INSERT INTO partners(ID,PARTNERS) VALUES ('1','Luis Tapia')
INSERT INTO partners(ID,PARTNERS) VALUES ('1','Violeta guzman')
INSERT INTO partners(ID,PARTNERS) VALUES ('1','Leonardo Roldan')

INSERT INTO companies(ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS, EMAIL,  CREATED_AT, DELETED, UPDATED_AT) VALUES ('2','Acme','Acme Company', 'SA', 'Jordan y Oquendo', 'acme@gmail.com', TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047');
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Alguien Asdfd')
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Alguien Asdf')
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Aasdfds Bsdfsd')

-- PROPOSALS
INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','A','/files/ADASOFTParteA.pdf','1');
INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','B','/files/ADASOFTParteB.pdf','1');

-- OBSERVATIONS
INSERT INTO observations (ID, CREATED_AT, DELETED, UPDATED_AT, FK_PROPOSAL_ID, TITLE, DESCRIPTION) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','seccion 1','esta es una observacion');

-- PUBLICATIONS
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-30 08:00:00','CPTIS-0609-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('2','2020-10-06 08:00:00',0,'2020-10-06 08:00:00','1','Título de una nueva convocatoria','2020-10-06 08:00:00','CPTIS-0610-2021','2-2020','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('3','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-06 08:00:00','CPTIS-0611-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('4','2021-03-06 08:00:00',0,'2021-03-06 08:00:00','1','Título de una nueva convocatoria','2021-03-06 08:00:00','CPTIS-0609-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('5','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-06 08:00:00','CPTIS-0610-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('6','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-06 08:00:00','CPTIS-0611-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');