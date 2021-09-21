package com.boj.boj2096;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];

        for (int i = 0; i < N; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            map[i][0] = tmp[0];
            map[i][1] = tmp[1];
            map[i][2] = tmp[2];
        }

        int[] result = m.solution(N, map);
        System.out.printf("%d %d", result[0], result[1]);
    }

    @Test
    void test() {

        // given
        int N = 3;
        int[][] map = {
                {1, 2, 3},
                {4, 5, 6},
                {4, 9, 0}
        };

        int[] result = solution(N, map);

        assertEquals(18, result[0]);
        assertEquals(6, result[1]);
    }

    /**
     * 다음 줄로 내려갈 때 바로 아래의 수로 넘어가거나,
     * 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다.
     * <p>
     * https://www.acmicpc.net/problem/2096
     */
    private int[] solution(int N, int[][] map) {
        int[][][] DP = new int[N][3][2];

        int row = 0;
        final int min = 0;
        final int max = 1;

        DP[row][0][min] = map[0][0];
        DP[row][0][max] = map[0][0];

        DP[row][1][min] = map[0][1];
        DP[row][1][max] = map[0][1];

        DP[row][2][min] = map[0][2];
        DP[row][2][max] = map[0][2];

        for (row = 1; row < N; row++) {
            DP[row][0][max] = getMax(row, row - 1, 0, DP, map);
            DP[row][0][min] = getMin(row, row - 1, 0, DP, map);

            DP[row][1][max] = getMax(row, row - 1, 1, DP, map);
            DP[row][1][min] = getMin(row, row - 1, 1, DP, map);

            DP[row][2][max] = getMax(row, row - 1, 2, DP, map);
            DP[row][2][min] = getMin(row, row - 1, 2, DP, map);
        }

        return new int[]{
                getMaxBetween(
                        DP[N-1][0][max],
                        DP[N-1][1][max],
                        DP[N-1][2][max]
                ),

                getMinBetween(
                        DP[N-1][0][min],
                        DP[N-1][1][min],
                        DP[N-1][2][min]
                )
        };
    }

    private int[] getPossiblePositions(int el) {

        switch (el) {
            case 0:
                return new int[]{0, 1};
            case 1:
                return new int[]{0, 1, 2};
            case 2:
                return new int[]{1, 2};
        }

        return new int[]{};
    }

    private int getMax(int row, int prevRow, int el, int[][][] DP, int[][] map) {
        int[] positions = getPossiblePositions(el);
        return map[row][el] + getMaxBetween(prevRow, DP, positions);
    }


    private int getMin(int row, int prevRow, int el, int[][][] DP, int[][] map) {
        int[] positions = getPossiblePositions(el);
        return map[row][el] + getMinBetween(prevRow, DP, positions);
    }

    private int getMinBetween(int row, int[][][] DP, int[] positions) {
        int min = 999999;
        for (int position : positions) {
            min = Math.min(DP[row][position][0], min);
        }
        return min;
    }

    private int getMaxBetween(int row, int[][][] DP, int[] positions) {
        int max = 0;
        for (int position : positions) {
            max = Math.max(DP[row][position][1], max);
        }
        return max;
    }

    private int getMinBetween(int... values) {
        int min = 999999999;
        for (int value : values) {
            min = Math.min(min, value);
        }
        return min;
    }

    private int getMaxBetween(int... values) {
        int max = 0;
        for (int value : values) {
            max = Math.max(max, value);
        }
        return max;
    }
}
