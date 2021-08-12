package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;

public class MemberDaoImpl implements IMemberDao{
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
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
	public int insertMember(Connection conn, MemberVO mv) throws SQLException {
		
		String sql = "INSERT INTO MYMEMBER(MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) "
					+ "VALUES(?, ?, ?, ?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemId());
		pstmt.setString(2, mv.getMemName());
		pstmt.setString(3, mv.getMemTel());
		pstmt.setString(4, mv.getMemAddr());
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil.disConnect(null, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public boolean getMember(Connection conn, String memId) throws SQLException {
	
		boolean check = false;

			String sql = "SELECT COUNT(*) AS CNT FROM MYMEMBER "
						+"WHERE MEM_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			
			if(rs.next()) {
				count = rs.getInt("CNT");
			}
			
			if(count > 0) {
				check = true;
			}

			JDBCUtil.disConnect(null, stmt, pstmt, rs);

		return check;
	}


	@Override
	public List<MemberVO> getAllMemberList(Connection conn) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();

			String sql = "SELECT * FROM MYMEMBER";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				
				mv.setMemId(rs.getString("mem_id"));
				mv.setMemName(rs.getString("mem_name"));
				mv.setMemTel(rs.getString("mem_tel"));
				mv.setMemAddr(rs.getString("mem_addr"));
				
				memList.add(mv);
			}

			JDBCUtil.disConnect(conn, stmt, pstmt, rs);

		return memList;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) throws SQLException {
		
		String sql = "UPDATE MYMEMBER "
				+ "SET MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? "
				+ "WHERE MEM_ID = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemName());
		pstmt.setString(2, mv.getMemTel());
		pstmt.setString(3, mv.getMemAddr());
		pstmt.setString(4, mv.getMemId());
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		
		String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(Connection conn, MemberVO mv) throws SQLException {
		List<MemberVO> memList = new ArrayList<>();
		
		String sql = "SELECT * FROM MYMEMBER WHERE 1=1 ";
		
		if(mv.getMemId() != null && !mv.getMemId().equals("")) {
			sql += "AND MEM_ID = ?";
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			sql += "AND MEM_NAME = ?";
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
			sql += "AND MEM_TEL = ?";
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
			sql += "AND MEM_ADDR LIKE '%'  || ? || '%' ";
		}
		
		pstmt = conn.prepareStatement(sql);
		
		int index = 1;
		
		if(mv.getMemId() != null && !mv.getMemId().equals("")) {
			pstmt.setString(index++, mv.getMemId());
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			pstmt.setString(index++, mv.getMemName());
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
			pstmt.setString(index++, mv.getMemTel());
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
			pstmt.setString(index++, mv.getMemAddr());
		}
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			MemberVO mv2 = new MemberVO();
			
			mv2.setMemId(rs.getString("mem_id"));
			mv2.setMemName(rs.getString("mem_name"));
			mv2.setMemTel(rs.getString("mem_tel"));
			mv2.setMemAddr(rs.getString("mem_addr"));
			
			memList.add(mv2);
		}
		
		return memList;
	}

}