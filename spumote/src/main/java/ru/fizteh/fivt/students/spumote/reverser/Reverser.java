package ru.fizteh.fivt.students.spumote.reverser;

/**
 * Created by spumote on 14.12.15.
 */
public class Reverser {
    public static void main(String[] args) {
        for (int i = args.length - 1; i >= 0; i--) {
            System.out.print(args[i] + " ");
        }
    }
}
