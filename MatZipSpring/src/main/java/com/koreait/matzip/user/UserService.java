package com.koreait.matzip.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.rest.RestMapper;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestRecMenuVO;
import com.koreait.matzip.user.model.UserDMI;
import com.koreait.matzip.user.model.UserPARAM;
import com.koreait.matzip.user.model.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private RestMapper restMapper;
	
	//1:로그인성공 2:아이디없음 3:비번틀림
	public int login(UserPARAM param) {
		if(param.getUser_id().equals("")) { return Const.NO_ID; }
		
		UserDMI dbUser = mapper.selUser(param);
		
		if(dbUser == null) { return Const.NO_ID; }
		
		String cryptPw = SecurityUtils.getEncrypt(param.getUser_pw(), dbUser.getSalt());
		
		if(!cryptPw.equals(dbUser.getUser_pw())) {
			return Const.NO_PW;
		}
		param.setI_user(dbUser.getI_user());	//세션 로그인 i_user
		param.setUser_pw(null);	//비밀번호null로 변경
		param.setNm(dbUser.getNm());
		param.setProfile_img(dbUser.getProfile_img());
		return Const.SUCCESS;
		
	}

	public int join(UserVO param) {
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String cryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setSalt(salt);
		param.setUser_pw(cryptPw);
		
		return mapper.insUser(param);
	}

	public int ajaxToggleFavorite(UserPARAM param) {//i_user, i_rest, proc_type
		switch(param.getProc_type()) {
		case "ins":
			return mapper.insFavorite(param);
		case "del":
			return mapper.delFavorite(param);
		}
		return 0;
	}
	
	public List<UserDMI> selFavoriteList(UserPARAM param) {	
		List<UserDMI> list = mapper.selFavoriteList(param);

		for(UserDMI vo : list) {
			RestPARAM param2 = new RestPARAM();
			param2.setI_rest(vo.getI_rest());

			List<RestRecMenuVO> eachRecMenuList = restMapper.selRestRecMenus(param2);
			vo.setMenuList(eachRecMenuList);
		}

		return list;
	}
	
}
