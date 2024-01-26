package com.ipad.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.service.Service;

public class MemberController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String com)
			throws ServletException, IOException {
		String viewPage = null;
		Service service = null;

		if (com.equals("")) {

		} else if (com.equals("")) {

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

}
