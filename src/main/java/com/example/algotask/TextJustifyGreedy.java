package com.example.algotask;

import java.util.ArrayList;
import java.util.List;

public class TextJustifyGreedy {
    private List<String> getNextWords(String[] words, int pos, int maxWidth) {
        List<String> result = new ArrayList<>();
        int width= 0;
        int delta = 0;
        while(width<maxWidth && pos < words.length) {
            String w = words[pos];
            if (width + w.length() + delta <= maxWidth) {
                result.add(w);
                pos++;
                width+=w.length() + delta;
                delta = 1;
            } else {
                break;
            }
        }
        return result;
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int lastWord = -1;
        while(lastWord < words.length) {
            List<String> ws = getNextWords(words, lastWord + 1, maxWidth);
            if (ws.size() == 0) break;
            lastWord+=ws.size();
            StringBuilder sb = new StringBuilder();
            if (ws.size() > 1) {
                int spacesBetween = 1;
                int spacesAdditional = 0;
                if (lastWord >= words.length-1) {

                } else {
                    int totalWordLength = (int) ws.stream()
                            .flatMapToInt(CharSequence::chars)
                            .count() + spacesBetween * (ws.size() - 1);
                    spacesAdditional = maxWidth - totalWordLength;
                    if (spacesAdditional / (ws.size() - 1) > 0) {
                        spacesBetween += spacesAdditional / (ws.size() - 1);
                        spacesAdditional = spacesAdditional % (ws.size() - 1);
                    }
                }

                for (int i = 0; i< ws.size()-1; i++) {
                    String w = ws.get(i);
                    sb.append(w);
                    for (int j = 1; j <= spacesBetween; j++) {
                        sb.append(" ");
                    }
                    if (spacesAdditional > 0) {
                        sb.append(" ");
                        spacesAdditional--;
                    }
                }
            }
            sb.append(ws.get(ws.size()-1));
            for (int i = sb.toString().length(); i<maxWidth; i++) {
                sb.append(" ");
            }

            result.add(sb.toString());



        }
        return result;
    }

    public static void main(String[] args) {
        TextJustifyGreedy g = new TextJustifyGreedy() ;
        List<String> result = g.fullJustify(new String[] {"This", "is", "an", "example", "of", "text", "justif", "sss"}, 10);
        System.out.println(result);
    }

}
