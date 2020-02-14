package com.example.algotask;

import java.util.*;

public class TimeDurationCalc {
    // On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
    //
    //We store logs in timestamp order that describe when a function is entered or exited.
    //
    //Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
    //
    //A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
    //
    //The CPU is single threaded which means that only one function is being executed at a given time unit.
    //
    //Return the exclusive time of each function, sorted by their function id.
    // Input:
    //n = 2
    //logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
    //Output: [3, 4]

    static class Event implements Comparable<Event>{
        int id;
        int start;
        int end;
        int cnt;

        public Event(int id, int start) {
            this.id = id;
            this.start = start;
        }

        @Override
        public int compareTo(Event event) {
            return Integer.compare(this.start, event.start);
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];


        Deque<Event> stack = new ArrayDeque<>();
        Event event = null;
        int prevEnd = 0;
        for (String s : logs) {
            String[] d = s.split(":");
            int id = Integer.parseInt(d[0]);
            String type = d[1];
            int time = Integer.parseInt(d[2]);
            if (type.equals("start")) {
                if (event == null && !stack.isEmpty()) {
                    event = stack.pop();
                    result[event.id] = result[event.id] + time - prevEnd - 1;
                    prevEnd = time;
                    stack.push(event);
                    event = null;
                }
                if (event != null) {
                    result[event.id] = result[event.id] + (time - event.start);
                    stack.push(event);
                }
                event = new Event(id, time);
                prevEnd = time;
            } else {
                int delta = 1;
                if (event == null && !stack.isEmpty()) {
                    event = stack.pop();
                    delta = 0;
                }
                result[event.id] = result[event.id] + time - prevEnd + delta;
                prevEnd = time;
                event = null;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TimeDurationCalc t = new TimeDurationCalc();
        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("0:start:2");
        logs.add("0:end:5");
        logs.add("1:start:7");
        logs.add("1:end:7");
        logs.add("0:end:8");


        System.out.println(Arrays.toString(t.exclusiveTime(2, logs)));
    }
}
