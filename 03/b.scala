import annotation.tailrec
import io.Source.fromFile
import math.pow
import java.lang.Integer.parseInt
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

object ThreeB extends App{
	val timer: ThreadMXBean = ManagementFactory.getThreadMXBean()
	val start: Long = timer.getCurrentThreadCpuTime()

	def most_common_bit(binary: Array[String], position: Int): Char ={
		val relevant_binary: Array[Char] = binary.map(_(position))
		val zeros_count: Int = relevant_binary.count(_ == '0')
		val ones_count: Int = relevant_binary.count(_ == '1')

		if (zeros_count > ones_count){
			return '0'
		}
		return '1'
	}
	def least_common_bit(binary: Array[String], position: Int): Char ={
		return Map('0' -> '1', '1' -> '0')(most_common_bit(binary, position))
	}

	def binary_to_denary(binary: String): Int ={
		return parseInt(binary, 2)
	}
	@tailrec
	def get_rate(bit_extractor: (Array[String], Int) => Char, binary: Array[String], rates_bit_length: Int, position: Int = 0): Int ={
		if (binary.length == 1){
			return binary_to_denary(binary(0))
		}
		return get_rate(bit_extractor, binary.filter(_(position) == bit_extractor(binary, position)), rates_bit_length, position + 1)
	}

	val input: Array[String] = fromFile("input.txt").getLines.toArray
	val rates_bit_length: Int = input(0).length
	val oxygen_rate: Int = get_rate(most_common_bit, input, rates_bit_length)
	val carbon_rate: Int = get_rate(least_common_bit, input, rates_bit_length)
	val answer: Int = oxygen_rate * carbon_rate

	val end: Long = timer.getCurrentThreadCpuTime()
	println(answer)
	println(s"Took ${(end - start)/pow(10,9)} seconds")
}