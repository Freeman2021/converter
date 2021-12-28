CREATE TABLE currency (
	id bigserial NOT NULL,
	char_code varchar(255) NULL,
	"name" varchar(255) NULL,
	nominal int8 NULL,
	currency_value_id int8 NULL,
	CONSTRAINT char_code_index UNIQUE (char_code),
	CONSTRAINT currency_pkey PRIMARY KEY (id)
);

ALTER TABLE currency ADD CONSTRAINT fk1fvmomryokxefeetw4etl96f1 FOREIGN KEY (currency_value_id) REFERENCES currency_value(id)