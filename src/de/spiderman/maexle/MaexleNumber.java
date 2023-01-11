package de.spiderman.maexle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MaexleNumber {

	private final ArrayList<Integer> numbers = new ArrayList<>();

	public MaexleNumber() {

	}

	public void randomize(int count) {
		clear();
		for (int i = 0; i < count; i++) {
			numbers.add(new Random().nextInt(6) + 1);
		}
	}

	public char getIndex(int index) {
		return String.valueOf(getValue()).charAt(index);
	}

	public void clear() {
		numbers.clear();
	}

	public MaexleNumber(int... nums) {

		if (nums == null) {
			return;
		}

		for (int n : nums) {
			if (0 < n && n < 7) {
				numbers.add(n);
			}
		}
	}

	public int getValue() {
		if (numbers.isEmpty()) {
			return 0;
		}

		List<Integer> sortedNumbers = numbers.stream().sorted((n1, n2) -> n2.compareTo(n1)).collect(Collectors.toList());
		StringBuilder builder = new StringBuilder();
		sortedNumbers.forEach(builder::append);
		return Integer.parseInt(builder.toString());
	}

	@Override
	public String toString() {
		if (numbers.isEmpty()) {
			return null;
		}
		if (isPasch()) {
			return "Pasch: " + getValue();
		}
		if (isMaexle()) {
			return "Maexle";
		}
		return Integer.toString(getValue());
	}

	public boolean isPasch() {
		if (numbers.isEmpty()) {
			return false;
		}

		int i = numbers.get(0);
		for (int n : numbers) {
			if (n != i) return false;
		}
		return true;
	}

	public boolean isMaexle() {
		return getValue() == 21;
	}

	public boolean equals(MaexleNumber anotherMaexleNumber) {
		return getValue() == anotherMaexleNumber.getValue();
	}

	public boolean equals(int value) {
		return getValue() == value;
	}

	public boolean isBiggerThan(MaexleNumber anotherMaexleNumber) {
		// equal
		if (equals(anotherMaexleNumber)) {
			return false;
		}
		// Maexle
		if (isMaexle()) {
			return !anotherMaexleNumber.isMaexle();
		}
		// Pasch
		if (isPasch()) {
			return !anotherMaexleNumber.isMaexle() && (!anotherMaexleNumber.isPasch() || (getValue() > anotherMaexleNumber.getValue()));
		}
		// any other
		return !anotherMaexleNumber.isMaexle() && (!anotherMaexleNumber.isPasch() && (getValue() > anotherMaexleNumber.getValue()));
	}

	public boolean isSmallerThan(MaexleNumber anotherMaexleNumber) {
		// equal || Maexle
		if (equals(anotherMaexleNumber) || isMaexle()) {
			return false;
		}
		// Pasch
		if (isPasch()) {
			return anotherMaexleNumber.isMaexle() || (anotherMaexleNumber.isPasch() && getValue() < anotherMaexleNumber.getValue());
		}
		// any other
		return anotherMaexleNumber.isMaexle() || (anotherMaexleNumber.isPasch() || (getValue() < anotherMaexleNumber.getValue()));
	}

	public void addNumber(int n) {
		if (0 < n && n < 7) {
			numbers.add(n);
		}
	}

	public int length() {
		return getNumbers().size();
	}

	public ArrayList<Integer> getNumbers() {
		return numbers;
	}

	public int compareTo(MaexleNumber anotherMaexleNumber) {
		return compare(this, anotherMaexleNumber);
	}

	public static int compare(MaexleNumber n1, MaexleNumber n2) {
		return n1.isBiggerThan(n2) ? 1 : (n1.equals(n2) ? 0 : -1);
	}
}
