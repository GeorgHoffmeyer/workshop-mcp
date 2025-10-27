package gho.workshop.mcp.server

import org.springaicommunity.mcp.annotation.McpTool
import org.springaicommunity.mcp.annotation.McpToolParam
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class ContractProvider(
    private val contractRepository: ContractRepository,
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
        ) contractId: Int,
    ) = contractRepository.getContractById(contractId)

    @McpTool(
        name = "AddNewContract",
        title = "Add the contract to the database",
        description = "Add the contract to the database",
    )
    fun addContract(
        @McpToolParam(
            description = "The contract start date as ISO Date (e.g. 2025-01-01T00:00:00Z)",
            required = true,
        ) contractStartDate: ZonedDateTime,
        @McpToolParam(
            description = "The contract end date as ISO Date (e.g. 2025-12-31T00:00:00Z)",
            required = true,
        ) contractEndDate: ZonedDateTime,
        @McpToolParam(
            description = "Contract notice period in days (default is 30 days)",
            required = false,
        ) noticePeriodInDays: Int = 30,
        @McpToolParam(
            description = "Name of the contractor",
            required = true,
        ) contractor: String,
    ): Contract =
        contractRepository.addContract(
            contractStartDate = contractStartDate,
            contractEndDate = contractEndDate,
            noticePeriodInDays = noticePeriodInDays,
            contractor = contractor,
        )
}
