--drop table if exists resource_log;
--drop table if exists resource;
--drop table if exists service;
--drop table if exists device;
--drop table if exists customer;
--drop table if exists resource_type;
--drop table if exists model;
--drop table if exists company;

create table if not exists customer(
    cust_id			VARCHAR(15) primary key,
    cust_created_at	TIMESTAMP default now(),
    cust_name	    VARCHAR(10) not null,
    cust_addr		VARCHAR(30),
    phone_num		VARCHAR(11),
    cust_use_yn	    VARCHAR(1) default 'y' not null
);

create table if not exists service(
    svc_seq			SERIAL not null,
    cust_id			VARCHAR(15) not null references customer(cust_id),
    svc_created_at 	TIMESTAMP default now(),
    svc_use_yn		VARCHAR(1) default 'y' not null,
    primary key (svc_seq, cust_id)
);

create table if not exists company(
    comp_id		VARCHAR(20) primary key,
    external_id VARCHAR(30),
    comp_use_yn	VARCHAR(1) default 'y' not null
);

create table if not exists model(
    model_id        VARCHAR(30)	primary key,
    model_name	    VARCHAR(30),
    model_code	    VARCHAR(4),
    comp_id	        VARCHAR(20) references company(comp_id),
    model_use_yn 	VARCHAR(1) default 'y' not null
);

create table if not exists resource_type(
    rsc_type_seq    SERIAL primary key,
    rsc_type_group	VARCHAR(20),
    rsc_type_code	VARCHAR(20),
    value_type		VARCHAR(10),
    rsc_type_name	VARCHAR(20),
    model_id		VARCHAR(30) references model(model_id),
    rsc_type_use_yn	VARCHAR(1) default 'y' not null
);

create table if not exists device(
    dvc_seq     SERIAL primary key,
    model_id	VARCHAR(30) not null references model(model_id),
    dvc_name    VARCHAR(30),
    cust_id		VARCHAR(15) not null references customer(cust_id),
    dvc_use_yn	VARCHAR(1) default 'y' not null
    -- primary key (dvc_seq, model_id, cust_id)
);

create table if not exists resource(
    rsc_seq		    SERIAL primary key,
    dvc_seq		    SERIAL not null references device(dvc_seq),
    rsc_type_seq	SERIAL not null references resource_type(rsc_type_seq),
    rsc_value		VARCHAR(20),
    rsc_use_yn		VARCHAR(1) default 'y' not null
    -- primary key (rsc_seq, dvc_seq, rsc_type_seq)
);

create table if not exists resource_log(
    rsc_log_seq		    SERIAL primary key,
    rsc_log_created_at	TIMESTAMP  default now(),
    rsc_log_value		VARCHAR(20),
    rsc_seq			    SERIAL references resource(rsc_seq) on delete set null,
    rsc_log_use_yn		VARCHAR(1) default 'y' not null
);