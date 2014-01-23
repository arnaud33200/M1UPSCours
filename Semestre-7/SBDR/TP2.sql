##################################################################
##################### Exercice I ######################

desc Dict;

select * from Dict where TABLE_NAME = 'ALL_CATALOG';
desc ALL_CATALOG;

 Nom					   NULL ?   Type
 ----------------------------------------- -------- ----------------------------
 OWNER					   NOT NULL VARCHAR2(30)
 TABLE_NAME				   NOT NULL VARCHAR2(30)
 TABLE_TYPE					    VARCHAR2(11)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select * from Dict where TABLE_NAME = 'ALL_USERS';
desc ALL_USERS;

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ALL_USERS
Information about all users of the database

SQL>  Nom					   NULL ?   Type
 ----------------------------------------- -------- ----------------------------
 USERNAME				   NOT NULL VARCHAR2(30)
 USER_ID				   NOT NULL NUMBER
 CREATED				   NOT NULL DATE
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select * from Dict where TABLE_NAME = 'USER_TABLES';
desc USER_TABLES;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Description of the user's own relational tables


SQL>  Nom					   NULL ?   Type
 ----------------------------------------- -------- ----------------------------
 TABLE_NAME				   NOT NULL VARCHAR2(30)
 TABLESPACE_NAME				    VARCHAR2(30)
 CLUSTER_NAME					    VARCHAR2(30)
 IOT_NAME					    VARCHAR2(30)
 STATUS 					    VARCHAR2(8)
 PCT_FREE					    NUMBER
 PCT_USED					    NUMBER
 INI_TRANS					    NUMBER
 MAX_TRANS					    NUMBER
 INITIAL_EXTENT 				    NUMBER
 NEXT_EXTENT					    NUMBER
 MIN_EXTENTS					    NUMBER
 MAX_EXTENTS					    NUMBER
 PCT_INCREASE					    NUMBER
 FREELISTS					    NUMBER
 FREELIST_GROUPS				    NUMBER
 LOGGING					    VARCHAR2(3)
 BACKED_UP					    VARCHAR2(1)
 NUM_ROWS					    NUMBER
 BLOCKS 					    NUMBER
 EMPTY_BLOCKS					    NUMBER
 AVG_SPACE					    NUMBER
 CHAIN_CNT					    NUMBER
 AVG_ROW_LEN					    NUMBER
 AVG_SPACE_FREELIST_BLOCKS			    NUMBER
 NUM_FREELIST_BLOCKS				    NUMBER
 DEGREE 					    VARCHAR2(10)
 INSTANCES					    VARCHAR2(10)
 CACHE						    VARCHAR2(5)
 TABLE_LOCK					    VARCHAR2(8)
 SAMPLE_SIZE					    NUMBER
 LAST_ANALYZED					    DATE
 PARTITIONED					    VARCHAR2(3)
 IOT_TYPE					    VARCHAR2(12)
 TEMPORARY					    VARCHAR2(1)
 SECONDARY					    VARCHAR2(1)
 NESTED 					    VARCHAR2(3)
 BUFFER_POOL					    VARCHAR2(7)
 ROW_MOVEMENT					    VARCHAR2(8)
 GLOBAL_STATS					    VARCHAR2(3)
 USER_STATS					    VARCHAR2(3)
 DURATION					    VARCHAR2(15)
 SKIP_CORRUPT					    VARCHAR2(8)
 MONITORING					    VARCHAR2(3)
 CLUSTER_OWNER					    VARCHAR2(30)
 DEPENDENCIES					    VARCHAR2(8)
 COMPRESSION					    VARCHAR2(8)
 DROPPED					    VARCHAR2(3)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select * from Dict where TABLE_NAME = 'ALL_COL_COMMENTS';
desc ALL_COL_COMMENTS;
--------------------------------------------------------------------------------
ALL_COL_COMMENTS
Comments on columns of accessible tables and views


