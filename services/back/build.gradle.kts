// Use [./gradlew bootRun -Pprod] to pass a 'prod' property value
// TODO Open API
var currentProfile = "dev"
if (project.hasProperty("test")) {
	currentProfile = "test"
} else if (project.hasProperty("prod")) {
	currentProfile = "prod"
}
System.setProperty("spring.profiles.active", currentProfile)

plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.liquibase.gradle") version "2.2.0"
	kotlin("jvm") version "1.9.22"
}

group = "com.mcfly"
version = "0.0.1-SNAPSHOT"

java {
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("org.liquibase:liquibase-core")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	implementation("org.projectlombok:lombok-mapstruct-binding:0.1.0")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.2.RELEASE")
	implementation("org.webjars:bootstrap:5.3.0")
	implementation("org.webjars:webjars-locator:0.47")
	implementation("org.webjars.npm:bootstrap-icons:1.10.5")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("com.h2database:h2:2.2.220")
	implementation(kotlin("stdlib-jdk8"))

	when (currentProfile) {
		"dev" -> {
			implementation("com.github.javafaker:javafaker:1.0.2")
		}
		"test" -> {
			implementation("com.github.javafaker:javafaker:1.0.2") {
				exclude("org.yaml", "snakeyaml")
			}
		}
		"prod" -> {
			implementation("com.github.javafaker:javafaker:1.0.2")
		}
	}
}

dependencyManagement {
	imports {
		if (currentProfile == "test") {
			mavenBom("org.yaml:snakeyaml:2.1")
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}