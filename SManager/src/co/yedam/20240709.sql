-- 학생 정보 (학생번호, 이름, 연락처, 주소, 생년월일)
--tbl_student 테이블명.
create table tbl_student (
    std_no varchar2(10) primary key, -- 학생번호 varchar2타입으로 10자로 등록)
    std_name varchar2(100) not null, -- 학생명
    std_phone varchar2(20), -- 휴대폰번호 010-1111-2222
    address varchar2(100), -- 주소
    birth_date date, --생년월일
    creation_date date default sysdate --데이터 입력일
);

-- sample date
insert into tbl_student(std_no, std_name, std_phone,  address)
VALUES ('S2024-01', '홍길동', '010-1234-5678', '대구시 대구로 대구아파트 1');
insert into tbl_student(std_no, std_name, std_phone,  address)
VALUES ('S2024-02', '정길동', '010-1234-5678', '대구시 대구로 대구아파트 2');
insert into tbl_student(std_no, std_name, std_phone,  address)
VALUES ('S2024-03', '김길동', '010-1234-5678', '대구시 대구로 대구아파트 3');
insert into tbl_student(std_no, std_name, std_phone,  address)
VALUES ('S2024-04', '김민규', '010-1234-5678', '대구시 대구로 대구아파트 4');

select *
from tbl_student;

update tbl_student
set address ='대구시 대구로 대구아파트 2'
where std_no = 'S2024-02';

commit;

-- 생일 주소 변경
update tbl_student
set birth_date = to_date('1991-02-27', 'yyyy-mm-dd'),
    address = '대구시 대구로 대구2'
where std_no = 'S2024-02';
