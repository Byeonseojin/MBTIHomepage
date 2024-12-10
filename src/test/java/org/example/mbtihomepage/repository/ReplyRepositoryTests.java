package org.example.mbtihomepage.repository;

import org.example.mbtihomepage.entity.Community;
import org.example.mbtihomepage.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;
@SpringBootTest
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testListByBoard(){
        List<Reply> replyList = replyRepository.getRepliesByCommunityOrderByRno(Community.builder().bno(100L).build());
        replyList.forEach(reply -> System.out.println(reply));
    }
    @Test
    public void insertCommunitymembers(){
        IntStream.rangeClosed(1, 300).forEach(i ->{
            long bno = (long) (Math.random() * 100 + 1); //1~100 임의의 long 형의 정수 값


            Community community = Community.builder()
                    .bno(bno)
                    .build();

            Reply reply = Reply.builder()
                    .text("Reply" + i)
                    .community(community)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }
}
