package com.programmers.p42586;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Solution {

    @Test
    void test() {

        // given
        int[] progresses = new int[] { 93, 30, 55 };
        int[] speed = new int[] { 1, 30, 5 };

        // when
        int[] solution = solution(progresses, speed);

        // then
        assertArrayEquals(new int []{ 2, 1 }, solution);
    }

    public int[] solution(int[] progresses, int[] speeds) {
        int taskSize = progresses.length;
        List<Task> taskList = initTaskList(taskSize, progresses, speeds);

        Queue<Task> taskQueue = new ArrayDeque<>(taskList);

        // <date, deployCnt>
        Map<Integer, Integer> deployMap = initDeployMap(taskQueue);

        return deployMap.keySet().stream()
                .sorted()
                .map(deployMap::get)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private Map<Integer, Integer> initDeployMap(Queue<Task> taskList) {
        Map<Integer, Integer> deployMap = new HashMap<>();

        int lastDeployDate = taskList.peek().getDeployDate();

        while(!taskList.isEmpty()) {
            Task task = taskList.poll();

            if(task.getDeployDate() < lastDeployDate) {
                task.setDeployDate(lastDeployDate);
            }

            deployMap.putIfAbsent(task.getDeployDate(), 0);
            deployMap.compute(task.getDeployDate(), (date, deployCnt) -> deployCnt+=1);

            lastDeployDate = task.getDeployDate();
        }

        return deployMap;
    }

    private List<Task> initTaskList(int taskSize, int[] progresses, int[] speeds) {
        List<Task> taskList = new ArrayList<>(taskSize);
        for (int i = 0; i < taskSize; i++) {
            taskList.add(new Task(progresses[i], speeds[i]));
        }
        return taskList;
    }
}

class Task implements Comparable<Task> {
    private int progress;
    private int speed;
    private int deployDate;

    public Task(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
        this.deployDate = (int) Math.ceil((100 - progress) / (double) speed);
    }

    public int getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(int deployDate) {
        this.deployDate = deployDate;
    }

    @Override
    public int compareTo(Task o) {
        return this.deployDate - o.deployDate;
    }
}