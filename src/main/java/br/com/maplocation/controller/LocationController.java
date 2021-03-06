package br.com.maplocation.controller;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.maplocation.geocoder.GeocoderAddress;
import br.com.maplocation.model.Location;
import br.com.maplocation.service.LocationService;
import br.com.maplocation.service.TagService;

@Resource
@Path("/location")
public class LocationController {

	private Result result;
	private LocationService locationService;
	private TagService tagService;
	private GeocoderAddress geocodeAddress;
	
	public LocationController(Result result, LocationService locationService, TagService tagService, GeocoderAddress geocodeAddress) {
		this.result = result;
		this.locationService = locationService;
		this.tagService = tagService;
		this.geocodeAddress = geocodeAddress;
	}
	
	@Post("/cadastra")
	public void cadastra(Location location) {
		location.setCreated(new Date());
		locationService.cadastra(location);
		result.include("sucesso", "Location Cadastrado com Sucesso!");
		result.redirectTo(this).paginaDeCadastro();
	}

	@Get("/")
	public void paginaDeCadastro() {
		result.include("tags", tagService.lista());
	}

	@Get("/lista")
	public void lista() {
		List<Location> locations = locationService.lista();
		result.include("locations", locations);
		
	}

	@Get("/paginaListagem")
	public void paginaDeListagem() {
		lista();
	}

	@Get("/paginaAtualizacao/{id}")
	public void paginaDeAtualizacao(Integer id) {
		Location location = locationService.obtemPor(id);
		result.include("tags", tagService.lista());
		result.include("location", location);
	}

	@Post("/atualiza")
	public void atualiza(Location location) {
		location.setCreated(new Date());
		locationService.atualiza(location);
		result.include("sucesso", "Location Atualizado com Sucesso!");
		result.redirectTo(this).paginaDeListagem();
	}

	@Get("/exclui/{id}")
	public void exclui(Integer id) {
		locationService.exclui(id);
		result.include("sucesso", "Location Excluído com Sucesso!");
		result.redirectTo(this).paginaDeListagem();
	}

	@Get("/obtem/{id}")
	public void obtemPor(Integer id) {
		Location location = locationService.obtemPor(id);
		result.use(Results.json()).from(location, "location").serialize();
	}

	@Get("/getAddress")
	public void obtemEnderecoPor(Double latitude, Double longitude) {
		String address="";
		try {
			address = geocodeAddress.getAddress(latitude, longitude);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.use(Results.json()).from(address, "address").serialize();
		
	}

}
