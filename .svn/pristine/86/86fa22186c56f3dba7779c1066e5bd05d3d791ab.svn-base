package kr.or.ddit.typehandler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.DelegatingConnection;
import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.or.ddit.vo.EvaStandardVO;
import net.sf.log4jdbc.ConnectionSpy;
import oracle.jdbc.OracleConnection;

@Alias("evaType")
//@MappedTypes(java.util.ArrayList.class)
//@MappedJdbcTypes(JdbcType.ARRAY)
public class EvaTypeHandler extends BaseTypeHandler<List<EvaStandardVO>> {
	private void setTypeMap(Statement stmt, String jdbcTypeName, Class<?> mappedType) throws SQLException {
		Connection conn = stmt.getConnection();
		Map<String, Class<?>> typeMap = conn.getTypeMap();
		typeMap.put(jdbcTypeName, mappedType);
		conn.setTypeMap(typeMap);
	}
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int index, List<EvaStandardVO> list, JdbcType jdbcType)
			throws SQLException {
		setTypeMap(ps, "EVASTD", EvaStandardVO.class);
		setTypeMap(ps, "LIST_EVASTD", EvaStandardVO[].class);
		if(list==null||list.size()==0) {
			ps.setArray(index, null);
			return;
		}
		EvaStandardVO[] evalist = new EvaStandardVO[list.size()];
		for(int i = 0 ; i<list.size();i++) {
			evalist[i] = list.get(i);
		}
		DelegatingConnection<Connection> pooledConn = (DelegatingConnection<Connection>) ps.getConnection();
		ConnectionSpy connectionSpy = (ConnectionSpy) pooledConn.getInnermostDelegateInternal();
		OracleConnection conn = (OracleConnection) connectionSpy.getRealConnection();
		
		ps.setArray(index, conn.createARRAY("LIST_EVASTD", evalist));
	}

	@Override
	public List<EvaStandardVO> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		
		return getArrayListFromSqlArray(rs.getArray(columnName));
	}

	private List<EvaStandardVO> getArrayListFromSqlArray(Array array) throws SQLException {
		if(array == null) {
			return null;
		}
		EvaStandardVO[] eva = (EvaStandardVO[])array.getArray();
		if(eva == null) {
			return null;
		}
		List<EvaStandardVO> list  = new ArrayList<>();
		for(int i=0;i<eva.length ;i++) {
			list.add(eva[i]);
		}
		return list;
	}

	@Override
	public List<EvaStandardVO> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		
		return getArrayListFromSqlArray(rs.getArray(columnIndex));
	}

	@Override
	public List<EvaStandardVO> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return getArrayListFromSqlArray(cs.getArray(columnIndex));
	}

	

}
