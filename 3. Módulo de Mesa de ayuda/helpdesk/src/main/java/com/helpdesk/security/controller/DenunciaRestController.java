package com.helpdesk.security.controller;

import java.util.List;

import com.helpdesk.security.model.Denuncia;
import com.helpdesk.security.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/denuncia")
public class DenunciaRestController {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Denuncia> findAll() {
        return denunciaRepository.findAll();
    }
    
	@RequestMapping(method = RequestMethod.GET, value = "/{denunciaId}")
    public Denuncia findOne(@PathVariable Long denunciaId) {
        return denunciaRepository.findOne(denunciaId);
    }
    
	@RequestMapping(method = RequestMethod.POST)
    public Denuncia add(@RequestBody Denuncia denuncia) {
        denuncia.setEstado("A resolver");
        denuncia.setDecision("");

        return denunciaRepository.save(denuncia);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/aceptar")
    public Denuncia aceptar(@RequestBody Denuncia denuncia) {
        denuncia.setEstado("Resuelto");
        denuncia.setDecision("Borrar publicación.");

        // TODO LLAMAR A LA API PARA BORRAR LA PUBLICACIÓN

        return denunciaRepository.save(denuncia);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/rechazar")
    public Denuncia rechazar(@RequestBody Denuncia denuncia) {
        denuncia.setEstado("Resuelto");
        denuncia.setDecision("Denuncia rechazada.");

        return denunciaRepository.save(denuncia);
    }

	@RequestMapping(method = RequestMethod.PUT)
    public Denuncia update(@RequestBody Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{denunciaId}")
    public void delete(@PathVariable Long denunciaId) {
        denunciaRepository.delete(denunciaId);
    }
}