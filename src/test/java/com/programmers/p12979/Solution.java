package com.programmers.p12979;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/12979
 * 기지국 설치
 */
class Solution {

    @Test
    void test() {
        int N = 11;
        int[] stations = { 4, 11 };
        int W = 1;

        int solution = solution(N, stations, W);

        assertEquals(3, solution);
    }

    @Test
    void test2() {
        int N = 16;
        int[] stations = { 9 };
        int W = 2;

        int solution = solution(N, stations, W);

        assertEquals(3, solution);
    }

    @Test
    void test3() {
        int N = 10;
        int[] stations = { 5 };
        int W = 1;

        int solution = solution(N, stations, W);

        assertEquals(3, solution);
    }

    @Test
    void test4() {
        int N = 10;
        int[] stations = { 2, 8, 10 };
        int W = 1;

        int solution = solution(N, stations, W);

        assertEquals(1, solution);
    }

    public int solution(int n, int[] stations, int w) {
        int result = 0;
        int start = 1;
        int end = 1;

        int curStationIdx = 0;

        while(end <= n) {
            boolean isStartInRange = isInStationRange(start, stations, curStationIdx, w);

            if(!isStartInRange) {
                if(start >= n) {
                    result += requiredStationCnt(start, end, w);
                    break;
                }

                if(curStationIdx < 0) {
                    start = n;
                } else {
                    start = stations[curStationIdx] - w;
                }
                continue;
            }

            result += requiredStationCnt(start-1, end, w);
            start = stations[curStationIdx] + w + 1;
            end = stations[curStationIdx] + w + 1;
            curStationIdx = getNextStationIdx(curStationIdx, stations, w);
        }

        return result;
    }

    private int getNextStationIdx(int curStationIdx, int[] stations, int w) {

        for (int i = curStationIdx+1; i < stations.length; i++) {
            if(!isInStationRange(curStationIdx, stations, i, w)) {
                return i;
            }
        }

        return -1;
    }

    private int requiredStationCnt(int start, int end, int w) {
        final int range = start - end + 1;
        final int stationRange = w * 2 + 1;

        final boolean isCeil = range % stationRange > 0;
        int stationCnt = range/stationRange;
        return isCeil ? ++stationCnt : stationCnt;
    }

    private boolean isInStationRange(int curIdx, int[] stations, int curStationIdx, int w) {

        if(curStationIdx < 0) {
            return false;
        }

        int stationIdx = stations[curStationIdx];

        if(stationIdx < 0) {
            return false;
        }

        return stationIdx - w <= curIdx && curIdx <= stationIdx + w;
    }
}