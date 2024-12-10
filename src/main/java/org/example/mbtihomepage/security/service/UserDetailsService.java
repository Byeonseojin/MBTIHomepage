package org.example.mbtihomepage.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.mbtihomepage.security.dto.AuthMemberDTO;
import org.example.mbtihomepage.entity.Member;
import org.example.mbtihomepage.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("★ClubUserDetailsService: "+ username);
        Optional<Member> result = memberRepository.findByEmail(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check Email or Social");
        }
        Member member = result.get();
        log.info("--------------------------");
        log.info(member);

        AuthMemberDTO authMember = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
        );
        authMember.setName(member.getName());
//        clubAuthMember.setFromSocial(clubMember.isFromSocial());
        return authMember;
    }
}