package com.koreait.matzip.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.user.model.UserDMI;
import com.koreait.matzip.user.model.UserDTO;
import com.koreait.matzip.user.model.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	public int login(UserDTO param) {
		if(param.getUser_id().equals("")) {
			return Const.NO_ID;
		}
		UserDMI dbUser = mapper.selUser(param);
		System.out.println("pw : " + dbUser.getUser_pw());
		return 2;

	}

	public int join(UserVO param) {
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String cryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setSalt(salt);
		param.setUser_pw(cryptPw);
		
		return mapper.insUser(param);
	}
}
