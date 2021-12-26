import io.Source.fromFile
import math.pow
import java.lang.Integer.parseInt
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

object ThreeA extends App{
	val timer: ThreadMXBean = ManagementFactory.getThreadMXBean()
	val start: Long = timer.getCurrentThreadCpuTime()

	def most_common_bit(binary: Array[String], position: Int): Char ={
		val relevant_binary: Array[Char] = binary.map(_(position))
		val zeros_count: Int = relevant_binary.count(_ == '0')
		val ones_count: Int = relevant_binary.count(_ == '1')

		if (zeros_count < ones_count){
			return '1'
		}
		return '0'
	}
	def least_common_bit(binary: Array[String], position: Int): Char ={
		return Map('0' -> '1', '1' -> '0')(most_common_bit(binary, position))
	}

	def binary_to_denary(binary: String): Int ={
		return parseInt(binary, 2)
	}
	def get_rate(bit_extractor: (Array[String], Int) => Char, input: Array[String], rates_bit_length: Int): Int ={
		return binary_to_denary((0 until rates_bit_length).map(bit_extractor(input, _)).mkString)
	}

	val input: Array[String] = fromFile("input.txt").getLines.toArray
	val rates_bit_length: Int = input(0).length
	val gamma_rate: Int = get_rate(most_common_bit, input, rates_bit_length)
	val epsilon_rate: Int = get_rate(least_common_bit, input, rates_bit_length)
	val answer: Int = gamma_rate * epsilon_rate

	val end: Long = timer.getCurrentThreadCpuTime()
	println(answer)
	println(s"Took ${(end - start)/pow(10,9)} seconds")
}