SQL>  Nom					   NULL ?   Type
 ----------------------------------------- -------- ----------------------------
 OWNER					   NOT NULL VARCHAR2(30)
 TABLE_NAME				   NOT NULL VARCHAR2(30)
 COLUMN_NAME				   NOT NULL VARCHAR2(30)
 COMMENTS					    VARCHAR2(4000)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select * from Dict where TABLE_NAME = 'ALL_CONSTRAINTS';
desc ALL_CONSTRAINTS;
--------------------------------------------------------------------------------
ALL_CONSTRAINTS
Constraint definitions on accessible tables


SQL>  Nom					   NULL ?   Type
 ----------------------------------------- -------- ----------------------------
 OWNER					   NOT NULL VARCHAR2(30)
 CONSTRAINT_NAME			   NOT NULL VARCHAR2(30)
 CONSTRAINT_TYPE				    VARCHAR2(1)
 TABLE_NAME				   NOT NULL VARCHAR2(30)
 SEARCH_CONDITION				    LONG
 R_OWNER					    VARCHAR2(30)
 R_CONSTRAINT_NAME				    VARCHAR2(30)
 DELETE_RULE					    VARCHAR2(9)
 STATUS 					    VARCHAR2(8)
 DEFERRABLE					    VARCHAR2(14)
 DEFERRED					    VARCHAR2(9)
 VALIDATED					    VARCHAR2(13)
 GENERATED					    VARCHAR2(14)
 BAD						    VARCHAR2(3)
 RELY						    VARCHAR2(4)
 LAST_CHANGE					    DATE
 INDEX_OWNER					    VARCHAR2(30)
 INDEX_NAME					    VARCHAR2(30)
 INVALID					    VARCHAR2(7)
 VIEW_RELATED					    VARCHAR2(14)

~~~~~~~~~~~~ 4 ~~~~~~~~~~~~~~~
select distinct object_type FROM ALL_OBJECTS;
OBJECT_TYPE
-------------------
CONSUMER GROUP
SEQUENCE
SCHEDULE
PROCEDURE
OPERATOR
WINDOW
PACKAGE
PROGRAM
JAVA RESOURCE
XML SCHEMA
JOB CLASS
TABLE
SYNONYM
VIEW
FUNCTION
WINDOW GROUP
JAVA CLASS
INDEXTYPE
INDEX
TYPE
EVALUATION CONTEXT

~~~~~~~~~~~~ 5 ~~~~~~~~~~~~~~~~~
select * from Dict where TABLE_NAME = 'USER_USERS';
--------------------------------------------------------------------------------
USER_USERS
Information about the current user

~~~~~~~~~~~~ 6 ~~~~~~~~~~~~~~~~~
desc ALL_CATALOG;
7162 ligne(s) selectionnee(s).

desc USER_CATALOG;

-->

~~~~~~~~~~~~ 7 ~~~~~~~~~~~~~~~~~
select CONSTRAINT_NAME CONSTRAINT_TYPE from USER_CONSTRAINTS where TABLE_NAME = 'CHERCHEUR';
------------------------------
KEY_CHERCHEUR
EQ_CH

##################################################################
##################### Exercice II ######################

~~~~~~~~ 1 ~~~~~~~~~~~~~~
select CODEEQ from CHERCHEUR where CODECH = 2;
    CODEEQ
----------
	 3

Ecoule : 00 :00 :00.01

Plan d'execution
----------------------------------------------------------

--------------------------------------------------------------------------------
--

| Id  | Operation		    | Name	    | Rows  | Bytes | Cost (%CPU
)|

--------------------------------------------------------------------------------
--

|   0 | SELECT STATEMENT	    |		    |	  1 |	  6 |	  1   (0
)|

|   1 |  TABLE ACCESS BY INDEX ROWID| CHERCHEUR     |	  1 |	  6 |	  1   (0
)|

|*  2 |   INDEX UNIQUE SCAN	    | KEY_CHERCHEUR |	  1 |	    |	  0   (0
)|

--------------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   2 - access("CODECH"=2)
Note
-----
   - 'PLAN_TABLE' is old version

~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~ 2 ~~~~~~~~~~~~~

