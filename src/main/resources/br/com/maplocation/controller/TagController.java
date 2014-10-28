package br.com.maplocation.controller;

import br.com.caelum.vraptor.Result;
import br.com.maplocation.model.Tag;
import br.com.maplocation.service.TagService;

public class TagController {

	private Result result;
	private TagService service;
	
	public TagController(Result result, TagService service) {
		this.result = result;
		this.service = service;
	}

	public void cadastra(Tag tag) {
		service.cadastra(tag);
		result.include("sucesso", "Tag cadastrada com sucesso!");
	}

}
