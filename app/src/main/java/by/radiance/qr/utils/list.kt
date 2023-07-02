package by.radiance.qr.utils

fun <T>List<T>.getNext(current: T): T {
    return get(indexOf(current).takeIf { it < (size - 1) && it != -1 }?.let { it + 1 } ?: 0)
}