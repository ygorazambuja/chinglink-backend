package com.azambuja;

import com.azambuja.domain.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CategoriaResourceTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void categoriaPorPorId() throws Exception {
        Categoria categoria = new Categoria(2, "Escritório");
        String jsonRequest = objectMapper.writeValueAsString(categoria);
        MvcResult mvcResult = mockMvc
                .perform(get("/categoria/2").content(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest))
                .andExpect(status().isOk()).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        Categoria categoriaDoResultado = objectMapper.readValue(resultContent, Categoria.class);
        Assert.assertEquals(categoria, categoriaDoResultado);
    }

    @Test
    public void getAllCategoria() throws Exception {
        mockMvc.perform(get("/categoria").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andReturn();
    }

}
