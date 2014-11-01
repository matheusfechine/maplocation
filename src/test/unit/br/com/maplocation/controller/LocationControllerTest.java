package br.com.maplocation.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.maplocation.controller.util.JsonSerializer;
import br.com.maplocation.geocoder.GeocoderAddress;
import br.com.maplocation.model.Location;
import br.com.maplocation.model.Tag;
import br.com.maplocation.service.LocationService;
import br.com.maplocation.service.TagService;

import com.google.common.collect.Lists;

public class LocationControllerTest {

	private LocationController controller;
	private MockSerializationResult result;
	@Mock private LocationService service;
	@Mock private TagService tagService;
	@Mock private GeocoderAddress address;
	private JsonSerializer serializer;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		serializer = new JsonSerializer();
		result = new MockSerializationResult();
		controller = new LocationController(result, service, tagService, address);
	
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
	
	@Test
	public void deveriaObterUmaLocationViaJSon() throws Exception{
		when(service.obtemPor(anyInt())).thenReturn(location());
		controller.obtemPor(1);
		assertEquals(serializer.serialize("location", location()), result.serializedResult());
	}
	
	@Test
	public void deveriaRetornarEnderecoPorLatLong() throws Exception{
		when(address.getAddress(anyDouble(), anyDouble())).thenReturn("Endereco");
		controller.obtemEnderecoPor(111.11, 222.22);
		assertEquals(serializer.serialize("address", "Endereco"), result.serializedResult());
		
	}
}
