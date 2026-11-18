#!/usr/bin/env node
// PostToolUse(Edit|Write|MultiEdit): Mapper XML 저장 후 ${} 문자열 치환 사용을 경고
// 종료 코드 2 + stderr → Claude 에게 피드백되어 스스로 고치도록 유도
const fs = require('fs');

let input = '';
process.stdin.on('data', (c) => (input += c));
process.stdin.on('end', () => {
  let filePath = '';
  try {
    filePath = JSON.parse(input).tool_input?.file_path ?? '';
  } catch {
    process.exit(0);
  }
  if (!/[\\/]mapper[\\/].*\.xml$/.test(filePath) || !fs.existsSync(filePath)) process.exit(0);

  const lines = fs.readFileSync(filePath, 'utf8').split(/\r?\n/);
  const found = lines
    .map((line, i) => ({ line: i + 1, text: line.trim() }))
    .filter(({ text }) => /\$\{[^}]+\}/.test(text));
  if (found.length > 0) {
    const detail = found.map((f) => `  ${f.line}행: ${f.text}`).join('\n');
    console.error(`⚠️ ${filePath} 에 \${} 문자열 치환이 있습니다 (SQL Injection 위험).\n${detail}\n#{} 바인드 변수로 바꿔주세요.`);
    process.exit(2);
  }
  process.exit(0);
});
