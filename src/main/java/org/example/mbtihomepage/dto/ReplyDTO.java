package org.example.mbtihomepage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data // Add this annotation
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long rno;
    private String text;
    private String replyer;
    private Long bno;
    private LocalDateTime regDate, modDate;
}
