package com.ssafy.api.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.db.entity.BaseballPlayers;
import com.ssafy.db.entity.CheeringComments;

/**

  * @FileName : CheeringService.java
  * @Project : mlb-analysis-project
  * @Date : 2022. 9. 26 
  * @작성자 : 김동우
  * @변경이력 :
  * @프로그램 설명 :
  */
public interface CheeringService {
	public Optional<CheeringComments> getCheeringCommentsByUid(Integer uid);

	/**
	  * @Method Name : saveCheeringComments
	  * @작성일 : 2022. 9. 26
	  * @작성자 : 김동우
	  * @변경이력 : 
	
	  * @Method 설명 :
	  */
	
	public void saveCheeringComments();

}
