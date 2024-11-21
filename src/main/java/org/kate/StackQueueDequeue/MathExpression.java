package org.kate.StackQueueDequeue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExpression {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String string = reader.readLine().trim();

            Scanner scanner = new Scanner("1*2*3+(4*5*6*7+8*9+)   \n");
            String string = scanner.nextLine().trim();

            String result = calculate(string);
            writer.write(String.valueOf(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String calculate(String string) {
        if (!checkCorrectness(string)) {
            return "WRONG";
        }

        String expression = string.replaceAll("\\s+", "");

        List<String> postfixExpression = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Stack<String> infixExpression = new Stack<>();

        Pattern digit = Pattern.compile("^\\d+");
        Pattern minus = Pattern.compile("^[-]");
        Pattern multipl = Pattern.compile("^[*]");
        Pattern plus = Pattern.compile("^[+]");
        Pattern openBr = Pattern.compile("^[(]");
        Pattern closeBr = Pattern.compile("^[)]");

        while (!expression.isEmpty()) {
            Matcher digitMatcher = digit.matcher(expression);
            if (digitMatcher.find()) {
                String matcher = digitMatcher.group(0);
                postfixExpression.add(matcher);
                infixExpression.push(matcher);
                expression = expression.substring(matcher.length());
            } else if (minus.matcher(expression).find()) {
                if (infixExpression.isEmpty() || infixExpression.peek().equals("(")) {
                    postfixExpression.add("0");
                }
                while (!stack.empty() && stack.peek().matches("[-+*]")) {
                    postfixExpression.add(stack.pop());
                }
                stack.push("-");
                infixExpression.push("-");
                expression = expression.substring(1);
            } else if (multipl.matcher(expression).find()) {
                while (!stack.empty() && stack.peek().matches("[*]")) {
                    postfixExpression.add(stack.pop());
                }
                stack.push("*");
                infixExpression.push("*");
                expression = expression.substring(1);
            } else if (plus.matcher(expression).find()) {
                while (!stack.empty() && stack.peek().matches("[-+*]")) {
                    postfixExpression.add(stack.pop());
                }
                stack.push("+");
                infixExpression.push("+");
                expression = expression.substring(1);
            } else if (openBr.matcher(expression).find()) {
                infixExpression.push("(");
                stack.add("(");
                expression = expression.substring(1);
            } else if (closeBr.matcher(expression).find()) {
                while (!stack.empty() && !stack.peek().equals("(")) {
                    postfixExpression.add(stack.pop());
                }
                if (stack.empty()) {
                    return "WRONG";
                } else {
                    stack.pop();
                }
                infixExpression.push(")");
                expression = expression.substring(1);
            } else {
                return "WRONG";
            }
        }
        while (!stack.empty()) {
            if (!stack.peek().matches("[-+*]")) {
                return "WRONG";
            } else {
                postfixExpression.add(stack.pop());
            }
        }
        return String.valueOf(countPostfix(postfixExpression));
    }

    private static boolean checkCorrectness(String string) {
         return !string.isEmpty() &&
                 !string.matches(".*\\d+\\s+\\d+.*") &&
                 !string.matches(".*[a-zA-Z].*") &&
                 !string.matches(".*([-+*]\\s*[-+*]).*") &&
                 !string.matches("^([-]\\s+\\d+).*") &&
                 !string.matches(".*([(]\\s*[-]\\s+\\d+).*") &&
                 !string.matches("^[+*].*") &&
                 !string.matches(".*[(]+\\s*[+*]\\d+.*") &&
                 !string.matches(".*[-+*]$") &&
                 !string.matches(".*[+*]\\s*[)].*");
    }
    private static int countPostfix(List<String> expression) {
        Stack<Integer> stack = new Stack<>();
        for (String s : expression) {
            switch (s) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> stack.push(- stack.pop() + stack.pop());
                case "*" -> stack.push(stack.pop() * stack.pop());
                default -> stack.push(Integer.parseInt(s));
            }
        }
        return stack.peek();
    }
}
