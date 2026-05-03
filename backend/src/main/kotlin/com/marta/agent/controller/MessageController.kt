package com.marta.agent.controller

import com.marta.agent.service.AgentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class MessageRequest(val message: String)
data class MessageResponse(val message: String)

@RestController
@RequestMapping("/api/message")
class MessageController(private val agentService: AgentService) {

    @PostMapping
    fun message(@RequestBody request: MessageRequest): MessageResponse =
        MessageResponse(agentService.processMessage(request.message))
}
