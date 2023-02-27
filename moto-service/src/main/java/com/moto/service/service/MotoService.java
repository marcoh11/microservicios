package com.moto.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entity.Moto;
import com.moto.service.repository.MotoRepository;

@Service
public class MotoService {
 
	@Autowired 
	private MotoRepository motoRepository;
	
	public List<Moto> getAll(){
		return motoRepository.findAll();
	}
	public Moto getById(int id) {
		return motoRepository.findById(id).orElse(null);
	}
	public Moto save(Moto moto) {
		return motoRepository.save(moto);
	}
	public List<Moto> getByUsuarioId(int usuarioId) {
		return motoRepository.findByUsuarioId(usuarioId);
	}
}
