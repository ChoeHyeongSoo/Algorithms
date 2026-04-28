class Solution {
    public static int findLength(int[] nums1, int[] nums2) {

        int n = nums1.length, m = nums2.length;

        int max = 0;

        for (int diff = -(n-1); diff <= m-1; diff++) {

            int curr = 0;

            for (int i = 0; i < n; i++) {
                int j = i + diff;

                if (j < 0 || j >= m) continue;

                if (nums1[i]==nums2[j]) {
                    curr++;
                    max = Math.max(curr, max);
                } else
                    curr=0;
            }
        }
        return max;
    }
}