package it.baligh.webapp.UnitTest.RepositoryTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import it.baligh.webapp.Application;
import it.baligh.webapp.entities.Barcode;
import it.baligh.webapp.repository.BarcodeRepository;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class BarcodeRepositoryTest
{
	@Autowired
	private BarcodeRepository barcodeRepository;
	
	@Test
	public void TestfindByBarcode()
	{
		assertThat(barcodeRepository.findByBarcode("8008490000021"))
		.extracting(Barcode::getBarcode).isEqualTo("8008490000021");
		//.containsOnly("8008490000021", "8008490000021");
	}
}