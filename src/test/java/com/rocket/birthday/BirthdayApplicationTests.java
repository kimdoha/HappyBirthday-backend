package com.rocket.birthday;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class BirthdayApplicationTests {

  private MockMvc mockMvc;

  @BeforeEach
  void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation){
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .apply( MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
        .build();
  }

//	@Test
//	void contextLoads() {
//	}

}
