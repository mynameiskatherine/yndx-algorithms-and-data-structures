package org.kate.StackQueueDequeue;

import java.io.*;
import java.util.Stack;

public class BracketSequence {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String brackets = reader.readLine();

//            Scanner scanner = new Scanner("{]\n");
//            String brackets = scanner.nextLine();
            char[] sequence = brackets.toCharArray();

            String result = calculate(sequence);
            writer.write(String.valueOf(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String calculate(char[] sequence) {
        Stack<Character> stack = new Stack<>();
        for (char c : sequence) {
            switch (c) {
                case '(', '{', '[' -> stack.push(c);
                case ')' -> {
                    if (!stack.empty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return "no";
                    }
                }
                case '}' -> {
                    if (!stack.empty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return "no";
                    }
                }
                case ']' -> {
                    if (!stack.empty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return "no";
                    }
                }
            }
        }
        return stack.empty() ? "yes" : "no";
    }
}
