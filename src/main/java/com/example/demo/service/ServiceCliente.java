package com.example.demo.service;


import com.example.demo.DTO.ClienteDTO;
import com.example.demo.models.Cliente;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service

public class ServiceCliente {

    @Autowired
    private Repository repo;

    public List<ClienteDTO> getClientes() {
        List<Cliente> clientes = repo.findAll();
        return clientes.stream().map(cliente -> new ClienteDTO(cliente.getId(),cliente.getNombre(), cliente.getApellido(), cliente.edad(cliente.getNacimiento()))).collect(Collectors.toList());
    }

    public void post(Cliente cliente){
        repo.save(cliente);
    }

    public void update(Long id, Cliente cliente){
        Cliente updateCliente = repo.findById(id).get();
        updateCliente.setNombre(cliente.getNombre());
        updateCliente.setApellido(cliente.getApellido());
        repo.save(updateCliente);
    }


    public void delete(Long id){
        Cliente deleteCliente = repo.findById(id).get();
        repo.delete(deleteCliente);
    }
}
