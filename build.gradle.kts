import io.spring.gradle.dependencymanagement.org.codehaus.plexus.util.StringUtils.clean

plugins {
    java
    id("org.springframework.boot") version "3.3.5-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.google.protobuf") version "0.9.4"
}

group = "com.wanted.clone"
version = "0.0.1-SNAPSHOT"
val protobufVersion = "4.27.2"
val protobufPluginVersion = "0.9.4"
val grpcVersion = "1.65.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    compileOnly("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    // Retrofit and OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.0")
    implementation("com.google.code.gson:gson")
//    implementation("com.fasterxml.jackson.core:jackson-core:2.13.2")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // ArchUnit
    implementation("com.tngtech.archunit:archunit:1.3.0")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.3.0")

    // mapstruct 설정
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    // grpc 프로토콜 버터를 사용하기 위한 핵심 라이브러리 (Protobuf 메시지의 직렬화 및 역직렬화를 지원합니다.)
    implementation("com.google.protobuf:protobuf-java-util:${protobufVersion}")
    implementation("com.google.protobuf:protobuf-java:${protobufVersion}")

    // grpc 서버, 클라이언트 설정
    implementation("net.devh:grpc-spring-boot-starter:3.1.0.RELEASE") // Spring Boot와 gRPC의 통합을 간편하게 도와주는 스타터
    implementation("io.grpc:grpc-netty-shaded:${grpcVersion}") // Netty Shaded 사용(gRPC 서버와 클라이언트의 Netty 전송 계층을 제공)
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")     // Protobuf 메시지와 gRPC의 통합을 지원
    implementation("io.grpc:grpc-stub:${grpcVersion}")         // gRPC 클라이언트 스텁을 생성
    compileOnly("org.apache.tomcat:annotations-api:6.0.53")    // 이걸 추가해야 gRPC 컴파일시 javax 어노테이션 오류가 발생하지 않는다.
}

tasks.withType<Test> {
    useJUnitPlatform()
}

protobuf {
    // Protobuf 컴파일러를 지정하여 .proto 파일을 컴파일합니다.
    protoc {
        artifact = "com.google.protobuf:protoc:${protobufVersion}"
    }
    // gRPC 플러그인을 설정하여 Protobuf 파일로부터 gRPC 관련 코드를 생성합니다.
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }
    // 모든 프로토콜 버퍼 작업에 대해 gRPC 플러그인을 적용합니다.
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc") {}
            }
        }
    }
}