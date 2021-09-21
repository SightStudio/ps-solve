package com.leetcode.p3981;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution {

    @Test
    void test() {
        // given
        int[][] moves = {
            {0,0}, {2,0},
            {1,1}, {2,1},
            {2,2}
        };

        // when
        String tictactoe = tictactoe(moves);

        // then
        assertEquals("A", tictactoe);
    }

    public String tictactoe(int[][] moves) {
        return "";
    }
}