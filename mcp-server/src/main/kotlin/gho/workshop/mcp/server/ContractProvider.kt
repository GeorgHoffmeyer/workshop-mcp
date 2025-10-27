package gho.workshop.mcp.server

import org.springaicommunity.mcp.annotation.McpTool
import org.springaicommunity.mcp.annotation.McpToolParam
import org.springframework.stereotype.Component

@Component
class ContractProvider(
    private val contractRepository: ContractRepository
) {

    @McpTool(
        name = "GetContractById",
        title = "Get the contract by it's id",
        description = "Get the contract by it's id",
    )
    fun getContractById(
        @McpToolParam(
            description = "The contract id",
            required = true,
        ) contractId: Int
    ) = contractRepository.getContractById(contractId)
}