package com.gongshijie.method_trace_plugin

import com.android.build.gradle.AppExtension
import com.ss.android.ugc.bytex.common.CommonPlugin
import com.ss.android.ugc.bytex.common.TransformConfiguration
import com.ss.android.ugc.bytex.common.visitor.ClassVisitorChain
import com.ss.android.ugc.bytex.pluginconfig.anno.PluginConfig
import org.gradle.api.Project
import org.objectweb.asm.tree.ClassNode

@PluginConfig("method-trace-plugin")
class MethodTracePlugin : CommonPlugin<MethodTraceExtension, MethodTraceContext>() {
    override fun getContext(
        project: Project,
        android: AppExtension,
        extension: MethodTraceExtension
    ): MethodTraceContext {
        return MethodTraceContext(project, android, extension)
    }

    override fun transform(relativePath: String, chain: ClassVisitorChain): Boolean {
        chain.connect(MethodTraceClassVisitor(extension))
        return super.transform(relativePath, chain)
    }
}