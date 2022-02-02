--#테이블 삭제
brop table rboard;

--#시퀀스 삭제
brop sequence seq_rboard_no;

--#테이블 생성
create table rboard(
            no number,
            user_no number not null,
            title varchar2(500),
            content varchar2(4000),
            hit number,
            reg_date date,
            group_no number,
            order_no number,
            depth number,
     primary key(no),
     constraint rboard_fk foreign key (user_no)
     references users(no)
);

--#시퀀스 생성
create sequence seq_rboard_no
increment by 1
start with 1
nocache;

commit;

--#테이블 출력
select *
from rboard
order by group_no desc, order_no asc;

--#join 테이블 출력
select  rboard.no rbNo,
        rboard.title title,
        users.name uName,
        rboard.hit hit,
        to_char(rboard.reg_date, 'yyyy-mm-dd') regDate,
        rboard.user_no userNo,
        rboard.content content,
        rboard.group_no groupNo,
        rboard.order_no orderNo,
        rboard.depth depth
from users, rboard
where users.no = rboard.user_no
order by group_no desc, order_no asc;

--#insert 게시글 등록
insert into rboard
values(seq_rboard_no.nextval,
                        1,
                        'title',
                        'content',
                        0,
                        sysdate,
                        0,
                        1,
                        0
);

--#댓글 order_no
update rboard
set order_no = order_no+1
where no = ;


--#update depth 들여쓰기
update rboard
set depth = depth+1
where group_no = 1;

--#oder_no 

