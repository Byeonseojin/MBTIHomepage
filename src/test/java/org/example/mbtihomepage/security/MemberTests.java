package org.example.mbtihomepage.security;

import org.example.mbtihomepage.entity.Member;
import org.example.mbtihomepage.entity.MemberRole;
import org.example.mbtihomepage.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {
    @Autowired
    private MemberRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){
        // user1-user80:USER
        // USER81-USER90: USER, MANAGER
        // USER91-USER100: USER, MANAGER, ADMIN
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("user"+i+"@kopo.ac.kr")
                    .name("사용자"+i)
                    .password(passwordEncoder.encode("1234"))
                    .build();
            member.addMemberRole(MemberRole.USER);
            if(i>80){
                member.addMemberRole(MemberRole.MANAGER);
            }
            if(i>90){
                member.addMemberRole(MemberRole.ADMIN);
            }
            repository.save(member);
        });
    }

    @Test
    public void testRead() {
        Optional<Member> result = repository.findByEmail("user1@kopo.ac.kr");
        Member member = result.get();
        System.out.println("❤❤❤" + member);
    }
    @Test
    public void testUser1And2() {
        System.out.println("=== User1 검색 ===");
        Optional<Member> user1 = repository.findByEmail("user1@kopo.ac.kr");
        user1.ifPresent(u -> {
            System.out.println("User1 이메일: " + u.getEmail());
            System.out.println("User1 비밀번호: " + u.getPassword());
            System.out.println("User1 권한: " + u.getRoleSet());
        });

        System.out.println("=== User2 검색 ===");
        Optional<Member> user2 = repository.findByEmail("user2@kopo.ac.kr");
        user2.ifPresent(u -> {
            System.out.println("User2 이메일: " + u.getEmail());
            System.out.println("User2 비밀번호: " + u.getPassword());
            System.out.println("User2 권한: " + u.getRoleSet());
        });
        System.out.println("=== User12 검색 ===");
        Optional<Member> user12 = repository.findByEmail("user12@kopo.ac.kr");
        user12.ifPresent(u -> {
            System.out.println("User12 이메일: " + u.getEmail());
            System.out.println("User12 비밀번호: " + u.getPassword());
            System.out.println("User12 권한: " + u.getRoleSet());
        });
    }
}
