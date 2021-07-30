package com.bit.springNote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bit.springNote.dto.NoteDto;

/*DAO: Data Access Object */
public class NoteDao {

	DataSource ds;
	
	public NoteDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Write
	public void write(String writer, String content) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = ds.getConnection();
			sql = "insert into tblNote(id, writer, content)"
					+ "values(tblNoteSeq.nextval,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, content);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//List
	public ArrayList<NoteDto> list(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<NoteDto> dtos = new ArrayList<NoteDto>();
		try {
			con = ds.getConnection();
			sql = "select * from tblNote order by id desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String writer = rs.getString(2);
				String content = rs.getString(3);
				dtos.add(new NoteDto(id, writer, content));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	//Delete
	public void delete(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = ds.getConnection();
			sql = "delete from tblNote where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}









