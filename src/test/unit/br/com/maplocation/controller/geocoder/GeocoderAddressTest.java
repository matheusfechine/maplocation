package br.com.maplocation.controller.geocoder;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.maplocation.geocoder.GeocoderAddress;

public class GeocoderAddressTest {

	private static final String ADDRESS = "Avenida Santos Dumont, 2831-2939 - Manoel Dias Branco, Fortaleza - State of Ceará, Brazil";
	private GeocoderAddress geocoder;
	@Before
	public void setUp(){
		geocoder = new GeocoderAddress();
	}
	
	@Test
	public void deveRetornarEnderecoNaAldeota() throws Exception{
		String address = geocoder.getAddress(-3.735945, -38.499017);
		assertEquals(ADDRESS, address);
	}
}
