package jp.co.everforth.practice;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String... args) {
        if (args.length == 0) {
            System.out.println("引数が指定されていません。");
            System.exit(1);
        }

        Path filePath = null;
        try {
            filePath = Paths.get(args[0]);
        }
        catch (InvalidPathException e) {
            System.out.println("指定されたパスが正しくありません。:" + args[0]);
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(Tree.createTree(filePath));
    }

}


