# 💎 Caça ao Tesouro - Android Premium

Um aplicativo Android nativo desenvolvido como parte da disciplina de Soluções Mobile no Centro Universitário SATC. O projeto utiliza as tecnologias mais modernas de desenvolvimento Android, incluindo **Jetpack Compose** e **Clean Architecture**.

## 🚀 Tecnologias e Conceitos

-   **Linguagem:** Kotlin
-   **Toolkit UI:** Jetpack Compose
-   **Navegação:** Jetpack Navigation (NavHost & NavController)
-   **Arquitetura:** Clean Architecture (Domain, Data, Presentation)
-   **State Management:** StateFlow & ViewModels
-   **Design:** Material 3 com efeitos de Glassmorphism e Gradient backgrounds.

## 🏗️ Estrutura do Projeto

O projeto segue os princípios de **Clean Architecture** para garantir testabilidade e manutenção:

-   **`domain`**: Contém as regras de negócio puras (Entidades e Casos de Uso).
-   **`data`**: Implementação dos repositórios e fontes de dados (DataSources).
-   **`presentation`**: Camada de interface, contendo ViewModels, State management e os Composables (Screens & Components).
-   **`ui.theme`**: Definições de cores, tipografia e temas personalizados.

## ✨ Funcionalidades

-   [x] **Navegação Fluida:** Transições animadas entre telas.
-   [x] **Validação de Respostas:** O usuário só avança se acertar a charada.
-   [x] **Rastreamento de Tempo:** O app calcula e exibe o tempo total gasto na busca.
-   [x] **Pilha de Navegação (Back Stack):** Gerenciamento inteligente do botão de voltar para uma experiência nativa fluida.
-   [x] **Design Premium:** Interface moderna com suporte a temas e efeitos visuais avançados.

## 🛠️ Como Executar

### Pré-requisitos
-   Android Studio Iguana (ou superior)
-   Android SDK 34
-   Gradle 8.2+

### Passos
1.  Clone este repositório.
2.  Abra o projeto no **Android Studio**.
3.  Aguarde a sincronização do Gradle.
4.  Execute no emulador ou dispositivo físico.

## 📖 Instruções do Exercício

O objetivo é navegar por pistas (charadas) para encontrar o tesouro escondido.
1.  Inicie na **Tela Home**.
2.  Resolva as **3 Charadas** (Pistas).
3.  Encontre o **Tesouro** e veja seu tempo total!

---
**Professor:** Thyerri Mezzari  
**Instituição:** Centro Universitário SATC
