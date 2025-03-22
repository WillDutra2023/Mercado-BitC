# Mercado BitC

<img src="[imagem.png](https://www.google.com/url?sa=i&url=https%3A%2F%2Fandroid-developers.googleblog.com%2F2022%2F05%2Fwhats-new-in-jetpack-compose.html&psig=AOvVaw1qkaNv47NxZyitX1uxFtfh&ust=1742765194446000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCIDeqLTQnowDFQAAAAAdAAAAABAE)" alt="Jetpack Compose">

> O projeto busca combinar ferramentas populares do Android e demonstrar as melhores práticas de desenvolvimento, utilizando uma tecnologia atualizada, como Compose, Kotlin Flow e Koin.
O aplicativo de exemplo organiza sua camada de apresentação seguindo o padrão de arquitetura MVVM Clean Architecture.

## Arquitetura
*	Arquitetura Modera
    * [MVVM Clean Architecture](https://medium.com/@ami0275/mvvm-clean-architecture-pattern-in-android-with-use-cases-eff7edc2ef76) - Uma arquitetura reativa e em camadas..
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel),
    * [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
*	UI
    * [Compose](https://developer.android.com/jetpack/compose) Framework de UI declarativo.
    * [Material design](https://material.io/design)

## Tech Stack

- [Kotlin](https://kotlinlang.org/) - 100% de cobertura
- [Compose](https://developer.android.com/jetpack/compose)
      - [Material](https://developer.android.com/jetpack/androidx/releases/compose-material) - Construa interfaces no Jetpack Compose com componentes prontos do Material Design.
      - [Foundation](https://developer.android.com/jetpack/androidx/releases/compose-foundation) - Fornece blocos prontos para a criação de apps no Jetpack Compose e permite estender o sistema de design.
      - [UI](https://developer.android.com/jetpack/androidx/releases/compose-ui) - Componentes fundamentais para interface, incluindo layout, desenho e entrada de dados.
      - [ConstraintLayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout) - Adiciona funcionalidades do ConstraintLayout ao Jetpack Compose.
      - [Lifecycle-ViewModel](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Executa ações com base no ciclo de vida de atividades e fragmentos.
      - [Lottie](https://github.com/airbnb/lottie/blob/master/android-compose.md) - Biblioteca para renderizar animações do Adobe After Effects exportadas como JSON.
      - [Glide](https://bumptech.github.io/glide/int/compose.html) - Biblioteca eficiente de carregamento de imagens para Android, focada em rolagem suave (Google).

- Injeção de Dependências (DI)
    - [Koin](https://insert-koin.io/docs/quickstart/android/) - Framework leve e eficiente para injeção de dependências em aplicações Kotlin. Neste projeto, utilizamos Koin, mas também é possível usar Hilt e Dagger.

- [Jetpack](https://developer.android.com/jetpack)
      - [AndroidX](https://developer.android.com/jetpack/androidx)  - Evolução da antiga Support Library, que não é mais mantida.
      - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Executa ações conforme mudanças no ciclo de vida dos componentes.
      - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)  - Gerencia dados da interface do usuário de forma ciente do ciclo de vida, garantindo persistência em mudanças de configuração.
      - [Navigation](https://developer.android.com/guide/navigation) - Gerencia a navegação dentro do app.

- Outros
      - [retrofit](https://square.github.io/retrofit/)  for - Biblioteca para requisições de rede.
      - [Http-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Registra logs de requisições e respostas HTTP.
      - [Flow](https://developer.android.com/kotlin/flow) - Fl - Baseado em corrotinas, permite emitir múltiplos valores ao longo do tempo.
      - [Material Design](https://material.io/develop/android/docs/getting-started/) - Guia de design para criar interfaces atraentes.
      - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Suporte para programação assíncrona em Kotlin.
      - [Gson](https://github.com/google/gson) - Biblioteca para manipulação de JSON em Kotlin e Java.

## 📝 Licença

Esse projeto está sob licença. Veja o arquivo [LICENÇA](LICENSE.md) para mais detalhes.
