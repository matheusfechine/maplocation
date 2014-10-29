package br.com.maplocation.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import br.com.maplocation.model.Location;
import br.com.maplocation.model.Tag;
import dbunit.DbUnitManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext-persistence-test.xml", 
								  "classpath:applicationContext.xml"})
@Transactional
public class LocationServiceTest {
	
	private static final String LOCATIONS = "src/test/integration/datasets/Location.xml";
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
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
		Location location = service.obtem(location());
		assertEquals(location(), location);
		assertEquals("Data", dateFormat.format(asDate("25/10/2014 00:00:00")), dateFormat.format(location.getCreated()));
	}

	private Location location() {
		Location location = new Location();
		location.setLatitude(123D);
		location.setLongitude(23232D);
		location.setName("Teste");
		location.setCreated(asDate("25/10/2014 00:00:00"));
		location.setTags(Lists.newArrayList(tag3()));
		return location;
	}

	private Tag tag3() {
		Tag tag = new Tag();
		tag.setName("TAG3");
		tag.setCreated(asDate("25/10/2014 11:00:00"));
		return tag;
	}


	@Test
	public void deveriaListarTodasAsLocations(){
		List<Location> locations = service.lista();
		assertEquals(2, locations.size());
		assertEquals(Lists.newArrayList(location1(), location2()).toString(), locations.toString());
		assertEquals("Data", dateFormat.format(asDate("25/10/2014 00:00:00")), dateFormat.format(locations.get(0).getCreated()));
		assertEquals("Data", dateFormat.format(asDate("25/10/2014 01:00:00")), dateFormat.format(locations.get(1).getCreated()));
	}
	
	public Date asDate(String valor) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(valor);
		} catch (ParseException e) {
			return null;
		}
	}
	
	private Location location1() {
		Location location = new Location();
		location.setId(1);
		location.setLatitude(3.222D);
		location.setLongitude(2.222D);
		location.setName("Teste 1");
		location.setTags(Lists.newArrayList(tag1()));
		return location;
	}
	
	private Tag tag1() {
		Tag tag = new Tag();
		tag.setId(1);
		tag.setName("TAG1");
		tag.setCreated(asDate("25/10/2014 11:00:00"));
		return tag;
	}


	private Location location2() {
		Location location = new Location();
		location.setId(2);
		location.setLatitude(4.444D);
		location.setLongitude(5.555D);
		location.setName("Teste 2");
		location.setTags(Lists.newArrayList(tag2()));
		return location;
	}

	private Tag tag2() {
		Tag tag = new Tag();
		tag.setName("TAG2");
		tag.setCreated(asDate("25/10/2014 11:00:00"));
		return tag;
	}
	
	@Test
	public void deveriaAtualizarUmaLocation(){
		Location location = location();
		location.setLatitude(2.111D);
		location.setLongitude(3.1234D);
		location.setName("ATUALIZADO");
		location.setCreated(asDate("25/10/2014 10:00:01"));
		location.setTags(Lists.newArrayList(tag2()));
		service.atualiza(location);
		Location locationAtualizada = service.obtem(location);
		assertEquals(location, locationAtualizada);
		assertEquals("Data", dateFormat.format(asDate("25/10/2014 10:00:01")), dateFormat.format(locationAtualizada.getCreated()));
	}
	
	@Test
	public void deveriaExcluirUmaLocation(){
		List<Location> todasLocations = service.lista();
		assertEquals(2, todasLocations.size());
		service.exclui(location1().getId());
		todasLocations = service.lista();
		assertEquals(1, todasLocations.size());
		assertEquals(todasLocations.toString(), Lists.newArrayList(location2()).toString());
		assertEquals("Data", dateFormat.format(asDate("25/10/2014 01:00:00")), dateFormat.format(todasLocations.get(0).getCreated()));
	}
	
	@Test
	public void deveriaPesquisarPorId(){
		Location location = service.obtemPor(1);
		assertEquals(location1(), location);
	}
	
}
