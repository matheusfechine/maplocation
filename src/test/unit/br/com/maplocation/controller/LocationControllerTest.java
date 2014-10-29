package br.com.maplocation.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.maplocation.model.Location;
import br.com.maplocation.model.Tag;
import br.com.maplocation.service.LocationService;
import br.com.maplocation.service.TagService;

public class LocationControllerTest {

	private LocationController controller;
	private MockResult result;
	@Mock private LocationService service;
	@Mock private TagService tagService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		controller = new LocationController(result, service, tagService);
	
	}
	
	@Test
	public void deveriaChamarPaginaDeCadastro(){
		when(tagService.lista()).thenReturn(Lists.newArrayList(tag()));
		controller.paginaDeCadastro();
		assertEquals(Lists.newArrayList(tag()), result.included().get("tags"));
	}
	
	private Tag tag() {
		Tag tag = new Tag();
		tag.setName("TAG1");
		return tag;
	}

	@Test
	public void deveriaCadastrarUmaLocation(){
		controller.cadastra(location());
		verify(service).cadastra(any(Location.class));
		assertEquals("Location Cadastrado com Sucesso!", result.included().get("sucesso"));
	}

	private Location location() {
		Location location = new Location();
		location.setId(1);
		location.setLatitude(12345D);
		location.setLongitude(5432D);
		return location;
	}
	
	@Test
	public void deveriaChamarPaginaDeListagem(){
		controller.paginaDeListagem();
	}

	@Test
	public void deveriaListarTodasAsLocations(){
		when(service.lista()).thenReturn(Lists.newArrayList(location()));
		controller.lista();
		verify(service).lista();
		assertEquals(Lists.newArrayList(location()), result.included().get("locations"));
	}
	
	@Test
	public void deveriaEditarUmaLocation(){
		controller.atualiza(location());
		verify(service).atualiza(location());
		assertEquals("Location Atualizado com Sucesso!", result.included().get("sucesso"));
	}
	
	@Test
	public void deveriaExcluirUmaLocation(){
		controller.exclui(1);
		verify(service).exclui(1);
		assertEquals("Location Excluído com Sucesso!", result.included().get("sucesso"));
	}
	
	@Test
	public void deveriaChamarPaginaDeAtualizacao(){
		when(service.obtemPor(anyInt())).thenReturn(location());
		when(tagService.lista()).thenReturn(Lists.newArrayList(tag()));
		controller.paginaDeAtualizacao(1);
		assertEquals(location(), result.included().get("location"));
		assertEquals(Lists.newArrayList(tag()), result.included().get("tags"));
		
	}
	
}
