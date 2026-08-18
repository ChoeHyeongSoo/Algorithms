class Solution {
    public int[] solution(String[] park, String[] routes) {

        // 시작점 찾기
        int X = 0, Y = 0,
            width = park[0].length(),
            height = park.length;
        outer:
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){
                
                if (park[i].charAt(j)!='S') continue;
                Y = i; X = j; break outer;
                
            }
        }
        
        // 명령 수행 : 무시 조건 설정
        for (String line : routes) {
            
            char dir = line.charAt(0);
            int dist = line.charAt(2) - '0';
            
            operation:
            switch (dir) {
                case 'E':
                    if (X + dist >= width) break;
                    for (int d=1; d<=dist; d++)
                        if (park[Y].charAt(X+d)=='X') break operation;
                    X += dist;
                    break;
                case 'W':
                    if (X - dist < 0) break;
                    for (int d=1; d<=dist; d++)
                        if (park[Y].charAt(X-d)=='X') break operation;
                    X -= dist;
                    break;
                case 'S':
                    if (Y + dist >= height) break;
                    for (int d=1; d<=dist; d++)
                        if (park[Y+d].charAt(X)=='X') break operation;
                    Y += dist;
                    break;
                case 'N':
                    if (Y - dist < 0) break;
                    for (int d=1; d<=dist; d++)
                        if (park[Y-d].charAt(X)=='X') break operation;
                    Y -= dist;
                    break;
            }
            
        }
        
        
        return new int[]{Y, X};
    }
}