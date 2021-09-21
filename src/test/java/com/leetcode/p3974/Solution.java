package com.leetcode.p3974;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class Solution {

    @Test
    void testTemplate() {
        // given

        // when

        // then
    }


    @Test
    void test_1() {
        // given
        String str = "ab-cd";

        // when
        String result = reverseOnlyLetters(str);

        // then
        assertEquals("dc-ba", result);
    }

    @Test
    void test_2() {
        // given
        String str = "a-bC-dEf-ghIj";

        // when
        String result = reverseOnlyLetters(str);

        // then
        assertEquals("j-Ih-gfE-dCba", result);
    }

    @Test
    void test_3() {
        // given
        String str = "mv']4";

        // when
        String result = reverseOnlyLetters(str);

        // then
        assertEquals("4]'vm", result);
    }

    public String reverseOnlyLetters(String s) {
        Deque<Character> queue = new ArrayDeque<>(s.length());

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if(Character.isAlphabetic(c)) {
                queue.addFirst(c);
                chars[i] = '\\';
            }
        }

        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];

            if(aChar == '\\') {
                sb.append(queue.removeFirst());
            } else {
                sb.append(aChar);
            }
        }

        return sb.toString();
    }
}