package io.codecrow.mage.data.service

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

object FCMUtils {
    val TAG = FCMUtils.javaClass.simpleName
    fun subscribeToChannel(channelId : String) {
        Firebase.messaging.subscribeToTopic(channelId).addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
            }
    }

    fun unsubscribeFromChannel(channelId : String) {
        Firebase.messaging.unsubscribeFromTopic(channelId).addOnCompleteListener { task ->
            var msg = "Unsubscribed"
            if (!task.isSuccessful) {
                msg = "Unsubscribe failed"
            }
            Log.d(TAG, msg)
        }
    }

}