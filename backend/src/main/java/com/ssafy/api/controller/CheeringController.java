/**
 * 
 */
package com.ssafy.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.NoticeRegisterPostReq;
import com.ssafy.api.response.BaseRes;
import com.ssafy.api.service.CheeringService;
import com.ssafy.api.service.NoticeService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.CheeringComments;
import com.ssafy.db.entity.Notices;
import com.ssafy.db.entity.Users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**

  * @FileName : CheeringController.java
  * @Project : mlb-analysis-project
  * @Date : 2022. 9. 26 
  * @작성자 : 김동우
  * @변경이력 :
  * @프로그램 설명 :
  */
@Api(value = "응원 API", tags = {"cheering"})
@RestController
@RequestMapping("/api/cheering")
public class CheeringController {

	
	@Autowired
	CheeringService cheeringService;
	
	@GetMapping()
	@ApiOperation(value = "응원 댓글 수 가져오기", notes = "응원 댓글을 가져온다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 404, message = "해당 id 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<BaseRes> getCheeringComments(
			@RequestBody @ApiParam(value="응원 댓글을 가져올 게임", required = true) int gameId) {
		/*
		1. game id를 가져옴
		2. 가져온 id로 livegames 에 있는 away_id, away_name, home_id, home_name을 가져온다.
		3. 가져온 home_id 와 away_id 로 teams의 logo 를 가져옴
		만들 것: team id 를 통해 logo 가져온다 ( 있음)
		*/ 
		Optional<CheeringComments> ojt=  cheeringService.getCheeringCommentsByUid(gameId);
		if(ojt.isPresent()){
			return ResponseEntity.status(200).body(BaseRes.of(200, "Success",ojt.get()));
		}
		else {
			return ResponseEntity.status(404).body(BaseRes.of(404, "해당 id 없음"));
		}
	}
	
	@PutMapping()
	@ApiOperation(value = "응원 댓글 저장하기", notes = "응원 댓글을 가져온다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> setCheeringComments(
			@RequestBody @ApiParam(value="응원 댓글을 저장할 게임", required = true) String gameId){
				return null;
	}
}
