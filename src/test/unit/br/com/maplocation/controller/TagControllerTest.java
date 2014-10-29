package br.com.maplocation.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.maplocation.model.Tag;
import br.com.maplocation.service.TagService;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TagControllerTest {

	private TagController controller;
	private MockResult result;
	@Mock 
	private TagService service;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		controller = new TagController(result, service);
	}
	
	@Test
	public void deveriaCadastrarUmaTag(){
		controller.cadastra(tag());
		verify(service).cadastra(any(Tag.class));
		assertEquals("Tag cadastrada com sucesso!", result.included().get("sucesso"));
	}
	
	private Tag tag() {
		Tag tag = new Tag();
		tag.setName("name1");
		return tag;
	}
	
	@Test
	public void deveriaChamarPaginaDeCadastro(){
		controller.paginaDeCadastro();
	}
	
}
