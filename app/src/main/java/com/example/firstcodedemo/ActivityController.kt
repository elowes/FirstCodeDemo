package com.example.firstcodedemo

import android.app.Activity

class ActivityController {
    companion object {
        private val activities: ArrayList<Activity> = ArrayList()

        fun addActivity(activity: Activity) {
            activities.add(activity)
        }

        fun removeActivity(activity: Activity) {
            activities.remove(activity)
        }

        fun finishAll() {
            for (activity in activities) {
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }
            activities.clear()
        }
    }
}