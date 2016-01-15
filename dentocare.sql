DROP TABLE patient;
DROP TABLE doctor;
CREATE TABLE doctor
(
  id integer NOT NULL,
  email character varying(255) NOT NULL,
  first_name character varying(255),
  last_name character varying(255),
  password character varying(255),
  CONSTRAINT person_pkey PRIMARY KEY (id)
);

CREATE UNIQUE INDEX UK_PERSON_EMAIL ON doctor (email);



CREATE TABLE patient
(
  id integer NOT NULL,
    created timestamp,
    summary character varying(255) NOT NULL,
    message character varying(255) NOT NULL,
    from_id integer NOT NULL,
    to_id integer NOT NULL,
    CONSTRAINT patient_pkey PRIMARY KEY (id),
    FOREIGN KEY (to_id) REFERENCES doctor (id),
    FOREIGN KEY (from_id) REFERENCES doctor (id)
);
CREATE INDEX FK_PATIENT_FROM_ID ON patient (from_id);
CREATE INDEX FK_PATIENT_TO_ID ON patient (to_id);

insert into doctor(id,email,password,first_name,last_name) values (0,'adnan@khan.com','password','Adnan','Khan');
insert into doctor(id,email,password,first_name,last_name) values (1,'danish@khan.com','password','Danish','Khan');

update doctor set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';


insert into patient(id,created,summary,message,from_id,to_id) values (0,now(),'Test Patient 1','Test message 1',0,1);
insert into patient(id,created,summary,message,from_id,to_id) values (1,now(),'Test Patient 2','Test message 2',1,0);
insert into patient(id,created,summary,message,from_id,to_id) values (2,now(),'Test Patient 3','Test message 3',0,1);


CREATE SEQUENCE patient_id_seq START WITH 3 INCREMENT BY 1;

CREATE SEQUENCE doctor_id_seq START WITH 2 INCREMENT BY 1;


