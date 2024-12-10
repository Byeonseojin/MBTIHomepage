package org.example.mbtihomepage.service;


import org.example.mbtihomepage.dto.NoticeDTO;
import org.example.mbtihomepage.dto.PageRequestDTO;
import org.example.mbtihomepage.dto.PageResultDTO;
import org.example.mbtihomepage.entity.Member;
import org.example.mbtihomepage.entity.Notice;

public interface NoticeService {
    // 새글을 등록하는 기능
    Long register(NoticeDTO dto);  // NoticeDTO로 수정

    //게시목록 처리 기능
    PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    PageResultDTO<NoticeDTO, Object[]> searchNotices(PageRequestDTO pageRequestDTO);

    //특정 게시글 하나를 조회하는 기능
    NoticeDTO get(Long bno);

    //삭제 기능
    void removeWithReplies(Long bno);

    //수정 기능
    void modify(NoticeDTO noticeDTO);  // 여기서 noticeDTODTO가 아니라 noticeDTO로 수정

    // Entity를 DTO로 변환하는 메소드
    default NoticeDTO entityToDTO(Notice notice, Member member) {
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .bno(notice.getBno())
                .title(notice.getTitle())
                .content(notice.getContent())
                .regDate(notice.getRegDate())
                .modDate(notice.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .build();

        return noticeDTO;
    }

    // DTO를 Entity로 변환하는 메소드
    default Notice dtoToEntity(NoticeDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Notice notice = Notice.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return notice;
    }
}



