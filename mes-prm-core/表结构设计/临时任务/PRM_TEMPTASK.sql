-- Create table
create table PRM_TEMPTASK
(
  id             CHAR(36) primary key,
  cdeptid        VARCHAR2(36),
  vdeptname      VARCHAR2(50),
  cqcprojectid   VARCHAR2(36),
  vqcprojectcode VARCHAR2(50),
  vqcprojectname VARCHAR2(50),
  cstationid     VARCHAR2(36),
  vstationname   VARCHAR2(50),
  tqctime        TIMESTAMP(6),
  vbillcode      VARCHAR2(50),
  errdescribe    VARCHAR2(50),
  tenantid       VARCHAR2(36),
  sysid          VARCHAR2(50),
  orgid          VARCHAR2(36),
  orgid_name     VARCHAR2(50),
  ts             TIMESTAMP(6) not null,
  dr             INTEGER not null,
  creator        VARCHAR2(36),
  creator_name   VARCHAR2(50),
  creationtime   TIMESTAMP(6),
  modifier       VARCHAR2(36),
  modifier_name  VARCHAR2(50),
  modifiedtime   TIMESTAMP(6)
)
