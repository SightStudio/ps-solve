package com.programmers.p43162;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43162
 * 네트워크
 */
class Solution {

    @Test
    void test() {
        // given
        int n = 3;
        int[][] computers = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };

        // when
        int solution = solution(n, computers);

        // then
        Assertions.assertEquals(2, solution);
    }

    @Test
    void test2() {
        // given
        int n = 3;
        int[][] computers = {
            {1, 1, 0},
            {1, 1, 1},
            {0, 1, 1}
        };

        // when
        int solution = solution(n, computers);

        // then
        Assertions.assertEquals(1, solution);
    }

    @Test
    void test3() {
        // given
        int n = 4;
        int[][] computers = {
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {1, 0, 1, 1}
        };

        // when
        int solution = solution(n, computers);

        // then
        Assertions.assertEquals(1, solution);
    }

    public int solution(int n, int[][] computers) {
        List<List<Integer>> adjList = initAdjList(n, computers);
        boolean[] visited = new boolean[n];
        int totalVisitedCnt = 0;
        int result = 0;

        while(true) {
            int[] visitedCnt = new int[]{0};
            int notVisitedNode = findNotVisitedNode(visited);
            if(notVisitedNode < 0) {
                break;
            }

            dfs(notVisitedNode, visitedCnt, adjList, visited);

            totalVisitedCnt += visitedCnt[0];
            if(totalVisitedCnt <= n) {
                result++;
            } else {
                break;
            }
        }

        return result;
    }

    private int findNotVisitedNode(boolean[] visited) {

        for (int i = 0; i < visited.length; i++) {
            if(visited[i] == false) {
                return i;
            }
        }

        return -1;
    }

    private void dfs(int currentNode, int[] visitedCnt, List<List<Integer>> adjList, boolean[] visited) {
        if(visited[currentNode]) {
            return;
        }

        visited[currentNode] = true;
        visitedCnt[0]++;

        List<Integer> adjNode = adjList.get(currentNode);
        for (int j = 0; j < adjNode.size(); j++) {

            if(currentNode == j) {
                continue;
            }

            if(adjNode.get(j) == 1) {
                dfs(j, visitedCnt, adjList, visited);
            }
        }
    }

    private List<List<Integer>> initAdjList(int n, int[][] computers) {
        List<List<Integer>> adjList = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            List<Integer> adjNode = new ArrayList<>(n);
            for (int adj : computers[i]) {
                adjNode.add(adj);
            }
            adjList.add(adjNode);
        }

        return adjList;
    }
}