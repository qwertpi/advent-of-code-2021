import math.pow
import io.Source.fromFile
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

object OneA extends App {

    val timer: ThreadMXBean = ManagementFactory.getThreadMXBean()
    val start: Long = timer.getCurrentThreadCpuTime()
    
    def compare(value: Int, comparer: Int): Boolean ={
        return value > comparer
    }
    def get_lines(): Iterator[Int] ={
        return fromFile("input.txt").getLines.map(x => x.toInt)
    }

    val lines: Iterator[Int] = get_lines()
    val lines_without_first_element: Iterator[Int] = get_lines().drop(1)
    val answer: Int = lines_without_first_element.count(compare(_, lines.next()))
    
    val end: Long = timer.getCurrentThreadCpuTime()
    val diff: Int = (end - start).toInt
    println(answer)
    println(s"Took: ${diff} nanoseconds, that's ${(diff)/pow(10,9)} seconds")
}
