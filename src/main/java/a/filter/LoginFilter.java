package a.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{
	private String passPage;
	private String passPageList[];
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest)request).getServletPath();
		
		boolean flag = true;
		for(String str:passPageList){
			if(str.endsWith("*")){
				str = str.substring(0, str.length()-1);
				if(path.indexOf(str) != -1)flag = false;
			}else if(path.equals(str))
				flag = false;
		}
		if(path.equals("/index.html") && request.getParameter("innerPath") !=null )
			flag=true;
		if(flag){
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			if(session.getAttribute("user")!=null){
				chain.doFilter(request, response);
			}else{
				((HttpServletResponse)response).sendRedirect("loginPage.html");
	//			request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
		passPage = arg0.getInitParameter("page");
		passPageList = passPage.split(",");
	}

		
}
