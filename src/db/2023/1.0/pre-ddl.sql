-- User
CREATE TABLE "user"
(
    id            BIGINT       NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    version       INTEGER      NOT NULL,
    username      VARCHAR(255) NOT NULL,
    password      VARCHAR(255),
    salt          VARCHAR(255),
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    user_type     VARCHAR(255) NOT NULL,
    status        VARCHAR(255) NOT NULL,
    created_by_id BIGINT,
    updated_by_id BIGINT,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE "user"
    ADD CONSTRAINT uk_user_username UNIQUE (username);

ALTER TABLE "user"
    ADD CONSTRAINT fk_user_on_created_by FOREIGN KEY (created_by_id) REFERENCES "user" (id);

ALTER TABLE "user"
    ADD CONSTRAINT fk_user_on_updated_by FOREIGN KEY (updated_by_id) REFERENCES "user" (id);

-- Doctor

CREATE TABLE doctor
(
    id            BIGINT       NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    version       INTEGER      NOT NULL,
    education     VARCHAR(255) NOT NULL,
    specialities  VARCHAR(255) NOT NULL,
    status        VARCHAR(255),
    user_id       BIGINT       NOT NULL,
    created_by_id BIGINT,
    updated_by_id BIGINT,
    CONSTRAINT pk_doctor PRIMARY KEY (id)
);

ALTER TABLE doctor
    ADD CONSTRAINT fk_doctor_on_created_by FOREIGN KEY (created_by_id) REFERENCES "user" (id);

ALTER TABLE doctor
    ADD CONSTRAINT fk_doctor_on_updated_by FOREIGN KEY (updated_by_id) REFERENCES "user" (id);

ALTER TABLE doctor
    ADD CONSTRAINT fk_doctor_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);

-- Assistant

CREATE TABLE assistant
(
    id            BIGINT  NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    version       INTEGER NOT NULL,
    status        VARCHAR(255),
    user_id       BIGINT  NOT NULL,
    doctor_id     BIGINT  NOT NULL,
    created_by_id BIGINT,
    updated_by_id BIGINT,
    CONSTRAINT pk_assistant PRIMARY KEY (id)
);

ALTER TABLE assistant
    ADD CONSTRAINT fk_assistant_on_created_by FOREIGN KEY (created_by_id) REFERENCES "user" (id);

ALTER TABLE assistant
    ADD CONSTRAINT fk_assistant_on_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id);

ALTER TABLE assistant
    ADD CONSTRAINT fk_assistant_on_updated_by FOREIGN KEY (updated_by_id) REFERENCES "user" (id);

ALTER TABLE assistant
    ADD CONSTRAINT fk_assistant_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);

-- Patient

CREATE TABLE patient
(
    id            BIGINT                      NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    version       INTEGER                     NOT NULL,
    patient_id    VARCHAR(255),
    mobile_number VARCHAR(255)                NOT NULL,
    first_name    VARCHAR(255)                NOT NULL,
    last_name     VARCHAR(255)                NOT NULL,
    date_of_birth TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    blood_group   VARCHAR(255)                NOT NULL,
    status        VARCHAR(255)                NOT NULL,
    created_by_id BIGINT,
    updated_by_id BIGINT,
    CONSTRAINT pk_patient PRIMARY KEY (id)
);

ALTER TABLE patient
    ADD CONSTRAINT fk_patient_on_created_by FOREIGN KEY (created_by_id) REFERENCES "user" (id);

ALTER TABLE patient
    ADD CONSTRAINT fk_patient_on_updated_by FOREIGN KEY (updated_by_id) REFERENCES "user" (id);

--HealthMetric

CREATE TABLE health_metric
(
    id          BIGINT  NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    version     INTEGER NOT NULL,
    height      FLOAT   NOT NULL,
    weight      FLOAT   NOT NULL,
    temperature FLOAT   NOT NULL,
    bphigh      INTEGER NOT NULL,
    bp_low      INTEGER NOT NULL,
    hearth_rate INTEGER NOT NULL,
    sugar_level INTEGER NOT NULL,
    CONSTRAINT pk_health_metric PRIMARY KEY (id)
);

-- Prescription

CREATE TABLE prescription
(
    id               BIGINT  NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    version          INTEGER NOT NULL,
    medicines        VARCHAR(255),
    tests            VARCHAR(255),
    health_metric_id BIGINT,
    doctor_id        BIGINT  NOT NULL,
    patient_id       BIGINT  NOT NULL,
    created_by_id    BIGINT,
    updated_by_id    BIGINT,
    CONSTRAINT pk_prescription PRIMARY KEY (id)
);

ALTER TABLE prescription
    ADD CONSTRAINT fk_prescription_on_created_by FOREIGN KEY (created_by_id) REFERENCES "user" (id);

ALTER TABLE prescription
    ADD CONSTRAINT fk_prescription_on_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id);

ALTER TABLE prescription
    ADD CONSTRAINT fk_prescription_on_health_metric FOREIGN KEY (health_metric_id) REFERENCES health_metric (id);

ALTER TABLE prescription
    ADD CONSTRAINT fk_prescription_on_patient FOREIGN KEY (patient_id) REFERENCES patient (id);

ALTER TABLE prescription
    ADD CONSTRAINT fk_prescription_on_updated_by FOREIGN KEY (updated_by_id) REFERENCES "user" (id);

-- Attachment
CREATE TABLE attachment
(
    id              BIGINT       NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    version         INTEGER      NOT NULL,
    name            VARCHAR(255) NOT NULL,
    size            BIGINT       NOT NULL,
    type            VARCHAR(255) NOT NULL,
    data            BYTEA        NOT NULL,
    prescription_id BIGINT       NOT NULL,
    CONSTRAINT pk_attachment PRIMARY KEY (id)
);

ALTER TABLE attachment
    ADD CONSTRAINT fk_attachment_on_prescription FOREIGN KEY (prescription_id) REFERENCES prescription (id);