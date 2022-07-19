package interview.Baidu;

public class solution {

    public static void main(String[] args) {
        int[] arr1 = new int[]{4,5,6,7,0,1,2};
        int[] arr = new int[]{3,1};
        int target = 1;
        int res = search(arr,target);
        System.out.println(res);
    }

    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int n = nums.length;
        if(n == 1){
            return nums[0] == target ? 0 : -1;
        }

        int left = 0,right = n-1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int tmp = nums[mid];
            if(tmp == target){
                return mid;
            }else if (nums[0] <= tmp){
                //左边是递增的
                if(nums[0] <= target && target < tmp){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                //落到右边递增的区间
                if(tmp < target && nums[n-1] >= target){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
