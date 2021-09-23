package com.programmers.p43238;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43238
 * 입국 심사
 */
class Solution {

    @Test
    void test() {

        // given
        int n = 6;
        int[] times = { 7, 10 };

        // when
        long solution = solution(n, times);

        // then
        assertEquals(28, solution);
    }

    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;

        long left = 0;
        long right = (long) times[times.length - 1] * n;

        while(left <= right) {
            long mid = (left + right) / 2;
            long sum = calcSum(mid, times);

            if(sum < n) { //
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }

    private long calcSum(long mid, int[] times) {
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
            sum += mid/times[i];
        }
        return sum;
    }
}