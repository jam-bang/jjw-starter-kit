---
description: MyBatis Mapper XML의 SQL을 보안·성능 관점에서 점검
argument-hint: [Mapper 파일명]
---

`src/main/resources/mapper/` 의 ${1:-모든} Mapper XML을 점검해줘.

점검 항목:
1. `${}` 문자열 치환 사용 여부 (SQL Injection 위험) → `#{}` 로 바꾸는 방법 제시
2. `SELECT *` 사용, 불필요한 컬럼 조회
3. WHERE 조건 컬럼의 인덱스 필요 여부 (Oracle 기준)
4. 페이징 없는 대량 조회
5. 트랜잭션이 필요한데 빠진 Service 메서드

결과는 파일별 표(항목 / 위치 / 문제 / 권장 수정)로 정리한다. 코드는 수정하지 않는다.
