---
description: 새 도메인 API(Controller → Service → Mapper XML + 테스트)를 Starter Kit 규칙대로 생성
argument-hint: <도메인명> [설명]
---

`$1` 도메인의 CRUD API를 만들어줘. 설명: $2

규칙:
1. `src/main/java/com/jjw/starter/user` 패키지를 참고해 `com.jjw.starter.$1` 패키지를 만든다
   - 도메인 객체, `XxxDto`(CreateRequest / UpdateRequest / Response record), `XxxMapper`, `XxxService`, `XxxController`
2. SQL은 `src/main/resources/mapper/XxxMapper.xml` 에만 작성하고 바인드 변수 `#{}` 만 사용 (`${}` 금지)
3. 업무 오류는 `BusinessException(ErrorCode.XXX)` 로 처리하고 응답은 `ApiResponse` 로 감싼다
4. Oracle 테이블 DDL을 `src/main/resources/schema-oracle.sql` 끝에, 테스트용 DDL을 `src/test/resources/schema-test.sql` 끝에 추가
5. `src/test/java/.../$1/XxxServiceTest.java` 에 등록·조회·오류 케이스 테스트 작성
6. 마지막에 `./gradlew test` 를 실행하고 결과를 요약
