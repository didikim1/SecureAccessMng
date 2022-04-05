SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS EQ_ACCLOG;
DROP TABLE IF EXISTS CTN_ENTRPRSMBER;
DROP TABLE IF EXISTS CTN_NRLMBER_HISTORY;
DROP TABLE IF EXISTS CTN_NRLMBER;
DROP TABLE IF EXISTS COMTNDPAMENT;
DROP TABLE IF EXISTS COMTNPOSITION;
DROP TABLE IF EXISTS CTN_BBS;
DROP TABLE IF EXISTS CTN_BBSMASTER;
DROP TABLE IF EXISTS CTN_CHARGE;
DROP TABLE IF EXISTS CTN_CODE;
DROP TABLE IF EXISTS CTN_EMAIL;
DROP TABLE IF EXISTS CTN_FILEDETAIL;
DROP TABLE IF EXISTS CTN_FILE;
DROP TABLE IF EXISTS CTN_MANAGER;
DROP TABLE IF EXISTS CTN_ROLE;
DROP TABLE IF EXISTS EQ_ACCALLOWMNG;
DROP TABLE IF EXISTS EQ_ALLOW_IP;
DROP TABLE IF EXISTS EQ_COMPANY;
DROP TABLE IF EXISTS EQ_DEPOLY;
DROP TABLE IF EXISTS EQ_LIST;
DROP TABLE IF EXISTS EQ_IDC;
DROP TABLE IF EXISTS EQ_IDPWD;




/* Create Tables */

-- 부서정보
CREATE TABLE COMTNDPAMENT
(
	-- 부서명 고유ID
	DPAMENT_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '부서명 고유ID : 부서명 고유ID',
	-- 부서명
	DPAMENT_NAME varchar(50) COMMENT '부서명 : 부서명',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (DPAMENT_ID)
) COMMENT = '부서정보';


-- 직위
CREATE TABLE COMTNPOSITION
(
	-- 직위고유ID
	POSITION_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '직위고유ID : 직위고유ID',
	-- POSITION_NAME
	POSITION_NAME varchar(50) COMMENT '직위 : POSITION_NAME',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(50) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(50) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (POSITION_ID)
) COMMENT = '직위';


-- 게시판
CREATE TABLE CTN_BBS
(
	-- 게시물ID
	NTT_ID int NOT NULL AUTO_INCREMENT COMMENT '게시물ID : 게시물ID',
	-- 게시판ID
	BBS_ID int NOT NULL COMMENT '게시판ID : 게시판ID',
	-- 정렬순서
	SORT_ORDR int(10) COMMENT '정렬순서 : 정렬순서',
	-- 패스워드
	PASSWORD varchar(20) COMMENT '패스워드 : 패스워드',
	-- 게시물제목
	NTT_SJ varchar(100) COMMENT '게시물제목 : 게시물제목',
	-- 게시물내용
	NTT_CN text COMMENT '게시물내용 : 게시물내용',
	-- 사용여부
	USE_AT char(1) COMMENT '사용여부 : 사용여부',
	-- 게시물시작일
	NTCE_BGNDE datetime COMMENT '게시물시작일 : 게시물시작일',
	-- 게시물종료일
	NTCE_ENDDE datetime COMMENT '게시물종료일 : 게시물종료일',
	-- 첨부파일ID
	ATCH_FILE_ID int(10) COMMENT '첨부파일ID : 첨부파일ID',
	-- 조회횟수
	NTT_NO int(10) COMMENT '조회횟수 : 조회횟수',
	-- 최초등록시점
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록시점 : 최초등록시점',
	-- 마지막등록시점
	LAST_UPDUSR_PNTTM datetime COMMENT '마지막등록시점 : 마지막등록시점',
	-- FRST_REGISTER_ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : FRST_REGISTER_ID',
	-- 마지막등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '마지막등록ID : 마지막등록ID',
	-- 등록브라우저SESSIONID
	SESSION_BOARD_ID varchar(50) COMMENT '등록브라우저SESSIONID : 등록브라우저SESSIONID',
	-- 대상IP
	TARGET_IP int(11) COMMENT '대상IP : 대상IP',
	-- 대상계정
	TARGET_NRLM int(11) COMMENT '대상계정 : 대상계정',
	PRIMARY KEY (NTT_ID)
) COMMENT = '게시판';


