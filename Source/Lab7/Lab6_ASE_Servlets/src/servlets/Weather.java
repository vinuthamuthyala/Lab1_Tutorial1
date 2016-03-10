package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Weather
 */
@Path("/weather")
@WebServlet("/Weather")
public class Weather extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Weather() {
    	
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String source = request.getParameter("location");
		
		String[] WeatherInfo=callWeatherAPI(source);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null; 
		
		String lat=WeatherInfo[0],lon=WeatherInfo[1], hum=WeatherInfo[2],ft=WeatherInfo[3],desc=WeatherInfo[4],ic_url=WeatherInfo[5];
		

		
		String myURL1="https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="+source;
		System.out.println("Requeted URL:" + myURL1);
		StringBuilder sb1 = new StringBuilder();
		URLConnection urlConn1 = null;
		InputStreamReader in1 = null;
		try {
			URL url1 = new URL(myURL1);
			urlConn = url1.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb1.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL1, e);
		} 
		String responsetext1=sb1.toString();
		System.out.println(responsetext1);
		JSONObject json1=new JSONObject(responsetext1);
		JSONObject js1=json1.getJSONObject("query");
		JSONObject js2=js1.getJSONObject("pages");
		String j=js2.toString();
		int i=j.indexOf("extract");
		int k=j.lastIndexOf("pageid");
		String j2=j.substring(i+10, k-3);
		System.out.println("index  "+i);
		System.out.println("index  "+k);
		System.out.println("js1...."+js1.toString());
		System.out.println("js2...."+js2.toString());
		System.out.println("String"+j2);

		PrintWriter pw=response.getWriter();

		pw.println("<html>");
		pw.println("<head><title>Home</title>"+
		"<style>body{font-family: 'Open Sans', sans-serif;"+
		 "background:#3498db;margin: 0 auto 0 auto;width:100%;text-align:center;"+
		  "margin: 20px 0px 20px 0px;}"
		  + "p{font-size:12px;text-decoration: none;color:#ffffff;text-align: center;font-family: 'Open Sans', sans-serif;}"
		  + "</style></head><body>");
		pw.println("<h1>Basic information about "+source+"</h1>");
		pw.println("<p> Latitude : "+lat.toString()+"</p>");
		pw.println("<p> Longitude : "+lon.toString()+"</p>");
		pw.println("<p> Humidity : "+hum.toString()+"</p>");
		pw.println("<p> Temperature : "+ft.toString()+"&deg; F</p>");
		pw.println("<p> Description : "+desc.toString()+"</p>");
		pw.println("<img src="+ic_url+"></img>");
		pw.println("<p>About "+source+" : "+j2+"</p>");
		pw.println("</html></body>");		
		
	}
	public static String[] callWeatherAPI(String location){
		String myURL="http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid=379ebdd71e548870edfa66f91e5eeecc";
		System.out.println("Requeted URL:" + "http://api.openweathermap.org/data/2.5/weather?q=chicago&appid=379ebdd71e548870edfa66f91e5eeecc");
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 
		String responsetext=sb.toString();
		System.out.println(responsetext);
		JSONObject json=new JSONObject(responsetext);
		JSONObject js=json.getJSONObject("main");
		
		Integer hum=(Integer)js.get("humidity");
		Double temp=(Double)js.get("temp");
		Double ftemp=kelvintofahrnht(temp);
		System.out.println(ftemp.toString());
		Double ft=(double) Math.round(ftemp);
		System.out.println(hum.toString());
		JSONObject ja=json.getJSONObject("coord");
		System.out.println(ja.toString());
		Double lat=(Double)ja.get("lat");
		System.out.println(lat.toString());
		Double longi=(Double)ja.get("lon");
		JSONArray jsa=json.getJSONArray("weather");
		System.out.println(jsa.toString());
		String desc=jsa.getJSONObject(0).getString("description");
		String ic=jsa.getJSONObject(0).getString("icon");
		String ic_url="http://openweathermap.org/img/w/"+ic+".png";
		System.out.println(desc.toString());
		String[] weatherinfo=new String[6];
		weatherinfo[0]=lat.toString();
		weatherinfo[1]=longi.toString();
		weatherinfo[2]=hum.toString();
		weatherinfo[3]=ft.toString();
		weatherinfo[4]=desc.toString();
		weatherinfo[5]=ic_url.toString();
		return weatherinfo;
	}
	public static double kelvintofahrnht(Double temp){
		return (temp - 273.15) * 1.8 + 32;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
//		doGet(request, response);
//		public static String callURL() {
		System.out.println("doPost");
	
			
//		}
	}

}
