import io.Source.fromFile
import math.pow
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

object TwoB extends App{
	val timer: ThreadMXBean = ManagementFactory.getThreadMXBean()
	val start: Long = timer.getCurrentThreadCpuTime()

	trait BasePosition(depth: Int, horizontal: Int)
	case class Position2DWithAim(depth: Int, horizontal: Int, aim: Int) extends BasePosition(depth, horizontal)
	def update_position(pos: Position2DWithAim, command: String): Position2DWithAim ={
		val split_command: Array[String] = command.split(" ")
		val operation: String = split_command(0)
		val operand: Int = split_command(1).toInt
		if (operation == "forward") {
			return pos.copy(horizontal = pos.horizontal + operand, depth = pos.depth + (pos.aim * operand))
		}
		if (operation == "up") {
			return pos.copy(aim = pos.aim - operand)
		}
		if (operation == "down") {
			return pos.copy(aim = pos.aim + operand)
		}
		else {
			return ???
		}
	}

	val origin: Position2DWithAim = Position2DWithAim(0, 0, 0)
	val final_position: Position2DWithAim = fromFile("input.txt").getLines.foldLeft(origin)(update_position(_,_))
	val answer: Int = final_position.depth * final_position.horizontal
	
	val end: Long = timer.getCurrentThreadCpuTime()
	println(answer)
	val diff: Int = (end - start).toInt
	println(s"Took: ${diff} nanoseconds, that's ${(diff)/pow(10,9)} seconds")
}