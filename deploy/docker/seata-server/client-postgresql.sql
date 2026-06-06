-- -------------------------------- AT  --------------------------------
CREATE TABLE IF NOT EXISTS public.undo_log
(
    id            SERIAL       NOT NULL,
    branch_id     BIGINT       NOT NULL,
    xid           VARCHAR(128) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info BYTEA        NOT NULL,
    log_status    INT          NOT NULL,
    log_created   TIMESTAMP(0) NOT NULL,
    log_modified  TIMESTAMP(0) NOT NULL,
    CONSTRAINT pk_undo_log PRIMARY KEY (id),
    CONSTRAINT ux_undo_log UNIQUE (xid, branch_id)
);
CREATE INDEX ix_log_created ON undo_log(log_created);

COMMENT ON TABLE public.undo_log IS 'AT transaction mode undo table';
COMMENT ON COLUMN public.undo_log.branch_id IS 'branch transaction id';
COMMENT ON COLUMN public.undo_log.xid IS 'global transaction id';
COMMENT ON COLUMN public.undo_log.context IS 'undo_log context,such as serialization';
COMMENT ON COLUMN public.undo_log.rollback_info IS 'rollback info';
COMMENT ON COLUMN public.undo_log.log_status IS '0:normal status,1:defense status';
COMMENT ON COLUMN public.undo_log.log_created IS 'create datetime';
COMMENT ON COLUMN public.undo_log.log_modified IS 'modify datetime';

CREATE SEQUENCE IF NOT EXISTS undo_log_id_seq INCREMENT BY 1 MINVALUE 1 ;


-- -------------------------------- tcc  --------------------------------
CREATE TABLE IF NOT EXISTS public.tcc_fence_log
(
    xid              VARCHAR(128)  NOT NULL,
    branch_id        BIGINT        NOT NULL,
    action_name      VARCHAR(64)   NOT NULL,
    status           SMALLINT      NOT NULL,
    gmt_create       TIMESTAMP(3)  NOT NULL,
    gmt_modified     TIMESTAMP(3)  NOT NULL,
    CONSTRAINT pk_tcc_fence_log PRIMARY KEY (xid, branch_id)
);
CREATE INDEX idx_gmt_modified ON public.tcc_fence_log (gmt_modified);
CREATE INDEX idx_status ON public.tcc_fence_log (status);

-- -------------------------------- sage  --------------------------------
CREATE TABLE IF NOT EXISTS public.seata_state_machine_def
(
    id               VARCHAR(32)  NOT NULL,
    name             VARCHAR(128) NOT NULL,
    tenant_id        VARCHAR(32)  NOT NULL,
    app_name         VARCHAR(32)  NOT NULL,
    type             VARCHAR(20),
    comment_         VARCHAR(255),
    ver              VARCHAR(16)  NOT NULL,
    gmt_create       TIMESTAMP(3) NOT NULL,
    status           VARCHAR(2)   NOT NULL,
    content          TEXT,
    recover_strategy VARCHAR(16),
    CONSTRAINT pk_seata_state_machine_def PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.seata_state_machine_inst
(
    id                  VARCHAR(128)                NOT NULL,
    machine_id          VARCHAR(32)                NOT NULL,
    tenant_id           VARCHAR(32)                NOT NULL,
    parent_id           VARCHAR(128),
    gmt_started         TIMESTAMP(3)               NOT NULL,
    business_key        VARCHAR(48),
    start_params        TEXT,
    gmt_end             TIMESTAMP(3) DEFAULT now(),
    excep               BYTEA,
    end_params          TEXT,
    status              VARCHAR(2),
    compensation_status VARCHAR(2),
    is_running          BOOLEAN,
    gmt_updated         TIMESTAMP(3) DEFAULT now() NOT NULL,
    CONSTRAINT pk_seata_state_machine_inst PRIMARY KEY (id),
    CONSTRAINT unikey_buz_tenant UNIQUE (business_key, tenant_id)
)
;
CREATE TABLE IF NOT EXISTS public.seata_state_inst
(
    id                       VARCHAR(48)  NOT NULL,
    machine_inst_id          VARCHAR(128)  NOT NULL,
    name                     VARCHAR(128) NOT NULL,
    type                     VARCHAR(20),
    service_name             VARCHAR(128),
    service_method           VARCHAR(128),
    service_type             VARCHAR(16),
    business_key             VARCHAR(48),
    state_id_compensated_for VARCHAR(50),
    state_id_retried_for     VARCHAR(50),
    gmt_started              TIMESTAMP(3) NOT NULL,
    is_for_update            BOOLEAN,
    input_params             TEXT,
    output_params            TEXT,
    status                   VARCHAR(2)   NOT NULL,
    excep                    BYTEA,
    gmt_updated              TIMESTAMP(3) DEFAULT now(),
    gmt_end                  TIMESTAMP(3) DEFAULT now(),
    CONSTRAINT pk_seata_state_inst PRIMARY KEY (id, machine_inst_id)
);