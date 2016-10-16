-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 16, 2016 at 05:47 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sinatra_book_2`
--

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE `album` (
  `id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `label` int(11) NOT NULL,
  `year` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `book_string`
--

CREATE TABLE `book_string` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `value` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_string`
--

INSERT INTO `book_string` (`id`, `name`, `value`) VALUES
(1, 'author', 'Richard Cook'),
(2, 'title', 'Frank Sinatra: The Complete Discography'),
(3, 'person-subtitle', 'The People Book'),
(4, 'song-subtitle', 'The Song Book'),
(5, 'version', 'v0.1.5 (October 2016) '),
(6, 'abbreviations', 'Abbreviations'),
(7, 'musicians', 'Musicians'),
(8, 'groups', 'Groups'),
(9, 'arrangers', 'Arrangers'),
(10, 'songwriters', 'Songwriters'),
(11, 'published', 'Published:'),
(12, 'from_the', 'From the'),
(13, 'date', 'Date'),
(14, 'type', 'Type'),
(15, 'chapter', 'Chapter'),
(16, 'notes', 'Notes'),
(17, 'medley', 'medley'),
(18, 'session', 'session'),
(19, 'for', 'for'),
(20, 'for_album', 'for the album'),
(21, 'for_film', 'for the film'),
(22, 'for_television', 'for the television show'),
(23, 'for_radio', 'for the radio show'),
(24, 'at', 'at'),
(25, 'in', 'in'),
(26, 'personnel', 'Personnel'),
(27, 'master', 'master'),
(28, 'length', 'Length:'),
(29, 'original_release', 'Original release:'),
(30, 'key_release', 'Key release:'),
(31, 'film', 'Film:'),
(32, 'radio', 'Radio show:'),
(33, 'television', 'Television show:'),
(34, 'note', 'Note:'),
(35, 'all-releases', 'all releases'),
(37, 'acknowledgements-title', 'Acknowledgements'),
(38, 'acknowledgements', 'Thanks to Amna for proof reading.\n\\newline\n\nThe main sources for this book are the Sinatra Family''s \\ita{Frank Sinatra Sessionography}, found at \\url{http://sinatrafamily.com/session/index.php}, Luiz Carlos Do Nascimento Silva''s \\ita{Put Your Dreams Away: A Frank Sinatra Discography} (2000), and Richard W. Ackelson''s \\ita{Frank Sinatra: A Complete Recording History of Techniques, Songs, Composers, Lyricists, Arrangers, Sessions and First-Issue Albums, 1939-1984} (1992).\n\\newline\n\nWikipedia was the source for much of the information about song origins. Also consulted were the following artist discographies:\n\\begin{itemize}\n\\item A Pile o'' Cole''s \\ita{Nat King Cole: An Informal Discography} (found at \\url{http://apileocole.alongthehall.com/index.html})\n\\end{itemize}'),
(39, 'book-copyright', 'No part of this book may be reproduced in any form without permission from the publisher except for the quotation of brief passages in reviews.'),
(41, 'book-errors', 'While all effort has been made to ensure that all information is correct and accurate at time of publication, it''s inevitable that some errors will slip through the cracks. Please do let me know at \\href{mailto:sinatra@richardcook.me.uk}{ \\nolinkurl{sinatra@richardcook.me.uk} } if you spot one!'),
(42, 'introduction', 'Frank Sinatra was the greatest singer who ever lived, and probably ever will. When it comes to voices there are many that are technically superior, but Sinatra had one thing most singers lack - emotion. A Sinatra recording was not shaped around the medley of the song but around the lyrics, rooting itself in the meaning of the song. Frank''s impressive breath control and his meticulous approach to phrasing led to recordings which capture the very soul of a song. There''s a Sinatra recording for every mood, every feeling, every scenario. Whether you have the world on a string, or you''re hanging your tears out to dry, Sinatra has been there. The world may focus on the "ring-a-ding-ding" Rat Pack Sinatra, but there was a lot more to Frank then booze and broads. Frank Sinatra knew what it was like to be on top of the world, for sure, but he also knew what it was like to fall madly in love, or to yearn for a love that will forever be unrequited, or to lose that one person most important to you. And you can hear it all on the recordings - every phrase, be it swinging or romantic or sad, has just the right amount of melancholy, joy and hope to match not just the words he was singing, but the meaning beneath them. When Frank sang he expressed the feelings of longing and loneliness which everyone feels but few dare to express. \n\\newline\n\nMy Sinatra journey started in 2009. I was bored of most of the music I had been listening to, and was pretty much just playing the \\ita{Family Guy: Live in Vegas} album on repeat. So when I heard Family Guy creator and voice actor Seth MacFarlane was on the BBC Proms, I gave it a watch. The show, \\ita{A Celebration of MGM Musicals}, featured the John Wilson Orchestra. Needless to say I enjoyed it, and as soon as I finished watching I went to YouTube to download some of the performances, and these became the new songs I played on repeat. The songs that got my attention most were three songs from \\ita{High Society}, originally sung by Frank Sinatra. This led me to listening to some Sinatra on YouTube, and pretty soon I started collecting Sinatra CDs from all the local charity shops, building up my collection. Coincidently, my Sinatra journey came full circle last year when I got to attend the BBC Prom celebrating Frank''s 100 birthday, featuring none-other than the John Wilson Orchestra and Seth MacFarlane.\n\\newline\n\nThe purpose of this book is to help you, the reader, build a complete Sinatra collection, as well as inform you of all the information surrounding each recording. \nDespite the title, this book is currently far from complete. The current version (0.2) has a pretty thorough section on Frank''s recordings for Capitol Records, but with 19 sections in total there''s a lot missing. The scope for inclusion in this book will be the recordings I have in my collection. This may seem to devoid the  "complete" aspect of the book, but I''m not interested in adding recordings that exist in some valut somewhere never to be heard - the goal of this book is the complete set of recordings you, the reader, could obtain - if I''ve managed to obtain it in the past seven years, then you can too.\n\\newline\n\nWhile seemingly constrained, the scope of this book goes far beyond that of any Sinatra sessionography or discography that exists. As well as including all the offically released material, it will include old time radio and television shows, as well as bootleg concerts and session material. I will also delve into the often overlooked conducting career of Frank Sinatra, as well as the short but nevertheless impactful composing career of Frank Sinatra, hoping to eventually cover all recordings of the seven songs he wrote lyrics for. I also aim to hit a level of detail that is rarely found - for instance the four seconds of "Summertime" and the nine seconds of "Là ci darem la mano" found on the June 25, 1980 Carnegie Hall \\ita{Sinatra: New York} DVD, which are not documented anywhere else. Combined with frequent updates, this means no matter what material is released, officially or otherwise, this book will remain up-to-date and complete. \n\\newline\n\n\\ita{Frank Sinatra: The Complete Recordings} really comprises of three "books". This one, the main book, contains the recordings themselves, organised into sections and sessions. Details include the song''s heritage, the musicians, and the original and key release of each recording. The person book contains a list of musicians and the recordings they performed on, a list of arrangers and the tracks they arranged, and a list of songwriters and the songs they wrote. Finally, the song book lists all the recordings of each song. These two supplementary books provide a thrilling look into the relationship Sinatra maintained with his muscians and songs throughout his career, as well as providing a more conventional "index" to the people and songs.\n\\newline\n\nAs aforementioned, regular updates are planned for this book, most likely monthly. You can keep up-to-date by visiting \\url{http://www.richardcook.me.uk/sinatra}, where you can also sign up for email notifications when a new version is released. If you have any questions or suggestions, or have spotted a mistake, please don''t hesitate to contact me at \\href{mailto:sinatra@richardcook.me.uk}{ \\nolinkurl{sinatra@richardcook.me.uk} }.'),
(43, 'introduction-title', 'Introduction'),
(44, 'reprise', 'reprise'),
(45, 'parody', 'parody');

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `id` int(10) UNSIGNED NOT NULL,
  `title` varchar(30) NOT NULL,
  `studio` int(11) NOT NULL,
  `year` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `format`
--

CREATE TABLE `format` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `for_other`
--

CREATE TABLE `for_other` (
  `id` int(11) NOT NULL,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `label`
--

CREATE TABLE `label` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `at` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `person_role_note`
--

CREATE TABLE `person_role_note` (
  `id` int(11) NOT NULL,
  `note` varchar(200) NOT NULL,
  `session` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `radio`
--

CREATE TABLE `radio` (
  `id` int(10) UNSIGNED NOT NULL,
  `title` varchar(30) NOT NULL,
  `network` int(11) NOT NULL,
  `year` varchar(50) NOT NULL,
  `sponsor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `release`
