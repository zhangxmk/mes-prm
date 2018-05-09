-- Create table
create table PRM_TEMPTASK_B
(
  id            CHAR(36) primary key,
  vrowno        VARCHAR2(36),
  vprjcontent   VARCHAR2(100),
  vprjcriterion VARCHAR2(1000),
  vqcvalue      VARCHAR2(50),
  pk_temptask   CHAR(36) not null,
  isqualified   INTEGER,
  rdbvalue	 	VARCHAR2(300),
  tenantid      VARCHAR2(36),
  sysid         VARCHAR2(50),
  orgid         VARCHAR2(36),
  orgid_name    VARCHAR2(50),
  ts            TIMESTAMP(6),
  dr            INTEGER,
  creator       VARCHAR2(36),
  creator_name  VARCHAR2(50),
  creationtime  TIMESTAMP(6),
  modifier      VARCHAR2(36),
  modifier_name VARCHAR2(50),
  modifiedtime  TIMESTAMP(6)
)
