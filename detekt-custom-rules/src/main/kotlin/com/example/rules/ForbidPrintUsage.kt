package com.example.rules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtFile

class ForbidPrintUsage(config: Config) : Rule(config) {

    override val issue = Issue(
        ISSUE_ID,
        Severity.CodeSmell,
        ISSUE_DESCRIPTION,
        Debt.FIVE_MINS
    )

    override fun visitKtFile(file: KtFile) {
        super.visitKtFile(file)

        if (file.text.contains(REGEX)) {
            report(
                CodeSmell(
                    issue,
                    Entity.atPackageOrFirstDecl(file),
                    message = "The file ${file.name} has a 'print' or 'println' function usage. " +
                        "It must have to be removed."
                )
            )
        }
    }

    private companion object {
        val REGEX = Regex("(print\\(|println\\()")
        const val ISSUE_ID = "ForbidPrintUsage"
        const val ISSUE_DESCRIPTION = "This rule reports a file with a print() or println() function usage"
    }
}
