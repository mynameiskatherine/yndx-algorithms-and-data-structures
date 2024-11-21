package org.kate.StackQueueDequeue;

import java.io.*;
import java.util.List;
import java.util.Stack;

public class GreatLinelandResettlement {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int size = Integer.parseInt(reader.readLine());
            String[] settlements = reader.readLine().split(" ");
            int[] settlementsLevels = new int[size];
            for (int i = 0; i < settlements.length; i++) {
                settlementsLevels[i] = Integer.parseInt(settlements[i]);
            }

//            Scanner scanner = new Scanner("2\n" +
//                    "10 10\n");
//            int size = Integer.parseInt(scanner.nextLine());
//            String[] settlements = scanner.nextLine().split(" ");
//            int[] settlementsLevels = new int[size];
//            for (int i = 0; i < settlements.length; i++) {
//                settlementsLevels[i] = Integer.parseInt(settlements[i]);
//            }

            int[] result = calculate(settlementsLevels);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                builder.append(result[i]).append(" ");
            }

            writer.write(builder.toString().trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] calculate(int[] settlementsLevels) {
        Stack<List<Integer>> stack = new Stack<>();
        stack.push(List.of(0, settlementsLevels[0]));
        int[] resettled = new int[settlementsLevels.length];
        for (int i = 1; i < settlementsLevels.length; i++) {
            while (!stack.empty() && stack.peek().get(1) > settlementsLevels[i]) {
                resettled[stack.peek().getFirst()] = i;
                stack.pop();
            }
            stack.push(List.of(i, settlementsLevels[i]));
        }
        while (!stack.empty()) {
            resettled[stack.peek().getFirst()] = -1;
            stack.pop();
        }

        return resettled;
    }
}
