package br.com.fiap.gestao_medico_api.controller;

import br.com.fiap.gestao_medico_api.model.Medico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicoController {

    private List<Medico> medicos = new ArrayList<>();


    @GetMapping("/medicos")
    public List<Medico> getMedicos() {
        return medicos;
    }


    @PostMapping("/medicos")
    public ResponseEntity<Medico> addMedico(@RequestBody Medico medico) {
        System.out.println("Cadastro do medico: " + medico.getNome());
        medicos.add(medico);
        return ResponseEntity.status(201).body(medico);
    }


    @GetMapping("/medicos/{id}")
    public ResponseEntity<Medico> getMedico(@PathVariable Long id) {
        System.out.println("Buscando médico com ID: " + id);
        var medico = medicos.stream().filter(m -> m.getId().equals(id)).findFirst();
        if (medico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medico.get());
    }


    @PutMapping("/medicos/{id}")
    public ResponseEntity<Medico> updateMedico(@PathVariable Long id, @RequestBody Medico medicoAtualizado) {
        System.out.println("Atualizando médico com ID: " + id);
        var medico = medicos.stream().filter(m -> m.getId().equals(id)).findFirst();

        if (medico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Medico medicoExistente = medico.get();

        medicoExistente.setNome(medicoAtualizado.getNome());
        medicoExistente.setCpf(medicoAtualizado.getCpf());


        return ResponseEntity.ok(medicoExistente);
    }


    @DeleteMapping("/medicos/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        System.out.println("Deletando médico com ID: " + id);
        var medico = medicos.stream().filter(m -> m.getId().equals(id)).findFirst();

        if (medico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        medicos.remove(medico.get());
        return ResponseEntity.noContent().build();
    }
}
