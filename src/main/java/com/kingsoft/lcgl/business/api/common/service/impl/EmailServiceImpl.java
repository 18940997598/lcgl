package com.kingsoft.lcgl.business.api.common.service.impl;

import com.kingsoft.lcgl.business.api.common.service.EmailService;
import com.kingsoft.lcgl.business.api.common.task.EmailSendTask;
import javafx.util.Pair;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangdiankang on 2017/12/29.
 */
@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${email.user}")
    private String username;
    @Value("${email.pwd}")
    private String pwd;
    @Value("${email.hostName}")
    private String host;
    @Value("${email.fromAdress}")
    private String adress;
    @Value("${email.subject}")
    private String[] subject;
    @Value("${email.subjectKey}")
    private String[] subjectKey;
    private Map<String,String> SUBMAP =  new HashMap<>();
    private ExecutorService executor;
    public void sendMail(String[] receiver,String subjectkey,String content) {
        if(SUBMAP.size()<1||SUBMAP==null)
        {
            for(int i=0;i<subject.length;i++)
            {
                SUBMAP.put(subjectKey[i],subject[i]);
            }
        }
        SimpleEmail email = new SimpleEmail();
        EmailSendTask emailSendTask = new EmailSendTask(username,pwd,host,adress,SUBMAP,email,receiver,subjectkey,content);
        executor = Executors.newFixedThreadPool(1);
        executor.submit(emailSendTask);
    }

    public void sendAttachmentsMail(String sendTo, String titel, String content, List<Pair<String, File>> attachments) {
        if(SUBMAP.size()<1||SUBMAP==null)
        {
            for(int i=0;i<subject.length;i++)
            {
                SUBMAP.put(subjectKey[i],subject[i]);
            }
        }

    }

}
