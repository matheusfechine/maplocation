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
import br.com.maplocation.service.LocationService;

public class LocationControllerTest {

	private LocationController controller;
	private MockResult result;
	@Mock private LocationService service;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		controller = new LocationController(result, service);
	
	}
	
	@Test
	public void deveriaChamarPaginaDeCadastro(){
		controller.paginaDeCadastro();
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
		location.setLatitude(12345L);
		location.setLongitude(5432L);
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
		controller.atualiza(1);
		verify(service).atualiza(1);
		assertEquals("Location Atualizado com Sucesso!", result.included().get("sucesso"));
	}
	
	@Test
	public void deveriaExcluirUmaLocation(){
		controller.exclui(1);
		verify(service).exclui(1);
		assertEquals("Location Excluído com Sucesso!", result.included().get("sucesso"));
	}
}
