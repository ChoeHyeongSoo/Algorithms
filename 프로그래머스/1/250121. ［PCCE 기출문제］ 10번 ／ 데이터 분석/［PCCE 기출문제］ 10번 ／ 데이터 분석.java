import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer = {};
        
        Map<String, Integer> str2idx = Map.of(
            "code", 0,
            "date", 1,
            "maximum", 2,
            "remain", 3
        );
        
        int filter_idx = str2idx.get(ext),
            sort_idx = str2idx.get(sort_by);
        
        List<int[]> temp = new ArrayList<>();
        
        for (int[] d : data)
            if (d[filter_idx] < val_ext)
                temp.add(d);
        
        Collections.sort(temp, (a, b) -> a[sort_idx] - b[sort_idx]);
                
        return temp.toArray(int[][]::new);
    }
}