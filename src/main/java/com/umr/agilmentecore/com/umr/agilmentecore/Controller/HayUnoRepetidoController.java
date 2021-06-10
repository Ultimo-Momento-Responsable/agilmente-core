package com.umr.agilmentecore.com.umr.agilmentecore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.com.umr.agilmentecore.Class.HayUnoRepetido;
import com.umr.agilmentecore.com.umr.agilmentecore.Services.HayUnoRepetidoService;

@RestController
@RequestMapping("/hay-uno-repetido")
public class HayUnoRepetidoController {

	@Autowired
	private HayUnoRepetidoService servicio;
	
	@GetMapping
	public Page<HayUnoRepetido> listarTodos(Pageable pagina) {
		return servicio.listarTodos(pagina);
	}
	
	@PostMapping
	public HayUnoRepetido guardar(@RequestBody HayUnoRepetido j) {
		
		return servicio.guardar(j);
	}
}
