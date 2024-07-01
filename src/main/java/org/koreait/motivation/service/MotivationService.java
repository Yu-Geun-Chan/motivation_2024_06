package org.koreait.motivation.service;

import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;

// 사용자의 요청을 책임지는 로직(핵심 로직 담당)
public class MotivationService {

    private int lastId;
    private List<Motivation> motivations;

    public MotivationService() {
        lastId = 0;
        motivations = new ArrayList<Motivation>();
    }

    public int doAdd(String body, String source) {
        int id = lastId + 1;

        Motivation motivation = new Motivation(id, body, source);
        motivations.add(motivation);
        lastId = id; // 방금 만들어진 새 motivation의 id로 갱신해줘야 해서
        return id;
    }

    public void showList() {
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

    public int doDelete(Rq rq) {
        int id = 0;

        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e) {
            return -1;
        }

        Motivation foundMotivation = findById(id);

        if (foundMotivation == null) {
            return -2;
        }
        motivations.remove(foundMotivation);

        return id;
    }

    public int doEdit(Rq rq) {
        int id = 0;

        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e) {
            return -1;
        }

        Motivation foundMotivation = findById(id);

        if (foundMotivation == null) {
            return -2;
        }

        // 불러온 motivation 인스턴스 변수에 접근
        System.out.println("== body(기존) ==  : " + foundMotivation.getBody());
        System.out.println("== source(기존) == " + foundMotivation.getSource());

        System.out.print("body를 입력해 주세요. : ");
        String body = Container.getScanner().nextLine();
        System.out.print("source를 입력해 주세요. : ");
        String source = Container.getScanner().nextLine();

        foundMotivation.setBody(body);
        foundMotivation.setSource(source);

        return id;
    }

    // Motivation을 순회해서 value 값과 일치하는 id 값을 찾고
    // 그 안에 들어있는 데이터를 foundMotivation이라는 변수에 저장하는 로직
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
    // 여기까지
}
