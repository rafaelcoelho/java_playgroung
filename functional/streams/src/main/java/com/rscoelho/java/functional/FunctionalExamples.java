package com.rscoelho.java.functional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionalExamples {
	public static void main(String[] args) {
		final Function<Person, String> byName = Person::getName;
		final Function<Person, Integer> byAge = Person::getAge;

		final Comparator<Person> asc = Person::ageDifference;
		final Comparator<Person> desc = asc.reversed();

		final List<Person> people = Arrays.asList(new Person("Ana", 18), new Person("John", 21), new Person("Bob", 18),
				new Person("Lisa", 35));

		System.out.println("Ascending is: ");
		people.stream().sorted(asc).collect(toList()).forEach(System.out::println);

		System.out.println("\nDescending is: ");
		people.stream().sorted(desc).collect(toList()).forEach(System.out::println);

		System.out.println("\nSorting by name: ");
		people.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(toList())
				.forEach(System.out::println);

		System.out.println("\nGetting Youngest: ");
		people.stream().min(asc).ifPresent(System.out::println);

		System.out.println("\nGetting Oldest: ");
		people.stream().max(asc).ifPresent(System.out::println);

		System.out.println("\nSorting by two parameters: ");
		people.stream().sorted(comparing(byAge).thenComparing(byName)).forEach(System.out::println);

		groupByAge(people);

		try {
			iterateOverFS();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void groupByAge(final List<Person> persons) {
		final Map<Integer, List<Person>> result = persons.stream().collect(Collectors.groupingBy(Person::getAge));

		System.out.println("Grouped by age: " + result);

		System.out.println("Grouped by age and mapping from name: "
				+ persons.stream().collect(Collectors.groupingBy(Person::getAge, mapping(Person::getName, toList()))));
	}

	public static void iterateOverFS() throws IOException {
		System.out.println("Printing directory from current dir: ");
		Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(System.out::println);

		System.out.println("Printing hidden files from current dir: ");
		final List<File> files = Arrays.asList(new File(".").listFiles(File::isHidden));
		files.forEach(System.out::println);
	}

	public static class Person {
		private final String name;
		private final int age;

		public Person(final String name, final int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public int ageDifference(final Person other) {
			return age - other.age;
		}

		@Override
		public String toString() {
			return String.format("%s - %d", name, age);
		}
	}
}
