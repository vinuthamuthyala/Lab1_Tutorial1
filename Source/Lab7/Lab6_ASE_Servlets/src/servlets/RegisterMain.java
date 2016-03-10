package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

/**
 * Servlet implementation class RegisterMain
 */
@WebServlet("/registermain")
public class RegisterMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In do get");
		MongoClientURI uri = new MongoClientURI("mongodb://vinu:vinu@ds011449.mlab.com:11449/vinutham");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection users = db.getCollection("vinuthama");
		BasicDBObject query = new BasicDBObject();
	
		String firstname=request.getParameter("FirstName");
		System.out.println("first name"+firstname);
		String lastname=request.getParameter("LastName");
		String email=request.getParameter("email");
		String password=request.getParameter("EnterPassword");
		String confpasswd=request.getParameter("ConfirmPassword");
		JSONObject jsonObj = new JSONObject("{\"firstname\":\""+firstname+"\",\"lastname\":\""+lastname+"\",\"email\":\""+email+"\",\"password\":\""+password+"\"}");
		String jsoninsert=jsonObj.toString();
		DBObject dbObject = (DBObject)JSON.parse(jsoninsert);
		users.insert(dbObject);
		query.put("First Name",firstname);
		query.put("Last Name",lastname);
		query.put("Email",email);
		System.out.println(email);
		if(password==confpasswd){
			query.put("Password",password);
		}
		else
		{
			
		}
		DBCursor docs = users.find(query);
		response.getWriter().write(docs.toArray().toString());
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		System.out.println("Insert doget");
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
