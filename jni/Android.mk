LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_SRC_FILES += \
    librtmp/amf.c \
    librtmp/hashswf.c \
    librtmp/log.c \
    librtmp/parseurl.c \
    librtmp/rtmp.c 
    
LOCAL_CFLAGS := -D__STDC_CONSTANT_MACROS -DNO_CRYPTO
LOCAL_LDLIBS := -llog
LOCAL_MODULE := librtmp

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := dump
LOCAL_CFLAGS := -DRTMPDUMP_VERSION=\"v2.4\"
LOCAL_LDLIBS := -llog
LOCAL_SRC_FILES := dump/rtmpdump.c
LOCAL_C_INCLUDES := $(LOCAL_PATH)/../librtmp

LOCAL_STATIC_LIBRARIES := librtmp

include $(BUILD_SHARED_LIBRARY)
