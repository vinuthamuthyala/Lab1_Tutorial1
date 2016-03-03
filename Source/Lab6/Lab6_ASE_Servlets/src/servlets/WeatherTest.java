package servlets;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeatherTest {

	@Test
	public final void testGetWeatherInfo() {
String place="chicago";
String info[]={"41.85","-87.65","100","30.0"," overcast clouds","image"};
Weather w=new Weather();
try {
	String[] lat=Weather.callWeatherAPI(place);
	System.out.println(lat.length);
	System.out.println("initially declared"+info.length);
	assertEquals(lat.length, info.length);
	
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	}

	@Test
	public final void testCallURL() {
		//fail("Not yet implemented"); // TODO
	}
	
	


}

