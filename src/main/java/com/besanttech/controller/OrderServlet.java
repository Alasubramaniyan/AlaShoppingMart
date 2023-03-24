package com.besanttech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.besanttech.dao.OrderDao;
import com.besanttech.entities.Order;
import com.besanttech.entities.Product;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

	
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			String action = req.getParameter("action");
			if (action == null) {
				OrderDao orderDao = new OrderDao();
				HttpSession ses = req.getSession(false);
				String username = (String) ses.getAttribute("username");
				List<Order> orderList = orderDao.getUserOrders(username);
				ses.setAttribute("orderList", orderList);
				RequestDispatcher rd = req.getRequestDispatcher("views/order.jsp");
				rd.forward(req, res);
			}

			else if (action.equals("buy")) {
				OrderDao order = new OrderDao();
				HttpSession session = req.getSession(false);
				List<Product> products = (List<Product>) session.getAttribute("cartProducts");
				order.insertOrder(products, (String) session.getAttribute("username"));
				session.setAttribute("cartProducts", new ArrayList<Product>());
				session.setAttribute("cartPrice", 0);
				RequestDispatcher rd = req.getRequestDispatcher("views/cart.jsp");
				rd.forward(req, res);
			}

		}

	

		// TODO Auto-generated constructor stub
	}


