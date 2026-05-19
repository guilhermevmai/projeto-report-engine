package com.report_engine.api.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.report_engine.api.dto.response.UserReportDtoResponse;
import com.report_engine.api.exceptions.GenericException;
import com.report_engine.api.model.UsersReport;
import com.report_engine.api.model.enums.UserStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {


    public String readWithAllLines(MultipartFile file) {
        try {
            // ERRO 1: Carrega o arquivo inteiro para um array de bytes na RAM.
            // Se o arquivo tiver 200MB, 200MB serão alocados na Heap imediatamente.
            byte[] bytes = file.getBytes();

            // ERRO 2: Converte o arquivo inteiro em uma String.
            // Ocupa ainda mais memória (já que String em Java tem overhead).
            String conteudo = new String(bytes);

            // ERRO 3: Divide a String em uma lista de linhas.
            // Isso cria um objeto para CADA linha do arquivo.
            // Se o arquivo tiver 1 milhão de linhas, você criou 1 milhão de objetos na Heap.
            List<String> linhas = Arrays.asList(conteudo.split("\n"));

            UserReportDtoResponse response = new UserReportDtoResponse((long) linhas.size(), (long) linhas.size(), 0L, false, null);

            return "Foi";
        } catch (Exception e) {
            throw new GenericException(e.getCause());
        }
    }

    public String processWithStreaming(MultipartFile file) {

        return null;
    }

    public UserReportDtoResponse processFileWithBufferedReader(MultipartFile file) {
        Long contadorLinhas = 0L;
        Long linhasSucesso = 0L;
        Long linhasComErro = 0L;
        List<String> reasons = new ArrayList<>();

        CsvMapper mapper = new CsvMapper();
        mapper.registerModule(new JavaTimeModule());
        CsvSchema schema = mapper.schemaFor(UsersReport.class).withoutHeader();

        try (InputStream inputStream = file.getInputStream()) {
            MappingIterator<UsersReport> mappingIterator = mapper
                    .readerFor(UsersReport.class)
                    .with(schema)
                    .readValues(inputStream);

            while (mappingIterator.hasNext()) {
                contadorLinhas++;
                try {
                    UsersReport usersReport = mappingIterator.next();
                    linhasSucesso++;
                } catch (Exception e) {
                    e.printStackTrace();
                    linhasComErro++;
                    reasons.add(e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new GenericException("An error occured when trying to read the file.", e);
        }
        boolean isWarning = linhasComErro > 1;
        UserReportDtoResponse response = new UserReportDtoResponse(contadorLinhas, linhasSucesso, linhasComErro, isWarning, reasons);
        return response;
    }
}
