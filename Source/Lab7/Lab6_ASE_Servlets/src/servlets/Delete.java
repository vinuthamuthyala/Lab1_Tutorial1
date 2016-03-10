package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String s=request.getParameter("email");
//		String s="vinu";
		PrintWriter pw=response.getWriter();
		response.setContentType("text/plain");
		MongoClientURI uri  = new MongoClientURI("mongodb://vinu:vinu@ds011449.mlab.com:11449/vinutham"); 
	    MongoClient client = new MongoClient(uri);
	    DB db = client.getDB(uri.getDatabase());
	    System.out.println("Sysy"+db.getName());
	    DBCollection users = db.getCollection("vinuthama");
	//To Delete the city Details 
	    BasicDBObject query = new BasicDBObject();
	    query.append("email", s);

	    users.remove(query);
	    System.out.println("Details deleted");
	    pw.println("<html>");
		pw.println("<head><title>Home</title>"+
		"<style>body{font-family: 'Open Sans', sans-serif;"+
		 "background:#3498db;margin: 0 auto 0 auto;width:100%;text-align:center;"+
		  "margin: 20px 0px 20px 0px;}"
		  + "p{font-size:12px;text-decoration: none;color:#ffffff;text-align: center;font-family: 'Open Sans', sans-serif;}"
		  + "</style></head><body>");
		pw.println("<h1>Account deleted successfully</h1>");
		pw.println("<h3><a href='Login.html'>Go to Login</a></h3>");
		
		pw.println("</body></html>");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
