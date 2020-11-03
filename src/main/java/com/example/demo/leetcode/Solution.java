package com.example.demo.leetcode;

import org.junit.Test;

import java.util.Objects;

/**
 *  * @Description: leetcode 数组练习
 *  * @Author: chenxue
 *  * @Date: 2020-10-21 17:40
 *  * @since
 *  
 */
public class Solution {
    /**
     * @Description: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @Author: chenxue
     * @Date: 2020-10-21 17:41
     * @throws
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        if(Objects.nonNull(nums) && nums.length == 1) {
            return max;
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i; j < nums.length; j++) {
                max = Math.max(max, temp += nums[j]);
            }
        }
        return max;
    }

    @Test
    public void demo1(){
        int[] arry = {-2,-1};
        int i = maxSubArray(arry);
        System.out.println(i);
    }

}