-- 게시판마스터
CREATE TABLE CTN_BBSMASTER
(
	-- 게시판ID
	BBS_ID int NOT NULL AUTO_INCREMENT COMMENT '게시판ID : 게시판ID',
	-- 게시판유형코드
	BBS_TY_CODE varchar(50) COMMENT '게시판유형코드 : 게시판유형코드',
	-- 게시판코드
	BBS_CODE varchar(20) COMMENT '게시판코드 : 게시판코드',
	-- USE_AT
	USE_AT char(1) DEFAULT 'Y' COMMENT '사용여부 : USE_AT',
	-- 게시판명
	BBS_NM varchar(20) COMMENT '게시판명 : 게시판명',
	-- 첨부가능파일갯수
	POSBL_ATCH_FILE_NUMBER int(11) DEFAULT 5 COMMENT '첨부가능파일갯수 : 첨부가능파일갯수',
	-- 답장여부
	REPLY_POSBL_AT char(1) DEFAULT 'Y' COMMENT '답장여부 : 답장여부',
	-- 첨부가능여부
	FILE_ATCH_POSBL_AT varchar(50) COMMENT '첨부가능여부 : 첨부가능여부',
	-- 첨부가능사이즈(KB)
	POSBL_ATCH_FILE_SIZE varchar(20) COMMENT '첨부가능사이즈(KB) : 첨부가능사이즈(KB)',
	-- 최초등록시점
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록시점 : 최초등록시점',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(50) COMMENT '최초등록ID : 최초등록ID',
	-- 마지막등록시점
	LAST_UPDUSR_PNTTM datetime COMMENT '마지막등록시점 : 마지막등록시점',
	-- 마지막등록ID
	LAST_UPDUSR_ID varchar(50) COMMENT '마지막등록ID : 마지막등록ID',
	PRIMARY KEY (BBS_ID)
) COMMENT = '게시판마스터';


-- 담당업무
CREATE TABLE CTN_CHARGE
(
	-- 고유ID
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '고유ID : 고유ID',
	-- 담당업무
	NAME varchar(50) COMMENT '담당업무 : 담당업무',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (SEQ)
) COMMENT = '담당업무';


-- 코드관리
CREATE TABLE CTN_CODE
(
	-- 고유번호
	CODE_SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '고유번호 : 고유번호',
	-- 상위CODEID
	REF_CODE int(11) COMMENT '상위CODEID : 상위CODEID',
	-- 코드접두사
	PREFIX varchar(50) COMMENT '코드접두사 : 코드접두사',
	-- 분류코드명
	TITLE varchar(50) COMMENT '분류코드명 : 분류코드명',
	-- 부모(P),자식(C)
	TYPE char(1) COMMENT '타입 부모(P) 자식(C) : 부모(P),자식(C)',
	-- 코드ID
	ID varchar(20) COMMENT '코드ID : 코드ID',
	-- 코드ID명
	NAME varchar(20) COMMENT '코드ID명 : 코드ID명',
	-- 설명
	REMARK varchar(50) COMMENT '설명 : 설명',
	-- 정렬
	SORT int(11) COMMENT '정렬 : 정렬',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (CODE_SEQ)
) COMMENT = '코드관리';


-- EMAIL알림정보
CREATE TABLE CTN_EMAIL
(
	-- EMAIL고유 SEQ
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT 'EMAIL고유 SEQ : EMAIL고유 SEQ',
	-- 이메일알림수신자
	EAMIL_FROM varchar(100) COMMENT '이메일알림수신자 : 이메일알림수신자',
	-- 이메일알림발신자
	EAMIL_TO varchar(100) COMMENT '이메일알림발신자 : 이메일알림발신자',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (SEQ)
) COMMENT = 'EMAIL알림정보';


