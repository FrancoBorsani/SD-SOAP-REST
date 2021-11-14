package com.helpdesk.security.controller;

import java.util.List;

import com.helpdesk.security.model.Reclamo;
import com.helpdesk.security.repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reclamo")
public class ReclamoRestController {

    @Autowired
    private ReclamoRepository reclamoRepository;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<Reclamo> findAll() {
        return reclamoRepository.findAll();
    }
    
	@RequestMapping(method = RequestMethod.GET, value = "/{reclamoId}")
    public Reclamo findOne(@PathVariable Long reclamoId) {
        return reclamoRepository.findOne(reclamoId);
    }
    
	@RequestMapping(method = RequestMethod.POST)
    public Reclamo add(@RequestBody Reclamo reclamo) {
        reclamo.setEstado("A resolver");
        reclamo.setDecision("");

        return reclamoRepository.save(reclamo);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/aceptar")
    public Reclamo aceptar(@RequestBody Reclamo reclamo) {
        reclamo.setEstado("Resuelto");
        reclamo.setDecision("Devolver dinero.");

        return reclamoRepository.save(reclamo);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/rechazar")
    public Reclamo rechazar(@RequestBody Reclamo reclamo) {
        reclamo.setEstado("Resuelto");
        reclamo.setDecision("Reclamo rechazado.");

        return reclamoRepository.save(reclamo);
    }

	@RequestMapping(method = RequestMethod.PUT)
    public Reclamo update(@RequestBody Reclamo reclamo) {
        return reclamoRepository.save(reclamo);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{reclamoId}")
    public void delete(@PathVariable Long reclamoId) {
        reclamoRepository.delete(reclamoId);
    }
}