#include <jni.h>

#include <android/log.h>
#include <stddef.h>
#include <malloc.h>
#include <stdlib.h>

#define TAG "RtmpdumpNative"
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

#ifdef __cplusplus
extern "C" {
#endif

void callback_handler(const char *s);
extern int rtmpdump_main(int argc, char **argv);

static JavaVM *sVm;
static jobject clazz;

void initClassHelper(JNIEnv *env, const char *path, jobject *objptr) {
    jclass cls = env->FindClass(path);
    if (!cls) {
        LOGE("initClassHelper: failed to get %s class reference", path);
        return;
    }
    jmethodID constr = env->GetMethodID(cls, "<init>", "()V");
    if (!constr) {
        LOGE("initClassHelper: failed to get %s constructor", path);
        return;
    }
    jobject obj = env->NewObject(cls, constr);
    if (!obj) {
        LOGE("initClassHelper: failed to create a %s object", path);
        return;
    }
    (*objptr) = env->NewGlobalRef(obj);
}

void callback_handler(const char *s) {
    int status;
    JNIEnv *env;
    bool isAttached = false;
    status = sVm->GetEnv((void **) &env, JNI_VERSION_1_4);
    if (status < 0) {
        LOGE("callback_handler: failed to get JNI environment, "
                     "assuming native thread");
        status = sVm->AttachCurrentThread(&env, NULL);
        if (status < 0) {
            LOGE("callback_handler: failed to attach "
                         "current thread");
            return;
        }
        isAttached = true;
    }

    /* Construct a Java string */
    jstring js = env->NewStringUTF(s);
    if (!js) {
        LOGE("String is leeg");
    }
    jclass interfaceClass = env->GetObjectClass(clazz);
    if (interfaceClass == NULL) {
        LOGE("callback_handler: failed to get class reference");
        if (isAttached)
            sVm->DetachCurrentThread();
        return;
    }
    //Ljava/lang/String;
    /* Find the callBack method ID */
    jmethodID method = env->GetStaticMethodID(interfaceClass, "testCallback",
                                                 "(Ljava/lang/String;)V");
    if (!method) {
        LOGE("callback_handler: failed to get method ID");
        if (isAttached)
            sVm->DetachCurrentThread();
        return;
    }

    env->CallStaticVoidMethod(interfaceClass, method, js);

    if (isAttached)
        sVm->DetachCurrentThread();
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved) {
    LOGV("Loading native library compiled at " __TIME__ " " __DATE__);

    const char* path = "com/schriek/rtmpdump/callBack";

    sVm = jvm;
    JNIEnv *env;

    if (jvm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        LOGE("Failed to get the environment using GetEnv()");
        return -1;
    }

    initClassHelper(env, path, &clazz);

    return JNI_VERSION_1_4;
}

JNIEXPORT void JNICALL Java_com_schriek_rtmpdump_Rtmpdump_run(JNIEnv *env, jobject obj, jobjectArray args)
{
    LOGV("run() called");
    int i = 0;
    int argc = 0;
    char **argv = NULL;

    if (args != NULL) {
        argc = env->GetArrayLength(args);
        argv = (char **) malloc(sizeof(char *) * argc);

        for(i=0;i<argc;i++)
        {
            jstring str = (jstring)env->GetObjectArrayElement(args, i);
            argv[i] = (char *)env->GetStringUTFChars(str, NULL);
        }
    }

    LOGV("run passing off to main()");
    rtmpdump_main(argc, argv);
}

void Java_com_schriek_rtmpdump_Rtmpdump_testNative(JNIEnv* env,
                                                   jobject javaThis) {

    LOGV("testNative() called!");

    callback_handler("Loading native library compiled at " __TIME__ " " __DATE__);
}

void Java_com_schriek_rtmpdump_Rtmpdump_stop(JNIEnv* env, jobject javaThis) {
    LOGV("stop() called!");

    /* probably not the best way to do this */
    exit(0);
}

#ifdef __cplusplus
}
#endif