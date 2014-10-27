package br.com.maplocation.controller;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.maplocation.model.Location;
import br.com.maplocation.service.LocationService;

@Resource
@Path("/location")
public class LocationController {

	private Result result;
	private LocationService locationService;
	
	public LocationController(Result result, LocationService locationService) {
		this.result = result;
		this.locationService = locationService;
	}
	
	@Post
	@Path("/cadastra")
	public void cadastra(Location location) {
		location.setCreated(new Date());
		locationService.cadastra(location);
		result.include("sucesso", "Location Cadastrado com Sucesso!");
		result.redirectTo(this).paginaDeCadastro();
	}

	@Get
	@Path("/")
	public void paginaDeCadastro() {
	}

	@Get
	@Path("/lista")
	public void lista() {
		List<Location> locations = locationService.lista();
		result.include("locations", locations);
		
	}

	@Get
	@Path("/paginaListagem")
	public void paginaDeListagem() {
		lista();
	}

	@Get
	@Path("/atualiza")
	public void atualiza(Location location) {
		locationService.atualiza(location);
		result.include("sucesso", "Location Atualizado com Sucesso!");
	}

	@Delete
	@Path("/exclui/{id}")
	public void exclui(Integer id) {
		locationService.exclui(id);
		result.include("sucesso", "Location Excluído com Sucesso!");
	}

}
