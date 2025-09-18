package com.example.frota.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Caminhao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;

	String marca;

	String modelo;

	double cargaMax;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getCargaMax() {
		return cargaMax;
	}

	public void setCargaMax(double cargaMax) {
		this.cargaMax = cargaMax;
	}
}
