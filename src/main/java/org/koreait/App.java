package org.koreait;

import org.koreait.Motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== motivation execution ==");

        int lastId = 0;

        List<Motivation> motivations = new ArrayList<>();

        while (true) {
            System.out.printf("command) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("== motivation end ==");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
                continue;
            }

            if (cmd.equals("add")) {
                System.out.printf("motivation : ");
                String body = sc.nextLine();
                System.out.printf("source : ");
                String source = sc.nextLine();
                lastId++;

                Motivation motivation = new Motivation(lastId, body, source);
                motivations.add(motivation);

                System.out.printf("%d번 motivation이 등록 되었습니다.\n", motivation.getId());
            } else if (cmd.equals("list")) {
                if (motivations.size() == 0) {
                    System.out.println("등록된 motivation이 없습니다.");
                    continue;
                }
                System.out.println("== motivation list == ");
                System.out.println(" id   //  source  //  body  ");
                System.out.println("=".repeat(35));
                for (int i = motivations.size() - 1; i >= 0; i--) {
                    Motivation motivation = motivations.get(i);
                    if (motivation.getSource().length() > 7) {
                        System.out.printf("  %d   //   %s    //    %s  \n", motivation.getId(), motivation.getSource().substring(0, 5) + "...", motivation.getBody());
                    } else {
                        System.out.printf("  %d   //   %s    //    %s  \n", motivation.getId(), motivation.getSource(), motivation.getBody());
                    }
                }
            }
        }
    }
}






