package org.jeecg.modules.finance.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.finance.entity.EmailReceiver;
import org.jeecg.modules.finance.service.IEmailReceiverService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 邮件接收人
 * @Author: jeecg-boot
 * @Date:   2021-09-05
 * @Version: V1.0
 */
@Api(tags="邮件接收人")
@RestController
@RequestMapping("/finance/emailReceiver")
@Slf4j
public class EmailReceiverController extends JeecgController<EmailReceiver, IEmailReceiverService> {
	@Autowired
	private IEmailReceiverService emailReceiverService;
	
	/**
	 * 分页列表查询
	 *
	 * @param emailReceiver
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "邮件接收人-分页列表查询")
	@ApiOperation(value="邮件接收人-分页列表查询", notes="邮件接收人-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EmailReceiver emailReceiver,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EmailReceiver> queryWrapper = QueryGenerator.initQueryWrapper(emailReceiver, req.getParameterMap());
		Page<EmailReceiver> page = new Page<EmailReceiver>(pageNo, pageSize);
		IPage<EmailReceiver> pageList = emailReceiverService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param emailReceiver
	 * @return
	 */
	@AutoLog(value = "邮件接收人-添加")
	@ApiOperation(value="邮件接收人-添加", notes="邮件接收人-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EmailReceiver emailReceiver) {
		emailReceiverService.save(emailReceiver);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param emailReceiver
	 * @return
	 */
	@AutoLog(value = "邮件接收人-编辑")
	@ApiOperation(value="邮件接收人-编辑", notes="邮件接收人-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EmailReceiver emailReceiver) {
		emailReceiverService.updateById(emailReceiver);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "邮件接收人-通过id删除")
	@ApiOperation(value="邮件接收人-通过id删除", notes="邮件接收人-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		emailReceiverService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "邮件接收人-批量删除")
	@ApiOperation(value="邮件接收人-批量删除", notes="邮件接收人-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.emailReceiverService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "邮件接收人-通过id查询")
	@ApiOperation(value="邮件接收人-通过id查询", notes="邮件接收人-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EmailReceiver emailReceiver = emailReceiverService.getById(id);
		if(emailReceiver==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(emailReceiver);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param emailReceiver
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EmailReceiver emailReceiver) {
        return super.exportXls(request, emailReceiver, EmailReceiver.class, "邮件接收人");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EmailReceiver.class);
    }

}
