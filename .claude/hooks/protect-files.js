#!/usr/bin/env node
// PreToolUse(Edit|Write|MultiEdit): .env·운영 설정·gradle wrapper 등 보호 파일 수정 차단
// 종료 코드 2 = 도구 실행 차단, stderr 내용이 Claude 에게 전달된다
const PROTECTED = [
  /(^|[\\/])\.env(\.[^\\/]*)?$/,          // .env, .env.local ... (.env.example 은 아래에서 허용)
  /application-prod\.ya?ml$/,               // 운영 설정
  /[\\/]gradle[\\/]wrapper[\\/]/,          // gradle wrapper
  /(^|[\\/])\.git[\\/]/,                    // git 내부 파일
];

let input = '';
process.stdin.on('data', (c) => (input += c));
process.stdin.on('end', () => {
  let filePath = '';
  try {
    filePath = JSON.parse(input).tool_input?.file_path ?? '';
  } catch {
    process.exit(0); // 입력을 해석할 수 없으면 막지 않는다
  }
  if (/\.env\.example$/.test(filePath)) process.exit(0);
  if (PROTECTED.some((re) => re.test(filePath))) {
    console.error(`🔒 보호 파일은 수정할 수 없습니다: ${filePath}\n필요하면 사람이 직접 수정하세요.`);
    process.exit(2);
  }
  process.exit(0);
});
