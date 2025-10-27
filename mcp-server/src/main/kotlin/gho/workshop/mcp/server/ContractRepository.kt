package gho.workshop.mcp.server

import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.UUID

@Component
class ContractRepository {
    private val contract =
        mapOf<Int, Contract>(
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

    fun getContractById(id: Int) = contract[id]
}

data class Contract(
    val contractId: Int,
    val contractStartDate: ZonedDateTime,
    val contractEndDate: ZonedDateTime,
    val noticePeriodInDays: Int,
    val contrator: String,
)