select CODECH from CHERCHEUR C where C.CODEEQ = 2;   
 CODECH

----------
	 1
	 3
	 6
	12
	15

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

--------------------------------------------------------------------
| Id  | Operation	  | Name      | Rows  | Bytes | Cost (%CPU)|
--------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	      |     5 |    30 |     3	(0)|
|*  1 |  TABLE ACCESS FULL| CHERCHEUR |     5 |    30 |     3	(0)|
--------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   1 - filter("C"."CODEEQ"=2)


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~ 3 ~~~~~~~~~~~~~~~~~

select NOMLABO, URL from LABORATOIRE;
NOMLABO 	     URL
-------------------- ---------------
IRIT		     www.irit.fr
LORIM		     www.lorim.fr
LIFL		     www.lifl.fr

Ecoule : 00 :00 :00.01

Plan d'execution
----------------------------------------------------------

----------------------------------------------------------------------
| Id  | Operation	  | Name	| Rows	| Bytes | Cost (%CPU)|
----------------------------------------------------------------------
|   0 | SELECT STATEMENT  |		|     3 |    57 |     3   (0)|
|   1 |  TABLE ACCESS FULL| LABORATOIRE |     3 |    57 |     3   (0)|
----------------------------------------------------------------------


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~ 4 ~~~~~~~~~~~~~~~~

select CODECH, NOMCH from CHERCHEUR C, EQUIPE E where C.CODEEQ = E.CODEEQ and E.NOMEQ = 'SMM';

    CODECH NOMCH
---------- --------------------
	 4 ACEBE
	 5 ALEXANDRE
	11 FARINAS
	14 RENAULDIN

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

---------------------------------------------------------------------
| Id  | Operation	   | Name      | Rows  | Bytes | Cost (%CPU)|
---------------------------------------------------------------------
|   0 | SELECT STATEMENT   |	       |     3 |    66 |     7	(15)|
|*  1 |  HASH JOIN	   |	       |     3 |    66 |     7	(15)|
|*  2 |   TABLE ACCESS FULL| EQUIPE    |     1 |     8 |     3	 (0)|
|   3 |   TABLE ACCESS FULL| CHERCHEUR |    15 |   210 |     3	 (0)|
---------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   1 - access("C"."CODEEQ"="E"."CODEEQ")
   2 - filter("E"."NOMEQ"='SMM')

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~~ 5 ~~~~~~~~~~~~~~~~~

select CODECH, NOMCH from CHERCHEUR C, EQUIPE E, LABORATOIRE l where C.CODEEQ = E.CODEEQ and E.CODELABO = L.CODELABO and L.NOMLABO = 'IRIT';
    CODECH NOMCH;
---------- --------------------
	 2 DEJEANOT
	 4 ACEBE
	 5 ALEXANDRE
	 7 STEMMERA
	 9 ANTOINE
	10 FAURE
	11 FARINAS
	14 RENAULDIN

8 ligne(s) selectionnee(s).

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

--------------------------------------------------------------------------------
--

| Id  | Operation		      | Name	    | Rows  | Bytes | Cost (%CPU
)|

--------------------------------------------------------------------------------
--

|   0 | SELECT STATEMENT	      | 	    |	  5 |	145 |	 10  (20
)|

|*  1 |  HASH JOIN		      | 	    |	  5 |	145 |	 10  (20
)|

|   2 |   MERGE JOIN		      | 	    |	  2 |	 30 |	  6  (17
)|

|*  3 |    TABLE ACCESS BY INDEX ROWID| LABORATOIRE |	  1 |	  9 |	  2   (0
)|

|   4 |     INDEX FULL SCAN	      | KEY_LABO    |	  3 |	    |	  1   (0
)|

|*  5 |    SORT JOIN		      | 	    |	  5 |	 30 |	  4  (25
)|

|   6 |     TABLE ACCESS FULL	      | EQUIPE	    |	  5 |	 30 |	  3   (0
)|

|   7 |   TABLE ACCESS FULL	      | CHERCHEUR   |	 15 |	210 |	  3   (0
)|

