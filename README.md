# jjw-starter-kit

공공·사내 업무 시스템용 **Spring Boot + MyBatis + Oracle** 백엔드 API Starter Kit 입니다.
새 프로젝트를 시작할 때마다 반복하던 공통 설정(응답 형식, 예외 처리, DB 연결, 테스트)을 미리 담았습니다.

## 기술 스택
| 구분 | 내용 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.3 (Web, Validation, Actuator) |
| Persistence | MyBatis 3 (Mapper XML), Oracle (ojdbc11) |
| Test | JUnit 5, Spring Boot Test, H2 (Oracle 호환 모드) |
| Build | Gradle 8 (wrapper 포함) |
| Local DB | Docker Compose - Oracle Free |

## 폴더 구조
```
src/main/java/com/jjw/starter
├── StarterApplication.java
├── common/                 # 공통 응답·예외 처리
│   ├── ApiResponse.java    # { success, data, code, message }
│   ├── ErrorCode.java      # 오류 코드 ↔ HTTP 상태
│   ├── BusinessException.java
│   └── GlobalExceptionHandler.java
└── user/                   # 예제 모듈 (Controller → Service → Mapper)
    ├── UserController.java
    ├── UserService.java
    ├── UserMapper.java
    ├── UserDto.java
    └── User.java
src/main/resources
├── application.yml         # DB 접속 정보는 환경 변수로 주입
├── mapper/UserMapper.xml   # SQL (바인드 변수 #{} 사용)
└── schema-oracle.sql       # Oracle 초기 스키마
```

## 시작하기
```bash
cp .env.example .env              # 값 수정 후 사용 (커밋 금지)
docker compose --env-file .env up -d   # 로컬 Oracle 실행
# schema-oracle.sql 실행 후
./gradlew bootRun
```
Windows PowerShell 에서는 `.\gradlew.bat bootRun` 을 사용합니다.

## 테스트
```bash
./gradlew test   # H2(Oracle 모드)로 Mapper SQL과 API 응답 형식을 검증
```

## 예제 API
| Method | URL | 설명 |
|--------|-----|------|
| GET | /api/users | 목록 |
| GET | /api/users/{id} | 단건 |
| POST | /api/users | 등록 (loginId 중복 시 409) |
| PUT | /api/users/{id} | 수정 |
| DELETE | /api/users/{id} | 삭제 |
| GET | /actuator/health | 헬스 체크 |

## 새 모듈 추가 규칙
1. `user` 패키지를 복사해 `<도메인>` 패키지 생성
2. Mapper XML에 SQL 작성 (문자열 연결 금지, `#{}` 바인드 변수만)
3. 업무 오류는 `BusinessException(ErrorCode.XXX)` 로 던지면 공통 응답으로 변환
4. Service 테스트를 함께 작성

## Claude Code 설정
| 위치 | 내용 |
|------|------|
| `.claude/commands/` | `/add-api`, `/commit`, `/test`, `/sql-check` 커스텀 커맨드 |
| `.claude/agents/` | code-reviewer, debugger, test-writer, security-checker, doc-writer 서브에이전트 |
| `.claude/settings.json` + `.claude/hooks/` | 보호 파일 수정 차단, 위험 명령 차단, Mapper `${}` 경고 훅 (Node 필요) |
| `.mcp.json` | context7(최신 라이브러리 문서), sequential-thinking(단계적 설계 검토), playwright(브라우저 E2E 확인) |

처음 열면 Claude Code 가 프로젝트 MCP 서버 사용 승인을 묻습니다. `/mcp` 로 연결 상태를 확인하세요.
