package org.jeecg.modules.message.handle.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import freemarker.template.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.finance.entity.RegisterCompanyInfo;
import org.jeecg.modules.finance.service.IRegisterCompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengmh
 * @className FileEmailSendMsgHandle
 * @description To do
 * @date 2021/9/4 15:28
 */
@Component
@Slf4j
public class FileEmailSendMsgHandle{

    @Autowired
    Configuration configuration;

    @Autowired
    private IRegisterCompanyInfoService registerCompanyInfoService;

    private final static String templatePath = "email.ftl";

    private final static String from = "1491646291@qq.com";

    public void SendMsg(String[] receivers, String title,Integer interval) {
        JavaMailSender mailSender = (JavaMailSender) SpringContextUtils.getBean("mailSender");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(receivers);
            helper.setSubject(title);
            List<RegisterCompanyInfo> copmanyInfoList = queryCompanyInfo(interval);
            if (copmanyInfoList.size() == 0){
                return;
            }
            Map<String, Object> model = new HashMap<>();
            model.put("interval",interval);
            model.put("copmanyInfoList",copmanyInfoList);
            Template template = configuration.getTemplate(templatePath);
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(content,true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询到期的公司
    public List<RegisterCompanyInfo> queryCompanyInfo(Integer interval){
        QueryWrapper<RegisterCompanyInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",1);
        //通知日期
        Date informDate = DateUtils.addDays(new Date(), interval);
        String dateStr = org.jeecg.common.util.DateUtils.formatDate(informDate);
        queryWrapper.in("expire_date",dateStr);
        List<RegisterCompanyInfo> list = registerCompanyInfoService.list(queryWrapper);
        return list;
    }
}
