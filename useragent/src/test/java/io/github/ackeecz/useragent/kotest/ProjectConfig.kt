package io.github.ackeecz.useragent.kotest

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

/**
 * This class gathers project-wide Kotest tests config and is auto-located by Kotest during test run
 */
internal class ProjectConfig: AbstractProjectConfig() {

    override val isolationMode = IsolationMode.InstancePerLeaf
}
