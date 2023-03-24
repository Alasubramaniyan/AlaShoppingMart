package com.besanttech.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besanttech.dao.ProductDao;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

	 
		public void doGet(HttpServletRequest req,HttpServletResponse res)  
				throws ServletException,IOException  
				{ 
			 
			 ProductDao ps=new ProductDao();
			 req.setAttribute("products", ps.getAllProducts());
			 RequestDispatcher rs=req.getRequestDispatcher("views/products.jsp");
			 rs.forward(req, res);
		// TODO Auto-generated constructor stub
	}

}
