import scala.collection.immutable._
import scala.util.Random

trait SeqPerformance extends PerformanceSupport {

  def listAdd(): Unit = {

    println(s"データ追加 Vector vs List")

    // 処理時間格納のリスト
    var vectorResult = List.empty[Long]
    var listResult = List.empty[Long]

    // 空コレクションデータ
    val eVector = Vector.empty[Int]
    val eList = List.empty[Int]

    println(s"対象件数: ${SIZE_1000000}件")

    vectorResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      vectorResult =
        vectorResult.+:(printExecTime(() => addProc(eVector)(SIZE_1000000))(i))
      listResult =
        listResult.+:(printExecTime(() => addProc(eList)(SIZE_1000000))(i))
    }

    printAverage("Vector", vectorResult, "List", listResult)

    println(s"対象件数: ${SIZE_10000000}件")

    vectorResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      vectorResult =
        vectorResult.+:(printExecTime(() => addProc(eVector)(SIZE_10000000))(i))
      listResult =
        listResult.+:(printExecTime(() => addProc(eList)(SIZE_10000000))(i))
    }
    printAverage("Vector", vectorResult, "List", listResult)
  }

  def listRandomAccess(): Unit = {

    println(s"ランダムアクセス Vector vs List")

    // 処理時間格納のリスト
    var vectorResult = List.empty[Long]
    var listResult = List.empty[Long]

    // 空コレクションデータ
    val eVector = Vector.empty[Int]
    val eList = List.empty[Int]

    // 読み込み性能検証用データ
    val vector_10000 = addProc(eVector)(SIZE_10000).toVector
    val list_10000 = addProc(eList)(SIZE_10000).toList
    val vector_100000 = addProc(eVector)(SIZE_100000).toVector
    val list_100000 = addProc(eList)(SIZE_100000).toList

    println(s"対象件数: ${SIZE_10000}件")

    vectorResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      vectorResult =
        vectorResult.+:(printExecTime(() => randomSumProc(vector_10000))(i))
      listResult =
        listResult.+:(printExecTime(() => randomSumProc(list_10000))(i))
    }

    printAverage("Vector", vectorResult, "List", listResult)

    println(s"対象件数: ${SIZE_100000}件")

    vectorResult = List.empty[Long]
    listResult = List.empty[Long]

    // Listのランダムアクセスが遅すぎるので、計測を10回に減らす
    for (i <- 1 to 10) {
      vectorResult =
        vectorResult.+:(printExecTime(() => randomSumProc(vector_100000))(i))
      listResult =
        listResult.+:(printExecTime(() => randomSumProc(list_100000))(i))
    }

    printAverage("Vector", vectorResult, "List", listResult)
  }

  def listSequentialAccess(): Unit = {

    println(s"シーケンシャルアクセス Vector vs List")

    // 処理時間格納のリスト
    var vectorResult = List.empty[Long]
    var listResult = List.empty[Long]

    // 空コレクションデータ
    val eVector = Vector.empty[Int]
    val eList = List.empty[Int]

    // 読み込み性能検証用データ
    val vector_10000 = addProc(eVector)(SIZE_10000).toVector
    val list_10000 = addProc(eList)(SIZE_10000).toList
    val vector_100000 = addProc(eVector)(SIZE_100000).toVector
    val list_100000 = addProc(eList)(SIZE_100000).toList
    val vector_1000000 = addProc(eVector)(SIZE_1000000).toVector
    val list_1000000 = addProc(eList)(SIZE_1000000).toList

    println(s"対象件数: ${SIZE_10000}件")

    vectorResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      vectorResult =
        vectorResult.+:(printExecTime(() => sequentialSumProc(vector_10000))(i))
      listResult =
        listResult.+:(printExecTime(() => sequentialSumProc(list_10000))(i))
    }

    printAverage("Vector", vectorResult, "List", listResult)

    println(s"対象件数: ${SIZE_100000}件")

    vectorResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      vectorResult = vectorResult.+:(
        printExecTime(() => sequentialSumProc(vector_100000))(i)
      )
      listResult =
        listResult.+:(printExecTime(() => sequentialSumProc(list_100000))(i))
    }

    printAverage("Vector", vectorResult, "List", listResult)

    println(s"対象件数: ${SIZE_1000000}件")

    vectorResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      vectorResult = vectorResult.+:(
        printExecTime(() => sequentialSumProc(vector_1000000))(i)
      )
      listResult =
        listResult.+:(printExecTime(() => sequentialSumProc(list_1000000))(i))
    }

    printAverage("Vector", vectorResult, "List", listResult)
  }

  def lazyListAdd(): Unit = {

    println(s"データ追加 LazyList vs List")

    // 処理時間格納のリスト
    var lazyResult = List.empty[Long]
    var listResult = List.empty[Long]

    // 空コレクションデータ
    val eLazy = LazyList.empty[Int]
    val eList = List.empty[Int]

    println(s"対象件数: ${SIZE_1000000}件")

    lazyResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      lazyResult =
        lazyResult.+:(printExecTime(() => addProc(eLazy)(SIZE_1000000))(i))
      listResult =
        listResult.+:(printExecTime(() => addProc(eList)(SIZE_1000000))(i))
    }

    printAverage("LazyList", lazyResult, "List", listResult)

    println(s"対象件数: ${SIZE_10000000}件")

    lazyResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      lazyResult =
        lazyResult.+:(printExecTime(() => addProc(eLazy)(SIZE_10000000))(i))
      listResult =
        listResult.+:(printExecTime(() => addProc(eList)(SIZE_10000000))(i))
    }
    printAverage("LazyList", lazyResult, "List", listResult)
  }

  def lazyListRandomAccess(): Unit = {

    println(s"ランダムアクセス LazyList vs List")

    // 処理時間格納のリスト
    var lazyListResult = List.empty[Long]
    var listResult = List.empty[Long]

    // 空コレクションデータ
    val eLazyList = LazyList.empty[Int]
    val eList = List.empty[Int]

    // 読み込み性能検証用データ
    val lazyList_10000 = addLazyProc(eLazyList)(SIZE_10000)
    val list_10000 = addProc(eList)(SIZE_10000).toList
    val lazyList_100000 = addLazyProc(eLazyList)(SIZE_100000)
    val list_100000 = addProc(eList)(SIZE_100000).toList

    println(s"対象件数: ${SIZE_10000}件")

    lazyListResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      lazyListResult =
        lazyListResult.+:(printExecTime(() => randomSumProc(lazyList_10000))(i))
      listResult =
        listResult.+:(printExecTime(() => randomSumProc(list_10000))(i))
    }

    printAverage("LazyList", lazyListResult, "List", listResult)

    println(s"対象件数: ${SIZE_100000}件")

    lazyListResult = List.empty[Long]
    listResult = List.empty[Long]

    // Listのランダムアクセスが遅すぎるので、計測を10回に減らす
    for (i <- 1 to 10) {
      lazyListResult = lazyListResult.+:(
        printExecTime(() => randomSumProc(lazyList_100000))(i)
      )
      listResult =
        listResult.+:(printExecTime(() => randomSumProc(list_100000))(i))
    }

    printAverage("LazyList", lazyListResult, "List", listResult)
  }

  def lazyListSequentialAccess(): Unit = {

    println(s"シーケンシャルアクセス LazyList vs List")

    // 処理時間格納のリスト
    var lazyListResult = List.empty[Long]
    var listResult = List.empty[Long]

    // 空コレクションデータ
    val eLazyList = LazyList.empty[Int]
    val eList = List.empty[Int]

    // 読み込み性能検証用データ
    val lazyList_10000 = addLazyProc(eLazyList)(SIZE_10000)
    val list_10000 = addProc(eList)(SIZE_10000).toList
    val lazyList_100000 = addLazyProc(eLazyList)(SIZE_100000)
    val list_100000 = addProc(eList)(SIZE_100000).toList
    val lazyList_1000000 = addLazyProc(eLazyList)(SIZE_1000000)
    val list_1000000 = addProc(eList)(SIZE_1000000).toList

    println(s"対象件数: ${SIZE_10000}件")

    lazyListResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      lazyListResult = lazyListResult.+:(
        printExecTime(() => sequentialSumProc(lazyList_10000))(i)
      )
      listResult =
        listResult.+:(printExecTime(() => sequentialSumProc(list_10000))(i))
    }

    printAverage("LazyList", lazyListResult, "List", listResult)

    println(s"対象件数: ${SIZE_100000}件")

    lazyListResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      lazyListResult = lazyListResult.+:(
        printExecTime(() => sequentialSumProc(lazyList_100000))(i)
      )
      listResult =
        listResult.+:(printExecTime(() => sequentialSumProc(list_100000))(i))
    }

    printAverage("LazyList", lazyListResult, "List", listResult)

    println(s"対象件数: ${SIZE_1000000}件")

    lazyListResult = List.empty[Long]
    listResult = List.empty[Long]

    for (i <- 1 to 100) {
      lazyListResult = lazyListResult.+:(
        printExecTime(() => sequentialSumProc(lazyList_1000000))(i)
      )
      listResult =
        listResult.+:(printExecTime(() => sequentialSumProc(list_1000000))(i))
    }

    printAverage("LazyList", lazyListResult, "List", listResult)
  }

  def addProc(seq: Seq[Int])(size: Int): Seq[Int] = {
    var s = seq
    for (_ <- 1 to size) {
      s = s.+:(randomInt(SIZE_100000000))
    }
    s
  }

  def addLazyProc(seq: LazyList[Int])(size: Int): LazyList[Int] = {
    var s = seq
    for (_ <- 1 to size) {
      s = s.+:(randomInt(SIZE_100000000))
    }
    s
  }

  def randomSumProc(seq: Seq[Int]): Int = {
    var sum = 0
    for (i <- 0 until seq.size) {
      sum = sum + seq(i)
    }
    sum
  }

  def sequentialSumProc(seq: Seq[Int]): Int = {
    def loop(list: Seq[Int], sum: Int): Int = {
      // `head :: tail` や `elm :: Nil` のような書き方をすると、Vectorを渡した時に例外が発生する
      list match {
        case _ if list.isEmpty => sum
        case _                 => loop(list.tail, sum + list.head)
      }
    }

    loop(seq, 0)
  }
}