-- 관리회원
CREATE TABLE CTN_ENTRPRSMBER
(
	-- 관리회원고유
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '관리회원고유 : 관리회원고유',
	-- 부서명 고유ID
	DPAMENT_ID int(11) COMMENT '부서명 고유ID : 부서명 고유ID',
	-- 직위고유ID
	POSITION_ID int(11) COMMENT '직위고유ID : 직위고유ID',
	-- 고유ID
	UNIQ_ID varchar(20) COMMENT '고유ID : 고유ID',
	-- 비밀번호
	PASSWORD varchar(100) COMMENT '비밀번호 : 비밀번호',
	-- 이름
	MBER_NAME varchar(20) COMMENT '이름 : 이름',
	-- A:활성화
	MBER_STTUS char(1) DEFAULT 'A' COMMENT '상태 A: 활성화 : A:활성화',
	-- 휴대폰번호
	MOBLPHON_NO varchar(15) COMMENT '휴대폰번호 : 휴대폰번호',
	-- Email 주소
	EMAIL_ADDRESS varchar(50) COMMENT 'Email 주소 : Email 주소',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (SEQ)
) COMMENT = '관리회원';


-- 파일관리
CREATE TABLE CTN_FILE
(
	-- 파일관리고유번호
	ATCH_FILE_ID int(11) NOT NULL AUTO_INCREMENT COMMENT 'ATCH_FILE_ID : 파일관리고유번호',
	-- 사용여부 Y:N
	USE_AT char(1) COMMENT '사용여부 Y:N : 사용여부 Y:N',
	-- 등록시 SESSIONID
	SESSION_BOARD_ID varchar(50) COMMENT '등록시 SESSIONID : 등록시 SESSIONID',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(50) COMMENT '최초등록ID : 최초등록ID',
	-- 최초등록일자
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일자 : 최초등록일자',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(50) COMMENT '최종등록ID : 최종등록ID',
	-- 최종등록일자
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일자 : 최종등록일자',
	PRIMARY KEY (ATCH_FILE_ID)
) COMMENT = '파일관리';


-- 파일관리상세
CREATE TABLE CTN_FILEDETAIL
(
	-- 파일상세고유ID
	FILE_SN int(11) NOT NULL AUTO_INCREMENT COMMENT '파일상세고유ID : 파일상세고유ID',
	-- 파일관리고유번호
	ATCH_FILE_ID int(11) NOT NULL COMMENT 'ATCH_FILE_ID : 파일관리고유번호',
	-- 세션ID
	SESSION_BOARD_ID varchar(50) COMMENT '세션ID : 세션ID',
	-- 파일위치
	FILE_STRE_COURS varchar(2000) COMMENT '파일위치 : 파일위치',
	-- 파일URL접속경로
	URL_PATH varchar(2000) COMMENT '파일URL접속경로 : 파일URL접속경로',
	-- 저장된파일명
	STRE_FILE_NM varchar(255) COMMENT '저장된파일명 : 저장된파일명',
	-- 오리지날파일명
	ORIGNL_FILE_NM varchar(255) COMMENT '오리지날파일명 : 오리지날파일명',
	-- 파일확장자
	FILE_EXTSN varchar(20) COMMENT '파일확장자 : 파일확장자',
	-- 뷰어가능여부
	ISVIEW char(1) DEFAULT 'N' COMMENT '뷰어가능여부 : 뷰어가능여부',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(50) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(50) COMMENT '최종등록ID : 최종등록ID',
	PRIMARY KEY (FILE_SN)
) COMMENT = '파일관리상세';


