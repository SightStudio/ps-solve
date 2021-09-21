package com.programmers.p42888;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42888
 * 오픈채팅방
 */
public class Solution {

    @Test
    void test() {
        // given
        String[] record = {
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"
        };

        // when
        String[] solution = solution(record);

        // then
        String[] result = {
            "Prodo님이 들어왔습니다.",
            "Ryan님이 들어왔습니다.",
            "Prodo님이 나갔습니다.",
            "Prodo님이 들어왔습니다."
        };

        Assertions.assertArrayEquals(result, solution);
    }

    public String[] solution(String[] record) {

        Map<String, String> roomUidMap = initRoomUidMap(record);

        List<String> result = initRecordList(roomUidMap, record);

        return result.toArray(String[]::new);
    }

    private List<String> initRecordList(Map<String, String> roomUidMap, String[] records) {
        List<String> recordList = new ArrayList<>(records.length);

        for (String record : records) {

            String[] commands = record.split(" ");

            final String command = commands[0];
            final String uid = commands[1];
            final String nickName = roomUidMap.get(uid);

            if("Enter".equals(command)) {
                recordList.add(nickName+"님이 들어왔습니다.");
            } else if("Leave".equals(command)) {
                recordList.add(nickName+"님이 나갔습니다.");
            }
        }

        return recordList;
    }

    private Map<String, String> initRoomUidMap(String[] records) {
        Map<String, String> map = new HashMap<>();

        for (String record : records) {
            String[] commands = record.split(" ");

            final String command = commands[0];
            final String uid = commands[1];

            if("Enter".equals(command) || "Change".equals(command)) {
                final String nickname = commands[2];
                map.put(uid, nickname);
            }
        }

        return map;
    }
}