package org.jeecg.modules.finance.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 股东信息
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@ApiModel(value="register_company_info对象", description="注册公司信息表")
@Data
@TableName("register_share_holder")
public class RegisterShareHolder implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	@ApiModelProperty(value = "姓名")
	private java.lang.String name;
	/**身份证号/统一信用码*/
	@Excel(name = "身份证号", width = 15)
	@ApiModelProperty(value = "身份证号/统一信用码")
	private java.lang.String idCard;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
	@ApiModelProperty(value = "电话号码")
	private java.lang.String telPhone;
	/**所属公司*/
	@ApiModelProperty(value = "所属公司")
	private java.lang.String company;
	/**股权比例*/
	@Excel(name = "股权比例", width = 15)
	@ApiModelProperty(value = "股权比例")
	private java.lang.String sharesRate;
}
