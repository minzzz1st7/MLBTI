package com.ssafy.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.MailConfirmKeys;
import com.ssafy.db.entity.Users;
import com.ssafy.db.repository.EmailRepository;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Users createUsers(UserRegisterPostReq userRegisterInfo) {
		Users user = new Users();
		user.setEmail(userRegisterInfo.getEmail());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setGrade("GENERAL");
		user.setNickname(userRegisterInfo.getNickname());
		return userRepository.save(user);
	}

	/**
	  * @Method Name : getUsersByEmail
	  * @작성일 : 2022. 9. 21
	  * @작성자 : 김동우
	  * @변경이력 : 
	
	  * @Method 설명 : 이메일로 유저 객체를 가져옵니다.
	  * @param Email
	  * @return
	  */
	@Override
	public Users getUsersByEmail(String Email) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		Users user = userRepositorySupport.findUserByEmail(Email).get();
		return user;
	}

	/**
	  * @Method Name : modifyUserPassword
	  * @작성일 : 2022. 9. 21
	  * @작성자 : 김동우
	  * @변경이력 : 
	
	  * @Method 설명 :
	  * @param email
	  */
	@Override
	public void modifyUserInfo(String email,String newPassword, String newNickname) {
		// TODO Auto-generated method stub
		Users user = userRepositorySupport.findUserByEmail(email).get();

		user.setNickname(newNickname);
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	/**
	  * @Method Name : getUsersByNickName
	  * @작성일 : 2022. 9. 21
	  * @작성자 : 김동우
	  * @변경이력 : 
	
	  * @Method 설명 : 닉네임으로 user 객체 가져온다.
	  * @param nickname
	  * @return
	  */
	@Override
	public Optional<Users> getUsersByNickName(String nickname) {
		return userRepository.findByNickname(nickname);
	}
	

}
