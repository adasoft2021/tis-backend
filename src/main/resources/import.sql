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

--SEMESTERS
INSERT INTO semesters(ID, CREATED_AT, DELETED, UPDATED_AT, SEMESTER, NOW) VALUES ('1', TIMESTAMP '2021-02-11 12:34:21.209963', FALSE, TIMESTAMP '2021-02-11 12:35:17.118047','2-2021',TRUE);
INSERT INTO semesters(ID, CREATED_AT, DELETED, UPDATED_AT, SEMESTER, NOW) VALUES ('2', TIMESTAMP '2021-02-11 12:34:21.209963', FALSE, TIMESTAMP '2021-02-11 12:35:17.118047','1-2021',FALSE);
INSERT INTO semesters(ID, CREATED_AT, DELETED, UPDATED_AT, SEMESTER, NOW) VALUES ('3', TIMESTAMP '2020-02-11 12:34:21.209963', FALSE, TIMESTAMP '2020-02-11 12:35:17.118047','2-2020',FALSE);
INSERT INTO semesters(ID, CREATED_AT, DELETED, UPDATED_AT, SEMESTER, NOW) VALUES ('4', TIMESTAMP '2020-02-11 12:34:21.209963', FALSE, TIMESTAMP '2020-02-11 12:35:17.118047','1-2020',FALSE);
INSERT INTO semesters(ID, CREATED_AT, DELETED, UPDATED_AT, SEMESTER, NOW) VALUES ('5', TIMESTAMP '2019-02-11 12:34:21.209963', FALSE, TIMESTAMP '2019-02-11 12:35:17.118047','2-2019',FALSE);
INSERT INTO semesters(ID, CREATED_AT, DELETED, UPDATED_AT, SEMESTER, NOW) VALUES ('6', TIMESTAMP '2019-02-11 12:34:21.209963', FALSE, TIMESTAMP '2019-02-11 12:35:17.118047','1-2019',FALSE);

-- ADVISERS
INSERT INTO advisers (ID, FIRST_NAME, LAST_NAME)VALUES ( '1', 'Maria Leticia', 'Blanco Coca');

-- CLASS_CODES
INSERT INTO CLASS_CODES(ID, CREATED_AT, DELETED, UPDATED_AT, CODE, FK_ADVISER_ID) VALUES ('1', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'jey-ibl-glp','1');

-- PUBLICATIONS
-- ANNOUNCEMENT
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, FK_SEMESTER_ID, FILE_URL, TYPE) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Invitación Pública 01-2021','2021-02-06 08:00:00','CPTIS-0602-2021','2','https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/publications%2FInvitacionPublica022021.pdf?alt=media&token=e92d0fe1-8a45-4748-874d-d6d1c7e7a516','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, FK_SEMESTER_ID, FILE_URL, TYPE) VALUES ('2','2020-10-06 08:00:00',0,'2020-10-06 08:00:00','1','Invitación Pública 01-2020','2020-02-06 08:00:00','CPTIS-0602-2020','4','https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/publications%2FInvitacionPublica022021.pdf?alt=media&token=e92d0fe1-8a45-4748-874d-d6d1c7e7a516','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, FK_SEMESTER_ID, FILE_URL, TYPE) VALUES ('3','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Invitación Pública 02-2020','2020-10-07 08:00:00','CPTIS-0710-2020','3','https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/publications%2FInvitacionPublica022021.pdf?alt=media&token=e92d0fe1-8a45-4748-874d-d6d1c7e7a516','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, FK_SEMESTER_ID, FILE_URL, TYPE) VALUES ('4','2021-03-06 08:00:00',0,'2021-03-06 08:00:00','1','Primera Invitación Pública 02-2021','2021-10-08 08:00:00','CPTIS-0810-2021','1','https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/publications%2FInvitacionPublica022021.pdf?alt=media&token=e92d0fe1-8a45-4748-874d-d6d1c7e7a516','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, FK_SEMESTER_ID, FILE_URL, TYPE) VALUES ('5','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Segunda Invitación Pública 02-2021','2021-10-09 08:00:00','CPTIS-0910-2021','1','https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/publications%2FInvitacionPublica022021.pdf?alt=media&token=e92d0fe1-8a45-4748-874d-d6d1c7e7a516','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, FK_SEMESTER_ID, FILE_URL, TYPE) VALUES ('6','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Tercera Invitación Pública 02-2021','2021-10-10 08:00:00','CPTIS-1010-2021','1','https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/publications%2FInvitacionPublica022021.pdf?alt=media&token=e92d0fe1-8a45-4748-874d-d6d1c7e7a516','0');
-- SPECIFICATION_SHEETS
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, FK_SEMESTER_ID, FILE_URL, TYPE) VALUES ('7','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Primer Pliego de Especificaciones 02-2021','2021-10-09 08:00:00','PETIS-0910-2021','1','https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/publications%2FPliegoEspecificaciones22021.pdf?alt=media&token=1da28d0f-d7c2-4905-90bf-8f468dc005d3','1');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, FK_SEMESTER_ID, FILE_URL, TYPE) VALUES ('8','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Segundo Pliego de Especificaciones 02-2021','2021-10-10 08:00:00','PETIS-1010-2021','1','https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/publications%2FPliegoEspecificaciones22021.pdf?alt=media&token=1da28d0f-d7c2-4905-90bf-8f468dc005d3','1');

