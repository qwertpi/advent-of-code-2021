import math.pow
import io.Source.fromFile
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

object OneB extends App {

    val timer: ThreadMXBean = ManagementFactory.getThreadMXBean()
    val start: Long = timer.getCurrentThreadCpuTime()
    val window_size: Int = 3

    class CircularBuffer(max_size: Int){
        var current_size: Int = 0
        private var rear: Int = 0
        private var buffer: Array[Int] = new Array[Int](max_size)
        var sum = 0
        def add(value: Int): Unit ={
            rear = (rear + 1) % max_size
            if (current_size != max_size){
                current_size += 1
            }
            else{
                sum -= buffer(rear)
            }
            sum += value
            buffer.update(rear, value)
        }
        def access(): Array[Int] ={
            return buffer.clone()
        }
    }
    def get_lines(): Iterator[String] ={
        return fromFile("input.txt").getLines
    }
    def get_int_lines(): Iterator[Int] ={
        return get_lines().map(x => x.toInt)
    }

    val lines: Iterator[Int] = get_int_lines()
    val lines_without_first_element: Iterator[Int] = get_int_lines().drop(1)
    var window_1: CircularBuffer = new CircularBuffer(window_size)
    var window_2: CircularBuffer = new CircularBuffer(window_size)
    var answer: Int = 0

    for (_ <- 1 until window_size){
        window_1.add(lines.next())
        window_2.add(lines_without_first_element.next())
    }
    for (_ <- window_size to (get_lines().length - 1)){
        window_1.add(lines.next())
        window_2.add(lines_without_first_element.next())
        if (window_1.sum < window_2.sum){
            answer += 1
        }
    }
    val end: Long = timer.getCurrentThreadCpuTime()
    val diff: Int = (end - start).toInt
    println(answer)
    println(s"Took: ${diff} nanoseconds, that's ${(diff)/pow(10,9)} seconds")
}
