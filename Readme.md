# ReadMe.md

## 1. 개요

파일 확장자에 따라 특정 형식의 파일을 첨부하거나 전송하지 못하도록 제한하는 API


```mermaid
gantt
    title 일정
    dateFormat  YYYY-MM-DD
    section Section
    요구사항 분석 및 아키텍처 설계         :a1, 2023-12-08, 1d
    백엔드 개발    :after a1  , 1d
    프론트엔드 개발    :a2, 2023-12-10  , 1d
    테스트 및 배포 :after a2, 2d
    
```

||개발 환경|
|--|----|
|프론트엔드|Vue.js|
|백엔드|SpringBoot 3|
|DB|MySQL 8.0|
|배포|Docker, Nginx, AWS EC2|

## 2. 요구사항 정의

|요구사항| 구현 |
|--------|-----|
|1. bat, cmd, com, cpl, exe, scr, js 리스트는 고정적으로 존재해야 한다|:heavy_check_mark: |
|2. 확장자는 고정 확장자/커스텀 확장자로 나누어져야 한다| :heavy_check_mark: |
|3. 고정 확장자 리스트는 체크 박스가 존재해야 한다|:heavy_check_mark: |
| 4. 고정 확장자 리스트에서 체크 박스를 클릭시 활성화/비활성화 기능을 제공해야 한다 |:heavy_check_mark: |
|5. 확장자 차단 리스트는 리로드 시에도 유지되어야 한다|:heavy_check_mark:|
|6. 커스텀 확장자는 최대 200개까지만 활성화 가능하다|:heavy_check_mark:|
|7. 커스텀 확장자의 길이는 최소 1글자 최대 20자리까지만 생성 가능하다|:heavy_check_mark:|
|8. 커스텀 확장자는 추가 버튼이 존재하며 생성 버튼 클릭 시 텍스트 박스에 존재해야 하고 서버에 저장된다|:heavy_check_mark:|
|9. 커스텀 확장자는 X버튼 클릭시 서버에서 삭제되고 화면에서 사라져야 한다|:heavy_check_mark:|


## 2-1. 추가 기능 정의 및 제약 조건

|추가 기능|구현|
|--------|----|
|1. 서버 관리자가 확장자를 변경 생성 삭제 로그를 접속 ip와 남긴다|:heavy_check_mark:|
|2. 확장자 리스트는 서버에서 캐싱한다|:heavy_check_mark:|
|3.  이미 활성화한 커스텀 확장자면 중복된 확장자이기 때문에 추가 불가|:heavy_check_mark:|
|4. 공백이 포함되었거나 특수문자 한글 등 영어가 아닌 커스텀 확장자는 추가할 수 없다|:heavy_check_mark:|


## 3. 기능 명세서

|프론트엔드|구현|
|---------|------|
|첫 화면 로드 시 아무것도 차단 활성화 되지 않은 상태라면 기본 화면이 렌더링 된다(GET) |:heavy_check_mark:|
|고정 확장자 차단 활성화/비활성화를 클릭 시 상태를 변경한다(PATCH)|:heavy_check_mark:|
|상태 변경시 사용자의 의도치 않은(다른 운영진이 이미 변경했다던가) 예외가 발생하면 알림창을 띄워 예외 처리한다|:heavy_check_mark:|
|커스텀 확장자 입력 텍스트 박스에 커스텀 확장자를 입력하고 추가 버튼을 누르면 통신한다|:heavy_check_mark:|
|커스텀 확장자 생성 시 중복 값(다른 운영진이 이미 생성)이 있다면 예외를 반환한 서버 응답을 통해 알림창을 띄운다|:heavy_check_mark:|
|커스텀 확장자 이름의 길이는 최대 20자이며 20자가 넘는다면 하단에 빨간글씨로 유효하지 않다고 표시한다|:heavy_check_mark:|
|커스텀 확장자는 최대 200개만 등록할 수 있기 때문에 확장자의 개수를 표시한다|:heavy_check_mark:|
|커스텀 확장자 입력 input에 공백을 입력할 수 없도록 조작한다|:heavy_check_mark:|
|커스텀 확장자 리스트는 텍스트 박스에 표시한다(GET)|:heavy_check_mark:|
|커스텀 확장자 삭제 클릭 시 삭제한다(DELETE)|:heavy_check_mark:|
|삭제 시 사용자의 의도치 않은(다른 운영진이 이미 삭제했다던가) 예외가 발생하면 알림창을 띄워 예외 처리한다|:heavy_check_mark:|
|커스텀 확장자 리스트의 개수가 200개가 넘는다면 버튼을 비활성화 한다|:heavy_check_mark:|
|css로 dom을 배치한다|:heavy_check_mark:|



