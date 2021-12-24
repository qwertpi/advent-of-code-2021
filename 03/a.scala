import io.Source.fromFile
import math.pow
import java.lang.Integer.parseInt
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

object ThreeA extends App{
	val timer: ThreadMXBean = ManagementFactory.getThreadMXBean()
	val start: Long = timer.getCurrentThreadCpuTime()

	def most_common_bit(binary: Iterator[String], position: Int): Char ={
		var zeros_count: Int = 0
		var ones_count: Int = 0

		binary.foreach(_(position) match{
			case '0' => zeros_count += 1
			case '1' => ones_count += 1
		})
		if (zeros_count < ones_count){
			return '1'
		}
		return '0'
	}
	def least_common_bit(binary: Iterator[String], position: Int): Char ={
		return most_common_bit(binary, position) match{
			case '0' => '1'
			case '1' => '0'
		}
	}

	def binary_to_denary(binary: String): Int ={
		return parseInt(binary, 2)
	}
	def get_rate(bit_extractor: (Iterator[String], Int) => Char, rates_bit_length: Int): Int ={
		return binary_to_denary((0 until rates_bit_length).map(bit_extractor(fromFile("input.txt").getLines, _)).mkString)
	}

	val rates_bit_length: Int = fromFile("input.txt").getLines.next().length
	val gamma_rate: Int = get_rate(most_common_bit, rates_bit_length)
	val epsilon_rate: Int = get_rate(least_common_bit, rates_bit_length)
	val answer: Int = gamma_rate * epsilon_rate

	val end: Long = timer.getCurrentThreadCpuTime()
	println(answer)
	println(s"Took ${(end - start)/pow(10,9)} seconds")
}