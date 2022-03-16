package org.jeecg.modules.finance.service;

import org.jeecg.modules.finance.entity.RegisterShareHolder;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 股东信息
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface IRegisterShareHolderService extends IService<RegisterShareHolder> {

	public List<RegisterShareHolder> selectByMainId(String mainId);
}