|백엔드|구현|
|-----|----|
|ERD와 도메인 설계를 바탕으로 확장자 Entity를 작성한다|:heavy_check_mark:|
|ERD와 도메인 설계를 바탕으로 로그 Entity를 작성한다|:heavy_check_mark:|
|DB에 접근하는 Repository를 작성한다|:heavy_check_mark:|
|공통 API Response 클래스를 작성한다|:heavy_check_mark:|
|중복된 확장자 여부를 처리하는 예외 클래스를 작성한다|:heavy_check_mark:|
|접속 클라이언트의 IP를 로깅하기 위해 인터셉터 클래스를 작성한다|:heavy_check_mark:|
|브라우저의 cors 처리를 해결하기 위한 클래스를 작성한다|:heavy_check_mark:|
|확장자를 생성, 활성화/비활성화, 삭제 될때 캐시를 초기화 해준다|:heavy_check_mark:|
|데이터를 전송하는 DTO를 만들어 유효성을 검사한다|:heavy_check_mark:|
|스케쥴링을 통해 만료기간이 다 된 캐시는 삭제한다|:heavy_check_mark:|
|캐싱과 트랜젝션을 관리하는 Service 계층을 만든다|:heavy_check_mark:|
|클라이언트의 요청을 받는 Controller를 작성한다|:heavy_check_mark:|
|예외를 핸들링할 클래스를 작성한다|:heavy_check_mark:|
|커스텀 확장자 리스트의 개수가 200개를 초과하면 예외를 발생 시킨다|:heavy_check_mark:|
|고정 확장자 리스트를 제공해주는 /ext-fixed-list 메소드를 만든다(GET)|:heavy_check_mark:|
|고정 확장자의 상태를 변경해주는 /ext-fixed 메소드를 만든다(PATCH)|:heavy_check_mark:|
|커스텀 확장자 리스트를 제공해주는 /custom-list 메소드를 만든다(GET)|:heavy_check_mark:|
|커스텀 확장자 생성을 제공해주는 /ext-custom 메소드를 만든다(POST) |:heavy_check_mark:|
|커스텀 확장자 삭제를 제공해주는 /ext-custom 메소드를 만든다(DELETE)|:heavy_check_mark:|

## 4. API 명세서

|Api Path|Method|Request|Response |Description|구현|
|--------|-------|---------|----------|-----------|----|
|/api-v1/ext-fixed-list|GET|None|Body{ext-list : [{extName : string, isActivate : boolean}]}|고정 확장자 리스트 제공|:heavy_check_mark:|
|/api-v1/ext-fixed|PATCH|Body{extName : string, isActivate : boolean }|{status : string, message : string}|고정 확장자 상태 변경|:heavy_check_mark:|
|/api-v1/ext-custom-list|GET|None|Body{ext-list : [{extName : string}]}|커스텀 확장자 리스트 제공|:heavy_check_mark:|
|/api-v1/ext-custom|POST|Body{ext-name : string}|{status : string, message : string}|커스텀 확장자 생성|:heavy_check_mark:|
|/api-v1/ext-custom|Delete|Body{ext-name : string}|{status : string, message : string}|커스텀 확장자 삭제|:heavy_check_mark:|



## 5. 도메인 분석 및 설계


## 6.  ERD와 매핑


## 7. 테스트



## 8. 배포 아키텍쳐


