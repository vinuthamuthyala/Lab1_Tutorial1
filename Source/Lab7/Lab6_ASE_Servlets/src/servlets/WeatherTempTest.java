package servlets;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeatherTempTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public final void testkelvintofahrnht()
	{
		Double t=-416.86383999999987;
		Weather ls=new Weather();
		Double k=Weather.kelvintofahrnht(t);
		System.out.println(t);
		System.out.println(k);

		assertEquals(t, k);
	}

}
