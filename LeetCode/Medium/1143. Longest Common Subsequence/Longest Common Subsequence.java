class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        
        int[][] dp_table = new int[text1.length()+1][text2.length()+1];
//        dp_table[0][0] = (text1.charAt(0)==text2.charAt(0)) ? 1 : 0;
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i-1)==text2.charAt(j-1)) dp_table[i][j] = dp_table[i-1][j-1]+1;
                else dp_table[i][j] = Math.max(dp_table[i][j-1], dp_table[i-1][j]);
            }
        }

        return dp_table[text1.length()][text2.length()];
    }
}