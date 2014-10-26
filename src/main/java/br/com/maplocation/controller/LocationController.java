package br.com.maplocation.controller;

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
	private LocationService service;
	
	public LocationController(Result result, LocationService service) {
		this.result = result;
		this.service = service;
	}
	
	@Post
	@Path("/cadastra")
	public void cadastra(Location location) {
		service.cadastra(location);
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
		List<Location> locations = service.lista();
		result.include("locations", locations);
		
	}

	@Get
	@Path("/paginaListagem")
	public void paginaDeListagem() {
	}

	@Get
	@Path("/atualiza")
	public void atualiza(Location location) {
		service.atualiza(location);
		result.include("sucesso", "Location Atualizado com Sucesso!");
	}

	@Delete
	@Path("/exclui/{id}")
	public void exclui(Integer id) {
		service.exclui(id);
		result.include("sucesso", "Location Excluído com Sucesso!");
	}

}
