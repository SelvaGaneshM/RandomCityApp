package com.selvaganesh.randomcityapp.common.utils

import android.content.Context
import android.content.SharedPreferences

@Suppress("UNCHECKED_CAST")
class SharedPrefs(private val context: Context) {
    companion object {
        private const val PREF = "MyAppPrefName"
        private const val PREF_TOKEN = "user_token"
        private const val PREF_USER_ID = "user_id"
        private const val PROFILE_TYPE = "profile_type"
        private const val MOBILE_NUMBER = "mobile_number"
        private const val FULL_NAME = "full_name"
        private const val PROFILE_CREATED = "profile_created"
        private const val PROFILE_ID = "profile_id"
        private const val TRIP_BOOKING_ID = "trip_booking_id"
        private const val FIREBASE_TOKEN = "firebase_token"
        private const val STAFF_ID = "staff_Id"
        private const val STAFF_PROFILE_ID = "staff_Profile_Id"
        private const val STAFF_ROLE = "staff_Role"
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)


    fun saveToken(token: String) {
        put(PREF_TOKEN, token)
    }

    fun getToken(): String {
        return get(PREF_TOKEN, String::class.java)
    }

    fun saveUserID(agentID: String) {
        put(PREF_USER_ID, agentID)
    }

    fun getUserId(): String {
        return get(PREF_USER_ID, String::class.java)
    }

    fun saveMobileNumber(mobileNumber: String) {
        put(MOBILE_NUMBER, mobileNumber)
    }

    fun getMobileNumber(): String {
        return get(MOBILE_NUMBER, String::class.java)
    }

    fun saveFullName(fullName: String) {
        put(FULL_NAME, fullName)
    }

    fun getFullName(): String {
        return get(FULL_NAME, String::class.java)
    }


    fun saveProfileType(profileType: String) {
        put(PROFILE_TYPE, profileType)
    }

    fun getProfileType(): String {
        return get(PROFILE_TYPE, String::class.java)
    }

    fun setProfileCreated(boolean: Boolean) {
        put(PROFILE_CREATED, boolean)
    }

    fun isProfileCreated(): Boolean {
        return get(PROFILE_CREATED, Boolean::class.java)
    }

    fun saveProfileID(profileID: String) {
        put(PROFILE_ID, profileID)
    }

    fun getProfileID(): String {
        return get(PROFILE_ID, String::class.java)
    }

    fun setTripBookingId(bookingId: String) {
        put(TRIP_BOOKING_ID, bookingId)
    }

    fun getTripBookingId(): String {
        return get(TRIP_BOOKING_ID, String::class.java)
    }

    fun setFirebaseToken(bookingId: String) {
        put(FIREBASE_TOKEN, bookingId)
    }

    fun getFirebaseToken(): String {
        return get(FIREBASE_TOKEN, String::class.java)
    }

    fun setStaffId(staffId: String) {
        put(STAFF_ID, staffId)
    }

    fun getStaffId(): String {
        return get(STAFF_ID, String::class.java)
    }

    fun setStaffProfileId(staffProfileId: String) {
        put(STAFF_PROFILE_ID, staffProfileId)
    }

    fun getStaffProfileId(): String {
        return get(STAFF_PROFILE_ID, String::class.java)
    }

    fun setStaffRole(staffRole: String) {
        put(STAFF_ROLE, staffRole)
    }

    fun getStaffRole(): String {
        return get(STAFF_ROLE, String::class.java)
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        sharedPref.edit().run {
            remove(PREF_TOKEN)
            remove(PREF_USER_ID)
            remove(PROFILE_TYPE)
            remove(MOBILE_NUMBER)
            remove(PROFILE_CREATED)
            remove(PROFILE_ID)
            remove(STAFF_ID)
            remove(STAFF_PROFILE_ID)
            remove(STAFF_ROLE)
        }.apply()
    }

    fun getKey(): String {
        return "E630060D22261575D7B8B70B3A694B215DA8E7EBE711F24E0F531B0220FBB9CF4CC2438A410CD63B4686A4C4DA722800"
    }

    fun getRouteKey(): String {
        return "F0B3795A7275F1C8F3CC026726AE532A694EBBF95047EA1126AD3D66317DE498DC2597399B6B04550A8EF9B039CE0E03"
    }
}