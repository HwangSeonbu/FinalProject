package kr.or.ddit.security;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
/**
 * 
 * @author 민진홍
 * @since 2022. 5. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 5. 11.      민진홍      주소와 권한 가져오기
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public class RequestMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {
	private DataSource dataSource;

	@Required
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap;

	@PostConstruct
	public void init() {
		requestMap = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT DISTINCT '/'||REGEXP_SUBSTR(A.MENU_URL, '[^/]+', 1, 1)||'/*' AS pattern, C.ROLE_NAME authority ");
		sql.append(" FROM MENU A LEFT OUTER JOIN MENU_ROLES B ON A.MENU_TOP=B.MENU_ID ");
		sql.append(" LEFT OUTER JOIN ROLES C ON B.ROLE_ID = C.ROLE_ID ");
		sql.append(" WHERE A.MENU_URL IS NOT NULL AND A.MENU_YN = 'Y'  ");
		List<Map<String, Object>> resourcesList = template.queryForList(sql.toString(), new MapSqlParameterSource());
		if (resourcesList.size() > 0) {
			for (Map<String, Object> mappingInfo : resourcesList) {
				String pattern = (String) mappingInfo.get("pattern");
				String authority = (String) mappingInfo.get("authority");
				RequestMatcher matcher = new AntPathRequestMatcher(pattern);
				List<ConfigAttribute> attributes = null;
				attributes = requestMap.get(matcher);
				if (attributes == null) {
					attributes = new LinkedList<ConfigAttribute>();
				}
				attributes.add(new SecurityConfig(authority));
				requestMap.put(matcher, attributes);
			}
		}
	}

	@Override
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
		if (requestMap == null) {
			init();
		}
		return requestMap;
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}