-- 매니저회원
CREATE TABLE CTN_MANAGER
(
	-- 휴대폰번호
	PHONNUMBER varchar(15) COMMENT '휴대폰번호 : 휴대폰번호',
	-- 인증번호
	AUTHNUMBER varchar(50) COMMENT '인증번호 : 인증번호',
	-- 생성일
	CREATEDATE datetime COMMENT '생성일 : 생성일',
	-- SMS전송번호
	SMSNUMBER varchar(50) COMMENT 'SMS전송번호 : SMS전송번호',
	-- Y:사용
	STTS char(1) DEFAULT 'Y' COMMENT '상태 : Y:사용'
) COMMENT = '매니저회원';


-- 일반회원
CREATE TABLE CTN_NRLMBER
(
	-- 회원 SEQ
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '회원 SEQ : 회원 SEQ',
	-- 부서명 고유ID
	DPAMENT_ID int(11) COMMENT '부서명 고유ID : 부서명 고유ID',
	-- 직위고유ID
	POSITION_ID int(11) COMMENT '직위고유ID : 직위고유ID',
	-- 권한고유ID
	ROLE_ID int(11) COMMENT '권한고유ID : 권한고유ID',
	-- 고유ID
	CHARGE_ID int(11) COMMENT '담당업무ID : 고유ID',
	-- 고유ID
	UNIQ_ID varchar(20) COMMENT '고유ID : 고유ID',
	-- 비밀번호
	PASSWORD varchar(100) COMMENT '비밀번호 : 비밀번호',
	-- 상태  A: 활성화 C: 중지
	MBER_STTUS char(1) DEFAULT 'A' COMMENT '상태 : 상태  A: 활성화 C: 중지',
	-- 휴대폰번호
	MOBLPHON_NO varchar(15) COMMENT '휴대폰번호 : 휴대폰번호',
	-- Email 주소
	EMAIL_ADDRESS varchar(50) COMMENT 'Email 주소 : Email 주소',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	-- 비밀번호최종변경일
	LAST_UPDUSR_PWD datetime COMMENT '비밀번호최종변경일 : 비밀번호최종변경일',
	PRIMARY KEY (SEQ)
) COMMENT = '일반회원';


-- 일반회원_히스토리
CREATE TABLE CTN_NRLMBER_HISTORY
(
	-- 히스토리고유ID
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '히스토리고유ID : 히스토리고유ID',
	-- 회원 SEQ
	NRLMBER_ID int(11) COMMENT '회원 SEQ : 회원 SEQ',
	-- 권한고유ID
	ROLE_ID int(11) COMMENT '권한고유ID : 권한고유ID',
	-- 담당업무ID
	CHARGE_ID int(11) COMMENT '담당업무ID : 담당업무ID',
	-- M : 정 S: 부  A: 활성화 
	MBER_STTUS varchar(2) DEFAULT 'A' COMMENT '멤버상태 : M : 정 S: 부  A: 활성화 ',
	-- I: INSERT U:UPDATE D:DELETE S: 상태변경
	PROC_STTUS varchar(2) COMMENT '처리상태 : I: INSERT U:UPDATE D:DELETE S: 상태변경',
	-- 처리일자
	PROC_PNTTM datetime COMMENT '처리일자 : 처리일자',
	-- 처리자/최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '처리자 : 처리자/최초등록ID',
	-- 처리일자/최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '처리일자 : 처리일자/최초등록일',
	PRIMARY KEY (SEQ)
) COMMENT = '일반회원_히스토리';


-- 권한
CREATE TABLE CTN_ROLE
(
	-- 권한고유ID
	ROLE_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '권한고유ID : 권한고유ID',
	-- ROLE
	ROLE varchar(20) COMMENT '권한 : ROLE',
	-- 권한명칭
	ROLE_NAME varchar(50) COMMENT '권한명칭 : 권한명칭',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (ROLE_ID)
) COMMENT = '권한';


