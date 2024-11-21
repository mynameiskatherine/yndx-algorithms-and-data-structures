package org.kate.Testing;

import java.io.*;

public class TShirtsAndSocks {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] numbers = new int[] {100,100,1,1};

//        int[] numbers = new int[4];
//        int i = 0;
//        while (reader.ready()) {
//            numbers[i] = Integer.parseInt(reader.readLine());
//            i++;
//        }

        String result = calculate(numbers);

        writer.write(String.valueOf(result));

        reader.close();
        writer.close();
    }

    private static String calculate(int[] numbers) {
        int blueTShirts = numbers[0];
        int redTShirts = numbers[1];
        int blueSocks = numbers[2];
        int redSocks = numbers[3];

        if (blueTShirts == 0) {
            return blueSocks == 0 ? "1 1" : "1 " + (blueSocks + 1);
        }
        if (blueSocks == 0) {
            return (blueTShirts + 1) + " 1";
        }
        if (redTShirts == 0) {
            return redSocks == 0 ? "1 1" : "1 " + (redSocks + 1);
        }
        if (redSocks == 0) {
            return (redTShirts + 1) + " 1";
        }
        int reds = blueTShirts + 1 + blueSocks + 1;
        int blues = redTShirts + 1 + redSocks + 1;
        int randomTShirt = 1 + Math.max(blueSocks, redSocks) + 1;
        int randomSock = Math.max(blueTShirts, redTShirts) + 1 + 1;

        if (Math.min(reds, blues) - Math.min(randomTShirt, randomSock) <= 0) {
            return reds >= blues ? ((redTShirts + 1) + " " + (redSocks + 1)) : ((blueTShirts + 1) + " " + (blueSocks + 1));
        } else {
            return randomTShirt <= randomSock ? "1 " + (Math.max(blueSocks, redSocks) + 1) : (Math.max(blueTShirts, redTShirts) + 1) + " 1";
        }

    }
}
