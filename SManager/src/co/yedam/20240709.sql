-- �л� ���� (�л���ȣ, �̸�, ����ó, �ּ�, �������)
--tbl_student ���̺��.
create table tbl_student (
    std_no varchar2(10) primary key, -- �л���ȣ varchar2Ÿ������ 10�ڷ� ���)
    std_name varchar2(100) not null, -- �л���
    std_phone varchar2(20), -- �޴�����ȣ 010-1111-2222
    address varchar2(100), -- �ּ�
    birth_date date, --�������
    creation_date date default sysdate --������ �Է���
);

-- sample date
insert into tbl_student(std_no, std_name, std_phone,  address)
VALUES ('S2024-01', 'ȫ�浿', '010-1234-5678', '�뱸�� �뱸�� �뱸����Ʈ 1');
insert into tbl_student(std_no, std_name, std_phone,  address)
VALUES ('S2024-02', '���浿', '010-1234-5678', '�뱸�� �뱸�� �뱸����Ʈ 2');
insert into tbl_student(std_no, std_name, std_phone,  address)
VALUES ('S2024-03', '��浿', '010-1234-5678', '�뱸�� �뱸�� �뱸����Ʈ 3');
insert into tbl_student(std_no, std_name, std_phone,  address)
VALUES ('S2024-04', '��α�', '010-1234-5678', '�뱸�� �뱸�� �뱸����Ʈ 4');

select *
from tbl_student;

update tbl_student
set address ='�뱸�� �뱸�� �뱸����Ʈ 2'
where std_no = 'S2024-02';

commit;

-- ���� �ּ� ����
update tbl_student
set birth_date = to_date('1991-02-27', 'yyyy-mm-dd'),
    address = '�뱸�� �뱸�� �뱸2'
where std_no = 'S2024-02';