-- 접속관리담당자
CREATE TABLE EQ_ACCALLOWMNG
(
	-- 고유키값
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '고유키값 : 고유키값',
	-- 인증번호
	AUTHNUMBER varchar(50) COMMENT '인증번호 : 인증번호',
	-- SMS전송번호
	SMSNUMBER varchar(50) COMMENT 'SMS전송번호 : SMS전송번호',
	-- A:활성화
	STTUS char(1) COMMENT '상태 : A:활성화',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(50) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(50) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 마지막등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '마지막등록일 : 마지막등록일',
	PRIMARY KEY (SEQ)
) COMMENT = '접속관리담당자';


-- 접속로그
CREATE TABLE EQ_ACCLOG
(
	-- 접속이력 SEQ
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '접속이력 SEQ : 접속이력 SEQ',
	-- 업무코드 ID
	WORKTYPECODE_ID int(11) COMMENT '업무코드 ID : 업무코드 ID',
	-- IDC고유키값
	REF_EQ_IDC int(11) COMMENT 'IDC고유키값 : IDC고유키값',
	-- 장비목록고유값
	REF_EQ_LIST int(11) COMMENT '장비목록고유값 : 장비목록고유값',
	-- 장비접속ID
	REF_EQ_IDPWD int(11) COMMENT '장비접속ID : 장비접속ID',
	-- 에이전트접속IP고유SEQ
	REF_ALLOW_IP int(11) COMMENT '에이전트접속IP고유SEQ : 에이전트접속IP고유SEQ',
	-- 회원 SEQ
	REF_NRLMBER int(11) COMMENT '회원 SEQ : 회원 SEQ',
	-- 관리회원고유
	REF_ENTRPRSMBER int(11) COMMENT '관리회원고유 : 관리회원고유',
	-- 프로세스ID
	PROCESSID varchar(11) COMMENT '프로세스ID : 프로세스ID',
	-- 요청일자
	REQUESTTIME datetime COMMENT '요청일자 : 요청일자',
	-- 응답일자
	REPONSETIME datetime COMMENT '응답일자 : 응답일자',
	-- 처리상태 A:활성화 E:종료
	STTUS char(1) DEFAULT 'A' COMMENT '처리상태 : 처리상태 A:활성화 E:종료',
	-- 접근사유
	REASON varchar(300) COMMENT '접근사유 : 접근사유',
	-- A:ARS,NE:이메일알림
	AUTH_TYPE varchar(10) COMMENT 'A:ARS,NE:이메일알림 : A:ARS,NE:이메일알림',
	-- 인증결과코드
	AUTH_RTN_CODE varchar(10) COMMENT '인증결과코드 : 인증결과코드',
	-- 인증결과메세지
	AUTH_RTN_MESSAGE varchar(100) COMMENT '인증결과메세지 : 인증결과메세지',
	-- 로그인ID
	FRST_REGISTER_ID varchar(20) COMMENT '로그인ID : 로그인ID',
	-- 로그인일자
	FRST_REGISTER_PNTTM datetime COMMENT '로그인일자 : 로그인일자',
	-- 로그아웃ID
	LAST_UPDUSR_ID varchar(20) COMMENT '로그아웃ID : 로그아웃ID',
	-- 로그아웃일자
	LAST_UPDUSR_PNTTM datetime COMMENT '로그아웃일자 : 로그아웃일자',
	PRIMARY KEY (SEQ)
) COMMENT = '접속로그';


-- 접속허용IP
CREATE TABLE EQ_ALLOW_IP
(
	-- 에이전트접속IP고유SEQ
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '에이전트접속IP고유SEQ : 에이전트접속IP고유SEQ',
	-- 접속PC구분
	PC_TYPE varchar(50) COMMENT '접속PC구분 : 접속PC구분',
	-- IP주소
	ADDR varchar(50) COMMENT 'IP주소 : IP주소',
	-- IP이름
	NAME varchar(100) COMMENT 'IP이름 : IP이름',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (SEQ)
) COMMENT = '접속허용IP';


