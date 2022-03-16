package org.jeecg.modules.finance.service.impl;

import org.jeecg.modules.finance.entity.RegisterShareHolder;
import org.jeecg.modules.finance.mapper.RegisterShareHolderMapper;
import org.jeecg.modules.finance.service.IRegisterShareHolderService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 股东信息
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@Service
public class RegisterShareHolderServiceImpl extends ServiceImpl<RegisterShareHolderMapper, RegisterShareHolder> implements IRegisterShareHolderService {
	
	@Autowired
	private RegisterShareHolderMapper registerShareHolderMapper;
	
	@Override
	public List<RegisterShareHolder> selectByMainId(String mainId) {
		return registerShareHolderMapper.selectByMainId(mainId);
	}
}
