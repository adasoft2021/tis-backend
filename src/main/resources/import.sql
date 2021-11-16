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

-- CLASS_CODES
INSERT INTO CLASS_CODES(ID, CREATED_AT, DELETED, UPDATED_AT, CODE, FK_ADVISER_ID) VALUES ('1', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175', 'jey-ibl-glp','1');

-- COMPANIES
INSERT INTO companies(ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS, FK_ADVISER_ID) VALUES ('2','ADASOFT','ADA SOFTWARE', 'SRL', 'Jordan y Oquendo', '1')
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Luis Tapia');
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Violeta Guzman');
INSERT INTO partners(ID,PARTNERS) VALUES ('2','Leonardo Roldan');

INSERT INTO companies(ID, SHORT_NAME, NAME, COMPANY_TYPE, ADDRESS, FK_ADVISER_ID) VALUES ('3','Acme','Acme Company', 'SA', 'Jordan y Oquendo', '1');
INSERT INTO partners(ID,PARTNERS) VALUES ('3','Alguien Asdfd');
INSERT INTO partners(ID,PARTNERS) VALUES ('3','Alguien Asdf');
INSERT INTO partners(ID,PARTNERS) VALUES ('3','Aasdfds Bsdfsd');

-- PROPOSALS
INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','A','/files/ADASOFTParteA.pdf','1');
INSERT INTO proposals (CREATED_AT, DELETED, UPDATED_AT, CREATED_BY, PART, FILE_URL, ADVISER) VALUES ('2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','B','/files/ADASOFTParteB.pdf','1');


-- PUBLICATIONS
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-09-06 08:00:00','CPTIS-0609-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('2','2020-10-06 08:00:00',0,'2020-10-06 08:00:00','1','Título de una nueva convocatoria','2020-10-06 08:00:00','CPTIS-0610-2020','2-2020','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('3','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-07 08:00:00','CPTIS-0710-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('4','2021-03-06 08:00:00',0,'2021-03-06 08:00:00','1','Título de una nueva convocatoria','2021-10-08 08:00:00','CPTIS-0810-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('5','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-09 08:00:00','CPTIS-0910-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');
INSERT INTO publications (ID, CREATED_AT, DELETED, UPDATED_AT, FK_ADVISER_ID, TITLE, DATE, CODE, SEMESTER, FILE_URL, TYPE) VALUES ('6','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','Título de una nueva convocatoria','2021-10-10 08:00:00','CPTIS-1010-2021','2-2021','https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing','0');

-- SPACES
INSERT INTO spaces (ID,CREATED_AT, DELETED, UPDATED_AT) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO spaces (ID,CREATED_AT, DELETED, UPDATED_AT) VALUES ('2','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');
INSERT INTO spaces (ID,CREATED_AT, DELETED, UPDATED_AT) VALUES ('3','2021-10-06 08:00:00',0,'2021-10-06 08:00:00');

-- SPACE_ANSWERS
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('1', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','2','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('2', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','2','1');
INSERT INTO space_answers (ID,CREATED_AT, DELETED, UPDATED_AT, FK_COMPANY_ID, FK_SPACE_ID) VALUES ('3', TIMESTAMP '2021-11-07 21:56:27.36174', 0, TIMESTAMP '2021-11-07T21:56:27.36175','3','1');

-- FILES
INSERT INTO files (ID, DELETED, NAME, URL) VALUES ('1', 0, 'AdaSoftParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AdaSoftParteA.pdf?alt=media&token=abe66b00-508f-4821-a7fd-6d359a83fe4f');
INSERT INTO files (ID, DELETED, NAME, URL) VALUES ('1', 0, 'AdaSoftParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/Fundempresa%20TIS.pdf?alt=media&token=df9e5f85-1e50-4434-aaa1-450375effff1');
INSERT INTO files (ID, DELETED, NAME, URL) VALUES ('2', 0, 'AdaSoftParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AdaSoftParteA.pdf?alt=media&token=abe66b00-508f-4821-a7fd-6d359a83fe4f');
INSERT INTO files (ID, DELETED, NAME, URL) VALUES ('3', 0, 'acmeParteA.pdf', 'https://firebasestorage.googleapis.com/v0/b/tis-storage.appspot.com/o/AdaSoftParteA.pdf?alt=media&token=abe66b00-508f-4821-a7fd-6d359a83fe4f');

--REVIEW
INSERT INTO reviews (ID,CREATED_AT, DELETED, UPDATED_AT, PUBLISHED, TITLE, FK_COMPANY_ID, FK_ADVISER_ID) VALUES ('1', '2021-11-16 15:45:51.012352',FALSE, '2021-11-16 15:45:51.012352', FALSE, 'Orden de cambio', '2', '1');
INSERT INTO reviews_spaces(REVIEW_ID,SPACES_ID) VALUES ('1', '1');

-- OBSERVATIONS
INSERT INTO observations (ID, CREATED_AT, DELETED, UPDATED_AT, FK_PROPOSAL_ID, FK_REVIEW_ID, TITLE, DESCRIPTION) VALUES ('1','2021-10-06 08:00:00',0,'2021-10-06 08:00:00','1','1','seccion 1','esta es una observacion');