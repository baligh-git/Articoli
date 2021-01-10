package it.baligh.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.baligh.webapp.entities.Barcode;

public interface BarcodeRepository extends JpaRepository<Barcode, Integer>
{
	Barcode findByBarcode(String Barcode);
}
