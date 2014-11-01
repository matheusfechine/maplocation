package br.com.maplocation.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.maplocation.model.Location;
import br.com.maplocation.service.LocationService;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class MapControllerTest {

	@Mock
	private LocationService locationService;
	
	private MapController controller;
	private MockResult result;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		controller = new MapController(result, locationService);
	}
	
	@Test
	public void deveriaAbrirPaginaDoMapa(){
		when(locationService.lista()).thenReturn(Lists.newArrayList(location()));
		controller.paginaDoMapa();
		verify(locationService).lista();
		assertEquals(Lists.newArrayList(location()), result.included().get("locations"));
	}

	private Location location() {
		Location location = new Location();
		location.setName("Location");
		location.setLatitude(23.222);
		location.setLongitude(222.2);
		return location;
	}
	
}
