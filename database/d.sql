CREATE TABLE Patient (
	patient_id varchar(255) not null PRIMARY KEY,
	name varchar(255) not null,
	birthday date not null,
	health_insurance varchar(255),
	postCode varchar(255) not null,
	street varchar(255) not null,
	num varchar(255) not null,
	story varchar(255),
	apart varchar(255),
	phone_number varchar(255),
	address_valid varchar(255)
);

CREATE TABLE Screening (
	date_visit timestamp not null,
	patient_id varchar(255) not null REFERENCES Patient(patient_id),
	typeM varchar(255) not null,
	importance varchar(255) not null,
	screening_valid varchar(255),
	CONSTRAINT screening_pk PRIMARY KEY (date_visit, patient_id)
);

CREATE TABLE Drugs (
	date_visit timestamp not null,
	patient_id varchar(255) NOT NULL,
	drugName varchar(255) NOT NULL,
	dosage varchar(255) NOT NULL,
	substance varchar(255) NOT NULL,	
	drug_valid varchar(255),
	CONSTRAINT drugs_pk PRIMARY KEY (drugName,date_visit, patient_id),
	CONSTRAINT fk_drugs
    FOREIGN KEY (date_visit, patient_id)
    REFERENCES Screening(date_visit, patient_id)
);


ALTER SESSION SET NLS_TIMESTAMP_FORMAT='YYYY-MON-DD HH:MI:SS';

CREATE TABLE CareUnits (
	typeM varchar(255) not null,
	use int not null,
	limit int not null,
	state varchar(255) not null,	
	CONSTRAINT careunits_pk PRIMARY KEY (typeM)
);

CREATE TABLE Registry (
	typeM varchar(255) not null,
	patient_id varchar(255) not null,
	date_visit timestamp not null,
	CONSTRAINT registry_pk PRIMARY KEY (typeM, patient_id, date_visit),
	CONSTRAINT fk_registry1
    FOREIGN KEY (typeM)
    REFERENCES CareUnits(typeM),
    CONSTRAINT fk_registry2
    FOREIGN KEY (patient_id)
    REFERENCES Patient(patient_id)
);

insert into CareUnits values ('Screening',0,2,'normal');
insert into CareUnits values ('Pediatrics',0,2,'normal');
insert into CareUnits values ('Orthopedics',0,2,'normal');
insert into CareUnits values ('Surgery',0,1,'normal');
insert into CareUnits values ('General Medicine',0,3,'normal');

CREATE TABLE Pharmacy (
	drugName varchar(255) not null,
	dosage varchar(255) not null,
	substance varchar(255) not null,	
	stock int not null,
	stock_line int not null,
	order_size int not null,
	price Decimal(4,2) not null,
	order_inplace varchar(5),
	CONSTRAINT pharmacy_pk PRIMARY KEY (drugName, dosage, substance)
);

insert into Pharmacy values ('Reumon Gel', '50 mg/g','Etofenamato',  0, 20, 30, 9.20, 'NO');
insert into Pharmacy values ('Aspirina', '500 mg', 'Acido acetilsalicilico', 0, 40, 50, 5.00, 'NO');

CREATE TABLE DrugWaitingList (
	patient_id varchar(255) not null,
	date_visit timestamp not null,
	drugName varchar(255) not null,
	dosage varchar(255) not null,
	substance varchar(255) not null,
	CONSTRAINT drugsWaitingList_pk PRIMARY KEY (drugName, dosage, substance, date_visit, patient_id),
	CONSTRAINT fk_drugsWaitingList
    FOREIGN KEY (date_visit, patient_id)
    REFERENCES Screening(date_visit, patient_id)
);

