package org.jeecg.modules.finance.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.system.service.ISysDictItemService;
import org.jeecg.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.finance.entity.RegisterShareHolder;
import org.jeecg.modules.finance.entity.RegisterCompanyInfo;
import org.jeecg.modules.finance.vo.RegisterCompanyInfoPage;
import org.jeecg.modules.finance.service.IRegisterCompanyInfoService;
import org.jeecg.modules.finance.service.IRegisterShareHolderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: ?????????????????????
 * @Author: jeecg-boot
 * @Date:   2021-08-30
 * @Version: V1.0
 */
@Api(tags="?????????????????????")
@RestController
@RequestMapping("/finance/registerCompanyInfo")
@Slf4j
public class RegisterCompanyInfoController {
	@Autowired
	private IRegisterCompanyInfoService registerCompanyInfoService;
	@Autowired
	private IRegisterShareHolderService registerShareHolderService;

	 @Autowired
	 private ISysDictItemService sysDictItemService;

	 @Autowired
	 private ISysDictService sysDictService;

	/**
	 * ??????????????????
	 *
	 * @param registerCompanyInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "?????????????????????-??????????????????")
	@ApiOperation(value="?????????????????????-??????????????????", notes="?????????????????????-??????????????????")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RegisterCompanyInfo registerCompanyInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RegisterCompanyInfo> queryWrapper = QueryGenerator.initQueryWrapper(registerCompanyInfo, req.getParameterMap());
		Page<RegisterCompanyInfo> page = new Page<RegisterCompanyInfo>(pageNo, pageSize);
		IPage<RegisterCompanyInfo> pageList = registerCompanyInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   ??????
	 *
	 * @param registerCompanyInfoPage
	 * @return
	 */
	@AutoLog(value = "?????????????????????-??????")
	@ApiOperation(value="?????????????????????-??????", notes="?????????????????????-??????")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RegisterCompanyInfoPage registerCompanyInfoPage) {

		RegisterCompanyInfo registerCompanyInfo = new RegisterCompanyInfo();
		BeanUtils.copyProperties(registerCompanyInfoPage, registerCompanyInfo);
	/*	//??????????????????
		Date expireDate = calcullateExpireDate(registerCompanyInfo);
		if (expireDate != null){
			registerCompanyInfoPage.setExpireDate(expireDate);
			registerCompanyInfo.setExpireDate(expireDate);
		}*/
		//?????????????????????1
		registerCompanyInfo.setStatus(1);
		registerCompanyInfoService.saveMain(registerCompanyInfo, registerCompanyInfoPage.getRegisterShareHolderList());
		return Result.ok("???????????????");
	}
	
	/**
	 *  ??????
	 *
	 * @param registerCompanyInfoPage
	 * @return
	 */
	@AutoLog(value = "?????????????????????-??????")
	@ApiOperation(value="?????????????????????-??????", notes="?????????????????????-??????")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RegisterCompanyInfoPage registerCompanyInfoPage) {
		RegisterCompanyInfo registerCompanyInfo = new RegisterCompanyInfo();
		BeanUtils.copyProperties(registerCompanyInfoPage, registerCompanyInfo);
		/*//??????????????????
		Date expireDate = calcullateExpireDate(registerCompanyInfo);
		if (expireDate != null){
			registerCompanyInfoPage.setExpireDate(expireDate);
			registerCompanyInfo.setExpireDate(expireDate);
		}*/
		RegisterCompanyInfo registerCompanyInfoEntity = registerCompanyInfoService.getById(registerCompanyInfo.getId());
		if(registerCompanyInfoEntity==null) {
			return Result.error("?????????????????????");
		}
		registerCompanyInfoService.updateMain(registerCompanyInfo, registerCompanyInfoPage.getRegisterShareHolderList());
		return Result.ok("????????????!");
	}
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "?????????????????????-??????id??????")
	@ApiOperation(value="?????????????????????-??????id??????", notes="?????????????????????-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		registerCompanyInfoService.delMain(id);
		return Result.ok("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "?????????????????????-????????????")
	@ApiOperation(value="?????????????????????-????????????", notes="?????????????????????-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.registerCompanyInfoService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("?????????????????????");
	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "?????????????????????-??????id??????")
	@ApiOperation(value="?????????????????????-??????id??????", notes="?????????????????????-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RegisterCompanyInfo registerCompanyInfo = registerCompanyInfoService.getById(id);
		if(registerCompanyInfo==null) {
			return Result.error("?????????????????????");
		}
		return Result.ok(registerCompanyInfo);

	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "????????????????????????ID??????")
	@ApiOperation(value="??????????????????ID??????", notes="????????????-?????????ID??????")
	@GetMapping(value = "/queryRegisterShareHolderByMainId")
	public Result<?> queryRegisterShareHolderListByMainId(@RequestParam(name="id",required=true) String id) {
		List<RegisterShareHolder> registerShareHolderList = registerShareHolderService.selectByMainId(id);
		return Result.ok(registerShareHolderList);
	}

    /**
    * ??????excel
    *
    * @param request
    * @param registerCompanyInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RegisterCompanyInfo registerCompanyInfo) {
      // Step.1 ??????????????????????????????
      QueryWrapper<RegisterCompanyInfo> queryWrapper = QueryGenerator.initQueryWrapper(registerCompanyInfo, request.getParameterMap());
      queryWrapper.orderByAsc("regal_person_name");
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      //Step.2 ??????????????????
      List<RegisterCompanyInfo> queryList = registerCompanyInfoService.list(queryWrapper);
	  List<DictModel> registeTypeList = sysDictService.queryDictItemsByCode("register_type");
	  //List<DictModel> chargeTypeList = sysDictService.queryDictItemsByCode("charge_type");
	  Map<String,String> registerTypeMap = new HashMap<>();
	  for (DictModel dictModel:registeTypeList){
		  registerTypeMap.put(dictModel.getValue(),dictModel.getText());
	  }
	  //Map<String,String> chargeTypeMap = new HashMap<>();
	  /*for (DictModel dictModel:chargeTypeList){
	  	chargeTypeMap.put(dictModel.getValue(),dictModel.getText());
	  }*/
	  for (RegisterCompanyInfo info:queryList){
	  	info.setRegisterType(registerTypeMap.get(info.getRegisterType()));
	  //	info.setChargeType(chargeTypeMap.get(info.getChargeType()));
	  }
      // ??????????????????
      String selections = request.getParameter("selections");
      List<RegisterCompanyInfo> registerCompanyInfoList = new ArrayList<RegisterCompanyInfo>();
      if(oConvertUtils.isEmpty(selections)) {
          registerCompanyInfoList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          registerCompanyInfoList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 ??????pageList
      List<RegisterCompanyInfoPage> pageList = new ArrayList<RegisterCompanyInfoPage>();
      for (RegisterCompanyInfo main : registerCompanyInfoList) {
          RegisterCompanyInfoPage vo = new RegisterCompanyInfoPage();
          BeanUtils.copyProperties(main, vo);
          List<RegisterShareHolder> registerShareHolderList = registerShareHolderService.selectByMainId(main.getId());
          vo.setRegisterShareHolderList(registerShareHolderList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi ??????Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "???????????????????????????");
      mv.addObject(NormalExcelConstants.CLASS, RegisterCompanyInfoPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("???????????????????????????", "?????????:"+sysUser.getRealname(), "?????????????????????"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * ??????excel????????????
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// ????????????????????????
          ImportParams params = new ImportParams();
          params.setTitleRows(0);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<RegisterCompanyInfoPage> list = ExcelImportUtil.importExcel(file.getInputStream(), RegisterCompanyInfoPage.class, params);
              for (RegisterCompanyInfoPage page : list) {
                  RegisterCompanyInfo po = new RegisterCompanyInfo();
                  BeanUtils.copyProperties(page, po);
				  fillImport(po);
                  registerCompanyInfoService.saveMain(po, page.getRegisterShareHolderList());
              }
              return Result.ok("?????????????????????????????????:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("??????????????????:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("?????????????????????");
    }

	/*@RequestMapping(value = "/template", method = RequestMethod.POST)
    public ModelAndView downloadTemplate(HttpServletRequest request, HttpServletResponse response){

	}*/

    //??????????????????
   /* public Date calcullateExpireDate(RegisterCompanyInfo registerCompanyInfo){
		Date startDate = registerCompanyInfo.getStartDate();
		if (startDate == null){
			return null;
		}
		Integer num = registerCompanyInfo.getTimeValue();
		if (num == null || num == 0){
			return null;
		}
		//??????
		if (registerCompanyInfo.getChargeType().equals("1")){
			return DateUtils.addYears(startDate,num);
		}else if(registerCompanyInfo.getChargeType().equals("2")){
			//??????
			return DateUtils.addMonths(startDate,3*num);
		}else if(registerCompanyInfo.getChargeType().equals("3")){
			//??????
			return DateUtils.addMonths(startDate,num);
		}
		return null;
	}*/

	//?????????????????????
	public void fillImport(RegisterCompanyInfo info){
    	//??????????????????
		info.setCreateTime(new Date());
		//????????????
		Map<String, String> registerTypeMap = queryDictValue("register_type");
		if (!StringUtils.isEmpty(info.getRegisterType())) {
			info.setRegisterType(registerTypeMap.get(info.getRegisterType()));
		}
	/*	//??????????????????
		Map<String, String> chargeTypeMap = queryDictValue("charge_type");
		if (!StringUtils.isEmpty(info.getChargeType())) {
			info.setChargeType(chargeTypeMap.get(info.getChargeType()));
		}
		//??????????????????
		Date expireDate = calcullateExpireDate(info);
		if (expireDate != null){
			info.setExpireDate(expireDate);
		}*/
	}

	public Map<String,String> queryDictKey(String dictText){
		List<DictModel> dictInfoList = sysDictService.queryDictItemsByCode(dictText);
		Map<String,String> dictInfoeMap = new HashMap<>();
		for (DictModel dictModel:dictInfoList){
			dictInfoeMap.put(dictModel.getValue(),dictModel.getText());
		}
		return dictInfoeMap;
	}

	 public Map<String,String> queryDictValue(String dictText){
		 List<DictModel> dictInfoList = sysDictService.queryDictItemsByCode(dictText);
		 Map<String,String> dictInfoeMap = new HashMap<>();
		 for (DictModel dictModel:dictInfoList){
			 dictInfoeMap.put(dictModel.getText(),dictModel.getValue());
		 }
		 return dictInfoeMap;
	 }
}
