package app.domain.wiseSaying;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {

    private final Scanner sc;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingService = new WiseSayingService();
    }

    public void actionWrite() {
        System.out.println("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionPrint() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseSayingList = wiseSayingService.getAllItems();

        wiseSayingList.reversed().forEach(w -> {
            System.out.printf("%d / %s / %s\n", w.getId(), w.getAuthor(), w.getContent());
        });
    }

    public void actionDelete(String cmd) {

        String param = cmd.split("\\?")[1];
        String[] paramBits = param.split("=");
        String strId = paramBits[1];
        int id = Integer.parseInt(strId);

        boolean result = wiseSayingService.delete(id);

        if(!result) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
        }
    }
}