package com.besanttech.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet
		 extends HttpServlet {
				public void doGet(HttpServletRequest req, HttpServletResponse res)
						throws ServletException, IOException {
					
					// clear session
					req.getSession(false).invalidate();
					RequestDispatcher rd = req.getRequestDispatcher("/");
					rd.forward(req, res);
				}
		// TODO Auto-generated constructor stub
	}


