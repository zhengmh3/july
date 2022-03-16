package org.jeecg.modules.finance.service.impl;

import org.jeecg.modules.finance.entity.RegisterCompanyInfo;
import org.jeecg.modules.finance.entity.RegisterShareHolder;
import org.jeecg.modules.finance.mapper.RegisterShareHolderMapper;
import org.jeecg.modules.finance.mapper.RegisterCompanyInfoMapper;
import org.jeecg.modules.finance.service.IRegisterCompanyInfoService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 注册公司信息表
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@Service
public class RegisterCompanyInfoServiceImpl extends ServiceImpl<RegisterCompanyInfoMapper, RegisterCompanyInfo> implements IRegisterCompanyInfoService {

	@Autowired
	private RegisterCompanyInfoMapper registerCompanyInfoMapper;
	@Autowired
	private RegisterShareHolderMapper registerShareHolderMapper;
	
	@Override
	@Transactional
	public void saveMain(RegisterCompanyInfo registerCompanyInfo, List<RegisterShareHolder> registerShareHolderList) {
		registerCompanyInfoMapper.insert(registerCompanyInfo);
		if(registerShareHolderList!=null && registerShareHolderList.size()>0) {
			for(RegisterShareHolder entity:registerShareHolderList) {
				//外键设置
				entity.setCompany(registerCompanyInfo.getId());
				registerShareHolderMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(RegisterCompanyInfo registerCompanyInfo,List<RegisterShareHolder> registerShareHolderList) {
		registerCompanyInfoMapper.updateById(registerCompanyInfo);
		
		//1.先删除子表数据
		registerShareHolderMapper.deleteByMainId(registerCompanyInfo.getId());
		
		//2.子表数据重新插入
		if(registerShareHolderList!=null && registerShareHolderList.size()>0) {
			for(RegisterShareHolder entity:registerShareHolderList) {
				//外键设置
				entity.setCompany(registerCompanyInfo.getId());
				registerShareHolderMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		registerShareHolderMapper.deleteByMainId(id);
		registerCompanyInfoMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			registerShareHolderMapper.deleteByMainId(id.toString());
			registerCompanyInfoMapper.deleteById(id);
		}
	}
	
}
