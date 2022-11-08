import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("convention.libraries")
}

/**
 * Exists because `compile` task is ambiguous in projects with jvm and android modules combined
 */
val compileAllTask: TaskProvider<Task> = tasks.register("compileAll") {
    description = "Compiles all available modules in all variants"

    dependsOn(tasks.withType<KotlinCompile>())
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = Libs.javaVersion.toString()

        allWarningsAsErrors = false
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
    }
}
