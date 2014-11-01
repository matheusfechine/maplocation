package br.com.maplocation.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.maplocation.model.Location;
import br.com.maplocation.service.LocationService;

@Resource
@Path("/map")
public class MapController {

	private LocationService locationService;
	private Result result;
	
	public MapController(Result result, LocationService locationService) {
		this.result = result;
		this.locationService = locationService;
	}

	@Get("/")
	public void paginaDoMapa() {
		List<Location> locations = locationService.lista();
		result.include("locations", locations);
	}

}
