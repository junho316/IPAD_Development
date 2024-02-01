package com.ipad.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipad.controller.Controller;
import com.ipad.controller.JsonController;
import com.ipad.controller.LocationAnalysisController;
import com.ipad.controller.LocationRecommandController;
import com.ipad.controller.SaleAnalysisController;
import com.ipad.service.connectDB.UpdateDBService;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

	UpdateDBService updateDBService;

	public void init() throws ServletException {
		updateDBService = new UpdateDBService();
		updateDBService.updateDB();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDO(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDO(request, response);
	}

	protected void actionDO(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String viewPage = null;
		Controller controller = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		String pathAfterContext = extractDesiredPart(com);
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		if (com.equals("/main.do")) {
			viewPage = "/jsp/main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else {
			if (pathAfterContext.equals("/locationAnalysis")) {
				controller = new LocationAnalysisController();
				controller.execute(request, response, com);
			} else if (pathAfterContext.equals("/member")) {

			} else if (pathAfterContext.equals("/board")) {

			} else if (pathAfterContext.equals("/SaleAnalysis")) {
				controller = new SaleAnalysisController();
				controller.execute(request, response, com);
			} else if (pathAfterContext.equals("/json")) {
				controller = new JsonController();
				controller.execute(request, response, com);

			} else if (pathAfterContext.equals("/locationRecommand")) {
				controller = new LocationRecommandController();
				controller.execute(request, response, com);
			} 
		}
	}

	public void destroy() {
		updateDBService.shutdownScheduler();
	}

	private String extractDesiredPart(String com) {
		String[] segments = com.split("/");
		if (segments.length > 1) {
			return "/" + segments[1];
		} else {
			return "Invalid com format";
		}
	}

}
