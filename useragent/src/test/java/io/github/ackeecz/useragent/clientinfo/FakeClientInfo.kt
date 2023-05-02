package io.github.ackeecz.useragent.clientinfo

internal class FakeClientInfo : ClientInfo {

    override var appName: String = "appName"

    override var appVersionName: String = "1.0.0"

    override var appVersionCode: Int = 1

    override var packageName: String = "cz.ackee.app"

    override var androidVersion: String = "13"

    override var deviceModel: String = "SM-A310F"
}
