package org.kate.StackQueueDequeue;

import java.io.*;
import java.util.Stack;

public class PostfixExpression {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] expression = reader.readLine().trim().split(" ");

//            Scanner scanner = new Scanner("0 8 - 0 6 - +\n");
//            String[] expression = scanner.nextLine().trim().split(" ");

            long result = calculate(expression);
            writer.write(String.valueOf(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long calculate(String[] expression) {
        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < expression.length; i++) {
            switch (expression[i]) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> stack.push(- stack.pop() + stack.pop());
                case "*" -> stack.push(stack.pop() * stack.pop());
                default -> stack.push(Long.parseLong(expression[i]));
            }
        }
        return stack.peek();
    }
}
