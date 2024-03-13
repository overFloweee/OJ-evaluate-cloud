INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1753691949204328450, '1.两数之和', '# 两数之和

## 输出用例
1 2

## 输出用例
3', '["简单","数组","哈希表"]', 'a + b', 3, 44, 23, '[{"input":"1 2","output":"3"},{"input":"3 4","output":"7"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":999}', 0, 0, 1749712093340307458, '2024-02-03 16:08:37', '2024-02-23 16:11:08', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760526396809617410, '9.回文数', '给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

例如，121 是回文，而 123 不是。

- 示例 1：

> 输入：x = 121
> 
> 输出：true
-  示例 2：

> 输入：x = -121
> 
> 输出：false
> 
> 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
- 示例 3：
> 
> 输入：x = 10
> 
> 输出：false
> 
> 解释：从右向左读, 为 01 。因此它不是一个回文数。', '["简单","数组","数学"]', '', null, 14, 5, '[{"input":"121","output":"true"},{"input":"-121","output":"false"},{"input":"10","output":"false"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-22 12:46:16', '2024-02-23 11:14:48', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760641692954349569, '7.整数反转', '给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。
 

示例 1：

输入：x = 123

输出：321

示例 2：

输入：x = -123

输出：-321

示例 3：

输入：x = 120

输出：21

示例 4：

输入：x = 0

输出：0


- 提示：

-231 <= x <= 231 - 1', '["中等","数学"]', '```

public int reverse(int x) {
    int rev = 0;
    while (x != 0) {
        if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
            return 0;
        }
        int digit = x % 10;
        x /= 10;
        rev = rev * 10 + digit;
    }
    return rev;
}


```', null, 13, 8, '[{"input":"123","output":"321"},{"input":"-123","output":"-321"},{"input":"120","output":"21"},{"input":"0","output":"0"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-22 20:24:25', '2024-02-23 11:14:27', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760676116492255233, '10.正则表达式匹配', '给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 \'.\' 和 \'*\' 的正则表达式匹配。

\'.\' 匹配任意单个字符
\'*\' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

 
示例 1：

输入：s = "aa", p = "a"

输出：false

解释："a" 无法匹配 "aa" 整个字符串。

示例 2:

输入：s = "aa", p = "a*"

输出：true

解释：因为 \'*\' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 \'a\'。因此，字符串 "aa" 可被视为 \'a\' 重复了一次。
示例 3：

输入：s = "ab", p = ".*"

输出：true

解释：".*" 表示可匹配零个或多个（\'*\'）任意字符（\'.\'）。
 

提示：

- 1 <= s.length <= 20
- 1 <= p.length <= 20
- s 只包含从 a-z 的小写字母。
- p 只包含从 a-z 的小写字母，以及字符 . 和 *。
- 保证每次出现字符 * 时，前面都匹配到有效的字符', '["困难","递归","字符串","动态规划"]', '```
public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == \'*\') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

public boolean matches(String s, String p, int i, int j) {
    if (i == 0) {
        return false;
    }
    if (p.charAt(j - 1) == \'.\') {
        return true;
    }
    return s.charAt(i - 1) == p.charAt(j - 1);
}
```', null, 10, 4, '[{"input":"\\"aa\\" \\"a\\"","output":"false"},{"input":"\\"aa\\" \\"a*\\"","output":"true"},{"input":"\\"ab\\" \\".*\\"","output":"true"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-22 22:41:12', '2024-02-23 11:56:25', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760866683490729985, '371. 两整数之和', '给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。

 

示例 1：

输入：a = 1, b = 2

输出：3

示例 2：

输入：a = 2, b = 3

输出：5
 

提示：

- -1000 <= a, b <= 1000', '["中等","位运算","数学"]', '```
public int getSum(int a, int b) {
    while (b != 0) {
        int carry = (a & b) << 1;
        a = a ^ b;
        b = carry;
    }
    return a;
}

```', null, 14, 4, '[{"input":"1 2","output":"3"},{"input":"2 3","output":"5"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 11:18:27', '2024-02-23 16:55:33', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760932564736499714, '3. 无重复字符的最长子串', '给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: s = "bbbbb"

输出: 1

解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:

输入: s = "pwwkew"

输出: 3

解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。', '["中等","哈希表","字符串","滑动窗口"]', '```
public int lengthOfLongestSubstring(String s) {
    // 哈希集合，记录每个字符是否出现过
    Set<Character> occ = new HashSet<Character>();
    int n = s.length();
    // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
    int rk = -1, ans = 0;
    for (int i = 0; i < n; ++i) {
        if (i != 0) {
            // 左指针向右移动一格，移除一个字符
            occ.remove(s.charAt(i - 1));
        }
        while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
            // 不断地移动右指针
            occ.add(s.charAt(rk + 1));
            ++rk;
        }
        // 第 i 到 rk 个字符是一个极长的无重复字符子串
        ans = Math.max(ans, rk - i + 1);
    }
    return ans;
}

```', null, 5, 2, '[{"input":"\\"abcabcbb\\"","output":"3"},{"input":"\\"bbbbb\\"","output":"1"},{"input":"\\"pwwkew\\"","output":"3"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 15:40:14', '2024-02-23 16:55:33', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760934904315076610, '45. 跳跃游戏 II', '给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。

每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:

0 <= j <= nums[i] 
i + j < n
返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

 

示例 1:

输入: nums = [2,3,1,1,4]

输出: 2

解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

示例 2:

输入: nums = [2,3,0,1,4]

输出: 2', '["中等","贪心","数组","动态规划"]', '```
public int jump(String[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, Integer.parseInt(i + nums[i]));
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

```', null, 5, 2, '[{"input":"2 3 1 1 4","output":"2"},{"input":"2 3 0 1 4","output":"2"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 15:49:32', '2024-02-23 16:55:33', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760936501426348033, '11. 盛最多水的容器', '给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。

输入：[1,8,6,2,5,4,8,3,7]

输出：49 

解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
示例 2：

输入：height = [1,1]

输出：1', '["中等","贪心","数组","双指针"]', '```
public int maxArea(int[] height) {
    int l = 0, r = height.length - 1;
    int ans = 0;
    while (l < r) {
        int area = Math.min(height[l], height[r]) * (r - l);
        ans = Math.max(ans, area);
        if (height[l] <= height[r]) {
            ++l;
        }
        else {
            --r;
        }
    }
    return ans;
}
```', null, 10, 4, '[{"input":"1 8 6 2 5 4 8 3 7","output":"49 "},{"input":"1 1","output":"1"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 15:55:53', '2024-02-23 16:55:33', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760939642301538306, '31. 下一个排列', '整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。

必须 原地 修改，只允许使用额外常数空间。

示例 1：

输入：nums = [1,2,3]

输出：[1,3,2]

示例 2：

输入：nums = [3,2,1]

输出：[1,2,3]

示例 3：

输入：nums = [1,1,5]

输出：[1,5,1]', '["中等","数组","双指针"]', '```
public void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }
    if (i >= 0) {
        int j = nums.length - 1;
        while (j >= 0 && nums[i] >= nums[j]) {
            j--;
        }
        swap(nums, i, j);
    }
    reverse(nums, i + 1);
}

public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

public void reverse(int[] nums, int start) {
    int left = start, right = nums.length - 1;
    while (left < right) {
        swap(nums, left, right);
        left++;
        right--;
    }
}


```', null, 5, 2, '[{"input":"1 2 3","output":"1 3 2"},{"input":"3 2 1","output":"1 2 3"},{"input":"1 1 5","output":"1 5 1"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 16:08:22', '2024-02-23 16:55:44', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760942986038435841, '79. 单词搜索', '给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例 1：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"

输出：true

示例 2：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"

输出：true

示例 3：


输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"

输出：false
 

提示：

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board 和 word 仅由大小写英文字母组成', '["中等","数组","回溯","矩阵"]', '```
public boolean exist(char[][] board, String word) {
    int h = board.length, w = board[0].length;
    boolean[][] visited = new boolean[h][w];
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            boolean flag = check(board, visited, i, j, word, 0);
            if (flag) {
                return true;
            }
        }
    }
    return false;
}

public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
    if (board[i][j] != s.charAt(k)) {
        return false;
    } else if (k == s.length() - 1) {
        return true;
    }
    visited[i][j] = true;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean result = false;
    for (int[] dir : directions) {
        int newi = i + dir[0], newj = j + dir[1];
        if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
            if (!visited[newi][newj]) {
                boolean flag = check(board, visited, newi, newj, s, k + 1);
                if (flag) {
                    result = true;
                    break;
                }
            }
        }
    }
    visited[i][j] = false;
    return result;
}

```', null, 65, 32, '[{"input":"board = [[\\"A\\",\\"B\\",\\"C\\",\\"E\\"],[\\"S\\",\\"F\\",\\"C\\",\\"S\\"],[\\"A\\",\\"D\\",\\"E\\",\\"E\\"]], word = \\"ABCCED\\"","output":"true"},{"input":"board = [[\\"A\\",\\"B\\",\\"C\\",\\"E\\"],[\\"S\\",\\"F\\",\\"C\\",\\"S\\"],[\\"A\\",\\"D\\",\\"E\\",\\"E\\"]], word = \\"SEE\\"","output":"true"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 16:21:39', '2024-02-23 16:56:39', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760943480756592641, '128. 最长连续序列', '给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

 

示例 1：

输入：nums = [100,4,200,1,3,2]

输出：4

解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]

输出：9', '["中等","并查集","数组","哈希表"]', '```
public int longestConsecutive(int[] nums) {
    Set<Integer> num_set = new HashSet<Integer>();
    for (int num : nums) {
        num_set.add(num);
    }

    int longestStreak = 0;

    for (int num : num_set) {
        if (!num_set.contains(num - 1)) {
            int currentNum = num;
            int currentStreak = 1;

            while (num_set.contains(currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }

            longestStreak = Math.max(longestStreak, currentStreak);
        }
    }

    return longestStreak;
}
```', null, 23, 10, '[{"input":"100 4 200 1 3 2","output":"4"},{"input":"0 3 7 2 5 8 4 6 01","output":"9"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 16:23:37', '2024-02-23 16:56:09', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760944459589705730, '32. 最长有效括号', '给你一个只包含 \'(\' 和 \')\' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

 

示例 1：


输入：s = "(()"

输出：2

解释：最长有效括号子串是 "()"

示例 2：

输入：s = ")()())"

输出：4

解释：最长有效括号子串是 "()()"

示例 3：

输入：s = ""

输出：0', '["困难","栈","字符串","动态规划"]', '```
public int longestValidParentheses(String s) {
    int maxans = 0;
    Deque<Integer> stack = new LinkedList<Integer>();
    stack.push(-1);
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == \'(\') {
            stack.push(i);
        } else {
            stack.pop();
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                maxans = Math.max(maxans, i - stack.peek());
            }
        }
    }
    return maxans;
}
```', null, 32, 11, '[{"input":"\\"(()\\"","output":"2"},{"input":"\\")()())\\"","output":"4"},{"input":"\\"\\"","output":"0"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 16:27:30', '2024-02-23 16:56:09', 0);
INSERT INTO `evaluate-oj`.question (id, title, content, tags, answer, difficulty, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1760944833214111745, '46. 全排列', '给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

 

示例 1：


输入：nums = [1,2,3]

输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

示例 2：

输入：nums = [0,1]

输出：[[0,1],[1,0]]

示例 3：

输入：nums = [1]

输出：[[1]]', '["中等","数组","回溯"]', '```
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    List<Integer> output = new ArrayList<Integer>();
    for (int num : nums) {
        output.add(num);
    }

    int n = nums.length;
    backtrack(n, output, res, 0);
    return res;
}

public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
    // 所有数都填完了
    if (first == n) {
        res.add(new ArrayList<Integer>(output));
    }
    for (int i = first; i < n; i++) {
        // 动态维护数组
        Collections.swap(output, first, i);
        // 继续递归填下一个数
        backtrack(n, output, res, first + 1);
        // 撤销操作
        Collections.swap(output, first, i);
    }
}
```', null, 36, 19, '[{"input":"1 2 3","output":"[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]"},{"input":"0 1","output":"[[0,1],[1,0]]"},{"input":"1","output":"[[1]]"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1749712093340307458, '2024-02-23 16:28:59', '2024-02-23 16:56:09', 0);
