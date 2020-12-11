package com.tutorialspoint;

import java.util.*;
import java.util.regex.Pattern;

public class ScannerDemo {

   public static void main(String[] args) {

   String s = "Hello World! 3+3.0=6";

   // create a new scanner with the specified String Object
   Scanner scanner = new Scanner(s);

   // check if the scanner has a token
   System.out.println("" + scanner.hasNext());

   // print the rest of the string
   System.out.println("" + scanner.nextLine());

   // check if the scanner has a token after printing the line
   System.out.println("" + scanner.hasNext());

   // close the scanner
   scanner.close();
   }
}