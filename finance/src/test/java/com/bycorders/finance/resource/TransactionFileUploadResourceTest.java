package com.bycorders.finance.resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class TransactionFileUploadResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test__expectedSuccessUploadWhenFileIsCorrect() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/transactions-file-upload")
                .file(mockMultipartFile("src/test/data/cnab.txt"))
                .characterEncoding("UTF_8")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void test__expectedError415UploadWhenFileTypeIsWrong() throws Exception {

        MockMultipartFile file= new MockMultipartFile("file", "cnab.pdf", MediaType.APPLICATION_PDF_VALUE,"".getBytes());

        mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/transactions-file-upload")
                .file(file).contentType(MediaType.APPLICATION_PDF_VALUE)
                .accept(MediaType.TEXT_PLAIN)).andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType());

    }

    private MockMultipartFile mockMultipartFile (String path) throws IOException {
        File filePath = new File(path);
        FileInputStream fileInputStream = new FileInputStream(filePath);

        MockMultipartFile file= new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
                fileInputStream.readAllBytes()
        );

        return file;
    }
}
