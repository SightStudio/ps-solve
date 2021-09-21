package com.programmers.p49191;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/49191
 * 순위
 */
class Solution {

   @Test
   void test() {
       int n = 5;
       int[][] result = {
           { 4, 3 },
           { 4, 2 },
           { 3, 2 },
           { 1, 2 },
           { 2, 5 }
       };

       int solution = solution(n, result);

       assertEquals(n, solution);
   }

    public int solution(int n, int[][] results) {
        int answer = 0;
        return answer;
    }
}