-- 고객사정보
CREATE TABLE EQ_COMPANY
(
	-- 고유키값
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '고유키값 : 고유키값',
	-- 고객사코드
	COMPANYCODE varchar(50) COMMENT '고객사코드 : 고객사코드',
	-- 고객사명칭
	COMPANYNAME varchar(50) COMMENT '고객사명칭 : 고객사명칭',
	-- 화면에노출될이름
	DISPLAYNAME varchar(50) COMMENT '화면에노출될이름 : 화면에노출될이름',
	PRIMARY KEY (SEQ)
) COMMENT = '고객사정보';


-- 배포정보
CREATE TABLE EQ_DEPOLY
(
	-- SESSION정보
	SESSION_BOARD_ID varchar(50) COMMENT 'SESSION정보 : SESSION정보',
	-- 고객사고유ID
	REF_COMPANY int(11) COMMENT '고객사고유ID : 고객사고유ID',
	-- 버전정보
	VERSION varchar(100) COMMENT '버전정보 : 버전정보'
) COMMENT = '배포정보';


-- IDC정보
CREATE TABLE EQ_IDC
(
	-- IDC고유키값
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT 'IDC고유키값 : IDC고유키값',
	-- IDC명칭
	NAME varchar(50) COMMENT 'IDC명칭 : IDC명칭',
	-- IDC주소
	ADDR varchar(100) COMMENT 'IDC주소 : IDC주소',
	-- IDC상세주소
	ADDR_DETAIL varchar(100) COMMENT 'IDC상세주소 : IDC상세주소',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (SEQ)
) COMMENT = 'IDC정보';


-- 장비접속정보
CREATE TABLE EQ_IDPWD
(
	-- 장비접속ID
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '장비접속ID : 장비접속ID',
	-- 장비목록 고유값
	REF_EQ_LIST int(11) COMMENT '장비목록 고유값 : 장비목록 고유값',
	-- 명칭
	NAME varchar(50) COMMENT '명칭 : 명칭',
	-- 장비접속ID
	ID varchar(100) COMMENT '장비접속ID : 장비접속ID',
	-- 장비접속패스워드
	PWD varchar(100) COMMENT '장비접속패스워드 : 장비접속패스워드',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (SEQ)
) COMMENT = '장비접속정보';


-- 장비목록
CREATE TABLE EQ_LIST
(
	-- 장비목록고유값
	SEQ int(11) NOT NULL AUTO_INCREMENT COMMENT '장비목록고유값 : 장비목록고유값',
	-- IDC고유키값
	REF_EQ_IDC int(11) NOT NULL COMMENT 'IDC고유키값 : IDC고유키값',
	-- 서버명
	NAME varchar(50) COMMENT '서버명 : 서버명',
	-- 기종
	MODEL varchar(50) COMMENT '기종 : 기종',
	-- 제조업체
	MANUFACTURE varchar(50) COMMENT '제조업체 : 제조업체',
	-- 사용용도
	USAGE varchar(50) COMMENT '사용용도 : 사용용도',
	-- 상면
	POSITION varchar(50) COMMENT '상면 : 상면',
	-- 서버상태
	STTUS varchar(50) COMMENT '서버상태 : 서버상태',
	-- 자산명
	PROPERTY_NAME varchar(50) COMMENT '자산명 : 자산명',
	-- 자산번호
	PROPERTY_NUM varchar(50) COMMENT '자산번호 : 자산번호',
	-- 스펙_CPU
	SEPEC_CPU varchar(20) COMMENT '스펙_CPU : 스펙_CPU',
	-- 스펙_메모리
	SEPEC_MM varchar(20) COMMENT '스펙_메모리 : 스펙_메모리',
	-- 스펙_디스크
	SEPEC_DISK varchar(20) COMMENT '스펙_디스크 : 스펙_디스크',
	-- 스펙_랜카드
	SEPEC_NIC varchar(20) COMMENT '스펙_랜카드 : 스펙_랜카드',
	-- OS타입
	OS_TYPE varchar(20) COMMENT 'OS타입 : OS타입',
	-- 서버IP1
	SV_IP1 varchar(50) COMMENT '서버IP1 : 서버IP1',
	-- 서버IP2
	SV_IP2 varchar(50) COMMENT '서버IP2 : 서버IP2',
	-- 서버IP3
	SV_IP3 varchar(50) COMMENT '서버IP3 : 서버IP3',
	-- 서버IP4
	SV_IP4 varchar(50) COMMENT '서버IP4 : 서버IP4',
	-- SSH PORT
	SV_PORT int(11) COMMENT 'SSH PORT : SSH PORT',
	-- 관리자ID
	MANAGER_ID varchar(20) COMMENT '관리자ID : 관리자ID',
	-- 입고일
	RECEIVING_PNTTM datetime COMMENT '입고일 : 입고일',
	-- 최초등록ID
	FRST_REGISTER_ID varchar(20) COMMENT '최초등록ID : 최초등록ID',
	-- 최종등록ID
	LAST_UPDUSR_ID varchar(20) COMMENT '최종등록ID : 최종등록ID',
	-- 최초등록일
	FRST_REGISTER_PNTTM datetime COMMENT '최초등록일 : 최초등록일',
	-- 최종등록일
	LAST_UPDUSR_PNTTM datetime COMMENT '최종등록일 : 최종등록일',
	PRIMARY KEY (SEQ)
) COMMENT = '장비목록';



