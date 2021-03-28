package com.gongshijie.feed

import com.gongshijie.feed.api.NewsCell

object VHParser {
    fun parseViewType(cell: NewsCell): Int {
        var result = 0
        return when (cell.uniqueKey.last()) {
            in 'a'..'g' -> VHType.RIGHT_IMAGE.type
            in 'h'..'n' -> VHType.BIG_IMAGE.type
            in 'o'..'t' -> VHType.NO_IMAGE.type
            in 'u'..'z' -> VHType.THREE_IMAGE.type
            in '1'..'5' -> VHType.XIGUA.type
            in '6'..'0' -> VHType.CINEMA.type
            else -> VHType.RIGHT_IMAGE.type
        }
    }
}
