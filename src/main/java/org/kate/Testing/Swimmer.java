package org.kate.Testing;

import java.io.*;

public class Swimmer {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] numbers = new int[6];
        int i = 0;
        while (reader.ready()) {
            numbers[i] = Integer.parseInt(reader.readLine());
            i++;
        }

        String result = calculate(numbers);

        writer.write(result);

        reader.close();
        writer.close();
    }

    private static String calculate(int[] numbers) {
        int raftSWX = numbers[0];
        int raftSWY = numbers[1];
        int raftNEX = numbers[2];
        int raftNEY = numbers[3];
        int swimmerX = numbers[4];
        int swimmerY = numbers[5];

        if (swimmerX <= raftSWX) {
            if (swimmerY >= raftNEY) {
                return "NW";
            } else if (swimmerY <= raftSWY) {
                return "SW";
            } else {
                return "W";
            }
        }
        if (swimmerX >= raftNEX) {
            if (swimmerY >= raftNEY) {
                return "NE";
            } else if (swimmerY <= raftSWY) {
                return "SE";
            } else {
                return "E";
            }
        }
        if (swimmerY >= raftNEY) {
            return "N";
        } else {
            return "S";
        }

//        if (swimmerX <= raftSWX) {
//            return (swimmerY >= raftNEY) ? "NW" : ((swimmerY <= raftSWY) ? "SW" : "W");
//        }
//        if (swimmerX >= raftNEX) {
//            return (swimmerY >= raftNEY) ? "NE" : ((swimmerY <= raftSWY) ? "SE" : "E");
//        }
//        if (swimmerY >= raftNEY) {
//            return "N";
//        } else {
//            return "S";
//        }
    }
}
