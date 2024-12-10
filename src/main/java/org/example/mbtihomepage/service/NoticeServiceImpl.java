package org.example.mbtihomepage.service;

import lombok.RequiredArgsConstructor;
import org.example.mbtihomepage.dto.NoticeDTO;
import org.example.mbtihomepage.dto.PageRequestDTO;
import org.example.mbtihomepage.dto.PageResultDTO;
import org.example.mbtihomepage.entity.Member;
import org.example.mbtihomepage.entity.Notice;
import org.example.mbtihomepage.repository.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    private final NoticeRepository repository;
    @Override
    public Long register(NoticeDTO dto) {
        Notice notice = dtoToEntity(dto);
        repository.save(notice);
        return notice.getBno();
    }


    @Override
    public PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        // Function that converts the result set into NoticeDTO without the reply count
        Function<Object[], NoticeDTO> fn = (en -> entityToDTO((Notice) en[0], (Member) en[1]));

        // Pageable 객체를 생성하고, 정렬 기준을 설정합니다.
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());

        // 공지사항 목록을 가져오는 repository 메소드 호출
        Page<Object[]> result = repository.getNoticeWithWriter(pageable);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<NoticeDTO, Object[]> searchNotices(PageRequestDTO pageRequestDTO) {
        Function<Object[], NoticeDTO> fn = (en -> entityToDTO((Notice) en[0], (Member) en[1]));

        // Pass both type and keyword to the repository method
        Page<Object[]> result = repository.searchNotices(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );

        return new PageResultDTO<>(result, fn);
    }


    @Override
    public NoticeDTO get(Long bno) {
        Object result = repository.getNoticeByBno(bno);

        Object[] arr = (Object[]) result;

        NoticeDTO noticeDTO = entityToDTO((Notice) arr[0],(Member) arr[1]);
        return noticeDTO;
    }
    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        //원글 삭제
        repository.deleteById(bno);
    }
    @Transactional
    @Override
    public void modify(NoticeDTO noticeDTO) {
        Notice notice = repository.getReferenceById(noticeDTO.getBno());
        notice.changetitle(noticeDTO.getTitle());
        notice.changeContent(noticeDTO.getContent());

        repository.save(notice);
    }

}
