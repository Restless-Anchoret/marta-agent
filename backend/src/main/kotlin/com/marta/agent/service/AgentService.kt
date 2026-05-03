package com.marta.agent.service

import org.springframework.stereotype.Service

@Service
class AgentService {

    fun processMessage(message: String): String = message
}
