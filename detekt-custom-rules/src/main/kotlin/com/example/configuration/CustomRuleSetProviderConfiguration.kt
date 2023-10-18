package com.example.configuration

import com.example.rules.ForbidPrintUsage
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CustomRuleSetProviderConfiguration : RuleSetProvider {

    override val ruleSetId: String = RULE_SET_ID

    override fun instance(config: Config) = RuleSet(ruleSetId, listOf(ForbidPrintUsage(config)))

    private companion object {
        const val RULE_SET_ID = "detekt-custom-rules"
    }
}
