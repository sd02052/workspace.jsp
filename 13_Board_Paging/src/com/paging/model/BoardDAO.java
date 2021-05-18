package com.paging.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	Connection con = null; // DB 연결하는 객체
	PreparedStatement pstmt = null; // DB에 sql문을 전송하는 객체
	ResultSet rs = null; // sql문을 실행 후 결과값을 가지고 있는 객체

	String sql = null; // 쿼리문을 저장할 객체

	// private -> 싱글톤 방식으로 ProductDAO 객체를 만들자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로 기본 생정자의 접근제어자를
	// private 으로 선언을 해야 함.
	// 2단계 : 정적 멤버로 선언을 해야 함 - static 으로 선언을 한다는 의미.

	private static BoardDAO instance = null;

	// 3단계 : 외부에서 객체 생성을 하지 못하게 접근을 제어 - private 기본 생성자를 만듬.
	private BoardDAO() {

	}

	// 4단계 : 기본 생성자 대신에 싱글턴 객체를 return을 해 주는 getInstance()
	// 메서드를 만들어서 여기에 접근하게 하는 방법
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
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

	public int getListCount() {
		int count = 0;

		try {
			openConn();

			sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	// board 테이블에서 게시물을 가져오는 메서드
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);

		// 해당 페이지에서 마지막 번호
		int endNo = (page * rowsize);

		try {
			openConn();

			sql = "select * from " + "(select row_number() over(order by board_no desc) rnum, b.* from board b)"
					+ "where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));

				list.add(dto);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int insertBoard(BoardDTO dto) {
		int result = 0, count = 0;

		try {
			openConn();

			sql = "select max(board_no) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}

			sql = "insert into board values(?,?,?,?,?,default,sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());

			result = pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void boardHit(int no) {
		try {
			openConn();

			sql = "update board set board_hit = board_hit + 1 where board_no =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BoardDTO getBoardCont(int no) {
		BoardDTO dto = new BoardDTO();

		try {
			openConn();

			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	public int updateBoard(BoardDTO dto) {
		int result = 0;

		try {
			openConn();

			sql = "update board set board_title = ?, board_cont = ? where board_no = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_cont());
			pstmt.setInt(3, dto.getBoard_no());

			result = pstmt.executeUpdate();

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteBoard(int no, String pwd) {
		int result = 0;

		try {
			openConn();

			sql = "select * from board where board_no =?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (pwd.equals(rs.getString("board_pwd"))) {
					sql = "delete from board where board_no =?";
					pstmt = con.prepareStatement(sql);

					pstmt.setInt(1, no);

					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// board 테이블에서 중간에 삭제된 게시글이 있는 경우 글번호를 다시 적용시키는 메서드
	public void updateNo(int no) {

		try {
			openConn();

			sql = "update board set board_no = board_no - 1 where board_no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int searchListCount(String field, String name) {
		int count = 0;
		openConn();

		if (field.equals("title")) {
			try {
				sql = "select count(*) from board where board_title like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, "%" + name + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (field.equals("content")) {
			try {
				sql = "select count(*) from board where board_cont like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, "%" + name + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (field.equals("title_content")) {
			try {
				sql = "select count(*) from board where board_title like ? or board_cont like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, "%" + name + "%");
				pstmt.setString(2, "%" + name + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sql = "select count(*) from board where board_writer like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, "%" + name + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	public List<BoardDTO> searchBoardList(int page, int rowsize, String field, String name) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);

		// 해당 페이지에서 마지막 번호
		int endNo = (page * rowsize);

		openConn();

		if (field.equals("title")) {
			try {
				sql = "select * from "
						+ "(select row_number() over(order by board_no desc) rnum, b.* from board b where board_title like ?) "
						+ "where rnum between ? and ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();

					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));

					list.add(dto);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (field.equals("content")) {
			try {
				sql = "select * from "
						+ "(select row_number() over(order by board_no desc) rnum, b.* from board b where board_cont like ?) "
						+ "where rnum between ? and ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();

					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));

					list.add(dto);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (field.equals("title_content")) {
			try {
				sql = "select * from "
						+ "(select row_number() over(order by board_no desc) rnum, b.* from board b where board_title like ? or board_cont like ?) "
						+ "where rnum between ? and ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setString(2, "%" + name + "%");
				pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();

					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));

					list.add(dto);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sql = "select * from "
						+ "(select row_number() over(order by board_no desc) rnum, b.* from board b where board_writer like ?) "
						+ "where rnum between ? and ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();

					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));

					list.add(dto);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
