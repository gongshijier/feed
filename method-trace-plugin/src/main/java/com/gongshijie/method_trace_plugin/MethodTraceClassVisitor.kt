package com.gongshijie.method_trace_plugin

import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class MethodTraceClassVisitor(extension: MethodTraceExtension?) : BaseClassVisitor() {

    override fun visitSource(source: String?, debug: String?) {
        super.visitSource(source, debug)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {

        val methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        if (true) {
            return TraceMethodVisitor(Opcodes.ASM6, methodVisitor, access, name, descriptor)
        }



        return methodVisitor
    }
}
