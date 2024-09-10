package me.theek.memox.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import me.theek.memox.core.datastore_proto.OnboardingPreferences
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingPreferencesSerializer @Inject constructor() : Serializer<OnboardingPreferences> {

    override val defaultValue: OnboardingPreferences
        get() = OnboardingPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): OnboardingPreferences {
        return try {
            OnboardingPreferences.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: OnboardingPreferences, output: OutputStream) {
        t.writeTo(output)
    }
}