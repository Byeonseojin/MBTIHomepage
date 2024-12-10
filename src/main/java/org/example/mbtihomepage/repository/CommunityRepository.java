package org.example.mbtihomepage.repository;

import org.example.mbtihomepage.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    @Query("SELECT n, w FROM Community n LEFT JOIN n.writer w WHERE " +
            "(:type = 't' AND n.title LIKE %:keyword%) OR " +
            "(:type = 'c' AND n.content LIKE %:keyword%) OR " +
            "(:type = 'w' AND w.name LIKE %:keyword%) OR " +
            "(:type = 'tc' AND (n.title LIKE %:keyword% OR n.content LIKE %:keyword%)) OR " +
            "(:type = 'tcw' AND (n.title LIKE %:keyword% OR n.content LIKE %:keyword% OR w.name LIKE %:keyword%)) OR " +
            "(:type IS NULL AND (n.title LIKE %:keyword% OR n.content LIKE %:keyword% OR w.name LIKE %:keyword%))")
    Page<Object[]> searchCommunities(@Param("type") String type, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT n, w FROM Community n LEFT JOIN n.writer w WHERE n.bno = :bno")
    Object getCommunityByBno(@Param("bno") Long bno);

    @Query("SELECT n, w FROM Community n LEFT JOIN n.writer w")
    Page<Object[]> getCommunityWithWriter(Pageable pageable);
}
