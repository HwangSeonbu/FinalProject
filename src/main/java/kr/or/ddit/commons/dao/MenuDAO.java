package kr.or.ddit.commons.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MenuParentVO;
import kr.or.ddit.vo.MenuSideVO;
import kr.or.ddit.vo.MenuTopVO;

@Mapper
public interface MenuDAO {
	
	public List<MenuTopVO> selectTopMenuList();
	
	public List<MenuSideVO> selectSideMenuList(String menuTop);
}
