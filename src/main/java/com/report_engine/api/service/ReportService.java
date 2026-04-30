package com.report_engine.api.service;

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


    public String processarArquivo(MultipartFile file, ReadFilesStrategies chosedStrategy) throws IOException {
        switch (chosedStrategy) {
            case ALL_LINES :
                return readWithAllLines(file);
            case STREAM:
                return readWithBufferedReader(file);
            default:
                return String.format("None strategies found by the following name: %s", chosedStrategy.toString());
        }
    }

    private String readWithAllLines(MultipartFile file) throws IOException {
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

        String mensagem = "Arquivo processado. Total de linhas: " + linhas.size();

        return mensagem;
    }

    private String readWithBufferedReader(MultipartFile file) throws IOException {
        int contadorLinhas = 0;
        String line;
        String split = ",";
        StringBuilder mensagem = new StringBuilder();
        int linhasComErro = 0;

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(split);
                try {
                    UsersReport usersReport = transformLineToObject(data);
                    System.out.println(contadorLinhas);
                    contadorLinhas++;
                } catch (Exception e) {
                    linhasComErro++;
                }
            }
        mensagem.append(String.format("Arquivo processado. Total de linhas: %d", contadorLinhas));
        if (linhasComErro > 0) {
            mensagem.append(String.format("\nAlgumas linhas não foram processadas. Total de linhas fora do padrão: %d", linhasComErro));
        }
        return mensagem.toString();
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
