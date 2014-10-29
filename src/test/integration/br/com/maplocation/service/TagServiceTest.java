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

import com.google.common.collect.Lists;

import br.com.maplocation.model.Tag;
import dbunit.DbUnitManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext-persistence-test.xml", 
								  "classpath:applicationContext.xml"})
@Transactional
public class TagServiceTest {
	
	private static final String TAGS = "src/test/integration/datasets/Tag.xml";
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	@Autowired
	private TagService service;
	
	@Autowired 
	private DbUnitManager manager;
	
	@Before
	public void setUp(){
		manager.cleanAndInsert(TAGS);
	}
	
	@Test
	public void deveriaCadastrarUmaTag(){
		Tag tag = tag();
		tag.setCreated(asDate("25/10/2014 00:00:00"));
		service.cadastra(tag);
		Tag tagGravada = service.obtemPor(tag.getId());
		assertEquals(tag, tagGravada);
		assertEquals("Data", dateFormat.format(asDate("25/10/2014 00:00:00")), dateFormat.format(tagGravada.getCreated()));
	}
	
	@Test
	public void deveriaObterUmaTag(){
		Tag tag = service.obtemPor(1);
		assertNotNull(tag);
		assertEquals(tag(), tag);
		assertEquals("Data", dateFormat.format(asDate("25/10/2014 00:00:00")), dateFormat.format(tag.getCreated()));
	}
	
	public Date asDate(String valor) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(valor);
		} catch (ParseException e) {
			return null;
		}
	}
	private Tag tag() {
		Tag tag = new Tag();
		tag.setName("TAG1");
		return tag;
	}
	
	@Test
	public void deveriaListarTodasAsTags(){
		assertEquals(Lists.newArrayList(tag()), service.lista());
	}
	
}
