package br.com.maplocation.geocoder;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class GeocoderAddress {

	public String getAddress(Double latitude, Double longitude) throws Exception{
		String address = "";
		
		JSONObject addresses = getJSONAddresses(latitude, longitude);
		if ("OK".equals(addresses.getString("status"))) {
			address = addresses.getJSONArray("results").getJSONObject(0).getString("formatted_address");
		}
		return address;
	}
	
	private JSONObject getJSONAddresses(double latitude, double longitude) throws Exception {
		URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=false");

		Thread.sleep(300);
		URLConnection connection = url.openConnection();
		InputStream inputStream = connection.getInputStream();
		String encoding = connection.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String content = IOUtils.toString(inputStream, encoding);
		inputStream.close();

		return new JSONObject(content);
	}
	
}
