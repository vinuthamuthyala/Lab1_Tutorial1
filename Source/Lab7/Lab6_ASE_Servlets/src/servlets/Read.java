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
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Servlet implementation class Read
 */
@WebServlet("/Read")
public class Read extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Read() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		MongoClientURI uri  = new MongoClientURI("mongodb://vinu:vinu@ds011449.mlab.com:11449/vinutham"); 
	    MongoClient client = new MongoClient(uri);
	    DB db = client.getDB(uri.getDatabase());
	    System.out.println("Sysy"+db.getName());
	    DBCollection users = db.getCollection("vinuthama");
	   
	    BasicDBObject query = new BasicDBObject();
	    BasicDBObject field = new BasicDBObject();
	   
	    DBCursor cursor = db.getCollection("vinuthama").find(query,field);
	    pw.println("Details Of Users Registered");
	    int i=1;
	    while (cursor.hasNext()) {
	        BasicDBObject obj = (BasicDBObject) cursor.next();
	        System.out.println("email"+obj.getString("email"));
	        pw.println(i+"."+""+obj.getString("firstname")+" "+obj.getString("lastname")+"   "+obj.getString("email"));
	        i++;
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
