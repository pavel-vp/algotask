package com.example.algotask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

interface SearchOperation {
    boolean search(String stringToSearch);
    int searchFirst(String stringToSearch);
    int searchFirst(String stringToSearch, int beginIndex);
}
interface ReplaceOperation {
    CustomString replace(String stringToSearch, String stringToReplace);
}

public class CustomString implements SearchOperation, ReplaceOperation {


    private final char[] data;

    public CustomString() {
        this(new char[0]);
    }

    public CustomString(char[] initData) {
        this.data = Arrays.copyOf(initData, initData.length);
    }

    @Override
    public boolean search(String stringToSearch) {
        return searchFirst(stringToSearch) > -1;
    }

    @Override
    public int searchFirst(String stringToSearch) {
        return searchFirst(stringToSearch, 0);
    }

    @Override
    public int searchFirst(String stringToSearch, int beginIndex) {
        if (stringToSearch == null || stringToSearch.isEmpty()) return -1;
        if (this.data == null || this.data.length < stringToSearch.length()) return -1;
        for (int i = beginIndex; i <=data.length - stringToSearch.length(); i++) {
            boolean allFound = true;
            for (int j = 0; j < stringToSearch.length(); j++) {
                if (data[i+j] != stringToSearch.charAt(j)) {
                    allFound = false;
                }
            }
            if (allFound) return i;
        }
        return -1;
    }

    private Character[] convertToCharacterArray(char[] arr) {
        Character[] charArray = IntStream.range(0, arr.length)
                .mapToObj(i -> arr[i])
                .toArray(Character[]::new);
        return charArray;
    }

    @Override
    public CustomString replace(String stringToSearch, String stringToReplace) {
        int prevPosition = 0;
        int position = 0;
        List<Character[]> buffer = new ArrayList<>();
        while ((position = searchFirst(stringToSearch, position)) >= 0) {
            char[] prev = Arrays.copyOfRange(data, prevPosition, position);
            Character[] charArray = convertToCharacterArray(prev);
            buffer.add(charArray); // previous part

            char[] repl = stringToReplace.toCharArray();
            Character[] replCharArray = convertToCharacterArray(repl);
            buffer.add(replCharArray); //add replication string

            prevPosition = position + stringToSearch.length();
            position = prevPosition;
        }
        if (buffer.size() == 0) {
            return this;
        }
        // add last one
        char[] last = Arrays.copyOfRange(data, prevPosition, data.length);
        Character[] lastCharArray = convertToCharacterArray(last);
        buffer.add(lastCharArray); // last

        long size = buffer.stream()
                .flatMap(Stream::of)
                .count();
        char[] newBuffer = new char[(int) size];
        int idx = 0;
        for (Character[] chars : buffer) {
            for (Character ch : chars) {
                newBuffer[idx] = ch;
                idx++;
            }
        }
        return new CustomString(newBuffer);

    }

    @Override
    public String toString() {
        return new String(this.data).intern();
    }

}