/* Create Foreign Keys */

ALTER TABLE CTN_ENTRPRSMBER
	ADD FOREIGN KEY (DPAMENT_ID)
	REFERENCES COMTNDPAMENT (DPAMENT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_NRLMBER
	ADD FOREIGN KEY (DPAMENT_ID)
	REFERENCES COMTNDPAMENT (DPAMENT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_ENTRPRSMBER
	ADD FOREIGN KEY (POSITION_ID)
	REFERENCES COMTNPOSITION (POSITION_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_NRLMBER
	ADD FOREIGN KEY (POSITION_ID)
	REFERENCES COMTNPOSITION (POSITION_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_BBS
	ADD FOREIGN KEY (BBS_ID)
	REFERENCES CTN_BBSMASTER (BBS_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_NRLMBER
	ADD FOREIGN KEY (CHARGE_ID)
	REFERENCES CTN_CHARGE (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_NRLMBER_HISTORY
	ADD FOREIGN KEY (CHARGE_ID)
	REFERENCES CTN_CHARGE (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EQ_ACCLOG
	ADD FOREIGN KEY (REF_ENTRPRSMBER)
	REFERENCES CTN_ENTRPRSMBER (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_FILEDETAIL
	ADD FOREIGN KEY (ATCH_FILE_ID)
	REFERENCES CTN_FILE (ATCH_FILE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_NRLMBER_HISTORY
	ADD FOREIGN KEY (NRLMBER_ID)
	REFERENCES CTN_NRLMBER (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EQ_ACCLOG
	ADD FOREIGN KEY (REF_NRLMBER)
	REFERENCES CTN_NRLMBER (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_NRLMBER
	ADD FOREIGN KEY (ROLE_ID)
	REFERENCES CTN_ROLE (ROLE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CTN_NRLMBER_HISTORY
	ADD FOREIGN KEY (ROLE_ID)
	REFERENCES CTN_ROLE (ROLE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EQ_ACCLOG
	ADD FOREIGN KEY (REF_ALLOW_IP)
	REFERENCES EQ_ALLOW_IP (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EQ_ACCLOG
	ADD FOREIGN KEY (REF_EQ_IDC)
	REFERENCES EQ_IDC (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EQ_LIST
	ADD FOREIGN KEY (REF_EQ_IDC)
	REFERENCES EQ_IDC (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EQ_ACCLOG
	ADD FOREIGN KEY (REF_EQ_IDPWD)
	REFERENCES EQ_IDPWD (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EQ_ACCLOG
	ADD FOREIGN KEY (REF_EQ_LIST)
	REFERENCES EQ_LIST (SEQ)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



