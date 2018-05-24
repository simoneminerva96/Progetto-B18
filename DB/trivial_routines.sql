CREATE DATABASE  IF NOT EXISTS `trivial` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `trivial`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: trivial
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping events for database 'trivial'
--

--
-- Dumping routines for database 'trivial'
--
/*!50003 DROP FUNCTION IF EXISTS `ADD_PLAYER1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `ADD_PLAYER1`(codice varbinary(100), pass varbinary(100)) RETURNS tinyint(1)
BEGIN
	declare choose boolean;
    
    if (exists (select * from players where id = codice )) then
    
		set choose = false;
    
    else 
		
        set choose = true;
        insert into players(ID, PASSWORD)
		values (codice, pass);
        
        insert into players_visibili(ID, PASS)
		values (codice, pass);
        
	end if;
    
    
RETURN (choose);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `CHOOSE_ANSWER1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `CHOOSE_ANSWER1`(codice varchar(5)) RETURNS varchar(300) CHARSET utf8
BEGIN
	declare choose varchar(300);
    
	set choose = (select risposta from risposte where id_domanda = codice and id_risposta = 1);
	
RETURN (choose);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `CHOOSE_ANSWER2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `CHOOSE_ANSWER2`(codice varchar(5)) RETURNS varchar(300) CHARSET utf8
BEGIN
	declare choose varchar(300);
    
	set choose = (select risposta from risposte where id_domanda = codice and id_risposta = 2);
	
RETURN (choose);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `CHOOSE_ANSWER3` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `CHOOSE_ANSWER3`(codice varchar(5)) RETURNS varchar(300) CHARSET utf8
BEGIN
	declare choose varchar(300);
    
	set choose = (select risposta from risposte where id_domanda = codice and id_risposta = 3);
	
RETURN (choose);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `CHOOSE_ANSWER4` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `CHOOSE_ANSWER4`(codice varchar(5)) RETURNS varchar(300) CHARSET utf8
BEGIN
	declare choose varchar(300);
    
	set choose = (select risposta from risposte where id_domanda = codice and id_risposta = 4);
	
RETURN (choose);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `CHOOSE_IDQUEST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `CHOOSE_IDQUEST`(codice varchar(5)) RETURNS varchar(300) CHARSET utf8
BEGIN
	declare choose varchar(300);
    
	set choose = (select id_quest from domande where substring(id_quest, 1, 3) = codice  order by rand() limit 1);
	
RETURN (choose);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `CHOOSE_QUEST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `CHOOSE_QUEST`(codice varchar(5)) RETURNS varchar(300) CHARSET utf8
BEGIN
	declare choose varchar(300);
    
	set choose = (select descrizione from domande where id_quest = codice);
	
RETURN (choose);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `PLAYER_EXIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `PLAYER_EXIST`(codice varbinary(100), pass varbinary(100)) RETURNS varchar(300) CHARSET utf8
BEGIN
	declare choose varchar(300);
    
    if (not exists (select * from players where id = codice and password = pass )) then
    
		set choose = "Nome giocatore errato/inesistente o Password errata";
    
    else 
		
        set choose = "Benvenuto!!";
        
	end if;
    
    
RETURN (choose);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ADD_PLAYER` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ADD_PLAYER`(inout IDNAME varchar(100),
														 inout PW varchar(100))
BEGIN
	
    declare CONT int;
    declare exit handler for 1062 select CONCAT('Errore il nome (',IDNAME,') é già stato registrato') AS msg;
    
    
    set CONT = (select count(*) from players where ID = IDNAME);
    
    
	insert into players(ID, PASSWORD)
	values (IDNAME, PW);
    
	
    
    commit;
     
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ADD_QUEST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ADD_QUEST`(inout pIDQUEST varchar(5) , 
														inout pQUEST varchar(300), 
                                                        inout pRISP1 varchar(300), 
                                                        inout pRISP2 varchar(300), 
                                                        inout pRISP3 varchar(300), 
                                                        inout pRISP4 varchar(300), 
                                                        inout pVALUE1 varchar(1), 
                                                        inout pVALUE2 varchar(1), 
                                                        inout pVALUE3 varchar(1), 
                                                        inout pVALUE4 varchar(1))
BEGIN

    declare pIDANS int;
    declare pCOD_ANS int;
    declare pIDQUEST2 int;
    
    set pCOD_ANS = (select coalesce(max(COD_ANS)+1, 1) from risposte);
    
    set pIDQUEST2 = (select coalesce(count(*) + 1, 1) from domande where substring(ID_QUEST, 1, 3) = pIDQUEST );
    
    set pIDQUEST = upper(pIDQUEST);
    set pIDQUEST = concat( pIDQUEST, pIDQUEST2);
    
    
    insert into domande(ID_QUEST, DESCRIZIONE)
    values (pIDQUEST, pQUEST);
    
    set pIDANS = 1;
    set pVALUE1 = upper(pVALUE1);
    set pVALUE2 = upper(pVALUE2);
    set pVALUE3 = upper(pVALUE3);
    set pVALUE4 = upper(pVALUE4);
    
    insert into risposte(COD_ANS, ID_DOMANDA, ID_RISPOSTA, RISPOSTA, value)
    value (pCOD_ANS, pIDQUEST, pIDANS, pRISP1, pVALUE1);
    
    set pIDANS = 2;
    set pCOD_ANS = pCOD_ANS + 1 ;
    insert into risposte(COD_ANS, ID_DOMANDA, ID_RISPOSTA, RISPOSTA, value)
    value (pCOD_ANS, pIDQUEST, pIDANS, pRISP2, pVALUE2);
    
    set pIDANS = 3;
    set pCOD_ANS = pCOD_ANS + 1 ;
    insert into risposte(COD_ANS, ID_DOMANDA, ID_RISPOSTA, RISPOSTA, value)
    value (pCOD_ANS, pIDQUEST, pIDANS, pRISP3, pVALUE3);
    
    set pIDANS = 4;
    set pCOD_ANS = pCOD_ANS + 1 ;
    insert into risposte(COD_ANS, ID_DOMANDA, ID_RISPOSTA, RISPOSTA, value)
    value (pCOD_ANS, pIDQUEST, pIDANS, pRISP4, pVALUE4);
    
    
    commit;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ADD_QUESTFIN` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ADD_QUESTFIN`(inout pIDQUEST varchar(5) , 
														inout pQUEST varchar(300), 
                                                        inout pRISP1 varchar(300), 
                                                        inout pRISP2 varchar(300), 
                                                        inout pRISP3 varchar(300), 
                                                        inout pRISP4 varchar(300), 
                                                        inout pVALUE1 varchar(1), 
                                                        inout pVALUE2 varchar(1), 
                                                        inout pVALUE3 varchar(1), 
                                                        inout pVALUE4 varchar(1))
BEGIN

    declare pIDANS int;
    declare pCOD_ANS int;
    declare pIDQUEST2 int;
    
    set pCOD_ANS = (select coalesce(max(COD_ANS)+1, 1) from risposte);
    
    set pIDQUEST2 = (select coalesce(count(*) + 1, 1) from domande where substring(ID_QUEST, 1, 4) = pIDQUEST );
    
    set pIDQUEST = upper(pIDQUEST);
    set pIDQUEST = concat( pIDQUEST, pIDQUEST2);
    
    
    insert into domande(ID_QUEST, DESCRIZIONE)
    values (pIDQUEST, pQUEST);
    
    set pIDANS = 1;
    set pVALUE1 = upper(pVALUE1);
    set pVALUE2 = upper(pVALUE2);
    set pVALUE3 = upper(pVALUE3);
    set pVALUE4 = upper(pVALUE4);
    
    insert into risposte(COD_ANS, ID_DOMANDA, ID_RISPOSTA, RISPOSTA, value)
    value (pCOD_ANS, pIDQUEST, pIDANS, pRISP1, pVALUE1);
    
    set pIDANS = 2;
    set pCOD_ANS = pCOD_ANS + 1 ;
    insert into risposte(COD_ANS, ID_DOMANDA, ID_RISPOSTA, RISPOSTA, value)
    value (pCOD_ANS, pIDQUEST, pIDANS, pRISP2, pVALUE2);
    
    set pIDANS = 3;
    set pCOD_ANS = pCOD_ANS + 1 ;
    insert into risposte(COD_ANS, ID_DOMANDA, ID_RISPOSTA, RISPOSTA, value)
    value (pCOD_ANS, pIDQUEST, pIDANS, pRISP3, pVALUE3);
    
    set pIDANS = 4;
    set pCOD_ANS = pCOD_ANS + 1 ;
    insert into risposte(COD_ANS, ID_DOMANDA, ID_RISPOSTA, RISPOSTA, value)
    value (pCOD_ANS, pIDQUEST, pIDANS, pRISP4, pVALUE4);
    
    
    commit;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-24 11:55:39
