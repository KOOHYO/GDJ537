package com.app.home.messenger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.home.user.DepartmentVO;
import com.app.home.user.EmployeeVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessengerService {

	@Autowired
	private MessengerMapper messengerMapper; 
	
	public List<EmployeeVO> getEmpList () throws Exception{
		return messengerMapper.getEmpList();
	}
	
	public List<DepartmentVO> getDepList() throws Exception{
		return messengerMapper.getDepList();
	}
	
	public List<EmployeeVO> getSearchResult (Map<String, String> map) throws Exception{
		return messengerMapper.getSearchResult(map); 
	}
	
	// 채팅방 생성
	public int setAddRoom(RoomVO roomVO)throws Exception{
		
		log.info("방장 아이디 => {} ", roomVO.getHostId());
		
		int result = messengerMapper.setAddRoom(roomVO);
		
		log.info("채팅방번호 => {} ", roomVO.getRoomNum());
		
		if(result > 0) {
				
			for(int ids : roomVO.getId()) {
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setId(ids);
				roomVO.setEmployeeVO(employeeVO);
				
				result = messengerMapper.setAddRoomUser(roomVO);
				
			}
			
		}
		
		return result;
	}
	
	// 채팅방 목록
	public List<RoomVO> getRoomList()throws Exception{
		return messengerMapper.getRoomList();
	}
	
}
