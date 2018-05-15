import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Addition extends HttpServlet{

	public Addition (){ 
}
	
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
	

		String a = request.getParameter("a");
		String m = request.getParameter("m");
		String b = request.getParameter("b");
		
		int sum=1;
		if(m.equals("+")){
			 sum=Integer.parseInt(a)+Integer.parseInt(b);

		}
		if(m.equals("*")){
			 sum=Integer.parseInt(a)*Integer.parseInt(b);
		}
		if(m.equals("/")){
			 sum=Integer.parseInt(a)/Integer.parseInt(b);
		}
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println(sum);
	}

}