/*
* Travis Hoover
* CSCI 3210 Project 2
* Professor Dong
* April 9, 2017
* */

import scala.io.Source //necessary library for file I/O

//prompt user for file to read data from
println("Please enter the file containing students and grades:")
val filename = scala.io.StdIn.readLine()


//Student object accepting grades from filename and calculates appropriate grades
class Student(var id : String, var CLA : String, var OLA : String, var quizzes : String, var exams : String, var finalScores : String) {
  
  def totalPoints : Int = this.CLA.toInt + this.OLA.toInt + this.quizzes.toInt + this.exams.toInt + this.finalScores.toInt

  //initialize letter grade
  var letterGrade : String = _

  //calculate student's letter grade
  if(totalPoints >= 90)
    letterGrade = "A"
  else if(totalPoints >= 87 && totalPoints < 90)
    letterGrade = "B+"
  else if (totalPoints >= 83 && totalPoints < 87)
    letterGrade = "B"
  else if (totalPoints >= 80 && totalPoints < 83)
    letterGrade = "B-"
  else if (totalPoints >= 77 && totalPoints < 80)
    letterGrade = "C+"
  else if (totalPoints >= 73 && totalPoints < 77)
    letterGrade = "C"
  else if (totalPoints >= 70 && totalPoints < 73)
    letterGrade = "C-"
  else if (totalPoints >= 67 && totalPoints < 70)
    letterGrade = "D+"
  else if (totalPoints >= 63 && totalPoints < 67)
    letterGrade = "D"
  else if (totalPoints >= 60 && totalPoints < 63)
    letterGrade = "D-"
  else if (totalPoints > 60)
    letterGrade = "F"
  else
    println("Error, invalid totalPoints calculation\n")


  //output student information
  printf("\nCLA grade of %s\n", CLA)
  printf("OLA grade of %s\n", OLA)
  printf("Quiz grade of %s\n", quizzes)
  printf("Exam grade of %s\n", exams)
  printf("Final grade of %s\n", finalScores)
  printf("Combined total points for %s is %d\n", id, totalPoints)
  printf("The letter grade for %s is %s\n\n", id, letterGrade)
}


//create map using data from file named filename. Use drop(1) to discard headers in file
val gradeMap = io.Source.fromFile(filename).getLines.drop(1).map {
  l =>
    val Array(k,v1,v2,v3,v4,v5,_*) = l.split("\\s+")  //builds map pairing array using the regular expression \\s+ to discard whitespaces
    k -> (v1,v2,v3,v4,v5) //binds values of array to key (key being the C# of the student)
  }.toMap

//for ((k,v) <- gradeMap) printf("key: %s, values: %s\n", k, v)  //prints out map


println("Enter first query: ")
val firstQuery = scala.io.StdIn.readLine()
try {
  new Student(firstQuery, gradeMap(firstQuery)._1, gradeMap(firstQuery)._2, gradeMap(firstQuery)._3, gradeMap(firstQuery)._4, gradeMap(firstQuery)._5)
} catch {
  case ex : Exception => println(ex, "(C number does not exist)\n")
}

println("Enter second query: ")
val secondQuery = scala.io.StdIn.readLine()
try {
  new Student(secondQuery, gradeMap(secondQuery)._1, gradeMap(secondQuery)._2, gradeMap(secondQuery)._3, gradeMap(secondQuery)._4, gradeMap(secondQuery)._5)
} catch {
  case ex : Exception => println(ex, "(C number does not exist)\n")
}

println("Hit any key to continue...")
val anyKey = scala.io.StdIn.readLine()

//Print out all values at the end
try {
    for (line <- Source.fromFile(filename).getLines()) {
      println(line)
    }
} catch {
  case ex: Exception => println(ex)
}
