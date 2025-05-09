package com.example.korea_sleepTech_springboot.시험;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Table(name="user")
@NoArgsConstructor
public class Test01 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;

    public Test01(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    @RestController
    @RequestMapping("/hello")
    class Controller {
        String str = "Hello, Spring Boot!";

        @GetMapping
        public String getStr() {
            String str1 = str;
            return str1;
        }
    }
}

// 1번: 3
// 2번: 2
// 3번: 3
// 4번: 2
// 5번: 2
// 6번: 3
// 7번: 4
// 8번: 4
// 9번: 3
// 10번: 3
// 11번: @
// 12번: DELETE
// 13번: @pathVariable
// 14번: @RequestParam
// 15번: 클라이언트에게 보여줄때
// 16번: DB를 CRUD함
// 17번: PUT
// 18: 컨트롤러

// 난이도: 코드 따라 쓸 때는 뭐가 뭔지 알거같은데 말로 풀어 놓으니까 헷갈려용
// 스스로 구현 할 수 있는 정도: book보고 했는데 참고 사항이 없으면 혼자 절대 못할거 같아용
// 이해 정도: 이해는 되지만 혼자 구현x, 연휴동안 노트북에 손도 안댔지만 점점 기억이 떠올라용