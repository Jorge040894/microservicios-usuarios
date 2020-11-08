package com.desarrolloweb.microservicios.app.usuarios.services;



import java.util.List;

import com.desarrolloweb.microservicios.app.cursos.commons.alumnos.models.entity.Alumno;
import com.desarrolloweb.microservicios.commons.services.CommonService;

public interface AlumnoService extends CommonService<Alumno>{
	
	public List<Alumno> findByNombreOrApellido(String term);
}
