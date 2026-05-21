package com.ensharp.gayeongsignup.emailsend;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public interface MailSendService {
    String sendTxtEmail(MailTxtSendDto mailTxtSendDto) throws UnsupportedEncodingException;
}
