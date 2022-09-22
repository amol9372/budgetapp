-- NOTE: the code below contains the SQL for the object itself
-- as well as for its dependencies or children (if applicable).
--
-- This feature is only a convenience in order to allow you to test
-- the whole object's SQL definition at once.
--
-- When exporting or generating the SQL for the whole database model
-- all objects will be placed at their original positions.


-- object: budget_tracker | type: SCHEMA --
DROP SCHEMA IF EXISTS budget_tracker CASCADE;
CREATE SCHEMA budget_tracker;
-- ddl-end --
-- ALTER SCHEMA budget_tracker OWNER TO postgres;
-- ddl-end --

-- object: budget_tracker.users | type: TABLE --
-- DROP TABLE IF EXISTS budget_tracker.users CASCADE;
CREATE TABLE budget_tracker.users
(
    id           integer      NOT NULL GENERATED ALWAYS AS IDENTITY,
    email        varchar(100) NOT NULL,
    fname        varchar(100),
    lname        varchar(100) NOT NULL,
    dob          date,
    mobile       varchar(50),
    "firebaseId" integer,
    last_login   timestamp,
    registered   boolean      NOT NULL,
    created_on   timestamp,
    updated_on   timestamp,
    CONSTRAINT email_uq UNIQUE (email),
    CONSTRAINT user_id_pk PRIMARY KEY (id)
);
-- ddl-end --
-- ALTER TABLE budget_tracker.users OWNER TO postgres;
-- ddl-end --

-- object: budget_tracker."group" | type: TABLE --
-- DROP TABLE IF EXISTS budget_tracker."group" CASCADE;
CREATE TABLE budget_tracker.groups
(
    id         integer  NOT NULL GENERATED ALWAYS AS IDENTITY,
    name       varchar  NOT NULL,
    members    smallint NOT NULL GENERATED ALWAYS AS IDENTITY,
    budget     smallint NOT NULL,
    created_by varchar  NOT NULL,
    CONSTRAINT group_id_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE budget_tracker."group" OWNER TO postgres;
-- ddl-end --

-- object: created_by_fk | type: CONSTRAINT --
ALTER TABLE budget_tracker.groups DROP CONSTRAINT IF EXISTS created_by_fk CASCADE;

ALTER TABLE budget_tracker.groups
    ADD CONSTRAINT created_by_fk FOREIGN KEY (created_by) REFERENCES budget_tracker.users (email);
-- MATCH SIMPLE ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: budget_tracker.category | type: TABLE --
-- DROP TABLE IF EXISTS budget_tracker.category CASCADE;
CREATE TABLE budget_tracker.category
(
    id         integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    name       varchar NOT NULL,
    created_on timestamp,
    CONSTRAINT category_id_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE budget_tracker.category OWNER TO postgres;
-- ddl-end --

-- object: budget_tracker.expenses | type: TABLE --
DROP TABLE IF EXISTS budget_tracker.expenses CASCADE;

CREATE TABLE budget_tracker.expenses
(
    id           integer   NOT NULL GENERATED ALWAYS AS IDENTITY,
    cost         float     NOT NULL,
    name         varchar   NOT NULL,
    category     varchar   NOT NULL,
    created_by   varchar   NOT NULL,
    paid_by      varchar   NOT NULL,
    stakeholders json,
    group_id     integer,
    created_on   timestamp NOT NULL,
    updated_on   timestamp,
    CONSTRAINT expense_id_pk PRIMARY KEY (id)
);
-- ddl-end --
-- ALTER TABLE budget_tracker.expenses OWNER TO postgres;
-- ddl-end --

-- object: category_id | type: CONSTRAINT --
--ALTER TABLE budget_tracker.expenses DROP CONSTRAINT IF EXISTS category_id_fk CASCADE;
--
--ALTER TABLE budget_tracker.expenses
--    ADD CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES budget_tracker.category (id);



-- MATCH SIMPLE ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: group_id | type: CONSTRAINT --
ALTER TABLE budget_tracker.expenses DROP CONSTRAINT IF EXISTS group_id_fk CASCADE;



ALTER TABLE budget_tracker.expenses
    ADD CONSTRAINT group_id_fk FOREIGN KEY (group_id)
        REFERENCES budget_tracker.groups (id);


-- MATCH SIMPLE ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: budget_tracker.user_group | type: TABLE --
DROP TABLE IF EXISTS budget_tracker.user_group CASCADE;

CREATE TABLE budget_tracker.user_group
(
    id       smallint NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id  smallint,
    group_id smallint,
    CONSTRAINT user_group_id_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE budget_tracker.user_group OWNER TO postgres;
-- ddl-end --

-- object: user_id_fk | type: CONSTRAINT --
ALTER TABLE budget_tracker.user_group DROP CONSTRAINT IF EXISTS user_id_fk CASCADE;

ALTER TABLE budget_tracker.user_group
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES budget_tracker.users (id);
-- MATCH SIMPLE ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: group_id_fk | type: CONSTRAINT --
ALTER TABLE budget_tracker.user_group DROP CONSTRAINT IF EXISTS group_id_fk CASCADE;

ALTER TABLE budget_tracker.user_group
    ADD CONSTRAINT user_group_id_fk FOREIGN KEY (group_id)
        REFERENCES budget_tracker.groups (id);
-- MATCH SIMPLE ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

