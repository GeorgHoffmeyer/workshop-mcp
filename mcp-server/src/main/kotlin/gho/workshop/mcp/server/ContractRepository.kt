package gho.workshop.mcp.server

import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class ContractRepository {
    private val contracts =
        mutableMapOf<Int, Contract>(
            1 to
                Contract(
                    1,
                    ZonedDateTime.parse("2021-07-07T00:00:00+00:00"),
                    ZonedDateTime.parse("2026-07-07T00:00:00+00:00"),
                    30,
                    "Max Mustermann",
                ),
            2 to
                Contract(
                    1,
                    ZonedDateTime.parse("2025-01-07T00:00:00+00:00"),
                    ZonedDateTime.parse("2025-12-31T00:00:00+00:00"),
                    30,
                    "Erika Mustermann",
                ),
        )

    fun getContractById(id: Int) = contracts[id]

    fun addContract(
        contractStartDate: ZonedDateTime,
        contractEndDate: ZonedDateTime,
        noticePeriodInDays: Int,
        contractor: String,
    ): Contract {
        val contract =
            Contract(
                contractId = contracts.keys.max() + 1,
                contractStartDate = contractStartDate,
                contractEndDate = contractEndDate,
                noticePeriodInDays = noticePeriodInDays,
                contractor = contractor,
            )

        contracts[contract.contractId] = contract

        return contract
    }
}

data class Contract(
    val contractId: Int,
    val contractStartDate: ZonedDateTime,
    val contractEndDate: ZonedDateTime,
    val noticePeriodInDays: Int,
    val contractor: String,
)
