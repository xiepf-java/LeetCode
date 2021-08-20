import java.util.*;

public class LeetCodeSolutions {


    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{i, map.get(target - nums[i])};
                }
                map.put(nums[i], i);
            }
            return null;
        }
    }

    class Solution11 {
        public int maxArea(int[] height) {
            int start = 0;
            int end = height.length - 1;
            int maxSize = 0;
            while (start < end) {
                int size = (end - start) * Math.min(height[start], height[end]);
                if (size > maxSize) {
                    maxSize = size;
                }
                if (height[start] >= height[end]) {
                    end--;
                } else {
                    start++;
                }
            }
            return maxSize;
        }
    }

    class Solution15 {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len - 2; i++) {
                if (nums[i] > 0) {
                    return result;
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int curNum = nums[i];
                int left = i + 1;
                int right = len - 1;
                while (left < right) {
                    if (curNum + nums[left] + nums[right] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(curNum);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (curNum + nums[left] + nums[right] > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
            return result;
        }

        class Solution16 {
            public int threeSumClosest(int[] nums, int target) {
                Arrays.sort(nums);
                int len = nums.length;
                //不能赋int MaxValue 会产生越界 默认值最好设为某个可能解
                int min = nums[0] + nums[1] + nums[len-1];
                for (int i = 0; i < len - 2; i++) {
                    int curNum = nums[i];
                    int left = i + 1;
                    int right = len - 1;
                    while (left < right) {
                        int total = curNum + nums[left] + nums[right];
                        if (Math.abs(target - total) < Math.abs(target - min)) {
                            min = total;
                        }
                        if (total > target) {
                            right--;
                        } else if(total < target){
                            left++;
                        }else{
                            return min;
                        }
                    }
                }
                return min;
            }
        }
    }
}
