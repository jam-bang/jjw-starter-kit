---
description: 테스트를 실행하고 실패 원인을 분석
argument-hint: [테스트 클래스명]
allowed-tools: Bash(./gradlew test:*), Bash(.\gradlew.bat test:*)
---

테스트를 실행해줘. 대상: ${1:-전체}

- 대상이 있으면 `./gradlew test --tests "*$1*"`, 없으면 `./gradlew test`
- 실패한 테스트가 있으면 실패 메시지와 관련 코드를 읽고 원인과 수정안을 제시한다 (바로 수정하지 말고 먼저 설명)
- 결과는 "통과 N / 실패 N" 형태로 요약한다
