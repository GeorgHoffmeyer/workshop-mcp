package gho.workshop.mcp.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

fun main(args: Array<String>) {
    SpringApplication.run(McpServerConfig::class.java, *args)
}

@SpringBootApplication
class McpServerConfig