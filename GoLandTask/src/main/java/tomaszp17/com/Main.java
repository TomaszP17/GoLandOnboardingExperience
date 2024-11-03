package tomaszp17.com;

import tomaszp17.com.indexer.Indexer;
import tomaszp17.com.tokenizer.TokenizerImpl;
import tomaszp17.com.tokenizer.Tokenizer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Tokenizer tokenizer = new TokenizerImpl();
        Indexer indexer = new Indexer(tokenizer);
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------");
        System.out.println("Ladies and Gentlemen, please sit down and watch!");
        System.out.println("IT'S SHOW TIME");
        System.out.println("Author: TomaszP17");
        System.out.println("-------------------------------------------------");
        while (true) {
            System.out.println("MENU");
            System.out.println("0. EXIT");
            System.out.println("1. ADD NEW FILE/DIR TO INDEX");
            System.out.println("2. FIND WORD");
            System.out.println("-------------------------------------------------");
            System.out.print("CHOOSE OPTION: ");
            String userOptionInput = scanner.nextLine();

            switch (userOptionInput) {
                case "1":
                    System.out.print("ENTER A PATH TO FILE/DIR: ");
                    String userPathInput = scanner.nextLine();
                    try {
                        indexer.indexPath(userPathInput);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    System.out.print("ENTER A WORD YOU'D LIKE TO FIND: ");
                    String userWordInput = scanner.nextLine();
                    Set<Path> results = indexer.search(userWordInput);
                    if (results.isEmpty()) {
                        System.out.println("THERE IS NO THAT WORD: " + userWordInput + " IN INDEXED FILES");
                    } else {
                        System.out.println("FILES WHICH HAVE THIS WORD: '" + userWordInput + "':");
                        results.forEach(System.out::println);
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("BAD CHOICE, TRY AGAIN ;)");
                    System.out.println("-------------------------------------------------");
            }
        }
    }
}