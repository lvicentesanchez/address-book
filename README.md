# Coding challenge

## Requirements

- Java8 installed

## Assumptions

As we are using a RB-Tree, we need to define an ordering for Person instances:

1. They would be ordered by date.
2. If the date is equal, they would be ordered by name (lexicographical order, surname first)
3. If the name is equal, they would be order by gender (lexicographical order)

## How to run?

To get/validate the answers to all questions, run:

`./sbt "testOnly *Answers"`
