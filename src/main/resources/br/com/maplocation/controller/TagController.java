package br.com.maplocation.controller;

import java.util.Date;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.maplocation.model.Tag;
import br.com.maplocation.service.TagService;

@Resource
@Path("/tag")
public class TagController {

	private Result result;
	private TagService service;
	
	public TagController(Result result, TagService service) {
		this.result = result;
		this.service = service;
	}

	@Post("/cadastra")
	public void cadastra(Tag tag) {
		tag.setCreated(new Date());
		service.cadastra(tag);
		result.include("sucesso", "Tag cadastrada com sucesso!");
		result.redirectTo(this).paginaDeCadastro();
	}

	@Get("/paginaCadastro")
	public void paginaDeCadastro() {
	}

	@Get("/obtem/{id}")
	public void obtemPor(Integer id) {
		Tag tag = service.obtemPor(id);
		result.use(Results.json()).from(tag, "tag").serialize();
	}

}
