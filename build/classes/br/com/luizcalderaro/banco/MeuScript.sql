 `projectmanhattan`.CREATE DATABASE `projectmanhattan` /*!40100 DEFAULT CHARACTER SET latin1 */;

DROP TABLE IF EXISTS `projectmanhattan`.`tbpermissao`;
CREATE TABLE  `projectmanhattan`.`tbpermissao` (
  `codigo` int(10) unsigned NOT NULL auto_increment,
  `idUser` int(10) unsigned NOT NULL default '0',
  `permissao` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `projectmanhattan`.`tbupload`;
CREATE TABLE  `projectmanhattan`.`tbupload` (
  `fileName` varchar(1000) default NULL,
  `file` mediumblob,
  `idFile` int(10) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`idFile`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `projectmanhattan`.`tbusuario`;
CREATE TABLE  `projectmanhattan`.`tbusuario` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `nome` varchar(100) NOT NULL default '',
  `user` varchar(100) NOT NULL default '',
  `senha` varchar(100) NOT NULL default '',
  `email` varchar(100) NOT NULL default '',
  `ativo` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;