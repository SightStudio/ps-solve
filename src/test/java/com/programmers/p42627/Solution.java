package com.programmers.p42627;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 디스크 컨트롤러
 * https://programmers.co.kr/learn/courses/30/lessons/42627
 */
public class Solution {

    @Test
    void test1() {

        // given
        int[][] jobs = {
            { 0, 3 },
            { 1, 9 },
            { 2, 6 }
        };

        // when
        int solution = solution(jobs);

        // then
        assertEquals(9, solution);
    }

    @Test
    void test2() {

        // given
        int[][] jobs = {
                {24, 10},
                {28, 39},
                {43, 20},
                {37, 5},
                {47, 22},
                {20, 47},
                {15, 34},
                {15, 2},
                {35, 43},
                {26, 1}
        };

        // when
        int solution = solution(jobs);

        // then
        assertEquals(72, solution);
    }

    public int solution(int[][] jobs) {
        final int jobSize = jobs.length;

        List<Job> jobList = initJobList(jobs);

        Queue<Job> jobWaitQueue = new PriorityQueue<>((o1, o2) ->
                o1.getEnterTime() == o2.getEnterTime() ?
                        o1.getCostTime()  - o2.getCostTime() :
                        o1.getEnterTime() - o2.getEnterTime()
        );

        Queue<Job> jobQueue = new PriorityQueue<>((o1, o2) ->
                o1.getCostTime() == o2.getCostTime() ?
                        o1.getEnterTime() - o2.getEnterTime() :
                        o1.getCostTime()  - o2.getCostTime()
        );

        jobWaitQueue.addAll(jobList);
        jobQueue.offer(jobWaitQueue.poll());

        int time = 0;
        int executeTotalTime = 0;

        while(!jobQueue.isEmpty()) {

            Job job = jobQueue.poll();

            if(time < job.getEnterTime()) {
                time += job.getEnterTime();
            }

            executeTotalTime += (time - job.getEnterTime()) + job.getCostTime();
            time += job.getCostTime();

            jobQueue.addAll(getRangedJobs(time, jobWaitQueue));

            if(!jobWaitQueue.isEmpty() && jobQueue.isEmpty()) {
                Job poll = jobWaitQueue.poll();
                jobQueue.offer(jobWaitQueue.poll());
                time += poll.getEnterTime();
            }
        }

        return executeTotalTime / jobSize;
    }

    private List<? extends Job> getRangedJobs(int time, Queue<Job> jobWaitQueue) {
        List<Job> rangedJobs = new LinkedList<>();

        while (!jobWaitQueue.isEmpty()) {
            Job job = jobWaitQueue.peek();

            if(job.getEnterTime() <= time) {
                rangedJobs.add(jobWaitQueue.poll());
            } else {
                break;
            }
        }

        return rangedJobs;
    }

    private List<Job> initJobList(int[][] jobs) {
        List<Job> jobList = new LinkedList<>();

        for (int[] job : jobs) {
            jobList.add(new Job(job));
        }

        return jobList;
    }
}

class Job {
    private final int enterTime;
    private final int costTime;

    public Job(int[] job) {
        this.enterTime = job[0];
        this.costTime = job[1];
    }

    public int getEnterTime() {
        return enterTime;
    }

    public int getCostTime() {
        return costTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        Job job = (Job) o;
        return enterTime == job.enterTime && costTime == job.costTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enterTime, costTime);
    }
}