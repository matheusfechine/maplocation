package br.com.maplocation.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.maplocation.controller.util.JsonSerializer;
import br.com.maplocation.model.Tag;
import br.com.maplocation.service.TagService;

public class TagControllerTest {

	private TagController controller;
	private MockSerializationResult result;
	@Mock 
	private TagService service;
	
	private JsonSerializer serializer;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		serializer = new JsonSerializer();
		result = new MockSerializationResult();
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
	
	@Test
	public void deveriaBuscarTagPorId() throws Exception{
		when(service.obtemPor(anyInt())).thenReturn(tag());
		controller.obtemPor(1);
		assertEquals(serializer.serialize("tag", tag()), result.serializedResult());

	}
}
