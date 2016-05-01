CREATE DATABASE kickstarterDb;

GRANT ALL privileges ON DATABASE kickstarterDb TO postgres;

CREATE TABLE Roles
(
  Id SERIAL,
  RoleName character varying(45),
  CONSTRAINT PK_Roles PRIMARY KEY (Id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Roles
  OWNER TO postgres;

INSERT INTO Roles (Id, RoleName) VALUES (1, 'User');
INSERT INTO Roles (Id, RoleName) VALUES (2, 'Administrator');

SELECT  setval('Roles_Id_seq', (SELECT MAX(Id) FROM Roles));


CREATE TABLE AppUsers
(
  Id SERIAL,
  Email character varying(45) NOT NULL,
  FirstName character varying(45),
  LastName character varying(45),
  Password character varying(45) NOT NULL,
  Salt character varying(45) NOT NULL,
  UserName character varying(45) NOT NULL,
  RoleId integer NOT NULL DEFAULT 1,
  CONSTRAINT PK_AppUsers PRIMARY KEY (Id),
  CONSTRAINT FK_AppUserRole FOREIGN KEY (RoleId)
      REFERENCES Roles (Id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE AppUsers
  OWNER TO postgres;

INSERT INTO AppUsers (Email, Password, Salt, UserName, RoleId) VALUES ('admin@ks.com', 'a665937d971a3ecebf5729222962e4aa8949b5fb', '[B@2bfbac38', 'admin', 2);
INSERT INTO AppUsers (Email, Password, Salt, UserName, RoleId) VALUES ('user@ks.com', 'a665937d971a3ecebf5729222962e4aa8949b5fb', '[B@2bfbac38', 'user', 1);

CREATE TABLE ProjectTypes
(
  Id SERIAL,
  Name character varying(45),
  CONSTRAINT "PK_ProjectTypes" PRIMARY KEY (Id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE ProjectTypes
OWNER TO postgres;

INSERT INTO ProjectTypes (Id, Name) VALUES (1, 'Art');
INSERT INTO ProjectTypes (Id, Name) VALUES (2, 'Games');
INSERT INTO ProjectTypes (Id, Name) VALUES (3, 'Comics');
INSERT INTO ProjectTypes (Id, Name) VALUES (4, 'Crafts');
INSERT INTO ProjectTypes (Id, Name) VALUES (5, 'Music');
INSERT INTO ProjectTypes (Id, Name) VALUES (6, 'Food');

SELECT  setval('projecttypes_Id_seq', (SELECT MAX(Id) FROM ProjectTypes));



CREATE TABLE Countries
(
  Id SERIAL,
  Name character varying(45),
  CONSTRAINT "PK_Countries" PRIMARY KEY (Id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE Countries
OWNER TO postgres;

INSERT INTO Countries (Id, Name) VALUES (1, 'Belarus');
INSERT INTO Countries (Id, Name) VALUES (2, 'Russia');
INSERT INTO Countries (Id, Name) VALUES (3, 'Poland');
INSERT INTO Countries (Id, Name) VALUES (4, 'Lithuania');
INSERT INTO Countries (Id, Name) VALUES (5, 'Vilnius');
INSERT INTO Countries (Id, Name) VALUES (6, 'Ukraine');

SELECT  setval('countries_Id_seq', (SELECT MAX(Id) FROM Countries));


CREATE TABLE Project
(
  Id SERIAL,
  Name character varying(45) NOT NULL,
  ProjectTypeId integer NOT NULL,
  CountryId integer NOT NULL,
  Owner integer NOT NULL,
  CONSTRAINT PK_Project PRIMARY KEY (Id),
  CONSTRAINT FK_ProjectOwner FOREIGN KEY (Owner)
  REFERENCES AppUsers (Id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT UQ_Project_Name_Owner UNIQUE (Name, Owner)
)
WITH (
OIDS=FALSE
);
ALTER TABLE Project
OWNER TO postgres;
