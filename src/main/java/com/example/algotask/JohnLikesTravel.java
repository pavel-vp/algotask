package com.example.algotask;

import java.util.*;

public class JohnLikesTravel {

    static class Photo implements  Comparable<Photo> {
        String origName;
        String ext;
        String city;
        String time;
        String num;

        public Photo(String origName) {
            this.origName = origName;
            fillFields();
        }

        private void fillFields() {
            String[] s = this.origName.split(",");
            this.ext = s[0].split("\\.")[1].trim();
            this.city = s[1].trim();
            this.time = s[2].trim();
        }

        @Override
        public int compareTo(Photo o) {
            return this.time.compareTo(o.time);
        }
    }

    private List<Photo> list = new ArrayList<>();

    public String solution(String S) {
        if (S == null || S.trim().isEmpty()) return "";
        String[] arr = S.split("\n");
        for (String s : arr) {
            list.add(new Photo(s));
        }

        Map<String, Set<Photo>> mapCity = new HashMap<>();
        for (Photo photo : list) {
            Set<Photo> set = mapCity.get(photo.city);
            if (set == null) {
                set = new TreeSet<>();
            }
            set.add(photo);
            mapCity.put(photo.city, set);
        }

        for (String city : mapCity.keySet()) {
            String size = mapCity.get(city).size() + "";
            String format = "%0"+size.length()+"d";
            int num = 1;
            for (Photo photo : mapCity.get(city)) {
                photo.num = String.format(format, num);
                num++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Photo photo : list) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(photo.city).append(photo.num).append(".").append(photo.ext);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        JohnLikesTravel j = new JohnLikesTravel();
        String s = "";
        String r = j.solution(s);
        System.out.println(r);

    }

}
