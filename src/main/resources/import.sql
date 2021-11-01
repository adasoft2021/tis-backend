-- BASE_QUALIFICATIONS
INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('1', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Cumplimiento de especificaciones del proponente', '15');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('2', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Claridad en la organización de la empresa del proponente', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('3', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Cumplimiento de especificaciones técnicas', '30');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('4', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Claridad en el proceso de desarrollo', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('5', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Plazo de ejecución', '10');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('6', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Precio total', '15');

INSERT INTO base_qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, DESCRIPTION, MAX_SCORE) VALUES ('7', '2021-10-06 08:00:00', 0, '2021-10-06 08:00:00', 'Uso de herramientas en el proceso de desarrollo', '10');

-- USERS
INSERT INTO users (ID, CREATED_AT, DELETED, UPDATED_AT, EMAIL) VALUES ('1', TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047','leticia@email');
INSERT INTO users (ID, CREATED_AT, DELETED, UPDATED_AT, EMAIL) VALUES ('2', TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047','adasoftsrl@gmail.com');
INSERT INTO users (ID, CREATED_AT, DELETED, UPDATED_AT, EMAIL) VALUES ('3', TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047','acme@gmail.com');


-- ADVISERS
INSERT INTO advisers (ID, FIRST_NAME, LAST_NAME)VALUES ( '1', 'Maria Leticia', 'Blanco Coca');

--COMPANIES
INSERT INTO companies(ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS) VALUES ('2','ADASOFT','ADA SOFTWARE', 'SRL', 'Jordan y Oquendo')
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Luis Tapia')
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Violeta Guzman')
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Leonardo Roldan')

INSERT INTO companies(ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS) VALUES ('3','Acme','Acme Company', 'SA', 'Jordan y Oquendo');
INSERT INTO partners(ID,PARTNERS) VALUES ('3','Alguien Asdfd')
INSERT INTO partners(ID,PARTNERS) VALUES ('3','Alguien Asdf')
INSERT INTO partners(ID,PARTNERS) VALUES ('3','Aasdfds Bsdfsd')

-- PROPOSALS
INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','A','/files/ADASOFTParteA.pdf','1');
INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','B','/files/ADASOFTParteB.pdf','1');

-- OBSERVATIONS
INSERT INTO observations (ID, CREATED_AT, DELETED, UPDATED_AT, FK_PROPOSAL_ID, TITLE, DESCRIPTION) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','seccion 1','esta es una observacion');

-- PUBLICATIONS
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-09-06 08:00:00','CPTIS-0609-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('2','2020-10-06 08:00:00',0,'2020-10-06 08:00:00','1','Título de una nueva convocatoria','2020-10-06 08:00:00','CPTIS-0610-2020','2-2020','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('3','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-07 08:00:00','CPTIS-0710-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('4','2021-03-06 08:00:00',0,'2021-03-06 08:00:00','1','Título de una nueva convocatoria','2021-10-08 08:00:00','CPTIS-0810-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('5','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-09 08:00:00','CPTIS-0910-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('6','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-10 08:00:00','CPTIS-1010-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');