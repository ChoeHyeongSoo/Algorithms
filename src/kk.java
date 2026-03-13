import java.io.*;
import java.util.*;

public class kk {
    // ================= [ 설정 구간 ] =================
    private static final int PREV_NUM = 1; // 이전 기록 불러오기 (0이면 무시)
    private static final int SAVE_NUM = 2; // 이번 배치 저장 번호
    // ===============================================

    public static void main(String[] args) {
        String[][] arr = new String[5][6];
        // [수정] 정확히 3자리만 비우도록 설정 (27명 + 3자리 = 30칸)
        int[][] notAvailable = {{4, 0}, {0, 2}, {1, 3}};

        // [인원 리스트] 총 27명
        List<String> frontGroup = new ArrayList<>(Arrays.asList(
                "양지훈", "한결", "권태혁", "오현우", "홍성경", "유연우", "김윤영", "박상은", "박재명", "서진주"
        ));
        List<String> others = new ArrayList<>(Arrays.asList(
                "김재원", "김영현", "김정윤", "최재성", "오서현", "김태정", "신재령",
                "김형수", "이원빈", "김현수", "이승민", "김서연", "이진욱", "최형수",
                "전우석", "최주연", "박화랑"
        ));

        // [가중치 설정] 숫자가 작을수록 뒷줄(4행 쪽)에 앉을 확률이 매우 높아짐
        Map<String, Double> weights = new HashMap<>();
        weights.put("김재원", 0.1);
        weights.put("김영현", 0.1);
        weights.put("최재성", 0.1);

        String loadFileName = "history" + PREV_NUM + ".txt";
        String saveFileName = "history" + SAVE_NUM + ".txt";
        Map<String, String> lastHistory = (PREV_NUM == 0) ? new HashMap<>() : loadHistory(loadFileName);

        int attempt = 0;
        while (true) {
            attempt++;
            Collections.shuffle(frontGroup);
            // 가중치를 적용하여 others 섞기
            List<String> weightedOthers = shuffleByWeight(others, weights);

            int fIdx = 0;
            int oIdx = 0;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (isNotAvailable(i, j, notAvailable)) {
                        arr[i][j] = "X";
                    } else if (i <= 1 && fIdx < frontGroup.size()) {
                        arr[i][j] = frontGroup.get(fIdx++);
                    } else if (oIdx < weightedOthers.size()) {
                        arr[i][j] = weightedOthers.get(oIdx++);
                    } else {
                        arr[i][j] = "X"; // 인원이 모자랄 경우만 X
                    }
                }
            }

            // 중복 검증 (이전 자리와 똑같으면 다시 셔플)
            if (isSeatUnique(arr, lastHistory)) break;

            if (attempt > 50000) {
                System.out.println("중복 없는 배치를 찾지 못했습니다. 제약을 완화하세요.");
                return;
            }
        }
        printAndSave(arr, saveFileName);
    }

    // 가중치 기반 셔플: 점수가 낮을수록 리스트의 뒤쪽으로 감
    private static List<String> shuffleByWeight(List<String> items, Map<String, Double> weights) {
        List<Map.Entry<String, Double>> scoreList = new ArrayList<>();
        Random rand = new Random();
        for (String name : items) {
            double w = weights.getOrDefault(name, 1.0);
            double score = rand.nextDouble() * w; // 가중치가 낮으면 점수도 낮아질 확률 높음
            scoreList.add(new AbstractMap.SimpleEntry<>(name, score));
        }
        scoreList.sort((a, b) -> Double.compare(b.getValue(), a.getValue())); // 내림차순 정렬
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Double> e : scoreList) result.add(e.getKey());
        return result;
    }

    private static boolean isSeatUnique(String[][] current, Map<String, String> history) {
        if (history.isEmpty()) return true;
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {
                if (current[i][j].equals("X")) continue;
                String key = i + "," + j;
                if (history.containsKey(key) && history.get(key).equals(current[i][j])) return false;
            }
        }
        return true;
    }

    private static Map<String, String> loadHistory(String fileName) {
        Map<String, String> history = new HashMap<>();
        File file = new File(fileName);
        if (!file.exists()) return history;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) history.put(parts[0], parts[1]);
            }
        } catch (IOException e) { System.out.println("로드 실패: " + e.getMessage()); }
        return history;
    }

    private static void printAndSave(String[][] arr, String fileName) {
        System.out.println("\n\t\t[ SCREEN ]");
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.printf("[%s]\t", arr[i][j]);
                    pw.println(i + "," + j + ":" + arr[i][j]);
                }
                System.out.println();
            }
            System.out.println("\n결과 저장 완료: " + fileName);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static boolean isNotAvailable(int r, int c, int[][] notAvailable) {
        for (int[] pos : notAvailable) if (pos[0] == r && pos[1] == c) return true;
        return false;
    }
}