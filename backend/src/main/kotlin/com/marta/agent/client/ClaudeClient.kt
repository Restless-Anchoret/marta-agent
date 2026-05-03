package com.marta.agent.client

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ClaudeClient(
    @param:Value($$"${claude.api-key}") private val apiKey: String
) {
    private val client: AnthropicClient = AnthropicOkHttpClient.builder()
        .apiKey(apiKey)
        .build()

    fun sendMessage(message: String): String {
        val response = client.messages().create(
            MessageCreateParams.builder()
                .model(Model.CLAUDE_OPUS_4_7)
                .maxTokens(1024L)
                .addUserMessage(message)
                .build()
        )
        return response.content()
            .firstNotNullOfOrNull { block -> block.text().orElse(null)?.text() }
            ?: ""
    }
}
