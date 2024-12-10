package org.example.mbtihomepage.repository;

import org.example.mbtihomepage.entity.Community;
import org.example.mbtihomepage.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;
@SpringBootTest
public class CommunityRepositoryTests {
    @Autowired
    private CommunityRepository communityRepository;

    @Test
    public void insertCommunityMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member communityMember = Member.builder()
                    .email("user" + i + "@kopo.ac.kr")
                    .build();

            Community community = Community.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .writer(communityMember)
                    .build();

            communityRepository.save(community);
        });
    }

    @Transactional
    @Test
    public void testRead() {
        Optional<Community> result = communityRepository.findById(5L);
        Community community = result.get();

        System.out.println(community);
        System.out.println(community.getWriter());
    }

//    @Test
//    public void testReadWithWriter() {
//        Object result = noticeRepository.getNoticeWithWriter(10L);
//        Object[] arr = (Object[]) result;
//        System.out.println(Arrays.toString(arr));
//    }

}
