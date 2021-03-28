package com.gongshijie.method_trace_plugin

import com.android.build.gradle.AppExtension
import com.ss.android.ugc.bytex.common.BaseContext
import org.gradle.api.Project

class MethodTraceContext(project: Project,
                         android: AppExtension, extension: MethodTraceExtension
) : BaseContext<MethodTraceExtension>(project, android, extension) {

}
