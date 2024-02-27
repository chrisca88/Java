package com.example.demo.controllers;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.models.Cliente;
import com.example.demo.service.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class Controller {

	@Autowired
	private ServiceCliente serviceCliente;

	@GetMapping
	public String index() {
		return "Conectado a la base";
	}


	@GetMapping("/listar")
	public List<ClienteDTO> getClientes() {
		return serviceCliente.getClientes();
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		Cliente cliente = serviceCliente.getClienteId(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/alta")
	public String post(@RequestBody Cliente cliente){
		serviceCliente.post(cliente);
		return ("El cliente fue guardado exitosamente");
	}

	@PutMapping("/modificar/{id}")
	public String update(@PathVariable Long id, @RequestBody Cliente cliente){
		serviceCliente.update(id,cliente);
		return ("El cliente ha sido modificado");
	}

	@DeleteMapping("/baja/{id}")
	public String delete(@PathVariable Long id){
		serviceCliente.delete(id);
		return ("El cliente ha sido eliminado");
	}

}