--PROJECT
INSERT INTO projects (ID, CREATED_AT, DELETED, UPDATED_AT, TITLE, FK_PUBLICATION_ANNOUNCEMENT_ID, FK_PUBLICATION_SPECIFICATION_ID, FK_ADVISER_ID) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','TIS Plataform','1','7','1');
INSERT INTO projects (ID, CREATED_AT, DELETED, UPDATED_AT, TITLE, FK_PUBLICATION_ANNOUNCEMENT_ID, FK_PUBLICATION_SPECIFICATION_ID, FK_ADVISER_ID) VALUES ('2','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','Mail TIS','2','8','1');

-- COMPANIES
INSERT INTO companies(ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS, FK_ADVISER_ID,FK_PROJECT_ID,FK_SEMESTER_ID) VALUES ('2','ADASOFT','ADA SOFTWARE', 'SRL', 'Jordan y Oquendo', '1','1','1');
INSERT INTO partners(FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('2','1','Luis Tapia','luistapia2998@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners(FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('2','2','Violeta Guzman','violeta@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners(FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('2','3','Leonardo Roldan','leo@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');

INSERT INTO companies(ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS, FK_ADVISER_ID,FK_PROJECT_ID,FK_SEMESTER_ID) VALUES ('3','Acme','Acme Company', 'SA', 'Jordan y Oquendo', '1','1','1');
INSERT INTO partners(FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('3','4','Alguien Asdfd','email1@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners(FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('3','5','Alguien Bbbbb','email2@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners(FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('3','6','Alguien Ccccc','email3@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');

--COMPANIES FROM CSV in h2
--INSERT INTO users(ID, CREATED_AT, DELETED, UPDATED_AT, EMAIL) SELECT ID, CURRENT_TIMESTAMP, FALSE, CURRENT_TIMESTAMP, EMAIL from CSVREAD('src\\main\\resources\\companies.csv', NULL );
--INSERT INTO COMPANIES( ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS, FK_ADVISER_ID,FK_SEMESTER_ID) SELECT ID,SHORT_NAME,NAME,COMPANY_TYPE,ADDRESS,FK_ADVISER_ID,FK_SEMESTER_ID FROM CSVREAD( 'src\\main\\resources\\companies.csv', NULL );

INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('4','7','Eduardo','eduardo@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('4','8','Victoria','victoria@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('4','9','Gustavo','gus@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');

INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('5','10','Alfonso','alfonso@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('5','11','Rebeca','rebeca@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('5','12','Ramona','ram@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');

INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('6','13','Sabrina','sabrina@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('6','14','Lucas','lucas@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('6','15','Ricardo','ricardo@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');

INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('7','16','Gabriela','gabriela@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('7','17','Carlos','carlos@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('7','18','Daniel','daniel@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('7','19','Gary','gary@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');

INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('72','20','Sandra','sandra@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('72','21','Pablo','pablo@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('72','22','Valeria','valeria@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('72','23','Roberto','roberto@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO partners (FK_COMPANY_ID,ID,NAME,EMAIL, CREATED_AT, DELETED, UPDATED_AT) VALUES ('72','24','Fabiola','fabiola@gmail.com','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');

-- PROPOSALS
INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','A','/files/ADASOFTParteA.pdf','1');
INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','B','/files/ADASOFTParteB.pdf','1');

-- SPACES
INSERT INTO spaces (ID,CREATED_AT, DELETED, UPDATED_AT, TITLE, FK_PROJECT_ID, LIMIT_DATE, DESCRIPTION, FK_ADVISER_ID, SPACE_TYPE) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','Parte A','1', '2021-11-06 08:00:00', 'Espacio para subir la Parte A', '1', 0);
INSERT INTO spaces (ID,CREATED_AT, DELETED, UPDATED_AT, TITLE, FK_PROJECT_ID, LIMIT_DATE, DESCRIPTION, FK_ADVISER_ID, SPACE_TYPE) VALUES ('2','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','Parte B','1', '2021-11-28 08:00:00', 'Espacio para subir la Parte A', '1', 0);
INSERT INTO spaces (ID,CREATED_AT, DELETED, UPDATED_AT, TITLE, FK_PROJECT_ID, LIMIT_DATE, DESCRIPTION, FK_ADVISER_ID, SPACE_TYPE) VALUES ('3','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','Parte A Corregida','1', '2021-12-06 08:00:00', 'Espacio para subir la Parte A corregida', '1', 0);
INSERT INTO spaces (ID,CREATED_AT, DELETED, UPDATED_AT, TITLE, FK_PROJECT_ID, LIMIT_DATE, DESCRIPTION, FK_ADVISER_ID, SPACE_TYPE) VALUES ('4','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','Parte B Corregida','1', '2021-12-06 08:00:00', 'Espacio para subir la Parte B corregida', '1', 0);
INSERT INTO spaces (ID,CREATED_AT, DELETED, UPDATED_AT, TITLE, FK_PROJECT_ID, LIMIT_DATE, DESCRIPTION, FK_ADVISER_ID, SPACE_TYPE) VALUES ('5','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','Contrato','1', '2021-12-16 08:00:00', 'Espacio para subir el contrato firmado', '1', 0);

-- SPACE_ANSWERS
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('1', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','2','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('2', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','2','2');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('3', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','3','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('4', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','3','2');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('5', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','4','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('6', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','4','2');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('7', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','2','3');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('8', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','2','4');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('9', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','5','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('10', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','5','2');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('11', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','6','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('12', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','6','2');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('13', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','7','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('14', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','7','2');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('15', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','7','3');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('16', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','7','4');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('17', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','72','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('18', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','72','2');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('19', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','72','3');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('20', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','72','4');

-- FILES
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('1','1',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AdaSoftParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AdaSoftParteA.pdf?alt=media&token=abe66b00-508f-4821-a7fd-6d359a83fe4f');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('2','2',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AdaSoftParteB.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AdaSoftParteB.pdf?alt=media&token=fa43290b-f14c-49b3-b072-01c458844dfc');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('3','3',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AcmeParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('4','4',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AcmeParteB.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('5','5',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'INNOSISParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('6','6',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'INNOSISParteB.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('7','7',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AdaSoftParteACorregida.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('8','8',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AdaSoftParteBCorregida.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('9','9',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AGAAKParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('10','10',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AGAAKParteB.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('11','11',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'ANKASoftParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('12','12',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'ANKASoftParteB.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('13','13',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AntWorkParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('14','14',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AntWorkParteB.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('15','15',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AntWorkParteACorregida.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('16','16',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'AntWorkParteBCorregida.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('17','17',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'JaguarSoftParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('18','18',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'JaguarSoftParteB.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('19','19',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'JaguarSoftParteACorregida.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteA.pdf?alt=media&token=414d8aa0-ae2d-4dda-8029-5dab08415ae9');
INSERT INTO files (FK_SPACE_ANSWER_ID, ID, CREATED_AT, DELETED, UPDATED_AT, NAME, URL) VALUES ('20','20',TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'JaguarSoftParteBCorregida.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AcmeParteB.pdf?alt=media&token=42d74775-fc89-488a-985e-57f89c2f7b39');

--REVIEW
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('1', '2021-11-16 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '3', '2', '1', 'Esta adenda de corrección debe ser entregada en el http://moodle3.umss.edu.bo/ antes de la firma del contrato. Paralelamente se solicita, llenar la planilla RESUMENGRUPOEMPRESA - disponible en moodle; con la información resumen de su propuesta técnica. En este archivo debe registrar el día que su GE ha elegido para el seguimiento de su propuesta de desarrollo.');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('1', '1'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('1', '2'); -- PARTE B
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('2', '2021-11-17 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '4', '2', '1', 'TIS acepta la propuesta técnica presentada por su empresa: DFS S.R.L.. Por lo que solicita hacerse presente el viernes 13 de marzo del 2020 a horas 9:30 a realizar firma de contrato, via reuni’on en meet..\nParalelamente se solicita, llenar la planilla adjunta - RESUMENGRUPOEMPRESA; con la información resumen de su propuesta técnica. En este archivo debe registrar el día que su GE ha elegido para el seguimiento de su propuesta de desarrollo en el tiempo que dure el contrato con TIS. Asímismo, recordar que para el día de la firma del contrato se requiere la entrega de la planilla resumen requerida.');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('2', '3'); -- CAMBIAR SPACES_ID, DEBERIA ESTAR PARTE A Y PARTE B CORREGIDA
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('2', '4');
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('3', '2021-11-16 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '2' , '3', '1', 'Esta adenda de corrección debe ser entregada en el http://moodle3.umss.edu.bo/ antes de la firma del contrato. Paralelamente se solicita, llenar la planilla RESUMENGRUPOEMPRESA - disponible en moodle; con la información resumen de su propuesta técnica. En este archivo debe registrar el día que su GE ha elegido para el seguimiento de su propuesta de desarrollo.');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('3', '1'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('3', '2'); -- PARTE B
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('4', '2021-11-16 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '4' , '4', '1', 'TIS acepta la propuesta técnica presentada por su empresa: DFS S.R.L.. Por lo que solicita hacerse presente el viernes 13 de marzo del 2020 a horas 9:30 a realizar firma de contrato, via reuni’on en meet..\nParalelamente se solicita, llenar la planilla adjunta - RESUMENGRUPOEMPRESA; con la información resumen de su propuesta técnica. En este archivo debe registrar el día que su GE ha elegido para el seguimiento de su propuesta de desarrollo en el tiempo que dure el contrato con TIS. Asímismo, recordar que para el día de la firma del contrato se requiere la entrega de la planilla resumen requerida.');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('4', '1'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('4', '2'); -- PARTE B
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('5', '2021-11-16 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '2' , '5', '1', 'TIS acepta la propuesta técnica presentada por su empresa: DFS S.R.L.. Por lo que solicita hacerse presente el viernes 13 de marzo del 2020 a horas 9:30 a realizar firma de contrato, via reuni’on en meet..\nParalelamente se solicita, llenar la planilla adjunta - RESUMENGRUPOEMPRESA; con la información resumen de su propuesta técnica. En este archivo debe registrar el día que su GE ha elegido para el seguimiento de su propuesta de desarrollo en el tiempo que dure el contrato con TIS. Asímismo, recordar que para el día de la firma del contrato se requiere la entrega de la planilla resumen requerida.');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('5', '1'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('5', '2'); -- PARTE B
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID) VALUES ('6', '2021-11-16 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '0' , '6', '1');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('6', '1'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('6', '2'); -- PARTE B
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('7', '2021-11-16 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '3' , '7', '1', 'Esta adenda de corrección debe ser entregada en el http://moodle3.umss.edu.bo/ antes de la firma del contrato.\nParalelamente se solicita, llenar la planilla adjunta - RESUMENGRUPOEMPRESA; con la información resumen de su propuesta técnica. En este archivo debe registrar el día que su GE ha elegido para el seguimiento de su propuesta de desarrollo en el tiempo que dure el contrato con TIS.\nAsímismo, recordar que para el día de la firma del contrato se requiere la entrega de la planilla resumen requerida.');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('7', '1'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('7', '2'); -- PARTE B
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('8', '2021-11-17 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '5' , '7', '1', 'TIS ha revisado la propuesta corregida que Evil Genius ha entregado y se ha evidenciado que no se han respondido a cabalidad las observaciones de la orden de cambio.');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('8', '3'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('8', '4'); -- PARTE B
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('9', '2021-11-16 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '3' , '72', '1', 'Esta adenda de corrección debe ser entregada en el http://moodle3.umss.edu.bo/ antes de la firma del contrato.\nParalelamente se solicita, llenar la planilla adjunta - RESUMENGRUPOEMPRESA; con la información resumen de su propuesta técnica. En este archivo debe registrar el día que su GE ha elegido para el seguimiento de su propuesta de desarrollo en el tiempo que dure el contrato con TIS.\nAsímismo, recordar que para el día de la firma del contrato se requiere la entrega de la planilla resumen requerida.');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('9', '1'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('9', '2'); -- PARTE B
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, STATUS, FK_COMPANY_ID, FK_ADVISER_ID, COMMENT) VALUES ('10', '2021-11-17 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', '1' , '72', '1', 'Este es un comentario realizado por el Asesor');
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('10', '3'); -- PARTE A
INSERT INTO reviews_spaces (REVIEW_ID,SPACES_ID) VALUES ('10', '4'); -- PARTE B

-- OBSERVATIONS
INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('2','1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Sección 1.1','el párrafo que a la letra dice “Para la automatización de los procesos administrativos de TIS se consideró que la solución idónea es el desarrollo de de un producto...” eliminar la doble de.');
INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('2','2','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Sección 2.1.2','corregir la redacción del párrafo que a la letra dice “Los objetivos de serán definidos antes de empezar cada Sprint en acuerdo con el cliente...” que no se entiende a que objetivos se refiere.');
INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('2','3','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Sección 2.3','apartado Review meeting, tienen una fecha 2 de noviembre que es día feriado, considerar este riesgo.');

INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('4','4','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','3','Sección 4.1','apartado Review meeting, tienen una fecha 2 de noviembre que es día feriado, considerar este riesgo.');

INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('13','5','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','7','Sección 2.2','Acta de constitución, sección Plazo de duración, la vida de la empresa es minima y no genera confianza a TIS, ya que estas fechas no permiten manteniiento de software.');
INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('13','6','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','7','Sección 3.4.5','Acta de constitución, sección Previsiones para reservas: que a la letra dice “En caso de fallecimiento, impedimento o incapacidad sobreviniente de uno de los socios, los restantes continuarán con el giro social, juntamente con los herederos forzosos o legales o los representantes según el caso hasta la culminación de la gestión anual.”, para fines de este contrato los herederos no forma parte de la sociedad en ningun contexto. TIS solicita se corrija este apartado.');
INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('14','7','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','7','Sección 5.1','En la sección Propuesta de servicios: TIS solicita que la empresa especifique con claridad que la modalidad del servicio es “Desarrollo de software”');

INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('16','8','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','Observación 4 y 6','TIS observa que la fecha de entrega del último sprint/producto final sobrepasa la fecha de culminación del contrato, por lo cual TIS requiere que estas fechas sean el 3 de enero de manera indefectible.');

INSERT INTO observations (FK_FILE_ID,ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('18','9','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','9','Sección 5.1','En la sección Propuesta de servicios: TIS solicita que la empresa especifique con claridad que la modalidad del servicio es “Desarrollo de software”');

-- QUALIFICATIONS
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('1', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','1','1');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('2', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','1','2');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('3', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','16','1','3');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('4', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','7','1','4');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('5', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','1','5');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('6', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','12','1','6');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('7', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','1','7');

INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('8', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','4','1');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('9', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','5','4','2');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('10', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','20','4','3');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('11', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','4','4','4');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('12', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','4','5');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('13', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','9','4','6');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('14', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','4','7');

INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('15', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','3','3','1');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('16', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','4','3','2');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('17', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','17','3','3');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('18', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','5','3','4');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('19', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','3','5');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('20', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','11','3','6');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('21', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','3','7');

INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('22', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','15','5','1');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('23', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','5','2');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('24', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','30','5','3');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('25', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','5','4');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('26', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','5','5');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('27', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','15','5','6');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('28', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','5','7');

INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('29', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','7','1');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('30', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','7','2');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('31', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','29','7','3');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('32', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','7','4');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('33', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','7','5');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('34', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','7','6');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('35', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','9','7','7');

INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('36', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','1');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('37', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','2');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('38', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','3');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('39', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','4');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('40', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','5');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('41', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','6');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('42', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','6','7');

INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('43', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','9','1');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('44', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','9','2');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('45', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','29','9','3');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('46', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','8','9','4');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('47', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','9','5');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('48', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','10','9','6');
INSERT INTO qualifications (ID, CREATED_AT, DELETED, UPDATED_AT, SCORE, FK_REVIEW_ID, FK_BASE_QUALIFICATION_ID) VALUES ('49', '2021-10-06 08:00:00',0,'2021-10-06 08:00:00','9','9','7');

--COMPANY_SPACES
INSERT INTO company_spaces (ID, FK_COMPANY_ID, FK_SPACE_ID, FK_REVIEW_ID, CREATED_AT, DELETED, UPDATED_AT) VALUES (NULL,'2','1',NULL,'2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO company_spaces (ID, FK_COMPANY_ID, FK_SPACE_ID, FK_REVIEW_ID, CREATED_AT, DELETED, UPDATED_AT) VALUES (NULL,'2','2',NULL,'2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO company_spaces (ID, FK_COMPANY_ID, FK_SPACE_ID, FK_REVIEW_ID, CREATED_AT, DELETED, UPDATED_AT) VALUES (NULL,'2','3','1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO company_spaces (ID, FK_COMPANY_ID, FK_SPACE_ID, FK_REVIEW_ID, CREATED_AT, DELETED, UPDATED_AT) VALUES (NULL,'2','4','1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO company_spaces (ID, FK_COMPANY_ID, FK_SPACE_ID, FK_REVIEW_ID, CREATED_AT, DELETED, UPDATED_AT) VALUES (NULL,'3','1',NULL,'2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO company_spaces (ID, FK_COMPANY_ID, FK_SPACE_ID, FK_REVIEW_ID, CREATED_AT, DELETED, UPDATED_AT) VALUES (NULL,'3','2',NULL,'2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
