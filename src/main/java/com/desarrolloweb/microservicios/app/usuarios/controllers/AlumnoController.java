package com.desarrolloweb.microservicios.app.usuarios.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.desarrolloweb.microservicios.app.cursos.commons.alumnos.models.entity.Alumno;
import com.desarrolloweb.microservicios.app.usuarios.services.AlumnoService;
import com.desarrolloweb.microservicios.commons.controllers.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {






	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result,@PathVariable Long id){
		if (result.hasErrors()) {
			return this.validar(result);
			
		}
		
		Optional<Alumno> o = service.findById(id);  
		   if (o.isEmpty()) {
			   return ResponseEntity.notFound().build();
		   }
		   Alumno alumnoDb = o.get();
		   alumnoDb.setNombre(alumno.getNombre());
		   alumnoDb.setApellido(alumno.getApellido());
		   return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}
	
	@GetMapping ("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}
}
