package it.baligh.webapp.UnitTest.RepositoryTests;

import static org.assertj.core.api.Assertions.assertThat;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import it.baligh.webapp.Application;
import it.baligh.webapp.entities.Articoli;
import it.baligh.webapp.repository.ArticoliRepository;



@ContextConfiguration(classes = Application.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticoliRepositoryTest
{
	@Autowired
	private ArticoliRepository articoliRepository;

	@Test
	public void TestfindByDescrizioneLike()
	{
		List<Articoli> items = articoliRepository.findByDescrizioneLike("ACQUA ULIVETO%");
		assertThat(items.size()).isEqualTo(2);
		
		
	}
	
	@Test
	public void TestfindByDescrizioneLikePage()
	{
		List<Articoli> items = articoliRepository.findByDescrizioneLike("ACQUA%",PageRequest.of(0, 10));
		assertThat(items.size()).isEqualTo(10);
	}

	@Test
	public void TestfindByCodArt() throws Exception
	{
		assertThat(articoliRepository.findByCodArt("002000301")).extracting(Articoli::getDescrizione).isEqualTo("ACQUA ULIVETO 15 LT");

				//.containsOnly("ACQUA ULIVETO 15 LT", "ACQUA ULIVETO 15 LT");
	}

}
