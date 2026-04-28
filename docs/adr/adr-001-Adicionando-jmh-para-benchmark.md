# ADR-001: Adotando JMH para Diagnóstico de Performance

**Status:** Aceito

**Data:** 28/04/2026
## Contexto

O projeto visa demonstrar o impacto do uso de memória (Heap) em operações de I/O. Precisamos de uma forma precisa e estatisticamente confiável para medir o impacto de diferentes estratégias de processamento de arquivos (ex: `readAllLines` vs `Stream`).

## Decisão

Adotamos o **JMH (Java Microbenchmark Harness)** como ferramenta oficial de medição. A execução será programática via `BenchmarkService` para permitir integração com uma interface Web (Angular).

## Consequências

- **Positivas:**
    
    - Medições estatisticamente corretas, eliminando o erro causado pelo JIT (compilação em tempo de execução).
        
    - Capacidade de isolar e comparar algoritmos com precisão.
        
- **Negativas:**
    
    - Aumento da complexidade inicial do projeto.
        
    - Potencial interferência no consumo de recursos da aplicação ao rodar em tempo real (risco de _Observer Effect_).