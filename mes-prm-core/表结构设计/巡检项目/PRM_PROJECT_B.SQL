CREATE TABLE "PRM_PROJECT_B" 
   (	"CPRJCONTENT" VARCHAR2(100), 
	"CJUDSTD" VARCHAR2(1000), 
	"FVALUETYPE" NUMBER(*,0), 
	"BAUTOCOLLECT" CHAR(1), 
	"CDATALABEL" VARCHAR2(36), 
	"CDATALABEL_NAME" VARCHAR2(50), 
	"BREQUIRED" CHAR(1), 
	"CPARENTID" VARCHAR2(36), 
	"CROWNO" VARCHAR2(20), 
	"ID" CHAR(36) NOT NULL ENABLE, 
	"TENANTID" VARCHAR2(36), 
	"SYSID" VARCHAR2(50), 
	"ORGID" VARCHAR2(36), 
	"ORGID_NAME" VARCHAR2(50), 
	"TS" TIMESTAMP (6), 
	"DR" NUMBER(*,0), 
	"CREATOR" VARCHAR2(36), 
	"CREATIONTIME" TIMESTAMP (6), 
	"MODIFIER" VARCHAR2(36), 
	"MODIFIEDTIME" TIMESTAMP (6), 
	"CREATOR_NAME" VARCHAR2(50), 
	"MODIFIER_NAME" VARCHAR2(50), 
	 PRIMARY KEY ("ID")
);