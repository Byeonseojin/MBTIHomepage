package org.example.mbtihomepage.repository;



import org.example.mbtihomepage.entity.Member;
import org.example.mbtihomepage.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class NoticeRepositoryTests {
    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@kopo.ac.kr")
                    .build();

            Notice notice = Notice.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .writer(member)
                    .build();

            noticeRepository.save(notice);
        });
    }

    @Transactional
    @Test
    public void testRead() {
        Optional<Notice> result = noticeRepository.findById(5L);
        Notice notice = result.get();

        System.out.println(notice);
        System.out.println(notice.getWriter());
    }

//    @Test
//    public void testReadWithWriter() {
//        Object result = noticeRepository.getNoticeWithWriter(10L);
//        Object[] arr = (Object[]) result;
//        System.out.println(Arrays.toString(arr));
//    }

}