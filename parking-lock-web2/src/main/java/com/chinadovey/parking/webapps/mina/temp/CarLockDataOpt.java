package com.chinadovey.parking.webapps.mina.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.chinadovey.parking.webapps.mina.pojo.Equipment0710;
import com.chinadovey.parking.webapps.utils.ConfUtils;

public class CarLockDataOpt {

	private Connection conn;

	public void conn() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = ConfUtils.getJdbcAddress();
		String username = ConfUtils.getJdbcUsername();
		String pwd = ConfUtils.getJdbcPwd();
		//conn = DriverManager.getConnection("jdbc:mysql://huaching.mysql.rds.aliyuncs.com:3306/pp_parking_data?characterEncoding=UTF-8","pp_parking", "pp52725299");
		conn = DriverManager.getConnection(url,username, pwd);
		conn.setAutoCommit(false);
	}

	public void insert(Equipment0710 data) throws SQLException {
		try {
			String sql = "insert into parking_car_lock_data values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now())";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getRtuId() + data.getEquiId());
			pstmt.setInt(2, data.getOpenState());
			pstmt.setInt(3, data.getCarState());
			pstmt.setFloat(4, data.getVoltage());
			pstmt.setInt(5, data.getEquiState());
			pstmt.setInt(6, data.getSource());
			pstmt.setInt(7, data.getCycle());
			pstmt.setInt(8, data.getHx());
			pstmt.setInt(9, data.getLx());
			pstmt.setInt(10, data.getHy());
			pstmt.setInt(11, data.getLy());
			pstmt.setInt(12, data.getHz());
			pstmt.setInt(13, data.getLz());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	public void commit() {
		try {
			if (conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback() {
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
