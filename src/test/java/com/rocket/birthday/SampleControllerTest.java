package com.rocket.birthday;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SampleControllerTest {

  @Autowired MockMvc mockMvc;

  @Test
  void getSampleByIdTest() throws Exception {
    String sampleId = "aaa";

    mockMvc.perform(
        get("/api/v1/samples/{sampleId}", sampleId)
    )
     .andExpect(status().isOk())
     .andExpect(jsonPath("sampleId", is(sampleId)))
     .andExpect(jsonPath("name", is("sample")))
     .andDo(
          document("sample",
              resourceDetails()
                  .tag("Sample")
                  .description("Get a sample by id"),
              responseFields(
                  fieldWithPath("sampleId").description("The sample identifier."),
                  fieldWithPath("name").description("The name of sample.")
              )
          )
      );
  }
}
