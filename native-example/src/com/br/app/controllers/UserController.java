package com.br.app.controllers;

import java.sql.SQLException;

import com.br.app.factory.ConnectionFactory;

public class UserController extends ConnectionFactory{
	protected final ConnectionFactory db = ConnectionFactory.getConnectionFactory();
	public String queryForLogin(String username, String password) {
		String result = "";
		if (username.length() == 0) {
			return "Login obrigat�rio!";	
		}else if(password.length() == 0) {
			return "Senha obrigat�ria!";
		}
		String sql = "select * from user where uuid=? and passphrase=?";
		String[] param = { username, password };
		rs = db.executeQuery(sql, param);
		try {
			if (rs.next()) {
				result = "Bem vindo " + rs.getString(2) + "!";
			}else {
				result = "Usu�rio ou senha inv�lidos!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
