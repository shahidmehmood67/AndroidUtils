package com.sm.android.utils.utils

object AppConstants {
    const val SETTING_DATA = "SETTING_DATA"
    const val HAPTIC_FEEDBACK = "haptic_feedback"
    const val SHOW_NOTIFICATION = "show_notification"
    const val TOUCH_SOUND = "sound_feedback"
    const val FLASH_ON_START = "flash_on_start"
    const val BIG_FLASH_AS_SWITCH = "big_flash_as_switch"
    const val SHAKE_TO_LIGHT = "shake_to_light"
    const val SHAKE_SENSITIVITY = "shake_sensitivity"
    const val MIN_TIME_BETWEEN_SHAKES_MILLIsECS = 1000
    const val CALL_NOTIFICATION = "call_notification"
    const val FLASH_EXIST = "flash_exist"
    const val UPDATE_REQUEST_CODE = 123
    const val IMAGE_MIME_TYPE = "text/plain"
    const val SHARE_BY = "Share by"
    const val PACKAGE = "package"
    const val MARKET_DETAILS_ID = "market://details?id="

    var FLASHRUNNING = false

    //intents
    const val GOOGLE_MESSAGING = "com.google.android.apps.messaging"

    //notification
    const val NOTIFICATION_CHANNEL_ID = "com.lahsuak.flashlight.FLASHLIGHT"
    const val NOTIFICATION_CHANNEL_NAME = "Flashlight Channel"
    const val NOTIFICATION_DETAILS = "Flashlight channel"

    const val NOTIFICATION_ON_APPS = "notification_on_apps"

    //foreground service notification
    const val PLAY = "Play"
    const val PAUSE = "Pause"
    const val ACTION_NAME = "action_name"

    //blink delay
    var BLINK_DELAY = 300L
    const val TIME_BETWEEN_FLASH = 300L
    const val BLINK_DELAY_RHYTHM = 1000L

    //flashing type
    const val FLASHING_TYPE_CONTINUOUS = "continuous"
    const val FLASHING_TYPE_RHYTHM  = "rhythm"

    //do not disturb
    var START_TIME = "start_time"
    var START_TIME_HOUR = 20
    var START_TIME_MINT = 30
    var END_TIME = "end_time"
    var END_TIME_HOUR = 8
    var END_TIME_MINT = 30

    //battery save
    var BATTERY_PERCENT = 10

}

