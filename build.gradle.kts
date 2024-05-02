plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

group = "org.beaconstudios"
version = "1.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://repo.papermc.io/repository/maven-public/") // Paper
    maven(url = "https://repo.smrt-1.com/releases") // LushLib (Dependencies)
    maven(url = "https://repo.smrt-1.com/snapshots") // LushLib
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${findProperty("minecraftVersion")}-R0.1-SNAPSHOT")
    compileOnly(files("libs/enchanted-bosses-${findProperty("enchantedBossesVersion")}.jar"))
    compileOnly(files("libs/SkillsLibrary-${findProperty("skillslibraryVersion")}-all.jar"))
    implementation("org.lushplugins:LushLib:${findProperty("lushlibVersion")}")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    shadowJar {
        minimize()

        val folder = System.getenv("pluginFolder_1-20")
        if (folder != null) destinationDirectory.set(file(folder))
        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    processResources{
        expand(project.properties)

        inputs.property("version", rootProject.version)
        filesMatching("plugin.yml") {
            expand("version" to rootProject.version)
        }
    }
}