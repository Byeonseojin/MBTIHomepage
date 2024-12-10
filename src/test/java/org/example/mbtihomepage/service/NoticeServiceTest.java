package org.example.mbtihomepage.service;




import org.example.mbtihomepage.dto.NoticeDTO;
import org.example.mbtihomepage.dto.PageRequestDTO;
import org.example.mbtihomepage.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    private NoticeService boardService;

    @Test
    public void testRegister(){
        NoticeDTO dto = NoticeDTO.builder()
                .title("101 Board Test...")
                .content("101 Board Test Board Test Board Test")
                .writerEmail("user7@kopo.ac.kr")
                .build();
        Long bno = boardService.register(dto);
        System.out.println("정상적으로 글이 저장되었습니다. : "+bno);
    }
    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<NoticeDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (NoticeDTO noticeDTO : result.getDtoList()){
            System.out.println(noticeDTO);
        }
    }

    @Test void testGet(){
        Long bno = 2L;

        NoticeDTO boardDTO = boardService.get(bno);

        System.out.println(boardDTO);
    }
    @Test
    public void testRemove(){
        Long bno = 1L;
        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify(){
        NoticeDTO boardDTO = NoticeDTO.builder()
                .bno(2L)
                .title("수정된 제목 연습")
                .content("수정된 내용 연습")
                .build();

        boardService.modify(boardDTO);
    }
}
