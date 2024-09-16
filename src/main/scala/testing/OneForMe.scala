package testing



object Twofer {
  def twofer(name: String = ""): String = if (name != null & !name.isEmpty) f"One for ${name}, one for me." else "One for you, one for me."
}

/** @version 1.2.0 */
