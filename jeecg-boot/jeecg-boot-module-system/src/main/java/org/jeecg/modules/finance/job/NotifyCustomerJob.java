package org.jeecg.modules.finance.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.finance.entity.EmailReceiver;
import org.jeecg.modules.finance.entity.RegisterCompanyInfo;
import org.jeecg.modules.finance.service.IEmailReceiverService;
import org.jeecg.modules.finance.service.IRegisterCompanyInfoService;
import org.jeecg.modules.message.entity.SysMessage;
import org.jeecg.modules.message.handle.ISendMsgHandle;
import org.jeecg.modules.message.handle.enums.SendMsgTypeEnum;
import org.jeecg.modules.message.handle.impl.EmailSendMsgHandle;
import org.jeecg.modules.message.handle.impl.FileEmailSendMsgHandle;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;
import java.util.Date;
import java.util.List;

/**
 * @author zhengmh
 * @description 定时通知客户是
 * @date 2021/9/2 20:50
 */

@Slf4j
public class NotifyCustomerJob implements Job {

    @Autowired
    private FileEmailSendMsgHandle fileEmailSendMsgHandle;

    @Autowired
    private IEmailReceiverService emailReceiverService;

    private final static String title = "客户到期通知";

    private String parameter;

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<EmailReceiver> list = emailReceiverService.list();
        if (CollectionUtils.isEmpty(list)){
            log.info("请配置至少有个接收人邮箱");
            return;
        }
        String[] receivers = new String[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            receivers[i] = list.get(i).getEmail();
        }
        String[] paramters = parameter.split(",");
        //发送邮件
        for (int i = 0; i < paramters.length; i++) {
            Integer interval = Integer.parseInt(paramters[i]);
            fileEmailSendMsgHandle.SendMsg(receivers,title,interval);
        }

    }

}
