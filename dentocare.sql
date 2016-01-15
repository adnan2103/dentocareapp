DROP TABLE patient;
DROP TABLE doctor;
CREATE TABLE doctor
(
  doctor_id integer NOT NULL,
  email character varying(255) NOT NULL,
  first_name character varying(255),
  last_name character varying(255),
  password character varying(255),
  CONSTRAINT doctor_pkey PRIMARY KEY (doctor_id)
);

CREATE UNIQUE INDEX UK_DOCTOR_EMAIL ON doctor (email);



CREATE TABLE patient
(
    patient_id integer NOT NULL,
    doctor integer NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    CONSTRAINT patient_pkey PRIMARY KEY (patient_id),
    FOREIGN KEY (doctor) REFERENCES doctor (doctor_id) ON DELETE CASCADE
);

CREATE INDEX FK_DOCTOR_ID ON patient (doctor);

insert into doctor(doctor_id,email,password,first_name,last_name) values (1,'adnan@khan.com','password','Adnan','Khan');
insert into doctor(doctor_id,email,password,first_name,last_name) values (2,'danish@khan.com','password','Danish','Khan');

update doctor set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';


DROP SEQUENCE patient_id_seq;
DROP SEQUENCE doctor_id_seq;

CREATE SEQUENCE patient_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE doctor_id_seq START WITH 3 INCREMENT BY 1;


