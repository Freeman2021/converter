CREATE TABLE currencies (
	id bigserial NOT NULL,
	char_code varchar(255) NULL,
	"name" varchar(255) NULL,
	nominal int8 NULL,
	value_id int8 NULL,
	CONSTRAINT char_code_index UNIQUE (char_code),
	CONSTRAINT currencies_pkey PRIMARY KEY (id)
);

ALTER TABLE currencies ADD CONSTRAINT fk22mdx3wx6exa668ndkud2r5ry FOREIGN KEY (value_id) REFERENCES currencies_value(id)