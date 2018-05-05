package com.kingsoft.lcgl.business.api.common.service;

import javafx.util.Pair;

import java.io.File;
import java.util.List;


/**
 * Created by yangdiankang on 2017/12/29.
 */
public interface EmailService {
    /**
     * 发送邮件
     * @param content 邮件内容
     */
     void sendMail(String[] receiver,String subjectkey,String content);

    /**
     * 发送文件邮件
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content 邮件内容
     * @param attachments<文件名，附件> 附件列表
     */
     void sendAttachmentsMail(String sendTo, String titel, String content, List<Pair<String, File>> attachments);

}
