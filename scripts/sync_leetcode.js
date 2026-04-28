const fs = require('fs/promises');
const path = require('path');
const util = require('util');
const execAsync = util.promisify(require('child_process').exec);

// 환경변수 및 기본 설정
const LEETCODE_SESSION = process.env.LEETCODE_SESSION;
const TARGET_FOLDER = 'LeetCode';

// 언어별 확장자 매핑
const EXTENSION_MAP = {
    'java': '.java',
    'python': '.py',
    'python3': '.py',
    'cpp': '.cpp',
    'javascript': '.js',
    'mysql': '.sql'
};

// 최근 통과한 제출 기록 Loading
async function getRecentSubmissions() {
    const response = await fetch('https://leetcode.com/graphql', {
        method: 'POST',
        headers: {
            'Cookie': `LEETCODE_SESSION=${LEETCODE_SESSION};`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            query: `
            query recentAcSubmissions {
                recentAcSubmissionList(username: "Eugene603", limit: 20) {
                    id
                    statusDisplay
                }
            }
            `
        })
    });

    const data = await response.json();
    // #디버깅용 코드 :
    console.log("1. 리트코드 목록 요청 응답:", JSON.stringify(data).substring(0, 250));
    const submissions = data.data?.recentAcSubmissionList || [];
    return submissions.filter(sub => sub.statusDisplay === 'Accepted');
}

// 제출 ID로 상세 코드 및 난이도 가져오기
async function getSubmissionDetails(id) {
    const response = await fetch('https://leetcode.com/graphql', {
        method: 'POST',
        headers: {
            'Cookie': `LEETCODE_SESSION=${LEETCODE_SESSION};`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            query: `
            query submissionDetails($id: Int!) {
                submissionDetails(submissionId: $id) {
                    code
                    memory
                    runtime
                    question {
                        questionFrontendId
                        title
                        titleSlug
                        content
                        difficulty
                        topicTags {
                            name
                        }
                    }
                    lang {
                        name
                        verboseName
                    }
                }
            }
            `,
            variables: { id: parseInt(id) }
        })
    });
    const data = await response.json();
    return data.data?.submissionDetails;
}

// 메인 실행 함수 구현
async function main() {
    if (!LEETCODE_SESSION) {
        console.error("Error: LEETCODE_SESSION 환경변수가 설정되지 않았습니다.");
        process.exit(1);
    }

    console.log("리트코드 API에서 최근 통과 기록을 조회합니다...");
    const acceptedIds = await getRecentSubmissions();
    console.log(`✅ 통과한 문제 ${acceptedSubmissions.length}개 발견`);

    for (const subId of acceptedIds) {
        const details = await getSubmissionDetails(subId);
        if (!details) continue;

        // const code = details.code;
        // const difficulty = details.question.difficulty;
        // const problemSlug = details.question.titleSlug;
        // const title = details.question.title;
        // const frontendId = details.question.questionFrontendId;
        // const content = details.question.content;
        // const runtime = sub.runtime || "N/A";
        // const memory = sub.memory || "N/A";

        const langName = details.lang.name;

        // 알고리즘 분류 추가
        // const topics = details.question.topicTags.map(tag => tag.name).join(', ') || "None";

        // // 폴더 및 파일명
        // const safeTitle = title.replace(/[<>:"\/\\|?*]/g, '');
        // const folderName = `${frontendId}. ${safeTitle}`; // 예: 718. Maximum Length of...
        //
        // // 폴더 경로 생성
        // const savePath = path.join(TARGET_FOLDER, difficulty, folderName);

        const { code, question, lang, memory, runtime } = details;
        const { difficulty, title, titleSlug, content, questionFrontendId, topicTags } = question;
        const safeTitle = title.replace(/[<>:"\/\\|?*]/g, '');
        const folderName = `${questionFrontendId}. ${safeTitle}`;
        const savePath = path.join(TARGET_FOLDER, difficulty, folderName);

        // 폴더 없으면 생성 (recursive 옵션 : 상위 폴더까지 한 번에 생성)
        await fs.mkdir(savePath, { recursive: true });

        const ext = { 'java': '.java', 'python3': '.py', 'python': '.py', 'cpp': '.cpp', 'javascript': '.js' }[lang.name] || '.txt';
        const topics = topicTags ? topicTags.map(t => t.name).join(', ') : "None";

        // const ext = EXTENSION_MAP[details.lang.name] || '.txt';
        const codeFilePath = path.join(savePath, `${safeTitle}${ext}`);
        const readmeFilePath = path.join(savePath, 'README.md');

        // README 내용 구성! 커스터마이징 가능 ==============================================================================================
        const readmeContent = `# [${difficulty}] [${title}](https://leetcode.com/problems/${problemSlug}/) - ${frontendId}\n\n`
            + `### 성능 요약\n메모리: ${memory}, 시간: ${runtime}\n\n`
            + `### 분류\n${topics}\n\n`
            + `### 문제 설명\n${content}\n`;

        await fs.writeFile(codeFilePath, code, 'utf8');
        await fs.writeFile(readmeFilePath, readmeContent, 'utf8');

        // 개별 Git 커밋 진행
        const commitSubject = `[${frontendId}] ${title} - ${langName} (${runtime}, ${memory})`;
        const commitBody = `[Category]\n${topics}`;
        // const fullCommitMsg = `${commitSubject}\n\n${commitBody}`;
        const fullCommitMsg = `[${questionFrontendId}] ${title} - ${lang.verboseName || lang.name} (${runtime}, ${memory})\n\n[Category]\n${topics}`;

        // try {
        //     // 해당 문제의 폴더만 Staging
        //     await execAsync(`git add "${savePath}"`);
        //
        //     // 따옴표 깨짐 방지 : 커밋 메시지를 임시 파일로 만들어 사용
        //     await fs.writeFile('.commit_msg.txt', fullCommitMsg, 'utf8');
        //     await execAsync(`git commit -F .commit_msg.txt`);
        //
        //     console.log(`커밋 성공: ${commitSubject}`);
        // } catch (error) {
        //     // 변경 사항이 없어서 커밋할 게 없는 경우 무시
        //     console.log(`변경 사항 없음: ${folderName}`);
        // }

        try {
            execSync(`git add "${savePath}"`);
            await fs.writeFile('.commit_msg.txt', fullCommitMsg, 'utf8');
            execSync(`git commit -F .commit_msg.txt`);
            console.log(`✅ 커밋 성공: ${folderName}`);
        } catch (error) {
            console.log(`변경사항 없음 (스킵): ${folderName}`);
        }
    }
}

main();