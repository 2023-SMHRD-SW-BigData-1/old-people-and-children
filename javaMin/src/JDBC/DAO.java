package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public void getConn() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String db_id = "project";
			String db_pw = "12345";

			conn = DriverManager.getConnection(url, db_id, db_pw);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getClose() {

		try {
			if (pst != null)
				pst.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int join(DTO mdto) {
		int row = 0;

		try {

			getConn();

			String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?)";
			pst = conn.prepareStatement(sql);

			pst.setString(1, mdto.getId());
			pst.setString(2, mdto.getPw());
			pst.setString(3, mdto.getName());
			pst.setInt(4, mdto.getScore());

			row = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("아이디가 중복됩니다.");
		} finally {
			getClose();
		}
		return row;
	}

	public DTO login(String id, String pw) {
		getConn();
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PW = ?";
		DTO mdto = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pw);

			rs = pst.executeQuery();
			if (rs.next()) {
				String s_id = rs.getString(1);
				String s_pw = rs.getString(2);
				String s_name = rs.getString(3);
				int s_score = rs.getInt(4);
				mdto = new DTO(s_id, s_pw, s_name, s_score);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return mdto;
	}

}