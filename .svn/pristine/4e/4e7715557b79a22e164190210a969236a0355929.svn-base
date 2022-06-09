package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**DEPARTMENT테이블의 객체를 담을 VO
 * @author 황선부
 * @since 2022. 4. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 28.      황선부       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="deptId")
public class DepartmentVO implements Serializable {
	private String deptId;
	private String deptName;
	private String colName;
	private Integer deptFl;
	private String colGwan;
	private Integer gwanSfl;
	private Integer gwanEfl;
	
	private GwanVO gwanVo;
	
	//pro객체 담을 리스트
	private Set<ProfessorVO> proList;
}
