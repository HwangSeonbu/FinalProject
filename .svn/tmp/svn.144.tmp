package kr.or.ddit.member.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO dao;
	
	@Resource(name="authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Inject
	private PasswordEncoder passwordEncoder;

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		try {
			retrieveMember(member.getUserNo()+"");
			result = ServiceResult.PKDUPLICATED;
		}catch (Exception e) {
			String encoded = passwordEncoder.encode(member.getUserPass());
			member.setUserPass(encoded);
			int rowcnt = dao.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<MemberVO> dataList = dao.selectMemberList(paging);
		paging.setDataList(dataList);
		return dataList;
	}

	@Override
	public MemberVO retrieveMember(String memId) throws PKNotFoundException {
		MemberVO member = dao.selectMember(memId);
		if(member==null)
			throw new PKNotFoundException(memId+"에 해당하는 회원이 없음.");
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getUserNo(), member.getUserPass()));
		int rowcnt = dao.updateMember(member);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getUserNo(), member.getUserPass()));
		int rowcnt = dao.deleteMember(member.getUserNo()+"");
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		
	}

}











