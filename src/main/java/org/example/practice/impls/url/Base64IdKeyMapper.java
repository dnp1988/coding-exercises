package org.example.practice.impls.url;
import java.util.Stack;

public class Base64IdKeyMapper {

    public String toKey(Integer id) {
        Integer remainder = id;
        Stack<Character> characters = new Stack<>();

        if(id == 0) {
            return "0";
        }

        while(remainder > 0) {
            int value = remainder % 62;
            remainder = remainder / 62;
            characters.add(mapValueToChar(value));
        }

        StringBuffer sb = new StringBuffer();
        while(!characters.isEmpty()) {
            Character character = characters.pop();
            sb.append(character);
        }

        return sb.toString();
    }

    private char mapValueToChar(int value) {
        if(0 <= value && value <= 9) {
            return (char) ('0' + value);
        } else if(10 <= value && value <= 36) {
            return (char) ('a' + value - 10);
        } else {
            return (char) ('A' + value - 36);
        }
    }

    public Integer toId(String urlKey) {
        Integer pos = 0;

        for(Integer i = urlKey.length() - 1; i >= 0 ; i--) {
            char charValue = urlKey.charAt(i);
            pos += mapCharToValue(charValue) * (int) Math.pow(62, (urlKey.length() - 1 - i));
        }

        return pos;
    }

    private int mapCharToValue(char charValue) {
        if('0' <= charValue && charValue <= '9') {
            return (int) charValue - '0';
        } else if('a' <= charValue && charValue <= 'z') {
            return (int) charValue - 'a' + 10;
        } else {
            return (int) charValue - 'A' + 36;
        }
    }
}