--------------------------------------------------------------------------------
--


Predicate Information (identified by operation id):
---------------------------------------------------

   1 - access("C"."CODEEQ"="E"."CODEEQ")
   3 - filter("L"."NOMLABO"='IRIT')
   5 - access("E"."CODELABO"="L"."CODELABO")
       filter("E"."CODELABO"="L"."CODELABO")

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~~~~~ 6 ~~~~~~~~~~~~~~~~~~~

select NOMEQ, count(*) as NBCHERCHEUR from CHERCHEUR C, EQUIPE E where C.CODEEQ = E.CODEEQ group by NOMEQ;
NOMEQ		     NBCHERCHEUR
-------------------- -----------
LSA			       2
FIRM			       5
PSA			       2
SMM			       4
SIMPA			       2

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

----------------------------------------------------------------------
| Id  | Operation	    | Name	| Rows	| Bytes | Cost (%CPU)|
----------------------------------------------------------------------
|   0 | SELECT STATEMENT    |		|     5 |    55 |     8  (25)|
|   1 |  HASH GROUP BY	    |		|     5 |    55 |     8  (25)|
|*  2 |   HASH JOIN	    |		|    15 |   165 |     7  (15)|
|   3 |    TABLE ACCESS FULL| EQUIPE	|     5 |    40 |     3   (0)|
|   4 |    TABLE ACCESS FULL| CHERCHEUR |    15 |    45 |     3   (0)|
----------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   2 - access("C"."CODEEQ"="E"."CODEEQ")'

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~ desactivation de la clé primair
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

alter table CHERCHEUR disable constraint EQ_CH cascade;
alter table CHERCHEUR disable constraint KEY_CHERCHEUR cascade;
alter table EQUIPE disable constraint RESP_EQUIPE cascade;
alter table EQUIPE disable constraint KEY_EQUIPE cascade;
select constraint_name, status from USER_CONSTRAINTS where TABLE_NAME = 'EQUIPE' or table_name = 'CHERCHEUR';

~~~~~~~ 1 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ééé

select CODEEQ from CHERCHEUR where CODECH = 2;
    CODEEQ
----------
	 3

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

--------------------------------------------------------------------
| Id  | Operation	  | Name      | Rows  | Bytes | Cost (%CPU)|
--------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	      |     1 |     6 |     3	(0)|
|*  1 |  TABLE ACCESS FULL| CHERCHEUR |     1 |     6 |     3	(0)|
--------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   1 - filter("CODECH"=2)

~~~~~~~ 2 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select CODECH from CHERCHEUR C where C.CODEEQ = 2;
    CODECH
----------
	 1
	 3
	 6
	12
	15

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

--------------------------------------------------------------------
| Id  | Operation	  | Name      | Rows  | Bytes | Cost (%CPU)|
--------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	      |     5 |    30 |     3	(0)|
|*  1 |  TABLE ACCESS FULL| CHERCHEUR |     5 |    30 |     3	(0)|
--------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   1 - filter("C"."CODEEQ"=2)

 ~~~~~~~~~ 3 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select NOMLABO, URL from LABORATOIRE;
NOMLABO 	     URL
-------------------- ---------------
IRIT		     www.irit.fr
LORIM		     www.lorim.fr
LIFL		     www.lifl.fr

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

----------------------------------------------------------------------
| Id  | Operation	  | Name	| Rows	| Bytes | Cost (%CPU)|
----------------------------------------------------------------------
|   0 | SELECT STATEMENT  |		|     3 |    57 |     3   (0)|
|   1 |  TABLE ACCESS FULL| LABORATOIRE |     3 |    57 |     3   (0)|
----------------------------------------------------------------------

 ~~~~~~~~~~~~ 4 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select CODECH, NOMCH from CHERCHEUR C, EQUIPE E where C.CODEEQ = E.CODEEQ and E.NOMEQ = 'SMM';
    CODECH NOMCH
