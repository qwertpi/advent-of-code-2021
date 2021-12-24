import io.Source.fromFile
import math.pow
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

object TwoA extends App{
	val timer: ThreadMXBean = ManagementFactory.getThreadMXBean()
	val start: Long = timer.getCurrentThreadCpuTime()

	trait BasePosition(depth: Int, horizontal: Int)
	case class Position2D(depth: Int, horizontal: Int) extends BasePosition(depth, horizontal)
	def update_position(pos: Position2D, command: String): Position2D ={
		val split_command: Array[String] = command.split(" ")
		val operation: String = split_command(0)
		val operand: Int = split_command(1).toInt
		if (operation == "forward") {
			return pos.copy(horizontal = pos.horizontal + operand)
		}
		if (operation == "up") {
			return pos.copy(depth = pos.depth - operand)
		}
		if (operation == "down") {
			return pos.copy(depth = pos.depth + operand)
		}
		else {
			return ???
		}
	}

	val origin: Position2D = Position2D(0, 0)
	val final_position: Position2D = fromFile("input.txt").getLines.foldLeft(origin)(update_position(_,_))
	val answer: Int = final_position.depth * final_position.horizontal
	
	val end: Long = timer.getCurrentThreadCpuTime()
	println(answer)
	val diff: Int = (end - start).toInt
	println(s"Took: ${diff} nanoseconds, that's ${(diff)/pow(10,9)} seconds")
}