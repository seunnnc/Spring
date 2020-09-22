package com.koreait.matzip.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.koreait.matzip.model.CodeVO;
import com.koreait.matzip.model.CommonMapper;
import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestPARAM;

@Service
public class RestService {
	
	@Autowired
	private RestMapper mapper;
	
	@Autowired
	private CommonMapper cMapper;
	
	String selRestList(RestPARAM param) {
		List<RestDMI> list = mapper.selRestList(param);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	int insRest(RestPARAM param) {

		return mapper.insRest(param);
	}
	
	List<CodeVO> selCategoryList() {
		CodeVO p = new CodeVO();
		p.setI_m(1);
		
		return cMapper.selCategoryList(p);
	}
	
}
