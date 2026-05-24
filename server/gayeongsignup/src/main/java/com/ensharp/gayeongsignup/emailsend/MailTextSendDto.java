package com.ensharp.gayeongsignup.emailsend;

public record MailTextSendDto( ///예외처리 지우기
                               String emailAddr,
                               String subject,
                               String content
) {

    public MailTextSendDto(MailTextSend entity) { //생성자
        this(
                entity.getEmailAddr(),
                entity.getSubject(),
                entity.getContent()
        );
    }

}
