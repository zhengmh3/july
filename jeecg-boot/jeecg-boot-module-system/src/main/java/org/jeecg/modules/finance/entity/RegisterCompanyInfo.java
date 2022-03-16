package org.jeecg.modules.finance.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 注册公司信息表
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@ApiModel(value="register_company_info对象", description="注册公司信息表")
@Data
@TableName("register_company_info")
public class RegisterCompanyInfo implements Serializable {
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
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
    private java.lang.String companyName;
	/**统一信用码*/
	@Excel(name = "统一信用码", width = 15)
    @ApiModelProperty(value = "统一信用码")
    private java.lang.String creditCode;
	/**企业注册时间*/
	@Excel(name = "企业注册时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "企业注册时间")
    private java.util.Date registerDate;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private java.lang.String address;
	/**法人*/
	@Excel(name = "法人", width = 15)
    @ApiModelProperty(value = "法人")
    private java.lang.String regalPersonName;
	/**法人身份证*/
	@Excel(name = "法人身份证", width = 15)
    @ApiModelProperty(value = "法人身份证")
    private java.lang.String regalPersonId;
	/**法人手机号*/
	@Excel(name = "法人手机号", width = 15)
    @ApiModelProperty(value = "法人手机号")
    private java.lang.String regalPersonTel;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @Dict(dicCode = "register_type")
    @ApiModelProperty(value = "类型")
    private java.lang.String registerType;
	/**开始时间*/
	@Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间")
    private java.util.Date startDate;
	/**到期时间*/
	@Excel(name = "到期时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期时间")
    private java.util.Date expireDate;
	/**收费类型*/
	/*@Excel(name = "收费类型", width = 15)
    @Dict(dicCode = "charge_type")
    @ApiModelProperty(value = "收费类型")
    private java.lang.String chargeType;*/
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
    private java.lang.Double account;
    /**状态*/
    //@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private java.lang.Integer status;
    /**时间数值*/
    //@Excel(name = "时间数值", width = 15)
  /*  @ApiModelProperty(value = "时间数值")
    private java.lang.Integer timeValue ;*/
    /**实际控制人*/
    @Excel(name = "实际控制人", width = 15)
    @ApiModelProperty(value = "实际控制人")
    private java.lang.String controller;

    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}
