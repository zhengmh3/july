package org.jeecg.modules.finance.service.impl;

import org.jeecg.modules.finance.entity.EmailReceiver;
import org.jeecg.modules.finance.mapper.EmailReceiverMapper;
import org.jeecg.modules.finance.service.IEmailReceiverService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 邮件接收人
 * @Author: jeecg-boot
 * @Date:   2021-09-05
 * @Version: V1.0
 */
@Service
public class EmailReceiverServiceImpl extends ServiceImpl<EmailReceiverMapper, EmailReceiver> implements IEmailReceiverService {

}
