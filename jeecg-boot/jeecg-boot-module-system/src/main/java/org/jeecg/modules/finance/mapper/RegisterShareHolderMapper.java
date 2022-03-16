package org.jeecg.modules.finance.mapper;

import java.util.List;
import org.jeecg.modules.finance.entity.RegisterShareHolder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 股东信息
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
public interface RegisterShareHolderMapper extends BaseMapper<RegisterShareHolder> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<RegisterShareHolder> selectByMainId(@Param("mainId") String mainId);
}
