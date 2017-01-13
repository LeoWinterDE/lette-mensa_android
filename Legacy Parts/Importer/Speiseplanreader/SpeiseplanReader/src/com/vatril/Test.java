package com.vatril;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.vatril.speise.Speise;

import java.util.Scanner;

/**
 * Created by Nikolai Jay Summers on 27.07.2016 at 17:15.
 */
public class Test {
    public static void main(String[] args) throws java.io.IOException {

        for (String s : args){
            System.out.println(args);
        }


        Speise[][] speisen = new com.vatril.analyzer.Analyzer().getSpeisen("/home/vatril/Dropbox/Javaschool/mensa_2016_07_05/NormaleKueche2016/C1.xls");

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the Speiseplanmanager!!!");

        while (true){
            try{
                System.out.print("Enter a row: ");
                int x = scan.nextInt();
                System.out.print("Enter a column: ");
                int y = scan.nextInt();
                System.out.println("");

                System.out.println(speisen[x][y]);

                System.out.println("");
                System.out.println("");
                System.out.print("Continue? [Y/N] ");
                switch (scan.next().toLowerCase()){
                    case "n":System.exit(0);
                    case "no":System.exit(0);
                }
                System.out.println("");

            }catch (Exception e){
                System.out.println("An error has occurred");
                System.out.println("");
                System.out.println("");
            }
        }
    }


}
