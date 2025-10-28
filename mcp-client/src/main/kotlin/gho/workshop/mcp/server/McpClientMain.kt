package gho.workshop.mcp.server

import com.azure.ai.openai.OpenAIServiceVersion
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.api.Advisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.ai.chat.memory.MessageWindowChatMemory
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider
import org.springframework.ai.model.azure.openai.autoconfigure.AzureOpenAIClientBuilderCustomizer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

fun main(args: Array<String>) {
    SpringApplication.run(McpClientConfiguration::class.java, *args)
}

@SpringBootApplication
class McpClientConfiguration {
    @Bean
    fun chatMermory(): ChatMemory = MessageWindowChatMemory.builder().build()

    @Bean
    fun chatMemoryAdvisor(chatMemory: ChatMemory): Advisor = MessageChatMemoryAdvisor.builder(chatMemory).build()

    @Bean
    fun chatClient(
        chatModel: ChatModel,
        toolCallbackProvider: SyncMcpToolCallbackProvider,
        chatMemoryAdvisor: Advisor,
    ): ChatClient =
        ChatClient
            .builder(chatModel)
            .defaultToolCallbacks(toolCallbackProvider)
            .defaultAdvisors(
                chatMemoryAdvisor,
            ).build()

    @Bean
    fun AzureOpenAIClientBuilderCustomizer(): AzureOpenAIClientBuilderCustomizer =
        AzureOpenAIClientBuilderCustomizer { clientBuilder ->
            clientBuilder.serviceVersion(OpenAIServiceVersion.V2024_06_01)
        }
}
