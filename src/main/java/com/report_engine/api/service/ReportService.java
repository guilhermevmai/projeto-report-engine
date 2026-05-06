package com.report_engine.api.service;

import com.report_engine.api.dto.response.UserReportDtoResponse;
import com.report_engine.api.model.UsersReport;
import com.report_engine.api.model.enums.UserStatus;
import com.report_engine.api.model.enums.benchmark.ReadFilesStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {


    public UserReportDtoResponse readWithAllLines(MultipartFile file) throws IOException {
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

        UserReportDtoResponse response = new UserReportDtoResponse((long) linhas.size(), (long) linhas.size(), 0L);

        return response;
    }

    public UserReportDtoResponse readWithBufferedReader(MultipartFile file) throws IOException {
        Long contadorLinhas = 0L;
        Long linhasSucesso = 0L;
        Long linhasComErro = 0L;
        String line;
        String split = ",";
        StringBuilder mensagem = new StringBuilder();


        BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        while ((line = reader.readLine()) != null) {
            contadorLinhas++;
            String[] data = line.split(split);
            try {
                UsersReport usersReport = transformLineToObject(data);
                linhasSucesso++;
            } catch (Exception e) {
                linhasComErro++;
            }
        }

        UserReportDtoResponse response = new UserReportDtoResponse(contadorLinhas, linhasSucesso, linhasComErro);
        return response;
    }

    private UsersReport transformLineToObject(String[] data) {

        UsersReport usersReport = new UsersReport(
                Integer.parseInt(data[0]),
                data[1],
                Long.parseLong(data[2]),
                LocalDate.parse(data[3]),
                UserStatus.valueOf(data[4].toUpperCase())
        );

        return usersReport;
    }
}
