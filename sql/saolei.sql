--玩家表
用户id  用户名 密码 注册时间  用户等级

--难度表
难度id  难度

--积分表
scores_id  难度id  获得的积分

--游戏对局表
用户id  困难度  是否获胜  游戏时间

--游戏存档表
cd_id 玩家名  游戏难度  游戏进度  游戏日期 



--玩家表
create table sl_users(
       id int primary key auto_increment,      
       uname varchar(50) not null,
       passwd varchar(200) not null,
       registerTime date,
       lv_user int
);

--create sequence seq_sl_users start with 10000;


insert into sl_users values( 1002, 'saber', '123', sysdate(), 1);


--难度表
create table sl_nd(
		difficulty_id int primary key auto_increment,
       difficulty varchar(10)
);

insert into sl_nd(difficulty, difficulty_id) values('简单',1);
insert into sl_nd(difficulty, difficulty_id) values('正常',2);
insert into sl_nd(difficulty, difficulty_id) values('困难',3);
insert into sl_nd(difficulty, difficulty_id) values('自定义',4);

--积分表
create table sl_scores(
       scores_id int primary key,
       difficulty_id int references sl_nd(difficulty_id),
       scores int
);
insert into sl_scores values(1,1,1);
insert into sl_scores values(2,2,2);
insert into sl_scores values(3,3,5);
insert into sl_scores values(4,4,0);


--游戏对局表
create table sl_dj(
		no int  primary key auto_increment,
       id int  references sl_users(id),
       difficulty_id int references sl_nd(difficulty_id),
       iswin char(4),
       gameTime numeric(32),
       gamedate Date
);


alter table sl_dj
add constraint sl_dj_check_iswin 
check (iswin in('获胜','失败'));



insert into sl_dj values( 10002, 1, '获胜', 100000, seq_sl_dj_id.nextval);
insert into sl_dj values( 10031, 1, '获胜', 20000, seq_sl_dj_id.nextval, sysdate);

--游戏存档表
dd_id 玩家名  游戏难度  游戏进度  游戏日期 
create table sl_cd(
       cd_id int primary key auto_increment,
       id int references sl_users(id),
       difficulty_id int references sl_nd(difficulty_id),
       schedule numeric(3,2),
       gameDate date
);


insert into sl_cd values(seq_cd_id.nextval, 10031, 1, 0.52, sysdate);





select *from sl_users;
select *from sl_nd;
select *from sl_scores;
select *from sl_dj order by dj_id desc;
select *from sl_cd order by cd_id desc;
delete sl_dj where gameTime=0
commit

select uname,0 as GSCORES, difficulty, lv_user, 0 as gametime, 0 as win_count ,count(*) as PLAY_COUNT from sl_users, sl_nd, sl_scores, sl_dj 
				where sl_users.id=sl_dj.id and sl_dj.difficulty_id=sl_nd.difficulty_id and sl_dj.difficulty_id=sl_scores.difficulty_id and difficulty='正常'
				group by uname, difficulty, lv_user
