package gho.workshop.mcp.server

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.ai.chat.client.ChatClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatService(
    private val chatClient: ChatClient,
) {
    private val logger = KotlinLogging.logger {}

    @PostMapping("/chat")
    fun chatEndpoint(
        @RequestBody questionRequest: ChatRequest,
    ): ResponseEntity<ChatResponse> {
        logger.info { "Received question: ${questionRequest.question}" }
        val answer =
            chat(
                questionRequest.question,
            )
        logger.info { "Received answer: $answer" }
        return ResponseEntity(ChatResponse(answer), HttpStatus.OK)
    }

    private fun chat(question: String): String =
        chatClient
            .prompt(question)
            .call()
            .content()

    private fun clearHistory() {
        // chatMemory.clear()
    }
}

data class ChatRequest(
    val question: String,
)

data class ChatResponse(
    val answer: String,
)
