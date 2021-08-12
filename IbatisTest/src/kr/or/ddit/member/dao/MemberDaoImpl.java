package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;

public class MemberDaoImpl implements IMemberDao{
	
	private static IMemberDao memDao;
	
	//싱글톤 생성자를 private으로 만듦
	private MemberDaoImpl() {
		
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		
		return memDao;
	}

	@Override
	public int insertMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("member.insertMember", mv);
		
		
		if(obj == null) { //iBatis insert 성공하면 null반환 
			cnt = 1; //0보다 크면 정상수행이기 때문
		}
		
		return cnt;
	}

	@Override
	public boolean getMember(SqlMapClient smc, String memId) throws SQLException {
	
		boolean check = false;
		
		int count = (int) smc.queryForObject("member.getMember", memId);
			
		if(count > 0) {
			check = true;
		}

		return check;
	}


	@Override
	public List<MemberVO> getAllMemberList(SqlMapClient smc) throws SQLException {
		
		List<MemberVO> memList = smc.queryForList("member.getMemberAll");
		
			
		return memList;
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = smc.update("member.updateMember", mv);
		
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		
		int cnt = smc.delete("member.deleteMember" , memId);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		List<MemberVO> memList = smc.queryForList("member.getSearchMember", mv);
		
		return memList;
	}

}