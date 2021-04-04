package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>(Arrays.asList("My", "name", "is", "John", "Doe"));
        List<List<String>> flatten = Arrays.asList(
                Arrays.asList("Victor", "Farcic"), Arrays.asList("John", "Doe", "Third"));
        List<Person> people = Arrays.asList(
                new Person("Sara", 4, "Portuguese"), new Person("Victor", 40, "Serbian"),
                new Person("Eva", 43, "Serbian"), new Person("Anna", 5, "Portuguese"));
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 7, 2, 3, 8));


//        1 - Convert elements of a collection to upper case.

        System.out.println("UpperCase: " + toUpperCase(list));

//        2 - Filter collection so that only elements with less than 4 characters are returned.

        System.out.println("Less than 4: " + lessThanFour(list));

//        3 - Flatten multidimensional collection (read about .flatMap non-terminal operation and use it)

        System.out.println("Flatten list: " + flatMultidimensionalCollection(flatten));

//        4 -Get oldest person from the collection

        System.out.println("Oldest person: " + getOldestPerson(people));

//        5 - Sum all elements of a numeric collection

        System.out.println("Sum of numbers: " + sum(numbers));

//        6 - Get names of all kids (under age of 18)

        System.out.println("Kids names: " + getNamesOfKids(people));

//        7 Partition adults and kids

        System.out.println("People partition: " + partition(people));

//        8 - Group people by nationality

        System.out.println("Grouping by nationality: " + groupByNationality(people));

//        9 - Return people names separated by comma

        System.out.println("Names separated by comma: " + separateByComma(people));

    }

    static List<String> toUpperCase(ArrayList<String> list) {
        List<String> upperCaseList = list.stream()
                .map(each -> each.toUpperCase())
                .collect(Collectors.toList());
        return upperCaseList;
    }

    static List<String> lessThanFour(ArrayList<String> list) {
        List<String> lessThan = list.stream()
                .filter(each -> each.length() < 4)
                .collect(Collectors.toList());
        return lessThan;
    }

    static List<String> flatMultidimensionalCollection(List<List<String>> flatten) {
        List<String> collect = flatten.stream()
                .flatMap(each -> each.stream())
                .collect(Collectors.toList());
        return collect;
    }

    static Person getOldestPerson(List<Person> people) {
        Person person = people.stream()
                .sorted(Comparator.reverseOrder())
                .findFirst().orElse(new Person());
        return person;
    }

    static int sum(ArrayList<Integer> numbers) {
        int sum = numbers.stream()
                .mapToInt(each -> each).sum();
        return sum;
    }

    static List<String> getNamesOfKids(List<Person> people) {
        List<String> kidsNames = people.stream()
                .filter(each -> each.getAge() < 18)
                .map(each -> each.getName())
                .collect(Collectors.toList());
        return kidsNames;
    }

    static Map<Boolean, List<Person>> partition(List<Person> people) {
        List<Person> notAdult = people.stream()
                .filter(each -> each.getAge() < 18).collect(Collectors.toList());
        List<Person> adult = people.stream()
                .filter(each -> each.getAge() >= 18).collect(Collectors.toList());
        HashMap<Boolean, List<Person>> hashMap = new HashMap<>();
        hashMap.put(true, adult);
        hashMap.put(false, notAdult);
        return hashMap;
    }

    static Map<String, List<Person>> groupByNationality(List<Person> people) {
        Map<String, List<Person>> collect = people.stream()
                .collect(Collectors.groupingBy(person -> person.getNationality()));
        return collect;
    }

    static String separateByComma(List<Person> people) {
        String collect = people.stream()
                .map(each -> each.getName() + ", ")
                .collect(Collectors.joining());
        return collect;
    }
}
