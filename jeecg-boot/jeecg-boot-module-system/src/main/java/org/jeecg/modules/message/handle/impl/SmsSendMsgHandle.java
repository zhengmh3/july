package org.jeecg.modules.message.handle.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DySmsEnum;
import org.jeecg.common.util.DySmsHelper;
import org.jeecg.modules.message.handle.ISendMsgHandle;

@Slf4j
public class SmsSendMsgHandle implements ISendMsgHandle {

	@Override
	public void SendMsg(String es_receiver, String es_title, String es_content) {
		// TODO Auto-generated method stub
		log.info("发短信");
		String phone = "18770915633";
		JSONObject message = new JSONObject();
		message.put("code", "您的产品明天将到期,请及时续费");
		try {
			boolean flag = DySmsHelper.sendSms(phone,message, DySmsEnum.LOGIN_TEMPLATE_CODE);
			if (flag){
				log.info("发送短信成功!");
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}
