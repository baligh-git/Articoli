package com.xantrix.webapp.UnitTest.ControllerTest;

import com.xantrix.webapp.Application;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ContextConfiguration(classes = Application.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InsertArtTest
{
	 
    private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@BeforeEach
	public void setup()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	String JsonData =  
			"{\n" + 
			"    \"codArt\": \"123Test\",\n" + 
			"    \"descrizione\": \"ARTICOLO TEST\",\n" + 
			"    \"um\": \"PZ\",\n" + 
			"    \"codStat\": \" TEST\",\n" + 
			"    \"pzCart\": 1,\n" + 
			"    \"pesoNetto\": 0,\n" + 
			"    \"idStatoArt\": \"1\",\n" + 
			"    \"dataCreaz\": \"2018-09-26\",\n" + 
			"	 \"famAssort\": {\n" + 
			"        \"id\": 1 \n" + 
			"    }\n" + 
			"}";
	
	@Test
	@Order(1)
	public void A_testInsArticolo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/articoli/inserisci")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	@Test
	@Order(2)
	public void B_testErrInsArticolo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/articoli/inserisci")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable())
				.andExpect(jsonPath("$.codice").value(406))
				.andExpect(jsonPath("$.messaggio").value("Articolo 123Test presente in anagrafica! Impossibile utilizzare il metodo POST"))
				.andDo(print());
	}
	
	String ErrJsonData =  
					"{\n" + 
					"    \"codArt\": \"123Test1\",\n" + 
					"    \"descrizione\": \"\",\n" + 
					"    \"um\": \"PZ\",\n" + 
					"    \"codStat\": \" TEST\",\n" + 
					"    \"pzCart\": 1,\n" + 
					"    \"pesoNetto\": 0,\n" + 
					"    \"idStatoArt\": \"1\",\n" + 
					"    \"dataCreaz\": \"2018-09-26\",\n" + 
					"	 \"famAssort\": {\n" + 
					"        \"id\": 1 \n" + 
					"    }\n" + 
					"}";
	
	@Test
	@Order(3)
	public void C_testErrInsArticolo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/articoli/inserisci")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ErrJsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.codice").value(400))
				//.andExpect(jsonPath("$.messaggio").value("Articolo 123Test presente in anagrafica! Impossibile utilizzare il metodo POST"))
				.andDo(print());
	}
	
	@Test
	@Order(4)
	public void D_testUpdArticolo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.put("/articoli/modifica")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	@Test
	@Order(5)
	public void E_testDelArticolo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.delete("/articoli/elimina/123Test")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Eliminazione Articolo 123Test Eseguita Con Successo"))
				.andDo(print());
	}
	
	
	
}
