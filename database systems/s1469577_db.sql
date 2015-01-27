create table MOVIES(Title varchar(30) not null,Year int not null,Director varchar(30),Country varchar(30),Rating real,Genre varchar(30),Gross int,Producer varchar(40),primary key(Title,Year));

create table ACTORS(Title varchar(30) not null,Year int not null, Character_name varchar(30) not null, Actor varchar(30),primary key(Title,Year,Character_name),foreign key(Title,Year) references MOVIES);

create table AWARDS(Title varchar(30) not null, Year int not null, Award varchar(30) not null, Result varchar(30),primary key(Title,Year,Award),foreign key(Title,Year) references MOVIES);

insert into MOVIES values('Face/Off',1997,'John Woo','UK',7.3,'Action',9878302,'Paramount Pictures');
insert into MOVIES values('Saving Private Ryan',1998,'Steven Spielberg','US',8.6,'Action',216119491,'Paramount Pictures');
insert into MOVIES values('Aliens',1986,'James Cameron','UK',8.4,'Action',85200000,'Twentieth Century Fox Film Corporation');
insert into MOVIES values('Titanic',1997,'James Cameron','US',7.7,'Drama',658672302,'Twentieth Century Fox Film Corporation');
insert into MOVIES values('A Beautiful Mind',2001,'Ron Howard','US',8.2,'Biography',170708996,'Universal Pictures');
insert into MOVIES values('Hannibal',2001,'Ridley Scott','UK',6.7,'Crime',165091464,'Universal Pictures');
insert into MOVIES values('It Happened One Night',1934,'Frank Capra','US',8.3,'Comedy',3250000,'Columbia Pictures Corporation');
insert into MOVIES values('It is a Wonderful Life',1946,'Frank Capra','US',8.1,'Drama',1282222,'Liberty Films');
insert into MOVIES values('La Strada',1954,'Federico Fellini','Italy',8.1,'Drama',2463570,'Ponti-De Laurentiis Cinematografica');
insert into MOVIES values('Last Year in Marienbad',1961,'Alain Resnais','France',7.9,'Drama',143381,'Cocinor');
insert into MOVIES values('The Tin Drum',1979,'Volker Schlondorff','Germany',7.6,'Drama',3963773,'Argos Films');
insert into MOVIES values('The Conman in Las Vegas',1999,'Jing Wong','Hong Kong',5.7,'Comedy',400936,'China Star Entertainment');

insert into ACTORS values('Face/Off',1997,'Sean Archer','John Travolta');
insert into ACTORS values('Face/Off',1997,'Castor Troy','John Travolta');
insert into ACTORS values('Saving Private Ryan',1998,'Captain Miller','Tom Hanks');
insert into ACTORS values('Aliens',1986,'Ellen Ripley','Sigourney Weaver');
insert into ACTORS values('Titanic',1997,'Jack Dawson','Leonardo DiCaprio');
insert into ACTORS values('A Beautiful Mind',2001,'Russell Crowe','John Nash');
insert into ACTORS values('Hannibal',2001,'Hannibal Lecter','Anthony Hopkins');
insert into ACTORS values('It Happened One Night',1934,'Peter','Clark Gable');
insert into ACTORS values('It is a Wonderful Life',1946,'George Bailey','James Stewart');
insert into ACTORS values('The Conman in Las Vegas',1999,'Peter','Alex Man');

insert into AWARDS values('Face/Off',1997,'Oscar Best Effects','nominated');
insert into AWARDS values('Saving Private Ryan',1998,'Oscar Best Director','won');
insert into AWARDS values('Saving Private Ryan',1998,'Oscar Best Actor','nominated');
insert into AWARDS values('Saving Private Ryan',1998,'Golden Globes Best Actor','nominated');
insert into AWARDS values('Aliens',1986,'Oscar Best Effects','won');
insert into AWARDS values('Titanic',1997,'Oscar Best Director','won');
insert into AWARDS values('Titanic',1997,'Oscar Best Picture','won');
insert into AWARDS values('A Beautiful Mind',2001,'Oscar Best Director','won');
insert into AWARDS values('Hannibal',2001,'ASCAP Best Film','won');
insert into AWARDS values('It Happened One Night',1934,'Oscar Best Director','won');
insert into AWARDS values('It Happened One Night',1934,'Oscar Best Actor','won');
insert into AWARDS values('It is a Wonderful Life',1946,'Oscar Best Actor','nominated');
insert into AWARDS values('La Strada',1954,'Oscar Best Foreign Film','won');
insert into AWARDS values('Last Year in Marienbad',1961,'Golden Lion','won');
insert into AWARDS values('The Tin Drum',1979,'César Best Foreign Film','won');

select distinct actor from ACTORS group by title,year,actor having count(character_name) > 1;

select avg(gross+0.0) as avg_gross from MOVIES where director in (select director from MOVIES where (title,year) in (select title,year from AWARDS where award='Oscar Best Director' and result='won'));

select producer, sum(gross) as sum_gross from MOVIES group by producer;

select distinct M1.producer from MOVIES M1, MOVIES M2 where M1.title<>M2.title and M1.producer=M2.producer and M1.year=M2.year and M1.gross>50000000 and M2.gross>50000000;

select director,title from MOVIES where rating>8 order by director,gross;

select distinct title from AWARDS where (year between 1990 and 1999) and title in (select distinct title from AWARDS group by title having count(award='% Best Actor')>1 );

select distinct title from AWARDS where (year between 1990 and 1999) and title in ((select distinct title from AWARDS) except (select distinct title from AWARDS where result='nominated')); 

select distinct M.title from MOVIES M,AWARDS A where M.title=A.title and M.year=A.year and (A.year<1960 or A.year>1990) and M.genre='Comedy' and (A.award='Oscar Best Director' or A.award='Oscar Best Actor') and A.result='won';

select M1.director from MOVIES M1,MOVIES M2 where M1.genre='Comedy' and M2.genre='Drama' and M1.director=M2.director and M1.rating>M2.rating;

select distinct actor from ACTORS where actor not in (select distinct A.actor from ACTORS A,MOVIES M where M.gross<50000000 and A.title=M.title and A.year=M.year);

select distinct A.award, avg(M.rating) as avg_rating from AWARDS A,MOVIES M where A.title=M.title and A.year=M.year and result='won' group by A.award;

with AVG (award,avgrating) as (select A.award,avg(M.rating) from AWARDS A,MOVIES M where A.title=M.title and A.year=M.year and A.result='won' group by A.award) select max(avgrating+0.0) as max_avg_rating from AVG;

select M1.title as m1,M2.title as m2 from MOVIES M1,MOVIES M2 where M1.rating>M2.rating and (M1.title,M2.title) in (select A1.title,A2.title from AWARDS A1,AWARDS A2 where A1.award=A2.award and A1.result='nominated' and A2.result='won');

select distinct A1.character_name from ACTORS A1,ACTORS A2 where A1.character_name=A2.character_name and (A1.title,A2.title) in(select M1.title,M2.title from MOVIES M1,MOVIES M2 where M1.country<>M2.country);

create view A2(decade,count) as (select (A1.year/10%10) as decade, count(A1.title) from (select* from AWARDS where result='won') A1 group by A1.year/10%10); 
create view A3(decade,us_count) as (select (A1.year/10%10) as decade, count(A1.title)+0.0 from (select* from AWARDS where result='won' except (select*from AWARDS where title in (select title from MOVIES where country<>'US'))) A1 group by A1.year/10%10); 
select A2.decade,((A3.us_count+0.0)/(A2.count+0.0)) as percentage from A2,A3 where A2.decade=A3.decade;


