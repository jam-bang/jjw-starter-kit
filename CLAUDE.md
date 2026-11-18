# jjw-starter-kit

Spring Boot 3 + MyBatis + Oracle 백엔드 API Starter Kit.

## 명령어
- 빌드: `./gradlew build`
- 테스트: `./gradlew test` (H2 Oracle 호환 모드)
- 실행: `./gradlew bootRun` (DB 접속 정보는 환경 변수 DB_URL / DB_USERNAME / DB_PASSWORD)

## 아키텍처 규칙
- 계층: Controller → Service → Mapper. 계층 건너뛰기 금지
- 도메인 객체(User)를 API로 직접 노출하지 말고 `UserDto.Response` 같은 DTO 사용
- 모든 응답은 `ApiResponse` 로 감싼다. 업무 오류는 `BusinessException(ErrorCode)` 사용
- SQL은 `src/main/resources/mapper/*.xml` 에만 작성하고 바인드 변수 `#{}` 필수 (`${}` 금지)
- 쓰기 메서드에는 `@Transactional`, 클래스 기본은 `readOnly = true`

## 코드 스타일
- 들여쓰기 4칸, 클래스 PascalCase, 메서드/변수 camelCase
- 주석·커밋 메시지·문서는 한국어
- 테스트 메서드 이름은 한국어 서술형 허용 (예: `없는_사용자를_삭제하면_NOT_FOUND_예외`)

## 금지
- `.env`, 운영 설정 파일 읽기·수정 금지
- 비밀 값 하드코딩 금지
