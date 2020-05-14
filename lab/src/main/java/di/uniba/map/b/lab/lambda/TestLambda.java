/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package di.uniba.map.b.lab.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 *
 * @author pierpaolo
 */
public class TestLambda {

    //Create Methods That Search for Members That Match One Characteristic
    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    //Create More Generalized Search Methods
    public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    //Specify Search Criteria Code in a Local Class
    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    //Predicate<T> is a predefined function in java.util.function that works as ChechPerson but with generics
    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    //Consumer<T> is a predefined function in java.util.function that works as printPerson but with generics
    public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    //Function<T, R> is a predefined function in java.util.function that works as R apply(T t).
    //Apply performs an action on T and return R
    public static void processPersonsWithFunction(List<Person> roster, Predicate<Person> tester,
            Function<Person, String> mapper, Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    //using generics
    /*
    This method invocation performs the following actions:
    1. Obtains a source of objects from the collection source. In this example, it obtains a source of Person objects from the 
    collection roster. Notice that the collection roster, which is a collection of type List, is also an object of type Iterable.
    2. Filters objects that match the Predicate object tester. In this example, the Predicate object is a lambda expression that
    specifies which members would be eligible for Selective Service.
    3. Maps each filtered object to a value as specified by the Function object mapper. In this example, the Function object is a 
    lambda expression that returns the surname of a member.
    4. Performs an action on each mapped object as specified by the Consumer object block. In this example, the Consumer object is a 
    lambda expression that prints a string, which is the surname returned by the Function object.
     */
    public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester,
            Function<X, Y> mapper, Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Giovanni", "Verdi", 19, Person.Gender.MALE));
        persons.add(new Person("Marco", "Rossi", 9, Person.Gender.MALE));
        persons.add(new Person("Francesco", "Esposito", 22, Person.Gender.MALE));
        persons.add(new Person("Maria", "Rossi", 19, Person.Gender.FEMALE));
        persons.add(new Person("Francesca", "Verdi", 19, Person.Gender.FEMALE));
        persons.add(new Person("Anna", "Bianchi", 19, Person.Gender.FEMALE));
        //search persons older than
        printPersonsOlderThan(persons, 18);
        //search persons within age range
        printPersonsWithinAgeRange(persons, 0, 18);
        //create a new instance of ChechPerson
        printPersons(persons, new CheckPersonEligibleForSelectiveService());
        //use of an anonymous class
        printPersons(persons, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Gender.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        });
        /*
        The CheckPerson interface is a functional interface. A functional interface is any interface that 
        contains only one abstract method. (A functional interface may contain one or more default methods or 
        static methods.) Because a functional interface contains only one abstract method, you can omit the name
        of that method when you implement it. To do this, instead of using an anonymous class expression, you use a lambda expression
         */
        //the name of the abstract method is implicit
        printPersons(persons,
                (Person p) -> p.getGender() == Person.Gender.MALE // Predicate function
                && p.getAge() >= 18
                && p.getAge() <= 25
        );

        processPersons(persons,
                p -> p.getGender() == Person.Gender.MALE // Predicate function
                && p.getAge() >= 18
                && p.getAge() <= 25,
                p -> p.printPerson() // Consumer function
        );

        processPersonsWithFunction(persons,
                p -> p.getGender() == Person.Gender.MALE // Predicate function
                && p.getAge() >= 18
                && p.getAge() <= 25,
                p -> p.getSurname(), // Function
                surname -> System.out.println(surname) //Consumer
        );

        processElements(persons,
                p -> p.getGender() == Person.Gender.MALE // Predicate function
                && p.getAge() >= 18
                && p.getAge() <= 25,
                p -> p.getSurname(), // Function
                surname -> System.out.println(surname) //Consumer
        );

        processElements(persons,
                p -> p.getGender() == Person.Gender.MALE // Predicate function
                && p.getAge() >= 18
                && p.getAge() <= 25,
                p -> p.getAge(), // Function
                age -> System.out.println(age) //Consumer
        );

        //processElements can be replaced by a pipeline applied to a stream
        persons
                .stream()
                .filter(p -> p.getGender() == Person.Gender.MALE // Predicate function
                && p.getAge() >= 18
                && p.getAge() <= 25)
                .map(p -> p.getSurname())
                .forEach(surname -> System.out.println(surname));

        double avgAge = persons
                .stream()
                .filter(p -> p.getGender() == Person.Gender.MALE)
                .mapToInt(p -> p.getAge())
                .average().getAsDouble();
        System.out.println(avgAge);

        long m = persons
                .stream()
                .filter(p -> p.getAge() > 17)
                .mapToInt(p -> p.getAge())
                .count();
        System.out.println(m);

        //examples of generator
        IntStream.iterate(2, i -> i + 2)
                .limit(100)
                .forEach(p -> System.out.println(p));

        IntStream.rangeClosed(0, 10)
                .map(i -> (int) Math.pow(2, i))
                .forEach(p -> System.out.println(p));

        System.out.println(IntStream.range(0, 10)
                .average().getAsDouble());

        DoubleStream.generate(() -> Math.random())
                .filter(p -> p >= 0.5)
                .limit(100)
                .forEach(p -> System.out.println(p));

        System.out.println(DoubleStream.generate(() -> Math.random())
                .limit(100)
                .filter(p -> p > 0.5)
                .count());

        Integer totalAge = persons
                .stream()
                .mapToInt(p -> p.getAge())
                .sum();
        System.out.println(totalAge);

        totalAge = persons
                .stream()
                .map(p -> p.getAge())
                .reduce(0,
                        (a, b) -> a + b);
        System.out.println(totalAge);

        Averager avgAgeC = persons
                .stream()
                .map(p -> p.getAge())
                //.collect(Averager::new, Averager::accept, Averager::combine);
                .collect(() -> new Averager(), (a, b) -> a.accept(b), (a, b) -> a.combine(b));
        System.out.println(avgAgeC.average());

        List<String> namesOfMaleMembersCollect = persons
                .stream()
                .filter(p -> p.getGender() == Person.Gender.FEMALE)
                .map(p -> p.getName())
                .collect(Collectors.toList());
        System.out.println(namesOfMaleMembersCollect);

        Map<Person.Gender, List<Person>> collect = persons
                .stream()
                .filter(p -> p.getAge() > 17)
                .collect(Collectors.groupingBy(Person::getGender));

        Map<Person.Gender, List<String>> namesByGender = persons
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.toList())));

        Map<Person.Gender, Integer> totalAgeByGender = persons
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.reducing(
                                        0,
                                        Person::getAge,
                                        Integer::sum)));

        double average = persons
                .parallelStream()
                .filter(p -> p.getGender() == Person.Gender.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

        ConcurrentMap<Person.Gender, List<Person>> byGender = persons
                .parallelStream()
                .collect(
                        Collectors.groupingByConcurrent(Person::getGender));
    }

}
