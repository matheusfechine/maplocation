package br.com.maplocation.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.maplocation.model.Location;
import dbunit.DbUnitManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext-test.xml"})
@Transactional
public class LocationServiceTest {
	
	private static final String LOCATIONS = "src/test/integration/datasets/Location.xml";
	
	@Autowired
	private LocationService service;
	
	@Autowired 
	private DbUnitManager manager;
	
	@Before
	public void setUp(){
		manager.cleanAndInsert(LOCATIONS);
	}
	
	@Test
	public void deveriaCadastrarUmaLocation(){
		service.cadastra(location());
		assertEquals(location(), service.obtem(location()));
	}

	private Location location() {
		Location location = new Location();
		location.setLatitude(123D);
		location.setLongitude(23232D);
		location.setName("Teste");
		location.setCreated(asDate("25/10/2014 00:00:00"));
		return location;
	}
	
	private Date asDate(String dateValue){
		try {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateValue);
		} catch (ParseException e) {
			return null;
		}
	}

}
