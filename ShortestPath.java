package org.example;
import jdk.internal.net.http.common.Pair;

import java.util.*;
public class ShortestPath {
    private static class Elem {
        public String val;
        public int weight;
        public Elem(String val, int weight) {
            this.val = val;
            this.weight = weight;
        }
    }
    public static List<String> randomAccess(StrGraph g) {
        Random random = new Random();
        int ranInt = random.nextInt(0, g.list.size());
        int count = 0;
        String beginStr = "";
        for (String x : g.list.keySet()) {
            if (count == ranInt) {
                beginStr = x;
                break;
            }
            count += 1;
        }
        if (beginStr.equals("")) {
            System.out.println("Selection of begin point error!");
            return null;
        }
        List<String> res = new ArrayList<>();
        Set<Pair<String, String>> visited = new HashSet<>();
        res.add(beginStr);
        String curStr = beginStr;
        while (true) {
            int NumOfNeighbors = g.list.get(curStr).size();
            if (NumOfNeighbors == 0) {
                return res;
            }
            int randInt = random.nextInt(0, NumOfNeighbors);
            count = 0;
            String next = "";
            for (String i : g.list.get(curStr).keySet()) {
                if (count == randInt) {
                    next = i;
                    break;
                }
                count += 1;
            }
            if (next.equals("")) {
                System.out.println("Error generating");
                return null;
            }
            Pair<String, String> curPair = new Pair<>(curStr, next);
            boolean isbreak = false;
            if (visited.contains(curPair)) {
                isbreak = true;
            }
            visited.add(curPair);
            res.add(next);
            curStr = next;
            if (isbreak) {
                break;
            }
        }
        return res;
    }
    public static List<String> getShortestPath(StrGraph g, String begin, String end) {
        if (!g.list.containsKey(begin) || !g.list.containsKey(end)) {
            return null;
        } else if (begin.equals(end)) {
            return null;
        }
        List<String> res = new ArrayList<>();
        PriorityQueue<Elem> pq = new PriorityQueue<>(new Comparator<Elem>() {
            @Override
            public int compare(Elem elem, Elem t1) {
                return Integer.compare(elem.weight, t1.weight);
            }
        });
        Set<String> visited = new HashSet<>();
        Map<String, String> pre = new HashMap<>();
        visited.add(begin);
        for (String i : g.list.get(begin).keySet()) {
            pq.add(new Elem(i, g.list.get(begin).get(i)));
            pre.put(i, begin);
        }
        boolean isFound = false;
        while (!pq.isEmpty()) {
            Elem top = pq.poll();
            if (top.val.equals(end)) {
                isFound = true;
                break;
            }
            if (!visited.contains(top.val)) {
                visited.add(top.val);
                for (String i : g.list.get(top.val).keySet()) {
                    pq.add(new Elem(i, g.list.get(top.val).get(i) + top.weight));
                    pre.put(i, top.val);
                }
            }
        }
        if (isFound) {
            res.add(end);
            String tmp = pre.get(end);
            while (!tmp.equals(begin)) {
                res.add(tmp);
                tmp = pre.get(tmp);
            }
            res.add(begin);
        }
        System.out.println(res);
        return res;
    }
}
//modify on B1