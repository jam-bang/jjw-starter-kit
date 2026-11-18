#!/usr/bin/env node
// PreToolUse(Bash): 되돌리기 어려운 위험 명령 차단
const RULES = [
  { re: /\brm\s+(-[a-zA-Z]*r[a-zA-Z]*f|-[a-zA-Z]*f[a-zA-Z]*r)\b/, why: 'rm -rf (재귀 강제 삭제)' },
  { re: /\bgit\s+push\b.*(--force\b|-f\b|--force-with-lease\b)/, why: 'git force push' },
  { re: /\bgit\s+reset\s+--hard\b/, why: 'git reset --hard' },
  { re: /\bgit\s+clean\s+-[a-zA-Z]*f/, why: 'git clean -f' },
  { re: /\b(DROP\s+(TABLE|USER|TABLESPACE)|TRUNCATE\s+TABLE)\b/i, why: 'DB 객체 삭제/전체 삭제 SQL' },
  { re: /\bsudo\b/, why: 'sudo' },
  { re: /(cat|type|Get-Content)\s+[^|;&]*\.env\b(?!\.example)/i, why: '.env 내용 출력' },
];

let input = '';
process.stdin.on('data', (c) => (input += c));
process.stdin.on('end', () => {
  let command = '';
  try {
    command = JSON.parse(input).tool_input?.command ?? '';
  } catch {
    process.exit(0);
  }
  const hit = RULES.find((r) => r.re.test(command));
  if (hit) {
    console.error(`⛔ 위험 명령이 차단되었습니다: ${hit.why}\n명령: ${command}\n정말 필요하면 사용자에게 직접 실행을 요청하세요.`);
    process.exit(2);
  }
  process.exit(0);
});
