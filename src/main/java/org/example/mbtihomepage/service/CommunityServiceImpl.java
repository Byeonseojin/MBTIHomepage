package org.example.mbtihomepage.service;

import lombok.RequiredArgsConstructor;
import org.example.mbtihomepage.dto.CommunityDTO;
import org.example.mbtihomepage.dto.PageRequestDTO;
import org.example.mbtihomepage.dto.PageResultDTO;
import org.example.mbtihomepage.entity.Community;
import org.example.mbtihomepage.entity.Member;
import org.example.mbtihomepage.repository.CommunityRepository;
import org.example.mbtihomepage.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{
    private final CommunityRepository repository;
    private final ReplyRepository replyRepository;
    @Override
    public Long register(CommunityDTO dto) {
        Community community = dtoToEntity(dto);
        repository.save(community);
        return community.getBno();
    }


    @Override
    public PageResultDTO<CommunityDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Function<Object[], CommunityDTO> fn = (en -> entityToDTO((Community) en[0], (Member) en[1]));

        // Pageable 객체를 생성하고, 정렬 기준을 설정합니다.
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());

        // 공지사항 목록을 가져오는 repository 메소드 호출
        Page<Object[]> result = repository.getCommunityWithWriter(pageable);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<CommunityDTO, Object[]> searchCommunities(PageRequestDTO pageRequestDTO) {
        Function<Object[], CommunityDTO> fn = (en -> entityToDTO((Community) en[0], (Member) en[1]));

        // Pass both type and keyword to the repository method
        Page<Object[]> result = repository.searchCommunities(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );

        return new PageResultDTO<>(result, fn);
    }


    @Override
    public CommunityDTO get(Long bno) {
        Object result = repository.getCommunityByBno(bno);

        Object[] arr = (Object[]) result;

        CommunityDTO communityDTO = entityToDTO((Community) arr[0],(Member)  arr[1]);
        return communityDTO;
    }
    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        //댓글 삭제
        replyRepository.deleteByBno(bno);
        //원글 삭제
        repository.deleteById(bno);
    }
    @Transactional
    @Override
    public void modify(CommunityDTO communityDTO) {
        Community community = repository.getReferenceById(communityDTO.getBno());
        community.changetitle(communityDTO.getTitle());
        community.changeContent(communityDTO.getContent());

        repository.save(community);
    }

}
