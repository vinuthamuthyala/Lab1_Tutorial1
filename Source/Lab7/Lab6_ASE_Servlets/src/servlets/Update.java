package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String s=request.getParameter("email");
		
		
//		String firstname=request.getParameter("FirstName");
//		System.out.println("first name"+firstname);
//		String lastname=request.getParameter("LastName");
		String email=request.getParameter("email");
		String password=request.getParameter("EnterPassword");
		PrintWriter var=response.getWriter();
		response.setContentType("text/plain");
		MongoClientURI uri  = new MongoClientURI("mongodb://vinu:vinu@ds011449.mlab.com:11449/vinutham"); 
	    MongoClient client = new MongoClient(uri);
	    DB db = client.getDB(uri.getDatabase());
	    System.out.println("Sysy"+db.getName());
	    DBCollection users = db.getCollection("vinuthama");
	    //To DO Update Operation.................................
	    BasicDBObject newDocument = new BasicDBObject();
		/*newDocument.append("$set", new BasicDBObject().append("desc", "clear clouds"));
				
		BasicDBObject searchQuery = new BasicDBObject().append("cityname", "Kansas");
*/
//	    newDocument.append("$set", new BasicDBObject().append("firstname", firstname));
//	    newDocument.append("$set", new BasicDBObject().append("lastname", lastname));
	    newDocument.append("$set", new BasicDBObject().append("email", email));
	    newDocument.append("$set", new BasicDBObject().append("password", password));
		BasicDBObject searchQuery = new BasicDBObject().append("email", s);

	    users.update(searchQuery, newDocument);
	    var.println("Email "+s+" "+" Password was Updated to"+"  "+password);
		System.out.println("updated.............");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
