package com.koreait.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestFile;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestRecMenuVO;

@Mapper
public interface RestMapper {
	
	int insRest(RestPARAM param);
	int insRestRecmenu(RestRecMenuVO param);
	int insRestMenu(RestRecMenuVO param);

	int selRestChkUser(int param);
	List<RestDMI> selRestList(RestPARAM param);
	RestDMI selRest(RestPARAM param);
	List<RestRecMenuVO> selRestRecMenu(RestPARAM param);
	List<RestRecMenuVO> selRestMenus(RestPARAM param);
	
	int delRestRecMenu(RestPARAM param);
	int delRestMenu(RestPARAM param);
	int delRest(RestPARAM param);
	
}
