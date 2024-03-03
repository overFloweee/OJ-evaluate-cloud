package com.hjw.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main
{
    public static void main(String[] args)
    {
        // System.out.println(new Main().longestValidParentheses(args[0]));

        // List<String> list = Arrays.asList("aaa", "bbb");
        // list.forEach(s -> System.out.println(s));
        // Random random = new Random();
        // System.out.println(random);
        // random.ints().limit(10).forEach(System.out::println);

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(123, 123, 34));
        list.forEach(System.out::println);
    }

    public int longestValidParentheses(String s)
    {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '(')
            {
                stack.push(i);
            }
            else
            {
                stack.pop();
                if (stack.isEmpty())
                {
                    stack.push(i);
                }
                else
                {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

}
