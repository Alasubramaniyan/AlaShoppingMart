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

import com.besanttech.dao.ProductDao;
import com.besanttech.entities.Product;

@WebServlet("/cart")
public class CartServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res)  
			throws ServletException,IOException  
			{
		String action=req.getParameter("action");
		if(action==null) {
			req.getRequestDispatcher("views/cart.jsp").forward(req,res);
		}
		else if(action.equals("add")) {
			HttpSession ses=req.getSession(false);
			List<Product> cartProducts=(List<Product>) ses.getAttribute("cartProducts");
			if(cartProducts==null) {
				cartProducts=new ArrayList<>();
			}
			ProductDao productDao=new ProductDao();
			Product product =productDao.getProductById(Integer.parseInt(req.getParameter("product_id")));
			if(product!=null) {
			   cartProducts.add(product);
			   ses.setAttribute("cartProducts",cartProducts);
			   updateCartPrice (cartProducts,ses);
			}
			RequestDispatcher rd=req.getRequestDispatcher("/products");
			rd.forward(req, res);
		}
		else if(action.equals("remove")) {
			//int id=Integer.parseInt(req.getParameter("id"));
			HttpSession ses=req.getSession(false);
			List<Product> cartProducts=(List<Product>)ses.getAttribute("cartProducts");
			for(Product product: cartProducts){
				if(product.getId()==Integer.parseInt(req.getParameter("id"))) {
					cartProducts.remove(product);
					ses.setAttribute("cartProducts", cartProducts);
					updateCartPrice(cartProducts,ses);
					break;
				}
			}
			RequestDispatcher rd=req.getRequestDispatcher("views/cart.jsp");
			rd.forward(req, res);
		}
		// TODO Auto-generated constructor stub
	}

	private void updateCartPrice(List<Product> cartProducts, HttpSession ses) {

		float price =0;
		for(Product product: cartProducts) {
			price = (float) (price + product.getPrice());
		}
	
		ses.setAttribute("cartPrice", price);
	}
		// TODO Auto-generated method stub
		
	}

	


