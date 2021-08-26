package LeetCodeSolution;

import com.sun.javafx.image.IntPixelGetter;

import javax.print.attribute.standard.PresentationDirection;
import java.util.*;

public class Array {


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

     class Solution18 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);
            int len = nums.length;
            for(int i = 0; i < len - 3; i++){
                if(i != 0 && nums[i-1] == nums[i]){
                    continue;
                }
                if(nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target){
                    break;
                }
                if(nums[i] + nums[len-1] + nums[len-2] + nums[len-3] < target){
                    continue;
                }
                for(int j = i + 1; j < len - 2; j++){
                    if(j > i + 1 && nums[j-1] == nums[j]){
                        continue;
                    }
                    if(nums[i] + nums[j] + nums[j+1] + nums[j+2] > target){
                        break;
                    }
                    if(nums[i] + nums[j] + nums[len-1] + nums[len-2] < target){
                        continue;
                    }
                    int cur = nums[i] + nums[j];
                    int left = j + 1;
                    int right = len - 1;
                    while(left < right){
                        if(cur + nums[left] + nums[right] == target){
                            List<Integer> list = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                            result.add(list);
                            while(left < right && nums[left+1] == nums[left]) left++;
                            while(left < right && nums[right-1] == nums[right]) right--;
                            left++;
                            right--;
                        }
                        else if(cur + nums[left] + nums[right] < target) left++;
                        else right--;
                    }
                }
            }
            return result;
        }
    }

    class Solution18dfs {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int cur = 0;
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            dfs(nums, target,0);
            return ans;
        }
        public void dfs(int[] nums, int target, int sub){
            if(list.size() == 4){
                if(cur == target){
                    ans.add(new ArrayList<>(list));
                }
                return;
            }
            for(int i = sub; i < nums.length; i++){
                if(4 - list.size() > nums.length - i) return;
                if(i > sub && nums[i] == nums[i-1]) continue;
                if(i < nums.length - 1 && cur + nums[i] + (3 - list.size()) * nums[i + 1] > target) return;
                if(i < nums.length - 1 && cur + nums[i] + (3 - list.size()) * nums[nums.length - 1] < target) continue;
                cur += nums[i];
                list.add(nums[i]);
                dfs(nums, target, i+1);
                cur -= nums[i];
                list.remove(list.size() - 1);
            }
        }
    }

    class Solution26 {
        public int removeDuplicates(int[] nums) {
            if(nums == null || nums.length == 0){
                return 0;
            }
            int curPos = 1;
            for(int i = 1; i < nums.length; i++){
                if(nums[i] != nums[curPos-1]){
                    nums[curPos] = nums[i];
                    curPos++;
                }
            }
            return curPos;
        }
    }

    class Solution27 {
        public int removeElement(int[] nums, int val) {
            if(nums == null || nums.length == 0){
                return 0;
            }
            int curPos = 0;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] != val){
                    nums[curPos] = nums[i];
                    curPos++;
                }
            }
            return curPos;
        }
    }

     class Solution39 {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            dfs(candidates, 0, target);
            return ans;
        }

        public void dfs(int[] candidates, int begin, int target){
            if(target == 0){
                ans.add(new ArrayList<>(list));
                return;
            }
            for(int i = begin; i < candidates.length && candidates[i] <= target; i++){
                list.add(candidates[i]);
                dfs(candidates, i, target-candidates[i]);
                list.remove(list.size() - 1);
            }
        }
    }

    class Solution40 {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            dfs(candidates, 0, target);
            return ans;
        }
        public void dfs(int[] candidates, int begin, int target){
            if(target == 0){
                ans.add(new ArrayList<>(list));
                return;
            }
            for(int i = begin; i < candidates.length && candidates[i] <= target; i++){
                if(i > begin && candidates[i] == candidates[i-1]){
                    continue;
                }
                list.add(candidates[i]);
                dfs(candidates, i + 1, target - candidates[i]);
                list.remove(list.size()-1);
            }
        }
    }

    class Solution41 {
        public int firstMissingPositive(int[] nums) {
            int len = nums.length;
            for(int i = 0; i < len; i++){
                while(nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]){
                    swap(nums, i, nums[i] - 1);
                }
            }
            for(int i = 0; i < len; i++){
                if(nums[i] != i + 1){
                    return i + 1;
                }
            }
            return len + 1;
        }

        public void swap(int[] nums, int i, int j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    class Solution42dp {
        public int trap(int[] height) {
            int n = height.length;
            int[][] dp = new int[n][2];
            dp[0][0] = height[0];
            dp[n-1][1] = height[n-1];
            for(int i = 1; i < n; i++){
                dp[i][0] = Math.max(dp[i-1][0], height[i]);
            }
            for(int i = n - 2; i >= 0; i--){
                dp[i][1] = Math.max(dp[i+1][1], height[i]);
            }
            int ans = 0;
            for(int i = 1; i < n-1; i++){
                ans += Math.min(dp[i][0], dp[i][1]) - height[i];
            }
            return ans;
        }
    }

    class Solution42point {
        public int trap(int[] height) {
            int leftMax = 0;
            int rightMax = 0;
            int left = 0;
            int right = height.length - 1;
            int ans = 0;
            while(left <= right){
                if(leftMax < rightMax){
                    leftMax = Math.max(leftMax, height[left]);
                    ans += leftMax - height[left++];
                }else{
                    rightMax = Math.max(rightMax, height[right]);
                    ans += rightMax - height[right--];
                }
            }
            return ans;
        }
    }

    class Solution46 {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
            boolean[] used = new boolean[nums.length];
            dfs(nums, used);
            return ans;
        }
        public void dfs(int[] nums, boolean[] used){
            if(list.size() == nums.length){
                ans.add(new ArrayList<>(list));
                return;
            }
            for(int i = 0; i < nums.length; i++){
                if(!used[i]){
                    used[i] = true;
                    list.add(nums[i]);
                    dfs(nums, used);
                    used[i] = false;
                    list.remove(list.size()-1);
                }
            }
        }
    }

    class Solution47 {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            boolean[] used = new boolean[nums.length];
            dfs(nums, used, 0);
            return ans;
        }
        public void dfs(int[] nums, boolean[] used,int index){
            if(index == nums.length){
                ans.add(new ArrayList<>(list));
                return;
            }
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i != 0 && nums[i] == nums[i-1] && !used[i-1]){
                    continue;
                }
                used[i] = true;
                list.add(nums[i]);
                dfs(nums, used, index + 1);
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    class Solution48 {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            //沿对角线翻转
            for(int i = 0; i < n; i++){
                for(int j = i+1; j < n; j++){
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = tmp;
                }
            }
            //沿中线翻转
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n/2; j++){
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[i][n-j-1];
                    matrix[i][n-j-1] = tmp;
                }
            }
        }
    }

    class Solution53 {
        public int maxSubArray(int[] nums) {
            int preMax = 0;
            int ans = nums[0];
            for (int num : nums) {
                preMax = Math.max(num, preMax + num);
                ans = Math.max(preMax, ans);
            }
            return ans;
        }
    }














}
