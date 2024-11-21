package org.kate.StackQueueDequeue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class MinimalBracketSequence {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int size = Integer.parseInt(reader.readLine());
            String w = reader.readLine();
            char[] alphabet = w.toCharArray();
            String s = reader.readLine();
            char[] prefix = s.toCharArray();

//            Scanner scanner = new Scanner("6\n" +
//                    "][)(\n" +
//                    "([\n");
//            int size = Integer.parseInt(scanner.nextLine());
//            String w = scanner.nextLine();
//            char[] alphabet = w.toCharArray();
//            String s = scanner.nextLine();
//            char[] prefix = s.toCharArray();

            List<Character> result = calculate(size, alphabet, prefix);
            writer.write(result.stream().map(String::valueOf).collect(Collectors.joining("")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<Character> calculate(int size, char[] alphabet, char[] prefix) {
        List<Character> result = new ArrayList<>();
        if (prefix.length == size) {
            for (char c : prefix) {
                result.add(c);
            }
            return result;
        }

        Stack<Character> stack = new Stack<>();
        for (char ch : prefix) {
            result.add(ch);
            switch (ch) {
                case '(', '[' -> {
                    stack.push(ch);
                }
                case ')' -> {
                    if (!stack.empty() && stack.peek() == '(') {
                        stack.pop();
                    }
                }
                case ']' -> {
                    if (!stack.empty() && stack.peek() == '[') {
                        stack.pop();
                    }
                }
            }
        }
        Map<Character, Character> bracketPair = Map.of('(',')','[',']','0','0');
            while (size - result.size() > stack.size()) {
                char last = !stack.empty() ? stack.peek() : '0';

                for (char a : alphabet) {
                    if (a == bracketPair.get(last)) {
                        result.add(a);
                        stack.pop();
                        break;
                    } else if (a == '(' || a == '[') {
                        result.add(a);
                        stack.push(a);
                        break;
                    }
                }
            }

            while (!stack.empty()) {
                char ch = stack.peek();
                switch (ch) {
                    case '[' -> {
                        stack.pop();
                        result.add(']');
                    }
                    case '(' -> {
                        stack.pop();
                        result.add(')');
                    }
                }
            }

        return result;
    }
}
