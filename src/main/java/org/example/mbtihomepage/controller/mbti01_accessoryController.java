package org.example.mbtihomepage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mbti01_accessory/")
@RequiredArgsConstructor
public class mbti01_accessoryController {
    @GetMapping("/01_E")
    public String show01_E() {
        return "mbti01_accessory/01_E"; // Return the template name
    }

    @GetMapping("/01_F")
    public String show01_F() {
        return "mbti01_accessory/01_F"; // Return the template name
    }

    @GetMapping("/01_I")
    public String show01_I() {
        return "mbti01_accessory/01_I"; // Return the template name
    }

    @GetMapping("/01_J")
    public String show01_J() {
        return "mbti01_accessory/01_J"; // Return the template name
    }

    @GetMapping("/01_N")
    public String show01_N() {
        return "mbti01_accessory/01_N"; // Return the template name
    }

    @GetMapping("/01_P")
    public String show01_P() {
        return "mbti01_accessory/01_P"; // Return the template name
    }

    @GetMapping("/01_S")
    public String show01_S() {
        return "mbti01_accessory/01_S"; // Return the template name
    }

    @GetMapping("/01_T")
    public String show01_T() {
        return "mbti01_accessory/01_T"; // Return the template name
    }
}
