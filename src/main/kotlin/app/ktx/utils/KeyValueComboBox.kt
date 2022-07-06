package app.ktx.utils

class KeyValueComboBox
(
    val key: Any,
    val value: String
)
{
    override fun toString(): String
    {
        return this.value;
    }
}
