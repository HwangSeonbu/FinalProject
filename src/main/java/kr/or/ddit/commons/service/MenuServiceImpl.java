package kr.or.ddit.commons.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.MenuDAO;
import kr.or.ddit.vo.MenuParentVO;
import kr.or.ddit.vo.MenuSideVO;
import kr.or.ddit.vo.MenuTopVO;

@Service
public class MenuServiceImpl implements MenuService{

	@Inject
	MenuDAO dao;
	
	@Override
	public List<MenuTopVO> retrieveTopMenuList() {
		List<MenuTopVO> topMenuList = dao.selectTopMenuList();
		for(MenuTopVO eachVo : topMenuList) {
			eachVo.setTopmenuId(eachVo.getTopmenuId().trim());
		}
		return topMenuList;
	}

	@Override
	public List<MenuSideVO> selectSideMenuList(String menuTop) {
		
		List<MenuSideVO> rsList = new ArrayList<>();
		List<MenuSideVO> list = dao.selectSideMenuList(menuTop);
		for(MenuSideVO vo : list) {
			if(vo.getMenuParent() == null) {
				if(vo.getMenuUrl() == null) {
					rsList.add(vo);
				}else {
					rsList.add(vo);
				}
				
			}else {
				String parent = vo.getMenuParent();
				for(MenuSideVO parents : rsList) {
					if(parents.getMenuId().equals(parent)) {
						List<MenuSideVO> childList = parents.getChildList();
						if(childList == null) {
							childList = new ArrayList<>();
						}
						childList.add(vo);
						parents.setChildList(childList);
					}
				}
			}
		}
		
		return rsList;

		
	}

}
