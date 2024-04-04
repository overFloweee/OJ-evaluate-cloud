package com.hjw.api;

import java.util.ArrayList;

/**
 * @author qifei
 * @since 2024-03-28
 */
public class test
{
    public static void main(String[] args)
    {
        // int[] arr = {2, 3, 1, 2, 4, 3};
        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1};
        // int[] arr = {1, 4, 4};
        int length = getLength(arr, 11);
        System.out.println(length);
    }


    public static int getLength(int[] arr, int m)
    {
        int max = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++)
        {

            if (arr[i] > max)
            {
                max = arr[i];
                index = i;
            }
        }
        // System.out.println(max);
        // System.out.println(index);

        if (max >= m)
        {
            return 1;
        }

        int p1 = index + 1;
        int p2 = index - 1;
        int i = 1;
        while (p1 < arr.length && p2 >= 0)
        {
            if (max + arr[p1] >= m)
            {
                return 1 + i;
            }
            else if (max + arr[p2] >= m)
            {
                return 1 + i;
            }
            else
            {
                p1++;
                p2++;
                i++;
            }
        }

        return 0;


    }

}
