package com.aconchego.hospedagem.service;

import com.aconchego.hospedagem.model.Clientes;
import com.aconchego.hospedagem.repository.ClientesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    ClientesRepository clientesRepository;

    public Clientes criar(Clientes cli) {
        cli.setId(null);
        clientesRepository.save(cli);
        return cli;
    }

    public List<Clientes> listarTodos() {
        return clientesRepository.findAll();
    }

    public Clientes buscarPorId(Integer id) {
        return clientesRepository.findById(id).orElseThrow();
    }

    public void excluir(Integer id) {
        Clientes clienteEncontrado = buscarPorId(id);
        clientesRepository.deleteById(clienteEncontrado.getId());
    }

    public Clientes atualizar(Integer id, Clientes clienteEnviado) {
        Clientes clienteEncontrado = buscarPorId(id);
        clienteEncontrado.setNome(clienteEnviado.getNome());
        clienteEncontrado.setTipo(clienteEnviado.getTipo());
        clienteEncontrado.setRaca(clienteEnviado.getRaca());
        clienteEncontrado.setIdade(clienteEnviado.getIdade());
        clienteEncontrado.setSexo(clienteEnviado.getSexo());
        clienteEncontrado.setCastrado(clienteEnviado.isCastrado());
        clientesRepository.save(clienteEncontrado);
        return clienteEncontrado;
    }

}
