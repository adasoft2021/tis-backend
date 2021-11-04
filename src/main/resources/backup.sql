;             
CREATE USER IF NOT EXISTS "SA" SALT '427cc7f546f968c6' HASH '6adf0a382414ecf51fb1e2200a4f956653c0cebfbc83410d5f17580f5a7faf17' ADMIN;         
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9EA771EC_3463_416B_881A_65AECDA22A3E" START WITH 8 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9A2EB402_1EE7_4102_9B92_12A36608A62D" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_7F73F8F9_A330_422C_92FA_2F87CFC70A9D" START WITH 4 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_75F2D68F_C57D_4886_8D20_02FAC488DCA8" START WITH 2 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_8C928E65_C6D1_491C_BCE2_0A7E1B90B54C" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_6F40761F_1887_4E07_8D08_17355D06254E" START WITH 7 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_5DEDAA2E_5642_4E2E_B6F7_CB84AF1B851F" START WITH 3 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_155D4EEB_565B_49E3_9B5E_71591449F425" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_44BBCB0F_812E_414D_865D_DB0E93F92B75" START WITH 4 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_5BE6B513_5F51_4DE5_8098_8C2FCF4E3745" START WITH 1 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."ADVISERS"(
    "FIRST_NAME" VARCHAR(255) NOT NULL,
    "LAST_NAME" VARCHAR(255) NOT NULL,
    "ID" BIGINT NOT NULL
);       
ALTER TABLE "PUBLIC"."ADVISERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");     
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.ADVISERS;
INSERT INTO "PUBLIC"."ADVISERS" VALUES
('Maria Leticia', 'Blanco Coca', 1);  
CREATE MEMORY TABLE "PUBLIC"."BASE_QUALIFICATIONS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_9EA771EC_3463_416B_881A_65AECDA22A3E" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9EA771EC_3463_416B_881A_65AECDA22A3E",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "DESCRIPTION" VARCHAR(255) NOT NULL,
    "MAX_SCORE" INTEGER NOT NULL
);   
ALTER TABLE "PUBLIC"."BASE_QUALIFICATIONS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");          
-- 7 +/- SELECT COUNT(*) FROM PUBLIC.BASE_QUALIFICATIONS;     
INSERT INTO "PUBLIC"."BASE_QUALIFICATIONS" VALUES
(1, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'Cumplimiento de especificaciones del proponente', 15),
(2, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', STRINGDECODE('Claridad en la organizaci\u00f3n de la empresa del proponente'), 10),
(3, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', STRINGDECODE('Cumplimiento de especificaciones t\u00e9cnicas'), 30),
(4, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'Claridad en el proceso de desarrollo', 10),
(5, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', STRINGDECODE('Plazo de ejecuci\u00f3n'), 10),
(6, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'Precio total', 15),
(7, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'Uso de herramientas en el proceso de desarrollo', 10);  
CREATE MEMORY TABLE "PUBLIC"."CLASS_CODES"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_8C928E65_C6D1_491C_BCE2_0A7E1B90B54C" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_8C928E65_C6D1_491C_BCE2_0A7E1B90B54C",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "CODE" VARCHAR(255) NOT NULL,
    "FK_ADVISER_ID" BIGINT NOT NULL
);               
ALTER TABLE "PUBLIC"."CLASS_CODES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("ID");  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CLASS_CODES;             
CREATE MEMORY TABLE "PUBLIC"."COMPANIES"(
    "ADDRESS" VARCHAR(255),
    "COMPANY_TYPE" VARCHAR(255) NOT NULL,
    "NAME" VARCHAR(255) NOT NULL,
    "SHORT_NAME" VARCHAR(255) NOT NULL,
    "TELEPHONE" VARCHAR(255),
    "ID" BIGINT NOT NULL
);    
ALTER TABLE "PUBLIC"."COMPANIES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_5" PRIMARY KEY("ID");    
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.COMPANIES;               
INSERT INTO "PUBLIC"."COMPANIES" VALUES
('Jordan y Oquendo', 'SRL', 'ADA SOFTWARE', 'ADASOFT', NULL, 2),
('Jordan y Oquendo', 'SA', 'Acme Company', 'Acme', NULL, 3);       
CREATE MEMORY TABLE "PUBLIC"."FILES"(
    "ID" BIGINT NOT NULL,
    "NAME" VARCHAR(255),
    "URL" VARCHAR(255),
    "DELETED" BOOLEAN NOT NULL
);       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.FILES;   
CREATE MEMORY TABLE "PUBLIC"."OBSERVATIONS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_9A2EB402_1EE7_4102_9B92_12A36608A62D" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9A2EB402_1EE7_4102_9B92_12A36608A62D",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "DESCRIPTION" VARCHAR(255) NOT NULL,
    "TITLE" VARCHAR(255) NOT NULL,
    "FK_PROPOSAL_ID" BIGINT NOT NULL
);  
ALTER TABLE "PUBLIC"."OBSERVATIONS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" PRIMARY KEY("ID"); 
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.OBSERVATIONS;            
INSERT INTO "PUBLIC"."OBSERVATIONS" VALUES
(1, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'esta es una observacion', 'seccion 1', 1);          
CREATE MEMORY TABLE "PUBLIC"."PARTNERS"(
    "ID" BIGINT NOT NULL,
    "PARTNERS" VARCHAR(255)
);          
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.PARTNERS;
INSERT INTO "PUBLIC"."PARTNERS" VALUES
(2, 'Luis Tapia'),
(2, 'Violeta Guzman'),
(2, 'Leonardo Roldan'),
(3, 'Alguien Asdfd'),
(3, 'Alguien Asdf'),
(3, 'Aasdfds Bsdfsd');              
CREATE MEMORY TABLE "PUBLIC"."PROPOSALS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_5DEDAA2E_5642_4E2E_B6F7_CB84AF1B851F" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_5DEDAA2E_5642_4E2E_B6F7_CB84AF1B851F",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "ADVISER" BIGINT,
    "CREATED_BY" BIGINT NOT NULL,
    "FILE_URL" VARCHAR(255),
    "PART" VARCHAR(255),
    "FK_REVIEW_ID" BIGINT
);            
ALTER TABLE "PUBLIC"."PROPOSALS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");    
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.PROPOSALS;               
INSERT INTO "PUBLIC"."PROPOSALS" VALUES
(1, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 1, 1, '/files/ADASOFTParteA.pdf', 'A', NULL),
(2, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 1, 1, '/files/ADASOFTParteB.pdf', 'B', NULL);               
CREATE MEMORY TABLE "PUBLIC"."PUBLICATIONS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_6F40761F_1887_4E07_8D08_17355D06254E" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_6F40761F_1887_4E07_8D08_17355D06254E",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "CODE" VARCHAR(255) NOT NULL,
    "DATE" TIMESTAMP NOT NULL,
    "FILE_URL" VARCHAR(255) NOT NULL,
    "SEMESTER" VARCHAR(255) NOT NULL,
    "TITLE" VARCHAR(255) NOT NULL,
    "TYPE" INTEGER NOT NULL,
    "FK_ADVISER_ID" BIGINT NOT NULL
);              
ALTER TABLE "PUBLIC"."PUBLICATIONS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_C" PRIMARY KEY("ID"); 
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.PUBLICATIONS;            
INSERT INTO "PUBLIC"."PUBLICATIONS" VALUES
(1, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'CPTIS-0609-2021', TIMESTAMP '2021-09-06 08:00:00', 'https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing', '2-2021', STRINGDECODE('T\u00edtulo de una nueva convocatoria'), 0, 1),
(2, TIMESTAMP '2020-10-06 08:00:00', FALSE, TIMESTAMP '2020-10-06 08:00:00', 'CPTIS-0610-2020', TIMESTAMP '2020-10-06 08:00:00', 'https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing', '2-2020', STRINGDECODE('T\u00edtulo de una nueva convocatoria'), 0, 1),
(3, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'CPTIS-0710-2021', TIMESTAMP '2021-10-07 08:00:00', 'https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing', '2-2021', STRINGDECODE('T\u00edtulo de una nueva convocatoria'), 0, 1),
(4, TIMESTAMP '2021-03-06 08:00:00', FALSE, TIMESTAMP '2021-03-06 08:00:00', 'CPTIS-0810-2021', TIMESTAMP '2021-10-08 08:00:00', 'https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing', '2-2021', STRINGDECODE('T\u00edtulo de una nueva convocatoria'), 0, 1),
(5, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'CPTIS-0910-2021', TIMESTAMP '2021-10-09 08:00:00', 'https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing', '2-2021', STRINGDECODE('T\u00edtulo de una nueva convocatoria'), 0, 1),
(6, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00', 'CPTIS-1010-2021', TIMESTAMP '2021-10-10 08:00:00', 'https://drive.google.com/file/d/1Y1UxDtPa7Qr7uPzJ32D6pB7nwbCrrXxe/view?usp=sharing', '2-2021', STRINGDECODE('T\u00edtulo de una nueva convocatoria'), 0, 1);    
CREATE MEMORY TABLE "PUBLIC"."QUALIFICATIONS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_5BE6B513_5F51_4DE5_8098_8C2FCF4E3745" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_5BE6B513_5F51_4DE5_8098_8C2FCF4E3745",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "SCORE" INTEGER,
    "FK_BASE_QUALIFICATION_ID" BIGINT NOT NULL,
    "FK_REVIEW_ID" BIGINT NOT NULL
);         
ALTER TABLE "PUBLIC"."QUALIFICATIONS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" PRIMARY KEY("ID");               
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.QUALIFICATIONS;          
CREATE MEMORY TABLE "PUBLIC"."REVIEWS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_155D4EEB_565B_49E3_9B5E_71591449F425" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_155D4EEB_565B_49E3_9B5E_71591449F425",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "COMMENT" VARCHAR(255),
    "CREATED_BY" BIGINT NOT NULL,
    "TOTAL_SCORE" INTEGER
);
ALTER TABLE "PUBLIC"."REVIEWS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6C" PRIMARY KEY("ID");     
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.REVIEWS; 
CREATE MEMORY TABLE "PUBLIC"."SPACE_ANSWERS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_75F2D68F_C57D_4886_8D20_02FAC488DCA8" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_75F2D68F_C57D_4886_8D20_02FAC488DCA8",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "FK_COMPANY_ID" BIGINT NOT NULL,
    "FK_SPACE_ID" BIGINT NOT NULL
);            
ALTER TABLE "PUBLIC"."SPACE_ANSWERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID");
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.SPACE_ANSWERS;           
CREATE MEMORY TABLE "PUBLIC"."SPACES"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_44BBCB0F_812E_414D_865D_DB0E93F92B75" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_44BBCB0F_812E_414D_865D_DB0E93F92B75",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL
);             
ALTER TABLE "PUBLIC"."SPACES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_92" PRIMARY KEY("ID");      
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.SPACES;  
INSERT INTO "PUBLIC"."SPACES" VALUES
(1, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00'),
(2, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00'),
(3, TIMESTAMP '2021-10-06 08:00:00', FALSE, TIMESTAMP '2021-10-06 08:00:00');             
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_7F73F8F9_A330_422C_92FA_2F87CFC70A9D" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_7F73F8F9_A330_422C_92FA_2F87CFC70A9D",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "DELETED" BOOLEAN NOT NULL,
    "UPDATED_AT" TIMESTAMP NOT NULL,
    "EMAIL" VARCHAR(255) NOT NULL
);          
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4D" PRIMARY KEY("ID");       
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.USERS;   
INSERT INTO "PUBLIC"."USERS" VALUES
(1, TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047', 'leticia@email'),
(2, TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047', 'adasoftsrl@gmail.com'),
(3, TIMESTAMP '2021-10-11 12:34:21.209963', FALSE, TIMESTAMP '2021-10-11 12:35:17.118047', 'acme@gmail.com');         
ALTER TABLE "PUBLIC"."CLASS_CODES" ADD CONSTRAINT "PUBLIC"."UK_9DRGJO41ODGGLKDW797O92PKO" UNIQUE("FK_ADVISER_ID");            
ALTER TABLE "PUBLIC"."PUBLICATIONS" ADD CONSTRAINT "PUBLIC"."UK_OJR6L8TQOUPM6DQ3ELS21ESN" UNIQUE("CODE");     
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."UK_6DOTKOTT2KJSP8VW4D0M25FB7" UNIQUE("EMAIL");          
ALTER TABLE "PUBLIC"."COMPANIES" ADD CONSTRAINT "PUBLIC"."UK_QR5M39NTXRSR9J02OXP9T9Q03" UNIQUE("COMPANY_TYPE");               
ALTER TABLE "PUBLIC"."PROPOSALS" ADD CONSTRAINT "PUBLIC"."FKDS8782OGOVSXSV50BN4EC8OKV" FOREIGN KEY("FK_REVIEW_ID") REFERENCES "PUBLIC"."REVIEWS"("ID") NOCHECK;               
ALTER TABLE "PUBLIC"."PARTNERS" ADD CONSTRAINT "PUBLIC"."FKM40WF9UT6WOTXQY0QIL91W89W" FOREIGN KEY("ID") REFERENCES "PUBLIC"."COMPANIES"("ID") NOCHECK;        
ALTER TABLE "PUBLIC"."SPACE_ANSWERS" ADD CONSTRAINT "PUBLIC"."FKP3R6YWX3CXTGJ4MB3AQ1JMCV2" FOREIGN KEY("FK_COMPANY_ID") REFERENCES "PUBLIC"."COMPANIES"("ID") NOCHECK;        
ALTER TABLE "PUBLIC"."QUALIFICATIONS" ADD CONSTRAINT "PUBLIC"."FK3BQUODFL64JD493WP9WKIFMV7" FOREIGN KEY("FK_REVIEW_ID") REFERENCES "PUBLIC"."REVIEWS"("ID") NOCHECK;          
ALTER TABLE "PUBLIC"."QUALIFICATIONS" ADD CONSTRAINT "PUBLIC"."FKFE5LEGRFCATLN7GMUKA0HNUY7" FOREIGN KEY("FK_BASE_QUALIFICATION_ID") REFERENCES "PUBLIC"."BASE_QUALIFICATIONS"("ID") NOCHECK;  
ALTER TABLE "PUBLIC"."OBSERVATIONS" ADD CONSTRAINT "PUBLIC"."FK1H4XW2REJRUT7ESN3O657KF4C" FOREIGN KEY("FK_PROPOSAL_ID") REFERENCES "PUBLIC"."PROPOSALS"("ID") NOCHECK;        
ALTER TABLE "PUBLIC"."ADVISERS" ADD CONSTRAINT "PUBLIC"."FKDX8WQ2U3EDH0S1CV7ND6Q3QRQ" FOREIGN KEY("ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;            
ALTER TABLE "PUBLIC"."COMPANIES" ADD CONSTRAINT "PUBLIC"."FK75NYYIR60UKPFQ5V0V2JL0FRI" FOREIGN KEY("ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;           
ALTER TABLE "PUBLIC"."PUBLICATIONS" ADD CONSTRAINT "PUBLIC"."FKOT717L08JSGFV2KCRBR8L3EKY" FOREIGN KEY("FK_ADVISER_ID") REFERENCES "PUBLIC"."ADVISERS"("ID") NOCHECK;          
ALTER TABLE "PUBLIC"."SPACE_ANSWERS" ADD CONSTRAINT "PUBLIC"."FKHBH805KRKUHHL4NSQXYK9T23V" FOREIGN KEY("FK_SPACE_ID") REFERENCES "PUBLIC"."SPACES"("ID") NOCHECK;             
ALTER TABLE "PUBLIC"."CLASS_CODES" ADD CONSTRAINT "PUBLIC"."FKQB4M4B5KGYVEIL1X9W4THI5JO" FOREIGN KEY("FK_ADVISER_ID") REFERENCES "PUBLIC"."ADVISERS"("ID") NOCHECK;           
ALTER TABLE "PUBLIC"."FILES" ADD CONSTRAINT "PUBLIC"."FKB3KGLSVPWNF7IUEFM94S53FA9" FOREIGN KEY("ID") REFERENCES "PUBLIC"."SPACE_ANSWERS"("ID") NOCHECK;       
