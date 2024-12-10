package org.example.mbtihomepage.repository;


import org.example.mbtihomepage.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Member> findByEmail(String email);
}
