INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760524649101860866, 'java', 'public class Main {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println(a + b);
    }
}
', null, '{"message":"Accepted","memory":100,"time":163}', 2, 1753691949204328450, 1749712093340307458, '2024-02-22 12:39:19', '2024-02-22 12:39:23', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760526842601218050, 'java', 'public class Main {
    public static void main(String[] args) {
        int x = args[0];
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}
', null, '{"message":"Compile Error"}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 12:48:02', '2024-02-22 12:48:03', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760527126354272258, 'java', 'public class Main {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}
', null, '{"message":"Compile Error"}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 12:49:10', '2024-02-22 12:49:11', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760617519817248769, 'java', 'public class Main {
    public static void main(String[] args) {
        isPalindrome(Integer.parseInt(args[0]));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}
', null, '{"message":"Wrong Answer","memory":145,"time":149}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 18:48:22', '2024-02-22 18:48:24', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760618073930944514, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{}', 1, 1760526396809617410, 1749712093340307458, '2024-02-22 18:50:34', '2024-02-22 18:50:34', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760618382120013826, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":27,"time":161}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 18:51:47', '2024-02-22 18:51:49', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760618443704979458, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 12;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":180,"time":161}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 18:52:02', '2024-02-22 18:52:03', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760619582303326210, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 1;
        int record = 1;
        while (true)
        {
            i = x % 1;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Wrong Answer","memory":81,"time":152}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 18:56:33', '2024-02-22 18:56:34', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760632989668691969, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":124,"time":166}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 19:49:50', '2024-02-22 19:51:32', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760635031208415234, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":118,"time":153}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 19:57:57', '2024-02-22 19:58:58', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760635540518555650, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":119,"time":144}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 19:59:58', '2024-02-22 19:59:59', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760635718956830722, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":119,"time":163}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:00:41', '2024-02-22 20:00:47', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760635797100908545, 'java', 'public class Main {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println(a + b);
    }
}
', null, '{"message":"Wrong Answer","memory":112,"time":0}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:00:59', '2024-02-22 20:01:00', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760635866600525825, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":122,"time":172}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:01:16', '2024-02-22 20:01:17', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760635867250642946, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":126,"time":146}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:01:16', '2024-02-22 20:01:18', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760635869662367746, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 0;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}', null, '{"message":"Accepted","memory":122,"time":153}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:01:17', '2024-02-22 20:01:19', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760636916141215746, 'java', 'public class Main {
    public static void main(String[] args) {

    }
}
', null, '{"message":"Wrong Answer","memory":114,"time":156}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:05:26', '2024-02-22 20:05:27', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760636944419213314, 'java', 'public class Main {
    public static void main(String[] args) {

    }
}
', null, '{"message":"Wrong Answer","memory":115,"time":154}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:05:33', '2024-02-22 20:05:34', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760637049398448130, 'java', 'public class Main {
    public static void main(String[] args) {

    }
}
', null, '{"message":"Wrong Answer","memory":129,"time":162}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:05:58', '2024-02-22 20:05:59', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760637539926495234, 'java', 'public class Main {
    public static void main(String[] args) {

    }
}
', null, '{"message":"Wrong Answer","memory":118,"time":186}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:07:55', '2024-02-22 20:07:56', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760638984285409282, 'java', 'public class Main {
    public static void main(String[] args) {

    }
}
', null, '{"message":"Wrong Answer","memory":126,"time":150}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:13:39', '2024-02-22 20:13:40', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760639395926986753, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 12;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}
', null, '{"message":"Wrong Answer","memory":113,"time":151}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:15:17', '2024-02-22 20:15:19', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760639600286060545, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 12;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}
', null, '{"message":"Wrong Answer","memory":126,"time":151}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:16:06', '2024-02-22 20:16:07', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760639931271172098, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 12;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}
', null, '{"message":"Wrong Answer","memory":118,"time":149}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:17:25', '2024-02-22 20:17:26', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760640131809234946, 'java', 'public class Main
{
    public static void main(String[] args)
    {

        System.out.println(isPalindrome(Integer.parseInt(args[0])));
    }

    public static boolean isPalindrome(int x)
    {
        if (x < 0)
        {
            return false;
        }
        int i = 12;
        int sum = 0;
        int record = x;
        while (true)
        {
            i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
            if (x == 0)
            {
                break;
            }
        }
        if (sum == record)
        {
            return true;
        }
        return false;
    }
}
', null, '{"message":"Accepted","memory":121,"time":149}', 2, 1760526396809617410, 1749712093340307458, '2024-02-22 20:18:13', '2024-02-22 20:18:14', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760641905358098433, 'java', 'public class Main {
    public static void main(String[] args) {
        new Main().reverse(Integer.parseInt(args[0]));

    }

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
}
', null, '{"message":"Wrong Answer","memory":111,"time":158}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 20:25:16', '2024-02-22 20:25:17', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760642566451712001, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().method(Integer.parseInt(args[0])));
    }
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


}
', null, '{"message":"Compile Error"}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 20:27:53', '2024-02-22 20:27:54', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760642782420619265, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

}', null, '{"message":"Accepted","memory":123,"time":173}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 20:28:45', '2024-02-22 20:28:46', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760642987257843714, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

}', null, '{"message":"Accepted","memory":129,"time":156}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 20:29:33', '2024-02-22 20:29:35', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760659945177022466, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

}', null, '{"message":"Accepted","memory":120,"time":173}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 21:36:57', '2024-02-22 21:36:59', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760660443426783234, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

}', null, '{"message":"Accepted","memory":120,"time":158}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 21:38:55', '2024-02-22 21:38:57', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760660503187226626, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

}', null, '{"message":"Accepted","memory":122,"time":193}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 21:39:10', '2024-02-22 21:39:11', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760669858989146114, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

}', null, '{"message":"Accepted","memory":124,"time":165}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 22:16:20', '2024-02-22 22:16:22', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760671362559049729, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

}', null, '{"message":"Accepted","memory":111,"time":151}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 22:22:19', '2024-02-22 22:22:20', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760673305100615681, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev1;
    }

}', null, '{"message":"Compile Error"}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 22:30:02', '2024-02-22 22:30:03', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760673359408463874, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().reverse(Integer.parseInt(args[0])));
    }

    public int reverse(int x)
    {
        int rev = 0;
        while (x != 0)
        {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10)
            {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

}', null, '{"message":"Accepted","memory":111,"time":161}', 2, 1760641692954349569, 1749712093340307458, '2024-02-22 22:30:15', '2024-02-22 22:30:16', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760677385189523457, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(Integer.parseInt(args[0])));
    }

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


}
', null, '{"message":"Compile Error"}', 2, 1760676116492255233, 1749712093340307458, '2024-02-22 22:46:15', '2024-02-22 22:46:15', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760861793288781825, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
    }

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


}
', null, '{"message":"Compile Error"}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 10:59:01', '2024-02-23 10:59:02', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760862398833033217, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(args[0],args[1])));
    }

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


}
', null, '{"message":"Compile Error"}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 11:01:25', '2024-02-23 11:01:26', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760862522103627778, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(args[0],args[1]));
    }

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


}
', null, '{"message":"Accepted","memory":123,"time":165}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 11:01:55', '2024-02-23 11:01:56', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760863088309501954, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(args[0],args[1]));
    }

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


}
', null, '{"message":"Wrong Answer","memory":120,"time":161}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 11:04:10', '2024-02-23 11:04:11', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760863882815537153, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(args[0],args[1]));
    }

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


}
', null, '{"message":"Wrong Answer","memory":123,"time":164}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 11:07:19', '2024-02-23 11:07:20', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760864738310946818, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(args[0],args[1]));
    }

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


}
', null, '{"message":"Wrong Answer","memory":118,"time":186}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 11:10:43', '2024-02-23 11:10:44', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760865219485696001, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(args[0],args[1]));
    }

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


}
', null, '{"message":"Accepted","memory":128,"time":158}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 11:12:38', '2024-02-23 11:12:39', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760865441792196609, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(args[0],args[1]));
    }

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


}
', null, '{"message":"Accepted","memory":127,"time":149}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 11:13:31', '2024-02-23 11:13:32', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760876233153380353, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().isMatch(args[0],args[1]));
    }

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


}
', null, '{"message":"Accepted","memory":117,"time":159}', 2, 1760676116492255233, 1749712093340307458, '2024-02-23 11:56:24', '2024-02-23 11:56:25', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760932749327818753, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().lengthOfLongestSubstring(args[0]));
    }
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

}
', null, '{"message":"Compile Error"}', 2, 1760932564736499714, 1749712093340307458, '2024-02-23 15:40:58', '2024-02-23 15:41:00', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760932949547114497, 'java', 'import java.util.HashSet;
import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println(new Main().lengthOfLongestSubstring(args[0]));
    }

    public int lengthOfLongestSubstring(String s)
    {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i != 0)
            {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1)))
            {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

}
', null, '{"message":"Accepted","memory":122,"time":172}', 2, 1760932564736499714, 1749712093340307458, '2024-02-23 15:41:46', '2024-02-23 15:41:47', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760935287154368513, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().jump(args));
    }

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

}', null, '{"message":"Accepted","memory":114,"time":172}', 2, 1760934904315076610, 1749712093340307458, '2024-02-23 15:51:03', '2024-02-23 15:51:04', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760937327066701825, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Wrong Answer","memory":121,"time":145}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 15:59:10', '2024-02-23 15:59:10', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760937350558998529, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Wrong Answer","memory":127,"time":145}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 15:59:15', '2024-02-23 15:59:16', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760937354434535426, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Wrong Answer","memory":128,"time":145}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 15:59:16', '2024-02-23 15:59:17', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760937891716489218, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Wrong Answer","memory":129,"time":164}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 16:01:24', '2024-02-23 16:01:25', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760937973786435586, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Wrong Answer","memory":115,"time":199}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 16:01:44', '2024-02-23 16:01:45', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938154158284802, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Wrong Answer","memory":115,"time":149}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 16:02:27', '2024-02-23 16:04:13', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938632308940801, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Accepted","memory":116,"time":167}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 16:04:21', '2024-02-23 16:04:29', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938734104698882, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Accepted","memory":129,"time":151}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 16:04:45', '2024-02-23 16:04:46', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938735757254658, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Accepted","memory":120,"time":149}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 16:04:45', '2024-02-23 16:04:47', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938737183318017, 'java', 'public class Main
{
    public static void main(String[] args)
    {
        int[] height = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            height[i] = Integer.parseInt(args[i]);
        }
        System.out.println(new Main().maxArea(height));
    }

    public int maxArea(int[] height)
    {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r)
        {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r])
            {
                ++l;
            }
            else
            {
                --r;
            }
        }
        return ans;
    }

}', null, '{"message":"Accepted","memory":121,"time":151}', 2, 1760936501426348033, 1749712093340307458, '2024-02-23 16:04:46', '2024-02-23 16:04:48', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938761745162241, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().jump(args));
    }

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
        return steps;1
    }

}', null, '{"message":"Compile Error"}', 2, 1760934904315076610, 1749712093340307458, '2024-02-23 16:04:52', '2024-02-23 16:04:52', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938763057979394, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().jump(args));
    }

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
        return steps;1
    }

}', null, '{"message":"Compile Error"}', 2, 1760934904315076610, 1749712093340307458, '2024-02-23 16:04:52', '2024-02-23 16:04:52', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938764500819970, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().jump(args));
    }

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
        return steps;1
    }

}', null, '{"message":"Compile Error"}', 2, 1760934904315076610, 1749712093340307458, '2024-02-23 16:04:52', '2024-02-23 16:04:53', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760938795052130305, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().jump(args));
    }

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

}', null, '{"message":"Accepted","memory":120,"time":179}', 2, 1760934904315076610, 1749712093340307458, '2024-02-23 16:05:00', '2024-02-23 16:05:00', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760939927820394497, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().method(Integer.parseInt(args[0])));
    }
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

}
', null, '{"message":"Compile Error"}', 2, 1760939642301538306, 1749712093340307458, '2024-02-23 16:09:30', '2024-02-23 16:09:30', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760940008971788289, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().method(Integer.parseInt(args[0])));
    }
   
}
', null, '{"message":"Compile Error"}', 2, 1760939642301538306, 1749712093340307458, '2024-02-23 16:09:49', '2024-02-23 16:09:50', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760945309301170177, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().method(Integer.parseInt(args[0])));
    }

}
', null, '{"message":"Compile Error"}', 2, 1760944459589705730, 1749712093340307458, '2024-02-23 16:30:53', '2024-02-23 16:30:53', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760945611299446785, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().method(Integer.parseInt(args[0])));
    }

}
', null, '{"message":"Compile Error"}', 2, 1760944833214111745, 1749712093340307458, '2024-02-23 16:32:05', '2024-02-23 16:32:05', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760946294706757634, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().method(Integer.parseInt(args[0])));
    }

}
', null, '{"message":"Compile Error"}', 2, 1760944833214111745, 1754431212694810625, '2024-02-23 16:34:48', '2024-02-23 16:34:48', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760946385320501249, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().method(Integer.parseInt(args[0])));
    }

}
', null, '{"message":"Compile Error"}', 2, 1760944459589705730, 1754431212694810625, '2024-02-23 16:35:09', '2024-02-23 16:35:10', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760946504765890562, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().method(args[0]));
    }
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

}
', null, '{"message":"Compile Error"}', 2, 1760944459589705730, 1754431212694810625, '2024-02-23 16:35:38', '2024-02-23 16:35:38', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760946599674601473, 'java', 'public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().longestValidParentheses(args[0]));
    }
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

}
', null, '{"message":"Compile Error"}', 2, 1760944459589705730, 1754431212694810625, '2024-02-23 16:36:00', '2024-02-23 16:36:01', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760946723125551105, 'java', 'import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().longestValidParentheses(args[0]));
    }
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

}', null, '{"message":"Accepted","memory":112,"time":172}', 2, 1760944459589705730, 1754431212694810625, '2024-02-23 16:36:30', '2024-02-23 16:36:31', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760946757959245826, 'java', 'import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().longestValidParentheses(args[0]));
    }
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

}', null, '{"message":"Accepted","memory":116,"time":166}', 2, 1760944459589705730, 1754431212694810625, '2024-02-23 16:36:38', '2024-02-23 16:36:39', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760946760127700993, 'java', 'import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().longestValidParentheses(args[0]));
    }
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

}', null, '{"message":"Accepted","memory":116,"time":186}', 2, 1760944459589705730, 1754431212694810625, '2024-02-23 16:36:39', '2024-02-23 16:36:41', 0);
INSERT INTO `evaluate-oj`.question_submit (id, language, backendCode, frontedCode, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1760946762015137794, 'java', 'import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().longestValidParentheses(args[0]));
    }
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

}', null, '{"message":"Accepted","memory":129,"time":153}', 2, 1760944459589705730, 1754431212694810625, '2024-02-23 16:36:39', '2024-02-23 16:36:42', 0);
