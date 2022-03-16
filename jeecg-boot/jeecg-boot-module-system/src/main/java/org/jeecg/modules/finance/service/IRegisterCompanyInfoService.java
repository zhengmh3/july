package org.jeecg.modules.finance.service;

import org.jeecg.modules.finance.entity.RegisterShareHolder;
import org.jeecg.modules.finance.entity.RegisterCompanyInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 注册公司信息表
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface IRegisterCompanyInfoService extends IService<RegisterCompanyInfo> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(RegisterCompanyInfo registerCompanyInfo,List<RegisterShareHolder> registerShareHolderList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(RegisterCompanyInfo registerCompanyInfo,List<RegisterShareHolder> registerShareHolderList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
