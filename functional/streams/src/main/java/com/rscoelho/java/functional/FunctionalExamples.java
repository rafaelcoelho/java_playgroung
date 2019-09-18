package com.rscoelho.java.functional;

import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class FunctionalExamples {
    public static void main(String[] args) {
        final Function<Person, String> byName = Person::getName;
        final Function<Person, Integer> byAge = Person::getAge;

        Comparator<Person> asc = Person::ageDifference;
        Comparator<Person> desc = asc.reversed();

        List<Person> people = Arrays.asList(new Person("Aana", 18), new Person("Abohn", 18), new Person("Adob", 18),
                new Person("Acisa", 18));

        System.out.println("Ascending is: ");
        people.stream().sorted(asc).collect(toList()).forEach(System.out::println);

        System.out.println("\nDescending is: ");
        people.stream().sorted(desc).collect(toList()).forEach(System.out::println);

        System.out.println("\nSorting by name: ");
        people.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(toList()).forEach(System.out::println);
        
        System.out.println("\nGetting Youngest: ");
        people.stream().min(asc).ifPresent(System.out::println);

        System.out.println("\nGetting Oldest: ");
        people.stream().max(asc).ifPresent(System.out::println);

        System.out.println("\nSorting by two parameters: ");
        people.stream().sorted(comparing(byAge).thenComparing(byName)).forEach(System.out::println);
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

        public String toString() {
            return String.format("%s - %d", name, age);
        }
    }
}
