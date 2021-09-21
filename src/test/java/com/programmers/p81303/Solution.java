package com.programmers.p81303;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Solution {

    @Test
    void test() {
        int n = 8;
        int k = 2;
        String[] cmd = { "D 2","C","U 3","C","D 4","C","U 2","Z","Z" };

        String solution = solution(n, k, cmd);

        assertEquals("OOOOXOOO", solution);
    }


    public String solution(int n, int curIdx, String[] cmds) {

        Deque<Integer> undoStack = new ArrayDeque<>(n);
        boolean[] deletedRows = new boolean[n];

        List<Integer> table = IntStream.range(0, n)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        for (String cmd : cmds) {
            String[] command = cmd.split(" ");
            String action = command[0];

            if("U".equals(action)) {
                int x = Integer.parseInt(command[1]);
                selectUp(curIdx, x, table);
            } else if("D".equals(action)) {
                int x = Integer.parseInt(command[1]);
                selectDown(curIdx, x, table);
            } else if("C".equals(action)) {
                cut(curIdx, undoStack);
            } else if("Z".equals(action)) {
                undo(table, undoStack, deletedRows);
            }
        }

        String answer = "";
        return answer;
    }

    // 현재 선택된 행에서 X칸 위에 있는 행을 선택
    private void selectUp(int curIdx, int x, List<Integer> table) {

        if(curIdx - x < 0 ) {

        }

    }

    // 현재 선택된 행에서 X칸 아래에 있는 행을 선택
    private void selectDown(int curIdx, int x, List<Integer> table) {

    }

    // 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택
    private void cut(int curIdx, Deque<Integer> undoStack) {

    }

    // 가장 최근에 삭제된 행을 원래대로 복구
    private void undo(List<Integer> table, Deque<Integer> undoStack, boolean[] deletedRows) {
        if(undoStack.isEmpty()) {
            return;
        }

        int rollBackIdx = 0;
        Integer undoIdx = undoStack.pop();
        for (int i = undoIdx - 1; i > 0; i--) {
            if(deletedRows[i]) {
                rollBackIdx = i;
                break;
            }
        }
        table.add(rollBackIdx, undoIdx);
    }
}

class Row {
    int idx;

    public Row(int idx) {
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }
}