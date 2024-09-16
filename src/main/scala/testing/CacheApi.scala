package testing
import java.util.{LinkedHashMap, Map}
import scala.jdk.CollectionConverters._ // Correct import for collection conversion

class LRUCache[K, V](val maxSize: Int) {

  // LinkedHashMap with accessOrder = true to maintain LRU order
  private val cache = new LinkedHashMap[K, V](maxSize, 0.75f, true) {
    override def removeEldestEntry(eldest: Map.Entry[K, V]): Boolean = {
      size() > maxSize
    }
  }

  // Add a key-value pair to the cache
  def put(key: K, value: V): Unit = {
    cache.put(key, value)
  }

  // Retrieve a value from the cache, returning an Option
  def get(key: K): Option[V] = {
    Option(cache.get(key))
  }

  // Get current size of the cache
  def size: Int = cache.size()

  // Clear the cache
  def clear(): Unit = {
    cache.clear()
  }

  // Get all keys in the cache as a Scala Iterable
  def keys: Iterable[K] = cache.keySet().asScala

  // Get all entries (key-value pairs) in the cache as a Scala Iterable
  def entries: Iterable[(K, V)] = cache.entrySet().asScala.map(entry => (entry.getKey, entry.getValue))
}

object LRUCacheTest extends App {
  // Create an LRUCache with max size 3
  val cache = new LRUCache[String, Int](3)

  cache.put("one", 1)
  cache.put("two", 2)
  cache.put("three", 3)
  println(s"Initial Cache Entries: ${cache.entries.toList}")

  // Access "one" to make it most recently used
  cache.get("one")

  // Add "four", which should evict "two" (the least recently used entry)
  cache.put("four", 4)
  println(s"Cache after adding 'four' and accessing 'one': ${cache.entries.toList}")

  // Add "five", which should evict "three"
  cache.put("five", 5)
  println(s"Cache after adding 'five': ${cache.entries.toList}")
}