--

CREATE TABLE `release` (
  `id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `label` int(11) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL,
  `format` int(11) DEFAULT NULL,
  `all` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `abbreviation` varchar(10) NOT NULL,
  `group` int(10) NOT NULL,
  `position` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role_group`
--

CREATE TABLE `role_group` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `position` int(11) UNSIGNED NOT NULL,
  `arranger` tinyint(1) NOT NULL DEFAULT '0',
  `group` tinyint(1) NOT NULL DEFAULT '0',
  `writer` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `id` int(11) NOT NULL,
  `section` int(11) NOT NULL DEFAULT '11',
  `date` datetime NOT NULL,
  `date_display` varchar(50) NOT NULL,
  `location` int(11) NOT NULL DEFAULT '11',
  `label` int(11) DEFAULT NULL,
  `for_album` int(11) DEFAULT NULL,
  `for_other` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '3',
  `notes` text,
  `session_number` varchar(15) DEFAULT NULL,
  `radio` int(10) UNSIGNED DEFAULT NULL,
  `film` int(10) UNSIGNED DEFAULT NULL,
  `with` int(11) NOT NULL,
  `television` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session_song`
--

CREATE TABLE `session_song` (
  `id` int(11) NOT NULL,
  `session` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `master` varchar(50) DEFAULT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `reprise` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session_song_person`
--

CREATE TABLE `session_song_person` (
  `id` int(11) NOT NULL,
  `session_song` int(11) NOT NULL,
  `person` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `note` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session_song_song`
--

CREATE TABLE `session_song_song` (
  `id` int(11) NOT NULL,
  `session_song` int(11) NOT NULL,
  `song` int(11) NOT NULL,
  `position` int(10) UNSIGNED NOT NULL DEFAULT '1',
  `parody` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session_type`
--

CREATE TABLE `session_type` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `song`
--

CREATE TABLE `song` (
  `id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `source` int(11) DEFAULT NULL,
  `year` varchar(5) NOT NULL,
  `notes` text,
  `source2` int(11) DEFAULT NULL,
  `short_title` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `song_person`
--

CREATE TABLE `song_person` (
  `id` int(11) NOT NULL,
  `song` int(11) NOT NULL,
  `person` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `note` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `source`
--

CREATE TABLE `source` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `studio` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '2',
  `year` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `source_type`
--

CREATE TABLE `source_type` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `fromthe` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studio`
--

CREATE TABLE `studio` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `take`
--

CREATE TABLE `take` (
  `id` int(11) NOT NULL,
  `session_song` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `length` varchar(10) DEFAULT NULL,
  `original_release` int(11) DEFAULT NULL,
  `key_release` int(11) DEFAULT NULL,
  `notes` text,
  `position` int(11) NOT NULL DEFAULT '1',
  `film` int(11) UNSIGNED DEFAULT NULL,
  `television` int(11) UNSIGNED DEFAULT NULL,
  `radio` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `television`
--

CREATE TABLE `television` (
  `id` int(10) UNSIGNED NOT NULL,
  `title` varchar(30) NOT NULL,
  `network` int(11) NOT NULL,
  `year` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_album`
--
CREATE TABLE `vw_album` (
`id` int(11)
,`title` varchar(200)
,`label` varchar(100)
,`year` varchar(4)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_film`
--
CREATE TABLE `vw_film` (
`id` int(10) unsigned
,`title` varchar(30)
,`studio` varchar(50)
,`year` varchar(4)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_radio`
--
CREATE TABLE `vw_radio` (
`id` int(10) unsigned
,`title` varchar(30)
,`network` varchar(50)
,`sponsor` varchar(50)
,`year` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_release`
--
CREATE TABLE `vw_release` (
`id` int(11)
,`title` varchar(200)
,`label` varchar(100)
,`year` varchar(4)
,`format` varchar(30)
,`all` tinyint(1)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_role`
--
CREATE TABLE `vw_role` (
`id` int(11)
,`name` varchar(50)
,`abbreviation` varchar(10)
,`position` int(10) unsigned
,`role_group_name` varchar(100)
,`role_group_position` int(11) unsigned
,`arranger` tinyint(1)
,`group` int(10)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_session`
--
CREATE TABLE `vw_session` (
`id` int(11)
,`date` datetime
,`date_display` varchar(50)
,`location` int(11)
,`with` varchar(500)
,`label` varchar(100)
,`album` int(11)
,`other` int(11)
,`type` varchar(50)
,`notes` text
,`session_number` varchar(15)
,`radio` int(10) unsigned
,`film` int(10) unsigned
,`television` int(10) unsigned
,`section` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_session_song_person`
--
CREATE TABLE `vw_session_song_person` (
`id` int(11)
,`session_song` int(11)
,`person` int(11)
,`role` int(11)
,`note` int(11)
,`position` int(11)
,`arranger` tinyint(1)
,`group` tinyint(1)
,`writer` tinyint(1)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_session_song_person_details`
--
CREATE TABLE `vw_session_song_person_details` (
`id` int(11)
,`session_song` int(11)
,`person` int(11)
,`role` varchar(10)
,`note` int(11)
,`position` int(11)
,`arranger` tinyint(1)
,`group` tinyint(1)
,`writer` tinyint(1)
,`date` datetime
,`section` int(11)
,`role_position` int(10) unsigned
,`role_group_position` int(11) unsigned
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_song_person`
--
CREATE TABLE `vw_song_person` (
`id` int(11)
,`song` int(11)
,`person` int(11)
,`role` varchar(10)
,`year` varchar(5)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_source`
--
CREATE TABLE `vw_source` (
`id` int(11)
,`name` varchar(200)
,`studio` varchar(50)
,`type` varchar(50)
,`year` varchar(5)
,`fromthe` tinyint(4)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_television`
--
CREATE TABLE `vw_television` (
`id` int(10) unsigned
,`title` varchar(30)
,`network` varchar(50)
,`year` varchar(50)
);

-- --------------------------------------------------------

--
-- Table structure for table `with`
--

CREATE TABLE `with` (
  `id` int(11) NOT NULL,
  `name` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure for view `vw_album`
--
DROP TABLE IF EXISTS `vw_album`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_album`  AS  select `album`.`id` AS `id`,`album`.`title` AS `title`,`label`.`name` AS `label`,cast(`album`.`year` as char(4) charset utf8mb4) AS `year` from (`album` join `label` on((`album`.`label` = `label`.`id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_film`
--
DROP TABLE IF EXISTS `vw_film`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_film`  AS  select `film`.`id` AS `id`,`film`.`title` AS `title`,`studio`.`name` AS `studio`,cast(`film`.`year` as char(4) charset utf8mb4) AS `year` from (`film` join `studio` on((`film`.`studio` = `studio`.`id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_radio`
--
DROP TABLE IF EXISTS `vw_radio`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_radio`  AS  select `radio`.`id` AS `id`,`radio`.`title` AS `title`,`studio1`.`name` AS `network`,`studio2`.`name` AS `sponsor`,`radio`.`year` AS `year` from ((`radio` join `studio` `studio1` on((`studio1`.`id` = `radio`.`network`))) join `studio` `studio2` on((`studio2`.`id` = `radio`.`sponsor`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_release`
--
DROP TABLE IF EXISTS `vw_release`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_release`  AS  select `release`.`id` AS `id`,`release`.`title` AS `title`,`label`.`name` AS `label`,cast(`release`.`year` as char(4) charset utf8mb4) AS `year`,`format`.`name` AS `format`,`release`.`all` AS `all` from ((`release` left join `label` on((`release`.`label` = `label`.`id`))) left join `format` on((`release`.`format` = `format`.`id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_role`
--
DROP TABLE IF EXISTS `vw_role`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_role`  AS  select `role`.`id` AS `id`,`role`.`name` AS `name`,`role`.`abbreviation` AS `abbreviation`,`role`.`position` AS `position`,`role_group`.`name` AS `role_group_name`,`role_group`.`position` AS `role_group_position`,`role_group`.`arranger` AS `arranger`,`role`.`group` AS `group` from (`role` join `role_group` on((`role`.`group` = `role_group`.`id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_session`
--
DROP TABLE IF EXISTS `vw_session`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_session`  AS  select `session`.`id` AS `id`,`session`.`date` AS `date`,`session`.`date_display` AS `date_display`,`session`.`location` AS `location`,`with`.`name` AS `with`,`label`.`name` AS `label`,`session`.`for_album` AS `album`,`session`.`for_other` AS `other`,`session_type`.`name` AS `type`,`session`.`notes` AS `notes`,`session`.`session_number` AS `session_number`,`session`.`radio` AS `radio`,`session`.`film` AS `film`,`session`.`television` AS `television`,`session`.`section` AS `section` from (((`session` left join `label` on((`session`.`label` = `label`.`id`))) join `session_type` on((`session_type`.`id` = `session`.`type`))) join `with` on((`session`.`with` = `with`.`id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_session_song_person`
--
DROP TABLE IF EXISTS `vw_session_song_person`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_session_song_person`  AS  select `session_song_person`.`id` AS `id`,`session_song_person`.`session_song` AS `session_song`,`session_song_person`.`person` AS `person`,`session_song_person`.`role` AS `role`,`session_song_person`.`note` AS `note`,`session_song`.`position` AS `position`,`role_group`.`arranger` AS `arranger`,`role_group`.`group` AS `group`,`role_group`.`writer` AS `writer` from (((`session_song_person` join `session_song` on((`session_song_person`.`session_song` = `session_song`.`id`))) left join `role` on((`role`.`id` = `session_song_person`.`role`))) left join `role_group` on((`role_group`.`id` = `role`.`group`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_session_song_person_details`
--
DROP TABLE IF EXISTS `vw_session_song_person_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_session_song_person_details`  AS  select `vw_session_song_person`.`id` AS `id`,`vw_session_song_person`.`session_song` AS `session_song`,`vw_session_song_person`.`person` AS `person`,`role`.`abbreviation` AS `role`,`vw_session_song_person`.`note` AS `note`,`vw_session_song_person`.`position` AS `position`,`vw_session_song_person`.`arranger` AS `arranger`,`vw_session_song_person`.`group` AS `group`,`vw_session_song_person`.`writer` AS `writer`,`session`.`date` AS `date`,`session`.`section` AS `section`,`role`.`position` AS `role_position`,`role_group`.`position` AS `role_group_position` from ((((`vw_session_song_person` join `session_song` on((`vw_session_song_person`.`session_song` = `session_song`.`id`))) join `session` on((`session_song`.`session` = `session`.`id`))) join `role` on((`vw_session_song_person`.`role` = `role`.`id`))) join `role_group` on((`role`.`group` = `role_group`.`id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_song_person`
--
DROP TABLE IF EXISTS `vw_song_person`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_song_person`  AS  select `song_person`.`id` AS `id`,`song_person`.`song` AS `song`,`song_person`.`person` AS `person`,`role`.`abbreviation` AS `role`,`song`.`year` AS `year` from ((`song_person` join `role` on((`song_person`.`role` = `role`.`id`))) join `song` on((`song_person`.`song` = `song`.`id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_source`
--
DROP TABLE IF EXISTS `vw_source`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_source`  AS  select `source`.`id` AS `id`,`source`.`name` AS `name`,`studio`.`name` AS `studio`,`source_type`.`name` AS `type`,`source`.`year` AS `year`,`source_type`.`fromthe` AS `fromthe` from ((`source` left join `studio` on((`source`.`studio` = `studio`.`id`))) left join `source_type` on((`source`.`type` = `source_type`.`id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `vw_television`
--
DROP TABLE IF EXISTS `vw_television`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_television`  AS  select `television`.`id` AS `id`,`television`.`title` AS `title`,`studio`.`name` AS `network`,`television`.`year` AS `year` from (`television` join `studio` on((`television`.`network` = `studio`.`id`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id`),
  ADD KEY `label` (`label`);

--
-- Indexes for table `book_string`
--
ALTER TABLE `book_string`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id`),
  ADD KEY `studio` (`studio`);

--
-- Indexes for table `format`
--
ALTER TABLE `format`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `for_other`
--
ALTER TABLE `for_other`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `label`
--
ALTER TABLE `label`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`first_name`,`last_name`);

--
-- Indexes for table `person_role_note`
--
ALTER TABLE `person_role_note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `session` (`session`);

--
-- Indexes for table `radio`
--
ALTER TABLE `radio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `network` (`network`),
  ADD KEY `sponsor` (`sponsor`);

--
-- Indexes for table `release`
--
ALTER TABLE `release`
  ADD PRIMARY KEY (`id`),
  ADD KEY `label` (`label`),
  ADD KEY `format` (`format`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pos` (`group`,`position`),
  ADD UNIQUE KEY `abbr` (`abbreviation`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `group` (`group`);

--
-- Indexes for table `role_group`
--
ALTER TABLE `role_group`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pos` (`position`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `position` (`position`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`),
  ADD KEY `section` (`section`),
  ADD KEY `location` (`location`),
  ADD KEY `label` (`label`),
  ADD KEY `for_album` (`for_album`),
  ADD KEY `for_other` (`for_other`),
  ADD KEY `type` (`type`),
  ADD KEY `radio` (`radio`),
  ADD KEY `film` (`film`),
  ADD KEY `television` (`television`);

--
-- Indexes for table `session_song`
--
ALTER TABLE `session_song`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pos` (`session`,`position`),
  ADD KEY `session` (`session`);

--
-- Indexes for table `session_song_person`
--
ALTER TABLE `session_song_person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique` (`session_song`,`person`,`role`) USING BTREE,
  ADD KEY `person` (`person`),
  ADD KEY `role` (`role`),
  ADD KEY `note` (`note`);

--
-- Indexes for table `session_song_song`
--
ALTER TABLE `session_song_song`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique` (`session_song`,`position`) USING BTREE,
  ADD KEY `song` (`song`);

--
-- Indexes for table `session_type`
--
ALTER TABLE `session_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `source` (`source`),
  ADD KEY `source2` (`source2`);

--
-- Indexes for table `song_person`
--
ALTER TABLE `song_person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique` (`song`,`person`,`role`) USING BTREE,
  ADD KEY `person` (`person`),
  ADD KEY `role` (`role`),
  ADD KEY `note` (`note`);

--
-- Indexes for table `source`
--
ALTER TABLE `source`
  ADD PRIMARY KEY (`id`),
  ADD KEY `label` (`studio`),
  ADD KEY `type` (`type`);

--
-- Indexes for table `source_type`
--
ALTER TABLE `source_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `studio`
--
ALTER TABLE `studio`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `take`
--
ALTER TABLE `take`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `position` (`session_song`,`position`),
  ADD KEY `session_song` (`session_song`),
  ADD KEY `original` (`original_release`),
  ADD KEY `key` (`key_release`),
  ADD KEY `film` (`film`),
  ADD KEY `television` (`television`),
  ADD KEY `radio` (`radio`);

--
-- Indexes for table `television`
--
ALTER TABLE `television`
  ADD PRIMARY KEY (`id`),
  ADD KEY `network` (`network`) USING BTREE;

--
-- Indexes for table `with`
--
ALTER TABLE `with`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `album`
--
ALTER TABLE `album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `book_string`
--
ALTER TABLE `book_string`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT for table `film`
--
ALTER TABLE `film`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `format`
--
ALTER TABLE `format`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `for_other`
--
ALTER TABLE `for_other`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `label`
--
ALTER TABLE `label`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1104;
--
-- AUTO_INCREMENT for table `person_role_note`
--
ALTER TABLE `person_role_note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `radio`
--
ALTER TABLE `radio`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `release`
--
ALTER TABLE `release`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=142;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;
--
-- AUTO_INCREMENT for table `role_group`
--
ALTER TABLE `role_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=132;
--
-- AUTO_INCREMENT for table `session_song`
--
ALTER TABLE `session_song`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=444;
--
-- AUTO_INCREMENT for table `session_song_person`
--
ALTER TABLE `session_song_person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15918;
--
-- AUTO_INCREMENT for table `session_song_song`
--
ALTER TABLE `session_song_song`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=675;
--
-- AUTO_INCREMENT for table `session_type`
--
ALTER TABLE `session_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `song`
--
ALTER TABLE `song`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=557;
--
-- AUTO_INCREMENT for table `song_person`
--
ALTER TABLE `song_person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=763;
--
-- AUTO_INCREMENT for table `source`
--
ALTER TABLE `source`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=155;
--
-- AUTO_INCREMENT for table `source_type`
--
ALTER TABLE `source_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `studio`
--
ALTER TABLE `studio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `take`
--
ALTER TABLE `take`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=571;
--
-- AUTO_INCREMENT for table `television`
--
ALTER TABLE `television`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `with`
--
ALTER TABLE `with`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`label`) REFERENCES `label` (`id`);

--
-- Constraints for table `film`
--
ALTER TABLE `film`
  ADD CONSTRAINT `film_ibfk_1` FOREIGN KEY (`studio`) REFERENCES `studio` (`id`);

--
-- Constraints for table `person_role_note`
--
ALTER TABLE `person_role_note`
  ADD CONSTRAINT `person_role_note_ibfk_1` FOREIGN KEY (`session`) REFERENCES `session` (`id`);

--
-- Constraints for table `radio`
--
ALTER TABLE `radio`
  ADD CONSTRAINT `radio_ibfk_1` FOREIGN KEY (`network`) REFERENCES `studio` (`id`),
  ADD CONSTRAINT `radio_ibfk_2` FOREIGN KEY (`sponsor`) REFERENCES `studio` (`id`);

--
-- Constraints for table `release`
--
ALTER TABLE `release`
  ADD CONSTRAINT `release_ibfk_2` FOREIGN KEY (`label`) REFERENCES `label` (`id`),
  ADD CONSTRAINT `release_ibfk_3` FOREIGN KEY (`format`) REFERENCES `format` (`id`);

--
-- Constraints for table `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `role_ibfk_1` FOREIGN KEY (`group`) REFERENCES `role_group` (`id`);

--
-- Constraints for table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`section`) REFERENCES `section` (`id`),
  ADD CONSTRAINT `session_ibfk_10` FOREIGN KEY (`television`) REFERENCES `television` (`id`),
  ADD CONSTRAINT `session_ibfk_2` FOREIGN KEY (`location`) REFERENCES `location` (`id`),
  ADD CONSTRAINT `session_ibfk_3` FOREIGN KEY (`label`) REFERENCES `label` (`id`),
  ADD CONSTRAINT `session_ibfk_4` FOREIGN KEY (`for_album`) REFERENCES `album` (`id`),
  ADD CONSTRAINT `session_ibfk_5` FOREIGN KEY (`for_other`) REFERENCES `for_other` (`id`),
  ADD CONSTRAINT `session_ibfk_6` FOREIGN KEY (`type`) REFERENCES `session_type` (`id`),
  ADD CONSTRAINT `session_ibfk_8` FOREIGN KEY (`film`) REFERENCES `film` (`id`),
  ADD CONSTRAINT `session_ibfk_9` FOREIGN KEY (`radio`) REFERENCES `radio` (`id`);

--
-- Constraints for table `session_song`
--
ALTER TABLE `session_song`
  ADD CONSTRAINT `session_song_ibfk_1` FOREIGN KEY (`session`) REFERENCES `session` (`id`);

--
-- Constraints for table `session_song_person`
--
ALTER TABLE `session_song_person`
  ADD CONSTRAINT `session_song_person_ibfk_1` FOREIGN KEY (`session_song`) REFERENCES `session_song` (`id`),
  ADD CONSTRAINT `session_song_person_ibfk_2` FOREIGN KEY (`person`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `session_song_person_ibfk_3` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `session_song_person_ibfk_4` FOREIGN KEY (`note`) REFERENCES `person_role_note` (`id`);

--
-- Constraints for table `session_song_song`
--
ALTER TABLE `session_song_song`
  ADD CONSTRAINT `session_song_song_ibfk_1` FOREIGN KEY (`session_song`) REFERENCES `session_song` (`id`),
  ADD CONSTRAINT `session_song_song_ibfk_2` FOREIGN KEY (`song`) REFERENCES `song` (`id`);

--
-- Constraints for table `song`
--
ALTER TABLE `song`
  ADD CONSTRAINT `song_ibfk_1` FOREIGN KEY (`source`) REFERENCES `source` (`id`),
  ADD CONSTRAINT `song_ibfk_2` FOREIGN KEY (`source2`) REFERENCES `source` (`id`);

--
-- Constraints for table `song_person`
--
ALTER TABLE `song_person`
  ADD CONSTRAINT `song_person_ibfk_1` FOREIGN KEY (`song`) REFERENCES `song` (`id`),
  ADD CONSTRAINT `song_person_ibfk_2` FOREIGN KEY (`person`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `song_person_ibfk_3` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `song_person_ibfk_4` FOREIGN KEY (`note`) REFERENCES `person_role_note` (`id`);

--
-- Constraints for table `source`
--
ALTER TABLE `source`
  ADD CONSTRAINT `source_ibfk_2` FOREIGN KEY (`type`) REFERENCES `source_type` (`id`),
  ADD CONSTRAINT `source_ibfk_3` FOREIGN KEY (`studio`) REFERENCES `studio` (`id`);

--
-- Constraints for table `take`
--
ALTER TABLE `take`
  ADD CONSTRAINT `take_ibfk_1` FOREIGN KEY (`session_song`) REFERENCES `session_song` (`id`),
  ADD CONSTRAINT `take_ibfk_2` FOREIGN KEY (`original_release`) REFERENCES `release` (`id`),
  ADD CONSTRAINT `take_ibfk_3` FOREIGN KEY (`key_release`) REFERENCES `release` (`id`),
  ADD CONSTRAINT `take_ibfk_4` FOREIGN KEY (`film`) REFERENCES `film` (`id`),
  ADD CONSTRAINT `take_ibfk_5` FOREIGN KEY (`television`) REFERENCES `television` (`id`),
  ADD CONSTRAINT `take_ibfk_6` FOREIGN KEY (`radio`) REFERENCES `radio` (`id`);

--
-- Constraints for table `television`
--
ALTER TABLE `television`
  ADD CONSTRAINT `television_ibfk_1` FOREIGN KEY (`network`) REFERENCES `studio` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
