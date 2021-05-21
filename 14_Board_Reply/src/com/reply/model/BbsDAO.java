package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BbsDAO {
	Connection con = null; // DB 연결하는 객체
	PreparedStatement pstmt = null; // DB에 sql문을 전송하는 객체
	ResultSet rs = null; // sql문을 실행 후 결과값을 가지고 있는 객체
	String sql = null; // 쿼리문을 저장할 객체

	private static BbsDAO instance = null;

	private BbsDAO() {

	}

	public static BbsDAO getInstance() {
		if (instance == null) {
			instance = new BbsDAO();
		}
		return instance;
	}

	public void openConn() {
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();

			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");

			// 3단계 : DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// DB에 연결된 객체를 종료하는 메서드
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// jsp_bbs 테이블의 전체 게시물 수를 조회하는 메서드
	public int getListCount() {
		int count = 0;

		try {
			openConn();

			sql = "select count(*) from jsp_bbs";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return count;
	}

	// jsp_bbs 테이블의 페이지에 보여질 게시물의 수 만큼 게시물을 조회하는 메서드
	public List<BbsDTO> getBbsList(int page, int rowsize) {
		List<BbsDTO> list = new ArrayList<BbsDTO>();

		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = (page * rowsize);

		try {
			openConn();

			sql = "select * from(select row_number() over(order by board_group desc, board_step)"
					+ "rnum, b.* from jsp_bbs b) where rnum between ? and ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BbsDTO dto = new BbsDTO();

				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}

	public int insertBbs(BbsDTO dto) {
		int result = 0, count = 0;

		try {
			openConn();

			sql = "select count(*) from jsp_bbs";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}

			sql = "insert into jsp_bbs values(?, ?, ?, ?, ?, default, sysdate, ?, 0, 0)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			pstmt.setInt(6, count);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public void bbsHit(int no) {
		try {
			openConn();

			sql = "update jsp_bbs set board_hit = board_hit + 1 where board_no =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}

	public BbsDTO getBbsCont(int no) {
		BbsDTO dto = new BbsDTO();

		try {
			openConn();

			sql = "select * from jsp_bbs where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}

	public int updateBbs(BbsDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "update jsp_bbs set board_title = ?, board_cont = ? where board_no = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_cont());
			pstmt.setInt(3, dto.getBoard_no());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public int deleteBbs(int no, int group) {
		int result = 0, count = 0;

		try {
			openConn();

			sql = "select count(*) from jsp_bbs where board_group = ? and board_step > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				result = -1;
			} else {
				sql = "delete from jsp_bbs where board_no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);

				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public void updateNo(int no) {
		try {
			openConn();

			sql = "update jsp_bbs set board_no = board_no - 1, board_group = board_group - 1 where board_no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}

	// jsp_bbs 테이블 게시판 답변 글의 step을 하나 증가시키는 메서드
	public void replyUpdate(int group, int step) {
		try {
			openConn();

			sql = "update jsp_bbs set board_step = board_step + 1 where board_group = ? and board_step > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group);
			pstmt.setInt(2, step);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}

	// jsp_bbs 테이블의 게시글 원글에 답변글을 추가하는 메서드
	public int replyBbs(BbsDTO dto) {
		int result = 0, count = 0;

		try {
			openConn();

			sql = "select count(*) from jsp_bbs";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}

			sql = "insert into jsp_bbs values(?, ?, ?, ?, ?, default, sysdate, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			pstmt.setInt(6, dto.getBoard_group());
			pstmt.setInt(7, dto.getBoard_step() + 1);
			pstmt.setInt(8, dto.getBoard_indent() + 1);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
}
