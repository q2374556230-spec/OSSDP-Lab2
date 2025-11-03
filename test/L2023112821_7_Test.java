import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * 测试类：L2023112821_7_Test
 * 学号：2023112821
 * 题目：Solution7 - 交换字符使字符串变为字典序最小
 * 
 * 测试用例设计原则：
 * 1. 等价类划分：
 *    - 正常情况：包含多个可交换对的字符串
 *    - 边界情况：空pairs数组、单个pair、字符串长度为1
 *    - 连通性测试：直接连通、间接连通（传递性）
 * 2. 边界值分析：
 *    - 最小规模：单字符字符串、空pairs
 *    - 最大规模：多字符多pairs
 * 3. 特殊情况：
 *    - 已经是字典序最小
 *    - 完全逆序
 *    - 部分连通的情况
 */
public class L2023112821_7_Test {
    
    /**
     * 测试目的：验证示例1的正确性
     * 测试用例：s = "dcab", pairs = [[0,3],[1,2]]
     * 预期输出："bacd"
     * 测试覆盖：基本功能，多个独立的交换对
     */
    @Test
    public void testExample1() {
        Solution7 solution = new Solution7();
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(1, 2));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("bacd", result);
    }
    
    /**
     * 测试目的：验证示例2的正确性，测试传递连通性
     * 测试用例：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
     * 预期输出："abcd"
     * 测试覆盖：通过传递性使多个位置形成连通分量
     */
    @Test
    public void testExample2() {
        Solution7 solution = new Solution7();
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(1, 2));
        pairs.add(Arrays.asList(0, 2));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("abcd", result);
    }
    
    /**
     * 测试目的：验证示例3的正确性，测试完全连通的情况
     * 测试用例：s = "cba", pairs = [[0,1],[1,2]]
     * 预期输出："abc"
     * 测试覆盖：所有字符通过传递性完全连通
     */
    @Test
    public void testExample3() {
        Solution7 solution = new Solution7();
        String s = "cba";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 1));
        pairs.add(Arrays.asList(1, 2));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("abc", result);
    }
    
    /**
     * 测试目的：测试边界情况 - 空pairs数组（修复Bug1）
     * 测试用例：s = "abc", pairs = []
     * 预期输出："abc"
     * 测试覆盖：边界条件，无交换对时应返回原字符串
     */
    @Test
    public void testEmptyPairs() {
        Solution7 solution = new Solution7();
        String s = "abc";
        List<List<Integer>> pairs = new ArrayList<>();
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("abc", result);
    }
    
    /**
     * 测试目的：测试单个交换对的情况
     * 测试用例：s = "ba", pairs = [[0,1]]
     * 预期输出："ab"
     * 测试覆盖：单个交换对的基本功能
     */
    @Test
    public void testSinglePair() {
        Solution7 solution = new Solution7();
        String s = "ba";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 1));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("ab", result);
    }
    
    /**
     * 测试目的：测试已经是字典序最小的情况
     * 测试用例：s = "abc", pairs = [[0,1],[1,2]]
     * 预期输出："abc"
     * 测试覆盖：原字符串已是最优解
     */
    @Test
    public void testAlreadySorted() {
        Solution7 solution = new Solution7();
        String s = "abc";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 1));
        pairs.add(Arrays.asList(1, 2));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("abc", result);
    }
    
    /**
     * 测试目的：测试单字符字符串
     * 测试用例：s = "a", pairs = []
     * 预期输出："a"
     * 测试覆盖：最小规模输入
     */
    @Test
    public void testSingleCharacter() {
        Solution7 solution = new Solution7();
        String s = "a";
        List<List<Integer>> pairs = new ArrayList<>();
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("a", result);
    }
    
    /**
     * 测试目的：测试部分连通的复杂情况
     * 测试用例：s = "dcba", pairs = [[0,3],[2,3]]
     * 预期输出："dbca"
     * 测试覆盖：部分位置连通，部分位置独立
     * 解释：0和3连通，2和3连通，所以0、2、3形成连通分量，字符d、b、a可以互换
     * 位置0、2、3的字符按字典序排列为a、b、d，所以结果为 "d(位置1不变)b(位置2)c(位置1)a(位置3)"
     * 实际上：位置0、2、3形成连通分量，字符为d、b、a，排序后为a、b、d
     * 位置0取a，位置2取b，位置3取d，位置1保持c不变
     * 结果："acbd"
     */
    @Test
    public void testPartialConnected() {
        Solution7 solution = new Solution7();
        String s = "dcba";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(2, 3));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("acbd", result);
    }
    
    /**
     * 测试目的：测试完全逆序的情况
     * 测试用例：s = "edcba", pairs = [[0,1],[1,2],[2,3],[3,4]]
     * 预期输出："abcde"
     * 测试覆盖：所有字符完全连通，完全逆序变为正序
     */
    @Test
    public void testCompleteReverse() {
        Solution7 solution = new Solution7();
        String s = "edcba";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 1));
        pairs.add(Arrays.asList(1, 2));
        pairs.add(Arrays.asList(2, 3));
        pairs.add(Arrays.asList(3, 4));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("abcde", result);
    }
    
    /**
     * 测试目的：测试重复字符的情况
     * 测试用例：s = "aabbcc", pairs = [[0,5],[1,4],[2,3]]
     * 预期输出：相同字符按字典序排列
     * 测试覆盖：包含重复字符的优先队列处理
     */
    @Test
    public void testDuplicateCharacters() {
        Solution7 solution = new Solution7();
        String s = "aabbcc";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 5));
        pairs.add(Arrays.asList(1, 4));
        pairs.add(Arrays.asList(2, 3));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("aabbcc", result);
    }
    
    /**
     * 测试目的：测试较长字符串（修复Bug2 - 并查集大小）
     * 测试用例：s = "qdwyt", pairs = [[0,4],[1,3],[1,2],[2,3]]
     * 预期输出："dqtwy"
     * 测试覆盖：验证并查集大小正确初始化为 len 而非 len-1
     */
    @Test
    public void testLongerString() {
        Solution7 solution = new Solution7();
        String s = "qdwyt";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 4));
        pairs.add(Arrays.asList(1, 3));
        pairs.add(Arrays.asList(1, 2));
        pairs.add(Arrays.asList(2, 3));
        String result = solution.smallestStringWithSwaps(s, pairs);
        assertEquals("qdwyt", result);
    }
}