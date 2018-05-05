package com.kingsoft.lcgl.business.api.common.task;


import org.apache.commons.mail.SimpleEmail;
import org.slf4j.LoggerFactory;
import java.util.Map;



/**
 * Created by yangdiankang on 2018/1/2.
 */
public class EmailSendTask implements Runnable{

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailSendTask.class);
    private final String username;
    private final String pwd;
    private final String host;
    private final String adress;
    private final Map<String,String> SUBMAP;
    private final SimpleEmail email;
    private final String[] receiver;
    private final String subjectkey;
    private final String content;

    public EmailSendTask(String username, String pwd, String host, String adress,
                         Map<String, String> submap, SimpleEmail email, String[] receiver,
                         String subjectkey, String content) {
        this.username = username;
        this.pwd = pwd;
        this.host = host;
        this.adress = adress;
        SUBMAP = submap;
        this.email = email;
        this.receiver = receiver;
        this.subjectkey = subjectkey;
        this.content = content;
    }
    @Override
    public void run() {
        email.setHostName(host);
        try {
            for(String to:receiver){
                email.addTo(to);
            }
            email.setAuthentication(username, pwd);
            email.setCharset("UTF-8");//设定内容的语言集
            email.setFrom(adress);//设定发件人
            email.setSubject(SUBMAP.get(subjectkey));//设定主题
            email.setMsg(content);//设定邮件内容

            email.send();//发送邮件
            logger.info("发送邮件成功,主题："+SUBMAP.get(subjectkey)+"，内容："+content+"，收件人数："+receiver.length);
        }catch (Exception e){
            logger.info("发送邮件失败"+e);
    }
    }
}
