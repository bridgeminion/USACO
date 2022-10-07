public class LeetcodeScratch {

    public static int jump(int[] nums) {
            int[] dp = new int[nums.length];
            for (int i=0; i < nums.length; i++){
                dp[i] = nums.length+1;
            }
            dp[0] = 0;
            for (int i=0; i < nums.length; i++){
                for (int j=1; j <= nums[i]; j++){
                    if (i+j < nums.length){
                        dp[i+j] = Math.min(dp[i+j], dp[i]+1);
                    }
                }
            }
            return dp[nums.length-1];
        }

    public static void main(String[] args) {
        System.out.println(jump (new int[]{2, 3, 1, 1, 4}));
    }
}
