---
description: 변경 사항을 검토하고 한국어 커밋 메시지로 커밋
argument-hint: [추가 설명]
allowed-tools: Bash(git status:*), Bash(git diff:*), Bash(git add:*), Bash(git commit:*)
---

현재 변경 사항으로 커밋해줘. 추가 설명: $ARGUMENTS

1. `git status` 와 `git diff` 로 변경 내용을 확인한다
2. `.env`, 비밀 값, 빌드 산출물이 포함되어 있으면 커밋하지 말고 알려준다
3. 커밋 메시지 형식: `<타입>: <제목>` (feat / fix / refactor / docs / test / chore), 제목은 한국어 50자 이내
4. 본문에는 무엇을, 왜 바꿨는지 2~4줄로 적는다
5. push 는 하지 않는다
