val split = "".split(" ") match {
  case Array(s1, s2) => println(1); (s1, s2)
  case Array(s1) => println(2); (s1, "")
  case _ => ("", "")
}

println(split)