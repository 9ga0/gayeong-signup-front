package com.ensharp.gayeongsignup.emailsend;

public class MailTextSend {
    private String emailAddr;
    private String subject;
    private String content;

    ///네이밍 컨벤션 주의. 블로그그대로
    public MailTextSend(){}

    public String getEmailAddr() {
        return emailAddr;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }
}
