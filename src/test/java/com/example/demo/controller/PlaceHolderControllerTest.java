package com.example.demo.controller;

import com.example.demo.WebTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaceHolderControllerTest extends WebTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void struct() throws Exception {
        String uri = "/_placeholder/struct";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("string", "hello")
                .param("i32", "123")
                .accept(MediaType.APPLICATION_ATOM_XML))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat("response status should be OK", status, equalTo(200));
        String content = mvcResult.getResponse().getContentAsString();
        assertThat("response content should be match",
                content,
                equalTo("<req><string>hello</string><i32>123</i32></req>")
        );
    }

}
