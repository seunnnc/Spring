package com.koreait.matzip.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestPARAM;

@Controller
@RequestMapping("/rest")	//1차 주소값
public class RestController {
	
	@Autowired
	private RestService service;
	
	@RequestMapping("/map")	//2차 주소값
	public String restMap(Model model) {
		model.addAttribute(Const.TITLE, "지도보기");
		model.addAttribute(Const.VIEW, "rest/restMap");
		return ViewRef.TEMP_MENUTEMP;
	}
	
	@RequestMapping(value="/reg", method=RequestMethod.GET)
	public String restReg(Model model) {
		model.addAttribute("categoryList", service.selCategoryList());	//카테고리
		
		model.addAttribute(Const.TITLE, "맛집등록");
		model.addAttribute(Const.VIEW, "rest/restReg");
		return ViewRef.TEMP_MENUTEMP;
	}
	
	@RequestMapping(value="/reg", method=RequestMethod.POST)
	public String restReg(RestPARAM param, HttpSession hs) {
		//int i_user = SecurityUtils.getLoginUserPK(request);
		
		param.setI_user(SecurityUtils.getLoginUserPK(hs));
		int result = service.insRest(param);
		
		return "redirect:/rest/map";
	}
	
	@RequestMapping(value="/detail")
	public String detail(Model model, RestPARAM param) {
		RestDMI data = service.selRest(param);
		
		model.addAttribute("data", data);
		
		model.addAttribute(Const.TITLE, data.getNm());
		model.addAttribute(Const.VIEW, "rest/restDetail");
		return ViewRef.TEMP_MENUTEMP;
	}

	@RequestMapping(value="/ajaxGetList", produces={"application/json; charset=utf-8"})
	@ResponseBody public List<RestDMI> ajaxGetList(RestPARAM param) {
		System.out.println("sw_lat : " + param.getSw_lat());
		System.out.println("sw_lng : " + param.getSw_lng());
		System.out.println("ne_lat : " + param.getNe_lat());
		System.out.println("ne_lng : " + param.getNe_lng());
		
		return service.selRestList(param);
	}
	
	@RequestMapping(value="/del")
	public String del(RestPARAM param, HttpSession hs) {
		
		int loginI_user = SecurityUtils.getLoginUserPK(hs);
		param.setI_user(loginI_user);
		
		int result = 1;
		try {
			service.delRestTran(param);
		} catch (Exception e) {
			result =0;
		}
		System.out.println("result : " + result);
		return "redirect:/";
	}
}
