package com.coderhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

}
