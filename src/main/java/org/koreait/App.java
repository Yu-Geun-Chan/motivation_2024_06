package org.koreait;

import org.koreait.motivation.controller.MotivationController;
import org.koreait.motivation.entity.Motivation;
import org.koreait.system.controller.SystemController;

import java.util.Scanner;

public class App {
    byte system_status = 1;

    public void run() {
        System.out.println("== motivation execution ==");

        SystemController systemController = new SystemController();
        MotivationController motivationController = new MotivationController();

        while (system_status == 1) {
            System.out.printf("command) ");
            String cmd = Container.getScanner().nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
                continue;
            }

            Rq rq = new Rq(cmd);

            if (rq.getErrMsg().equals("오타 있음(id)")) {
                continue;
            }

            switch (rq.getActionMethod()) {
                case "exit":
                    systemController.exit();
                    system_status = 0;
                    break;
                case "add":
                    motivationController.add();
                    break;
                case "list":
                    motivationController.list();
                    break;
                case "delete":
                    motivationController.delete(rq);
                    break;
                default:
                    System.out.println("사용할 수 없는 명령어입니다.");
                    break;
            }
        }
    }
}







