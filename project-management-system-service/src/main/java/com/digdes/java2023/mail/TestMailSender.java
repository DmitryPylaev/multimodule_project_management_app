package com.digdes.java2023.mail;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class TestMailSender {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final String address;

    public TestMailSender(JavaMailSender mailSender,
                          SpringTemplateEngine templateEngine,
                          @Value("${spring.mail.username}") String address) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.address = address;
    }

    public void send(String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Map<String, Object> map = new HashMap<>();
        map.put("message", text);
        Context context = new Context();
        context.setVariables(map);
        String emailContent = templateEngine.process("index", context);
        mimeMessageHelper.setTo(address);
        mimeMessageHelper.setSubject("edudigdes");
        mimeMessageHelper.setFrom(address);
        mimeMessageHelper.setText(emailContent, true);
        log.info("В методе TestMailSender::send");
        mailSender.send(message);
    }
}
