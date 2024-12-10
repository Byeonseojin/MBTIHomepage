package org.example.mbtihomepage.controller;

import lombok.RequiredArgsConstructor;
import org.example.mbtihomepage.dto.CommunityDTO;
import org.example.mbtihomepage.dto.PageRequestDTO;
import org.example.mbtihomepage.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/community/")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        if (pageRequestDTO.getKeyword() != null && !pageRequestDTO.getKeyword().isEmpty()) {
            model.addAttribute("result", communityService.searchCommunities(pageRequestDTO));
        } else {
            model.addAttribute("result", communityService.getList(pageRequestDTO));
        }
    }


    @GetMapping("/register")
    public void register(){
    }

    @PostMapping("/register")
    public String registerPost(CommunityDTO dto, RedirectAttributes redirectAttributes) {
        Long postId = communityService.register(dto); // Assuming this returns the ID of the new post
        redirectAttributes.addFlashAttribute("msg", postId); // ID of the newly added post
        redirectAttributes.addFlashAttribute("actionType", "add");
        return "redirect:/community/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Long bno, Model model){
        CommunityDTO communityDTO = communityService.get(bno);
        model.addAttribute("dto", communityDTO);
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {
        communityService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg", bno); // ID of the deleted post
        redirectAttributes.addFlashAttribute("actionType", "delete");
        return "redirect:/community/list";  // /board/list -> /notice/list로 수정
    }

    @PostMapping("/modify")
    public String modify(CommunityDTO dto, @ModelAttribute("requestDTO")PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        communityService.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("bno", dto.getBno());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());

        return "redirect:/community/read";  // /board/read -> /notice/read로 수정
    }
}