---------- --------------------
	 4 ACEBE
	 5 ALEXANDRE
	11 FARINAS
	14 RENAULDIN

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

---------------------------------------------------------------------
| Id  | Operation	   | Name      | Rows  | Bytes | Cost (%CPU)|
---------------------------------------------------------------------
|   0 | SELECT STATEMENT   |	       |     3 |    66 |     7	(15)|
|*  1 |  HASH JOIN	   |	       |     3 |    66 |     7	(15)|
|*  2 |   TABLE ACCESS FULL| EQUIPE    |     1 |     8 |     3	 (0)|
|   3 |   TABLE ACCESS FULL| CHERCHEUR |    15 |   210 |     3	 (0)|
---------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   1 - access("C"."CODEEQ"="E"."CODEEQ")
   2 - filter("E"."NOMEQ"='SMM')

~~~~~~~~~~~~~~ 5 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select CODECH, NOMCH from CHERCHEUR C, EQUIPE E, LABORATOIRE l where C.CODEEQ = E.CODEEQ and E.CODELABO = L.CODELABO and L.NOMLABO = 'IRIT';
    CODECH NOMCH
---------- --------------------
	 2 DEJEANOT
	 4 ACEBE
	 5 ALEXANDRE
	 7 STEMMERA
	 9 ANTOINE
	10 FAURE
	11 FARINAS
	14 RENAULDIN

8 ligne(s) selectionnee(s).

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

--------------------------------------------------------------------------------
--

| Id  | Operation		      | Name	    | Rows  | Bytes | Cost (%CPU
)|

--------------------------------------------------------------------------------
--

|   0 | SELECT STATEMENT	      | 	    |	  5 |	145 |	 10  (20
)|

|*  1 |  HASH JOIN		      | 	    |	  5 |	145 |	 10  (20
)|

|   2 |   MERGE JOIN		      | 	    |	  2 |	 30 |	  6  (17
)|

|*  3 |    TABLE ACCESS BY INDEX ROWID| LABORATOIRE |	  1 |	  9 |	  2   (0
)|

|   4 |     INDEX FULL SCAN	      | KEY_LABO    |	  3 |	    |	  1   (0
)|

|*  5 |    SORT JOIN		      | 	    |	  5 |	 30 |	  4  (25
)|

|   6 |     TABLE ACCESS FULL	      | EQUIPE	    |	  5 |	 30 |	  3   (0
)|

|   7 |   TABLE ACCESS FULL	      | CHERCHEUR   |	 15 |	210 |	  3   (0
)|

--------------------------------------------------------------------------------
--


Predicate Information (identified by operation id):
---------------------------------------------------

   1 - access("C"."CODEEQ"="E"."CODEEQ")
   3 - filter("L"."NOMLABO"='IRIT')
   5 - access("E"."CODELABO"="L"."CODELABO")
       filter("E"."CODELABO"="L"."CODELABO")'

~~~~~~~~~~ 6 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

select NOMEQ, count(*) as NBCHERCHEUR from CHERCHEUR C, EQUIPE E where C.CODEEQ = E.CODEEQ group by NOMEQ;

NOMEQ		     NBCHERCHEUR
-------------------- -----------
LSA			       2
FIRM			       5
PSA			       2
SMM			       4
SIMPA			       2

Ecoule : 00 :00 :00.00

Plan d'execution
----------------------------------------------------------

----------------------------------------------------------------------
| Id  | Operation	    | Name	| Rows	| Bytes | Cost (%CPU)|
----------------------------------------------------------------------
|   0 | SELECT STATEMENT    |		|     5 |    55 |     8  (25)|
|   1 |  HASH GROUP BY	    |		|     5 |    55 |     8  (25)|
|*  2 |   HASH JOIN	    |		|    15 |   165 |     7  (15)|
|   3 |    TABLE ACCESS FULL| EQUIPE	|     5 |    40 |     3   (0)|
|   4 |    TABLE ACCESS FULL| CHERCHEUR |    15 |    45 |     3   (0)|
----------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   2 - access("C"."CODEEQ"="E"."CODEEQ")
