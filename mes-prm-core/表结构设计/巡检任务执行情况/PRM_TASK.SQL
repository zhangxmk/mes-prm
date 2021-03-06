CREATE TABLE "PRM_TASK" 
   (	"TASK_NO" VARCHAR2(36), 
	"POSTID" VARCHAR2(36), 
	"POSTID_NAME" VARCHAR2(36), 
	"PLANID" VARCHAR2(36), 
	"PLANID_NAME" VARCHAR2(50), 
	"BILLSTATUS" NUMBER DEFAULT 1, 
	"ID" CHAR(36) NOT NULL ENABLE, 
	"TEAM" VARCHAR2(36), 
	"TEAM_NAME" VARCHAR2(50), 
	"SHIFT" VARCHAR2(36), 
	"SHIFT_NAME" VARCHAR2(50), 
	"DEPTID" VARCHAR2(36), 
	"DEPTID_NAME" VARCHAR2(50), 
	"TENANTID" VARCHAR2(36), 
	"SYSID" VARCHAR2(50), 
	"ORGID" VARCHAR2(36), 
	"ORGID_NAME" VARCHAR2(50), 
	"TS" TIMESTAMP (6) NOT NULL ENABLE, 
	"DR" NUMBER DEFAULT 0 NOT NULL ENABLE, 
	"CREATOR" VARCHAR2(36), 
	"CREATIONTIME" TIMESTAMP (6), 
	"MODIFIER" VARCHAR2(36), 
	"MODIFIEDTIME" TIMESTAMP (6), 
	"CREATOR_NAME" VARCHAR2(50), 
	"MODIFIER_NAME" VARCHAR2(50), 
	"ASSIGN_TIME" TIMESTAMP (6), 
	 PRIMARY KEY ("ID")
);