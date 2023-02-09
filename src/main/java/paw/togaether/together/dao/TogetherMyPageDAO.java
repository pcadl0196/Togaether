package paw.togaether.together.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import paw.togaether.common.dao.AbstractDAO;

@Repository("togetherMyPageDAO")
public class TogetherMyPageDAO extends AbstractDAO{
	
	/* 23.02.08 마이페이지 내가참여한 리스트 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myTogetherList(Map<String, Object> map)throws Exception {
		return (List<Map<String, Object>>) selectList("togetherMypage.myTogetherList", map);
	}

}
