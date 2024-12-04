// Импорт необходимых классов для компиляции Kotlin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


// Объект для управления версиями зависимостей
object Versions {
    const val ROOT_PROJECT_VERSION = "0.0.1-SNAPSHOT"
    const val SPRING_DOC_VERSION = "2.1.0"
    const val JJWT_API_VERSION = "0.11.5"
    const val JVM_VERSION = "17"
    const val MOCKITO_VERSION = "2.2.0"
}

// Подключение необходимых плагинов
plugins {
    // Плагины для Kotlin
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"

    // Плагины для Spring Boot и управления зависимостями
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

// Настройки группы и версии проекта
group = "ru.itech"
version = Versions.ROOT_PROJECT_VERSION

// Настройка версии Java
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Репозитории для поиска зависимостей
repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

// Дополнительные свойства
extra["springCloudVersion"] = "2023.0.0"

// Управление зависимостями
dependencies {

    // Spring Boot Starters для бд, web безопасности и пр.
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-mail")




    // Jackson для обработки JSON в Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // SpringDoc для автоматической генерации документации OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-common:${Versions.SPRING_DOC_VERSION}")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.SPRING_DOC_VERSION}")


    // apache camel smpp для отправки sms
    implementation("org.apache.camel:camel-core:4.4.4")
    implementation("org.apache.camel:camel-smpp:4.4.4")


    // Тестовые зависимости
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO_VERSION}")
}



// Настройки компилятора Kotlin
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict") // cтрогая обработка аннотаций nullability
        jvmTarget = Versions.JVM_VERSION // версия JVM
    }
}

// Настройки тестовых задач
tasks.withType<Test> {
    useJUnitPlatform() // JUnit Platform для тестов
}


// Отключение стандартной задачи jar, если она не нужна
tasks.jar {
    enabled = false
}
