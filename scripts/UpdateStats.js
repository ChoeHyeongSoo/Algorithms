const fs = require('fs');
const path = require('path');

const platforms = {
    "BOJ": "백준",
    "Programmers": "프로그래머스",
    "SWEA": "src",
    "LeetCode": "LeetCode",
    "CodeTree": "CodeTree"
};

const validExtensions = ['.java', '.py', '.cpp', '.c', '.js', '.kt'];

function countFiles(dir) {
    let count = 0;

    // 폴더가 존재하지 않으면 0 반환
    if (!fs.existsSync(dir)) return 0;

    const files = fs.readdirSync(dir);

    for (const file of files) {
        const fullPath = path.join(dir, file);
        const stat = fs.statSync(fullPath);

        if (stat.isDirectory()) {
            count += countFiles(fullPath); // 하위 폴더 탐색
        } else {
            const ext = path.extname(fullPath);
            if (validExtensions.includes(ext)) {
                count++;
            }
        }
    }
    return count;
}

function createProgressBar(percent) {
    const totalBlocks = 20; // 전체 막대 길이
    const filledBlocks = Math.round((percent / 100) * totalBlocks);
    const emptyBlocks = totalBlocks - filledBlocks;

    return '█'.repeat(filledBlocks) + '░'.repeat(emptyBlocks);
}

function generateMarkdown() {
    let stats = {};
    let total = 0;

    for (const [name, dirPath] of Object.entries(platforms)) {
        const count = countFiles(dirPath);
        stats[name] = count;
        total += count;
    }

    let md = `\`\`\`text\n`;
    for (const [name, count] of Object.entries(stats)) {
        if (count === 0) continue;
        const percent = total > 0 ? ((count / total) * 100).toFixed(1) : 0;
        const bar = createProgressBar(percent);
        md += `${name.padEnd(15, ' ')} ${bar} ${percent}%\n`;
    }
    md += `\`\`\`\n\n`;

    // 마크다운 텍스트 커스텀 ==============================
    md += `### 📈 Problem Solving Stats\n\n`;
    md += `| 플랫폼 | 풀이 수 | 비중 |\n`;
    md += `| :--- | :--- | :--- |\n`;
    // ===============================================

    for (const [name, count] of Object.entries(stats)) {
        const percent = total > 0 ? ((count / total) * 100).toFixed(1) : 0;
        md += `| ${name} | ${count} | ${percent}% |\n`;
    }
    md += `| **Total** | **${total}** | **100%** |\n`;

    return md;
}

function updateReadme(newStatsMd) {
    const readmePath = path.join(__dirname, '../README.md');
    let content = fs.readFileSync(readmePath, 'utf8');

    const pattern = /(## 📊 Platform Ratio\s*)[\s\S]*?(?=\s*---\s*## 📂 Directory Structure)/;

    if (pattern.test(content)) {
        content = content.replace(pattern, `$1\n${newStatsMd}\n\n`);
        fs.writeFileSync(readmePath, content, 'utf8');
        console.log("✅ README.md Update Complete!");
    } else {
        console.error("❌ Error: 원인부터 찾아라 형수야");
    }
}

updateReadme(generateMarkdown());