package com.aconchego.hospedagem.controller;

import com.aconchego.hospedagem.model.Clientes;
import com.aconchego.hospedagem.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientesController {

    @Autowired
    ClientesService clientesService;

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/inserir-cliente")
    public String cadastroForm(Model model) {
        model.addAttribute("clientes", new Clientes());
        return "cadastro";
    }

    @GetMapping("/listagem")
    public String listaForm(Model model) {
        model.addAttribute("lista", clientesService.listarTodos());
        return "lista";
    }

    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Clientes clientes, Model model) {

        //clientes.setCastrado(false);

        if (clientes.getId() != null) {
            clientesService.atualizar(clientes.getId(), clientes);
        } else {
            //clientes.setCastrado(false);
            clientesService.criar(clientes);
        }

        
        return "redirect:/listagem";
    }

    @GetMapping("/exibir")
    public String mostraDetalhesClientes(Model model, @RequestParam String id) {
        Integer idClientes = Integer.parseInt(id);

        Clientes registroEncontrado = new Clientes();
        registroEncontrado = clientesService.buscarPorId(idClientes);

        model.addAttribute("clientes", registroEncontrado);
        return "exibir";
    }

    @GetMapping("/excluir")
    public String excluirClientes(Model model, @RequestParam String id) {
        Integer idClientes = Integer.parseInt(id);
        clientesService.excluir(idClientes);
        return "redirect:/listagem";
    }

    @GetMapping("/alterar")
    public String alterarClientes(Model model, @RequestParam String id) {
        Integer idClientes = Integer.parseInt(id);
        Clientes clienteEncontrado = clientesService.buscarPorId(idClientes);
        model.addAttribute("clientes", clienteEncontrado);
        return "alterar";
    }
}
