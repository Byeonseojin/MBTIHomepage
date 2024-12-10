package org.example.mbtihomepage.controller;

import lombok.RequiredArgsConstructor;
import org.example.mbtihomepage.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage/")
@RequiredArgsConstructor
public class HomepageController {
    private final NoticeService noticeService;
    @GetMapping("/index")
    public String index() {
        return "homepage/index";  // templates/homepage/main.html을 가리킴
    }
    @GetMapping("/main")
    public String showMain() {
        return "homepage/main";  // templates/homepage/main.html을 정확하게 가리킴
    }
    @GetMapping("/mbti01")
    public void showMbti01() {

    }

    @GetMapping("/mbti02")
    public String showMbti02() {
        return "homepage/mbti02"; // src/main/resources/templates/homepage/mbti02.html
    }

    @GetMapping("/mbti03")
    public String showMbti03() {
        return "homepage/mbti03"; // src/main/resources/templates/homepage/mbti03.html
    }

    @GetMapping("/mbti04")
    public String showMbti04() {
        return "homepage/mbti04"; // src/main/resources/templates/homepage/mbti04.html
    }

    @GetMapping("/mbti01/accessory")
    public String showAccessory() {
        return "homepage/mbti01_accessory"; // src/main/resources/templates/homepage/mbti01_accessory.html
    }
    @GetMapping("/login")
    public String loginPage() {
        return "homepage/login";
    }

}
