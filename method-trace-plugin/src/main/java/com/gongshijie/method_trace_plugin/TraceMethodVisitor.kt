package com.gongshijie.method_trace_plugin

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

class TraceMethodVisitor(
    api: Int, mv: MethodVisitor?, access: Int,
    name: String?, desc: String?
) : AdviceAdapter(api, mv, access, name, desc) {

    override fun onMethodEnter() {
        super.onMethodEnter()
        mv.visitLdcInsn(name);
        mv.visitMethodInsn(INVOKESTATIC, "androidx/core/os/TraceCompat", "beginSection", "(Ljava/lang/String;)V", false);

    }

    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        mv.visitMethodInsn(INVOKESTATIC, "androidx/core/os/TraceCompat", "endSection", "(Ljava/lang/String;)V", false);

    }
}