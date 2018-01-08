--��ұ�
�û�id  �û��� ���� ע��ʱ��  �û��ȼ�

--�Ѷȱ�
�Ѷ�id  �Ѷ�

--���ֱ�
scores_id  �Ѷ�id  ��õĻ���

--��Ϸ�Ծֱ�
�û�id  ���Ѷ�  �Ƿ��ʤ  ��Ϸʱ��

--��Ϸ�浵��
cd_id �����  ��Ϸ�Ѷ�  ��Ϸ����  ��Ϸ���� 



--��ұ�
create table sl_users(
       id int primary key auto_increment,      
       uname varchar(50) not null,
       passwd varchar(200) not null,
       registerTime date,
       lv_user int
);

--create sequence seq_sl_users start with 10000;


insert into sl_users values( 1002, 'saber', '123', sysdate(), 1);


--�Ѷȱ�
create table sl_nd(
		difficulty_id int primary key auto_increment,
       difficulty varchar(10)
);

insert into sl_nd(difficulty, difficulty_id) values('��',1);
insert into sl_nd(difficulty, difficulty_id) values('����',2);
insert into sl_nd(difficulty, difficulty_id) values('����',3);
insert into sl_nd(difficulty, difficulty_id) values('�Զ���',4);

--���ֱ�
create table sl_scores(
       scores_id int primary key,
       difficulty_id int references sl_nd(difficulty_id),
       scores int
);
insert into sl_scores values(1,1,1);
insert into sl_scores values(2,2,2);
insert into sl_scores values(3,3,5);
insert into sl_scores values(4,4,0);


--��Ϸ�Ծֱ�
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
check (iswin in('��ʤ','ʧ��'));



insert into sl_dj values( 10002, 1, '��ʤ', 100000, seq_sl_dj_id.nextval);
insert into sl_dj values( 10031, 1, '��ʤ', 20000, seq_sl_dj_id.nextval, sysdate);

--��Ϸ�浵��
dd_id �����  ��Ϸ�Ѷ�  ��Ϸ����  ��Ϸ���� 
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
				where sl_users.id=sl_dj.id and sl_dj.difficulty_id=sl_nd.difficulty_id and sl_dj.difficulty_id=sl_scores.difficulty_id and difficulty='����'
				group by uname, difficulty, lv_user
