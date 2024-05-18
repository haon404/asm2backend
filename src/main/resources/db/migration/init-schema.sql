
CREATE TABLE category
(
    id   BINARY(16)   NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE category_recruitments
(
    category_id     BINARY(16) NOT NULL,
    recruitments_id BINARY(16) NOT NULL,
    CONSTRAINT pk_category_recruitments PRIMARY KEY (category_id, recruitments_id)
);

CREATE TABLE company
(
    id   BINARY(16)   NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE recruitment
(
    id         BINARY(16)   NOT NULL,
    title      VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    company_id BINARY(16)   NOT NULL,
    CONSTRAINT pk_recruitment PRIMARY KEY (id)
);

CREATE TABLE recruitment_apply
(
    id             BINARY(16) NOT NULL,
    user_id        BINARY(16) NOT NULL,
    recruitment_id BINARY(16) NOT NULL,
    CONSTRAINT pk_recruitment_apply PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id        BINARY(16)   NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BINARY(16)   NOT NULL,
    role_id  BINARY(16)   NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE `role`
    ADD CONSTRAINT uc_role_role_name UNIQUE (role_name);

ALTER TABLE user
    ADD CONSTRAINT uc_user_role UNIQUE (role_id);

ALTER TABLE recruitment_apply
    ADD CONSTRAINT FK_RECRUITMENT_APPLY_ON_RECRUITMENT FOREIGN KEY (recruitment_id) REFERENCES recruitment (id);

ALTER TABLE recruitment_apply
    ADD CONSTRAINT FK_RECRUITMENT_APPLY_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE recruitment
    ADD CONSTRAINT FK_RECRUITMENT_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ROLE FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE category_recruitments
    ADD CONSTRAINT fk_catrec_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_recruitments
    ADD CONSTRAINT fk_catrec_on_recruitment FOREIGN KEY (recruitments_id) REFERENCES recruitment (id);