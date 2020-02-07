package com.kyleriedemann.drinkingbuddy.data.source.local

import com.kyleriedemann.drinkingbuddy.data.models.Notification
import com.kyleriedemann.drinkingbuddy.data.source.NotificationDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.kyleriedemann.drinkingbuddy.data.Result.Success
import com.kyleriedemann.drinkingbuddy.data.Result.Error

class NotificationLocalDataSource internal constructor(
    private val notificationDao: NotificationDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): NotificationDataSource {
    override suspend fun getNotifications() = withContext(ioDispatcher) {
        return@withContext try {
            Success(notificationDao.getNotifications())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getNotificationById(notificationId: String) = withContext(ioDispatcher) {
        try {
            val notification = notificationDao.getNotificationById(notificationId)
            if (notification != null) {
                return@withContext Success(notification)
            } else {
                return@withContext Error(Exception("Notification not found"))
            }
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun insertNotification(notification: Notification) = withContext(ioDispatcher) {
        notificationDao.insertNotification(notification)
    }

    override suspend fun updateNotification(notification: Notification) = withContext(ioDispatcher) {
        notificationDao.updateNotification(notification)
    }

    override suspend fun markRead(notificationId: String, read: Boolean) = withContext(ioDispatcher) {
        notificationDao.markRead(notificationId, read)
    }

    override suspend fun deleteNotification(notification: Notification) = withContext(ioDispatcher) {
        notificationDao.deleteNotification(notification)
    }

    override suspend fun deleteNotificationById(notificationId: String) = withContext(ioDispatcher) {
        notificationDao.deleteNotificationById(notificationId)
    }

    override suspend fun clearNotifications() = withContext(ioDispatcher) {
        notificationDao.clearNotifications()
    }
}