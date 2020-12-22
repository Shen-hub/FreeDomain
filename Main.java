package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<BlackList> bls = new ArrayList<BlackList>();

    public static void readBlackList(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));
        String[] buf = new String[4];
        sc.useDelimiter("[;\\n]");
        while(sc.hasNextLine()){
            int i = 0;
            while (sc.hasNext() && i < 4)
            {
                buf[i] = sc.next();
                i++;
            }
            BlackList bl = new BlackList(buf[0], buf[1], buf[2], buf[3]);
            bls.add(bl);
        }
    }

    public static void findDomain()
    {
        for (BlackList bl : bls)
        {
            if(bl.url.split("://")[0].equals("https"))
            {
                System.out.println(bl.domain);
                System.out.println("-------------------");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        readBlackList("register.txt");
        System.out.println("Загрузка регистра завершена.");
        findDomain();
    }
}
