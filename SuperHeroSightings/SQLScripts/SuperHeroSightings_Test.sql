drop database if exists SuperHeroSightings_Test;

create database SuperHeroSightings_Test;

use SuperHeroSightings_Test;

create table Mutants(
MutantID int not null auto_increment,
MutantName varchar(50) not null,
MutantDescription varchar(1000) not null,
primary key (MutantID));

create table Orgs (
OrgID int not null auto_increment,
OrgName varchar(50) not null,
OrgDescription varchar(1000) not null,
OrgStreet varchar(50) not null,
OrgCity varchar(50) not null,
OrgState varchar(50) not null,
OrgZip varchar(20),
OrgPhone varchar(20) not null,
OrgEmail varchar(50) not null,
primary key(OrgID));

create table Powers(
PowerID int not null auto_increment,
PowerDescription varchar(100) not null,
Primary key(PowerID));

create table Sightings(
SightingID int not null auto_increment,
SightingDate datetime not null,
LocationID int not null,
primary key(SightingID));

create table Locations(
LocationID int not null auto_increment,
LocationName varchar(50),
LocationDescription varchar(1000) not null,
LocationStreet varchar(50) not null,
LocationCity varchar(50) not null,
LocationState varchar(50) not null,
LocationZip varchar(20),
Latitude decimal(11,8),
Longitude decimal(11,8),
primary key(LocationID));

create table MutantsPowers(
MutantID int not null,
PowerID int not null,
primary key(MutantID, PowerID));

create table MutantsOrgs(
MutantID int not null,
OrgID int not null,
primary key(MutantID, OrgID));

create table MutantsSightings(
MutantID int not null,
SightingID int not null,
primary key(MutantID, SightingID));

alter table Sightings add constraint foreign key(LocationID)
references Locations(LocationID);

alter table MutantsPowers add constraint foreign key(MutantID)
references Mutants(MutantID);

alter table MutantsPowers add constraint foreign key(PowerID)
references Powers(PowerID);

alter table MutantsOrgs add constraint foreign key(MutantID)
references Mutants(MutantID);

alter table MutantsOrgs add constraint foreign key(OrgID)
references Orgs(OrgID);

alter table MutantsSightings  add constraint foreign key(MutantID)
references Mutants(MutantID);

alter table MutantsSightings add constraint foreign key(SightingID)
references Sightings(SightingID)



