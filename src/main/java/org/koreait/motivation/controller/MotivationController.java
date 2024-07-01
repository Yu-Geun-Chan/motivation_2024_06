package org.koreait.motivation.controller;

import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.entity.Motivation;
import org.koreait.motivation.service.MotivationService;

public class MotivationController {

    private MotivationService motivationService;


    public MotivationController() {
        motivationService = new MotivationService();
    }

    public void add() {
        System.out.print("body : ");
        String body = Container.getScanner().nextLine();
        System.out.print("source : ");
        String source = Container.getScanner().nextLine();

        int id = motivationService.doAdd(body, source);

        System.out.printf("%d번 motivation이 등록 되었습니다\n", id);
        // 마지막 번호 증가
    }

    public void list() {
        motivationService.showList();
    }

    public void delete(Rq rq) {
        System.out.println("== motivation delete ==");

        int id = motivationService.doDelete(rq);

        if (id == -1) {
            System.out.println("숫자를 입력하세요");
            return;
        } else if (id == -2) {
            System.out.println("해당 motivation은 존재하지 않습니다.");
            return;
        }
        System.out.printf(" %d번 motivation은 삭제되었습니다.\n", id);
    }

    public void edit(Rq rq) {
        System.out.println("== motivation edit ==");

        int id = motivationService.doEdit(rq);

        if (id == -1) {
            System.out.println("숫자를 입력하세요");
            return;
        } else if (id == -2) {
            System.out.println("해당 motivation은 존재하지 않습니다.");
            return;
        }
        System.out.printf("== %d번 motivation은 수정되었습니다. ==\n", id);
    }
}