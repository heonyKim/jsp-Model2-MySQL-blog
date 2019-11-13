package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import com.cos.model.Board;
import com.cos.util.DBClose;

public class BoardDao {
	// �̱������� ������ �ϴµ� �׳� ��

	private Connection conn; // ����
	private PreparedStatement pstmt; // SQL�� �ۼ�
	private ResultSet rs; // Ŀ��
	
	
	public int update(Board board) {
		final String SQL = "UPDATE board SET title=?, content=? WHERE id=?";
		
		conn = DBConn.getConnection();
		
		try {
			
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			int result = pstmt.executeUpdate();
			return result;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt);
		}
		
		return -1;
	}
	
	
	
	public int save(Board board) {

		conn = DBConn.getConnection();

		final String SQL = "INSERT INTO board(userId,title,content,createDate) VALUES(?, ?, ?,now())";

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			int result = pstmt.executeUpdate();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;
	}
	
	
	//�Խù� ����
	public int delete(int id) {
		final String SQL = "DELETE FROM board WHERE id=?";
		conn = DBConn.getConnection(); //������
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;	//���� ������ result = 1
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}
	
	
	// �α⸮��Ʈ 3�� ��������
	public List<Board> findOrderByReadCountDesc() {
		conn = DBConn.getConnection();
		
		final String SQL = "SELECT * FROM board ORDER BY readCount DESC limit 3";
		List<Board> boards = new ArrayList<Board>();
		try {
			
			pstmt = conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {

				Board board = new Board();
				
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				
				boards.add(board);

			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}
	
	
	
	// ����Ʈ ���� SELECT * FROM board ORDER BY id DESC
	public List<Board> findAll(int page) {
		conn = DBConn.getConnection();
		
		final String SQL = "SELECT * FROM board b, user u WHERE b.userId=u.id ORDER BY b.id DESC limit ?,3";
		List<Board> boards = new ArrayList<Board>();
		try {
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, (page-1)*3);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
	
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				boards.add(board);

			}
			
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}
	
	
	public List<Board> findAll(int page, String search) {
		conn = DBConn.getConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append("FROM board b, user u ");
		sb.append("WHERE b.userId = u.id and ");
		sb.append("(b.content LIKE ? or b.title LIKE ?) ");
		sb.append("ORDER BY b.id DESC LIMIT ?,3");
		final String SQL = sb.toString();
		
		List<Board> boards = new ArrayList<Board>();
		try {
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, (page-1)*3);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
	
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				boards.add(board);

			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}
	
	

	// �� ���� SELECT * FROM board WHERE id = ?
	public Board findById(int id) {

		conn = DBConn.getConnection();

		final String SQL = "SELECT * FROM board b, user u WHERE b.userId=u.id AND b.id=?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				Board board = new Board();
				
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));

				return board;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}

	
	
	public int increaseReadCount(int id) {
		conn = DBConn.getConnection();
		
		final String SQL = "UPDATE board SET readCount = readCount + 1 WHERE id=?";
		
		try {
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,id);
			int result = pstmt.executeUpdate();
			return result;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt);
		}

		return -1;
	}
	
	
	public int findMaxPage() {
		
		conn = DBConn.getConnection();

		final String SQL = "SELECT count(*) maxPage FROM board";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				double column = Math.ceil(rs.getInt("maxPage") / 3.0);
				int result = (int)(column);
				System.out.println("maxpage : "+ result);
				return result;
			}
			

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt);
		}
		
		
		return -1;
	}
	
	
}
