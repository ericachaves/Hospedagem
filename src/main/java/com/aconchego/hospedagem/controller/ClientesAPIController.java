package com.aconchego.hospedagem.controller;

import com.aconchego.hospedagem.model.Clientes;
import com.aconchego.hospedagem.service.ClientesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientesAPIController {

    @Autowired
    ClientesService clientesService;

    @PostMapping("/adicionar")
    public ResponseEntity<Clientes> addClientes(@RequestBody Clientes cli){
        var novosClientes = clientesService.criar(cli);
        return new ResponseEntity<>(novosClientes, HttpStatus.CREATED);

    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> listar(){
       List<Clientes> cliente =  clientesService.listarTodos();
       return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    
     @GetMapping("/buscar/{id}")
     public ResponseEntity<Clientes> pesquisar(@PathVariable Integer id){
        Clientes clienteEncontrado =  clientesService.buscarPorId(id);
        return new ResponseEntity<>(clienteEncontrado, HttpStatus.OK);
     }
     
     @PutMapping("/atualizar/{id}")
     public ResponseEntity<Clientes> editClientes(@PathVariable Integer id,@RequestBody Clientes cli){
         var editarClientes = clientesService.atualizar(id, cli);
        return new ResponseEntity<>(editarClientes, HttpStatus.OK); 
     }
     
     @DeleteMapping("/excluir/{id}")
     public ResponseEntity<?> delete(@PathVariable Integer id){
         clientesService.excluir(id);
         return new ResponseEntity<>(HttpStatus.OK);
     }
}
