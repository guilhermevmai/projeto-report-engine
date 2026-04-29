# ADR-002: Padronização de API, Data Binding e Tratamento de Erros
**Status:** Aceito
**Data:** 29/04/2026
## Contexto

O projeto necessita de uma forma robusta e padronizada para receber os parâmetros enviados pelas requisições e comunicar de forma clara os erros.

## Decisão
Foi adotado os seguintes padrões:

1. **Data Binding com Enums:** Utilização de `Enums` para tipagem de parâmetros, com `WebMvcConfigurer` para conversão global. Isso garante a tipagem forte e evita a proliferação de strings mágicas no código.

2. **Centralização de Erros:** Implementação de `@ControllerAdvice` para capturar exceções de infraestrutura (`MethodArgumentTypeMismatchException`, `MissingServletRequestParameterException`).

3. **DTOs Imutáveis:** Uso de `Records` (Java 17+) para a estrutura de respostas de erro (`ErrorResponseDTO`), garantindo imutabilidade e concisão.

## Consequências

- **Positivas:**
    
    - **Segurança:** O Controller recebe apenas dados validados.
        
    - **Manutenibilidade:** Qualquer novo parâmetro ou erro novo segue o mesmo padrão centralizado.
        
    - **DX (Developer Experience):** O front-end recebe respostas padronizadas em JSON com mensagens claras.
        
- **Negativas:**
    
    - Aumento de "boilerplate" inicial para configurar o tratamento global de exceções.