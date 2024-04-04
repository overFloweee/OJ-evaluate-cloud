package com.hjw.api.service;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        // String[] strings = {"1", "2"};
        // System.out.println(new Main().getSum(Integer.parseInt(strings[0]),
        //     Integer.parseInt(strings[1])));

        System.out.println(StrUtil.isNotEmpty(null));
    }

    public int getSum(int a, int b)
    {
        while (b != 0)
        {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
