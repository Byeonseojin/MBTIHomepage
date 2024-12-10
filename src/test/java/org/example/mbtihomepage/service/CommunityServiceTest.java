package org.example.mbtihomepage.service;

import org.example.mbtihomepage.dto.CommunityDTO;
import org.example.mbtihomepage.dto.NoticeDTO;
import org.example.mbtihomepage.dto.PageRequestDTO;
import org.example.mbtihomepage.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommunityServiceTest {
    @Autowired
    private CommunityService communityService;

    @Test
    public void testRegister(){
        CommunityDTO dto = CommunityDTO.builder()
                .title("101 Board Test...")
                .content("101 Board Test Board Test Board Test")
                .writerEmail("user7@kopo.ac.kr")
                .build();
        Long bno = communityService.register(dto);
        System.out.println("정상적으로 글이 저장되었습니다. : "+bno);
    }
    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<CommunityDTO, Object[]> result = communityService.getList(pageRequestDTO);

        for (CommunityDTO communityDTO : result.getDtoList()){
            System.out.println(communityDTO);
        }
    }

    @Test void testGet(){
        Long bno = 2L;

        CommunityDTO communityDTO = communityService.get(bno);

        System.out.println(communityDTO);
    }
    @Test
    public void testRemove(){
        Long bno = 1L;
        communityService.removeWithReplies(bno);
    }

    @Test
    public void testModify(){
        CommunityDTO communityDTO = CommunityDTO.builder()
                .bno(2L)
                .title("수정된 제목 연습")
                .content("수정된 내용 연습")
                .build();

        communityService.modify(communityDTO);
    }
}
