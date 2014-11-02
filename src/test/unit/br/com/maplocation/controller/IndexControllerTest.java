package br.com.maplocation.controller;

import org.junit.Before;
import org.junit.Test;

public class IndexControllerTest {

	private IndexController controller;
	
	@Before
	public void setUp(){
		controller = new IndexController();
	}
	
	@Test
	public void deveriaChamarPaginaDeIndex(){
		controller.index();
	}
	
}
