/*
  It works only when <prop key="hibernate.hbm2ddl.auto">create</prop>
*/

INSERT INTO Roles (Id, RoleName) VALUES (1, 'User');
INSERT INTO Roles (Id, RoleName) VALUES (2, 'Administrator');

SELECT  setval('Roles_Id_seq', (SELECT MAX(Id) FROM Roles));



INSERT INTO AppUsers (Email, Password, Salt, UserName, RoleId) VALUES ('admin@ks.com', 'a665937d971a3ecebf5729222962e4aa8949b5fb', '[B@2bfbac38', 'admin', 2);
INSERT INTO AppUsers (Email, Password, Salt, UserName, RoleId) VALUES ('user@ks.com', 'a665937d971a3ecebf5729222962e4aa8949b5fb', '[B@2bfbac38', 'user', 1);



INSERT INTO ProjectTypes (Id, Name) VALUES (1, 'Art');
INSERT INTO ProjectTypes (Id, Name) VALUES (2, 'Games');
INSERT INTO ProjectTypes (Id, Name) VALUES (3, 'Comics');
INSERT INTO ProjectTypes (Id, Name) VALUES (4, 'Crafts');
INSERT INTO ProjectTypes (Id, Name) VALUES (5, 'Music');
INSERT INTO ProjectTypes (Id, Name) VALUES (6, 'Food');

SELECT  setval('projecttypes_Id_seq', (SELECT MAX(Id) FROM ProjectTypes));



INSERT INTO Countries (Id, Name) VALUES (1, 'Belarus');
INSERT INTO Countries (Id, Name) VALUES (2, 'Russia');
INSERT INTO Countries (Id, Name) VALUES (3, 'Poland');
INSERT INTO Countries (Id, Name) VALUES (4, 'Lithuania');
INSERT INTO Countries (Id, Name) VALUES (5, 'Vilnius');
INSERT INTO Countries (Id, Name) VALUES (6, 'Ukraine');

SELECT  setval('countries_Id_seq', (SELECT MAX(Id) FROM Countries));
