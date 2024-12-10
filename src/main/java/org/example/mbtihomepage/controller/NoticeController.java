package org.example.mbtihomepage.controller;

import lombok.RequiredArgsConstructor;
import org.example.mbtihomepage.dto.NoticeDTO;
import org.example.mbtihomepage.dto.PageRequestDTO;
import org.example.mbtihomepage.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notice/")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        if (pageRequestDTO.getKeyword() != null && !pageRequestDTO.getKeyword().isEmpty()) {
            model.addAttribute("result", noticeService.searchNotices(pageRequestDTO));
        } else {
            model.addAttribute("result", noticeService.getList(pageRequestDTO));
        }
    }


    @GetMapping("/register")
    public void register(){
    }

    @PostMapping("/register")
    public String registerPost(NoticeDTO dto, RedirectAttributes redirectAttributes) {
        Long postId = noticeService.register(dto); // Assuming this returns the ID of the new post
        redirectAttributes.addFlashAttribute("msg", postId); // ID of the newly added post
        redirectAttributes.addFlashAttribute("actionType", "add");
        return "redirect:/notice/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Long bno, Model model){
        NoticeDTO boardDTO = noticeService.get(bno);
        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {
        noticeService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg", bno); // ID of the deleted post
        redirectAttributes.addFlashAttribute("actionType", "delete");
        return "redirect:/notice/list";  // /board/list -> /notice/list로 수정
    }

    @PostMapping("/modify")
    public String modify(NoticeDTO dto, @ModelAttribute("requestDTO")PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        noticeService.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("bno", dto.getBno());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());

        return "redirect:/notice/read";  // /board/read -> /notice/read로 수정
    }
}