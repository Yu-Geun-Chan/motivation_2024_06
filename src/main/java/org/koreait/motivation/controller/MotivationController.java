package org.koreait.motivation.controller;

import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.entity.Motivation;

import java.util.*;

public class MotivationController {

    int lastId;
    List<Motivation> motivations;

    public MotivationController() {
        lastId = 0;
        motivations = new ArrayList<>();
    }

    public void add() {
        int id = lastId + 1;
        System.out.print("body : ");
        String body = Container.getScanner().nextLine();
        System.out.print("source : ");
        String source = Container.getScanner().nextLine();

        Motivation motivation = new Motivation(id, body, source);

        motivations.add(motivation);

        System.out.printf("%d번 motivation이 등록 되었습니다\n", id);
        lastId++; // 마지막 번호 증가
    }

    public void list() {
        if (motivations.size() == 0) {
            System.out.println("등록된 motivation 없음");
            return;
        }
        System.out.println("== motivation list ==");
        System.out.printf("  id   //   source   //   body  \n");
        System.out.println("=".repeat(35));

        for (int i = motivations.size() - 1; i >= 0; i--) {
            Motivation motivation = motivations.get(i);

            if (motivation.getSource().length() > 7) {
                System.out.printf("   %d  //    %s    //    %s  \n", motivation.getId(), motivation.getSource().substring(0, 5) + "...", motivation.getBody());
                continue;
            }

            System.out.printf("   %d  //    %s     //    %s  \n", motivation.getId(), motivation.getSource(), motivation.getBody());
        }
    }

    public void delete(Rq rq) {
        int id = 0;

        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e) {
            System.out.println("== 숫자를 입력하세요. ==");
            return;
        }

        Motivation foundMotivation = findById(id);

        if (foundMotivation == null) {
            System.out.printf("== %d번 motivation은 없습니다. ==\n", id);
            return;
        }
        motivations.remove(foundMotivation);

        System.out.println("== motivation delete ==");
        System.out.printf("== %d번 motivation은 삭제되었습니다.\n", id);
    }

    private Motivation findById(int id) {
        Motivation foundMotivation;
        for (Motivation motivation : motivations) {
            if (motivation.getId() == id) {
                foundMotivation = motivation;
                return foundMotivation;
            }
        }
        return null;
    }
}