package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.model.Board;
import com.cos.model.Comment;
import com.cos.util.DBClose;

public class CommentDao {
	// �̱������� ������ �ϴµ� �׳� ��.

	private Connection conn; // MySQL�� �����ϱ� ���� �ʿ�
	private PreparedStatement pstmt; // �������� �ۼ� - ���� �ϱ� ���� �ʿ�
	private ResultSet rs; // ����� ������ Ŀ��

	
	   synchronized public Comment findByMaxId(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("select c.id,c.userId,c.boardId,c.content,c.createDate,u.username,u.userProfile ");
		sb.append("from comment c, user u ");
		sb.append("where c.userId=u.id ");
		sb.append("and c.id =(select max(id) from comment) ");
		
		final String SQL = sb.toString();
		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("c.id"));
				comment.setUserId(rs.getInt("userId"));
				comment.setBoardId(rs.getInt("boardId"));
				comment.setContent(rs.getString("c.content"));
				comment.setCreateDate(rs.getTimestamp("c.createDate"));
				comment.getUser().setUsername(rs.getString("u.username"));
				comment.getUser().setUserProfile(rs.getString("u.userProfile"));
				
				return comment;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	
	public int save(Comment comment) {

		conn = DBConn.getConnection();
		final String SQL = "INSERT INTO comment(userId,boardId,content,createDate) VALUES(?, ?, ?,now())";

		try {
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, comment.getUserId());
			pstmt.setInt(2, comment.getBoardId());
			pstmt.setString(3, comment.getContent());
			
			int result = pstmt.executeUpdate();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;
	}
	
	
	public int delete(int id) {

		final String SQL = "DELETE FROM comment WHERE id = ?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, id);

			int result = pstmt.executeUpdate(); // ����� Ʃ���� ������ ����
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;
	}
	
	public List<Comment> findByBoardId(int boardId){
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT c.id, c.userId, c.boardId, c.content, c.createDate,u.username,u.userProfile ");
		sb.append("FROM comment c, user u ");
		sb.append("WHERE c.userId = u.id ");
		sb.append("and boardId = ?");
		
		final String SQL = sb.toString();
		
		conn = DBConn.getConnection();
		
		try {
			List<Comment> comments = new ArrayList<>();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("c.id"));
				comment.setBoardId(rs.getInt("c.boardId"));
				comment.setUserId(rs.getInt("c.userId"));
				comment.setContent(rs.getString("c.content"));
				comment.setCreateDate(rs.getTimestamp("c.createDate"));
				comment.getUser().setUsername(rs.getString("u.username"));
				comment.getUser().setUserProfile(rs.getString("u.userProfile"));
				comments.add(comment); //�÷��ǿ� comment ���
			}
			
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return null;
	}
}