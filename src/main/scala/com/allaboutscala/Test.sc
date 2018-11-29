val split = "h a".split(" ") match {
  case Array(s1, s2) => (s1, s2)
  case Array(s1) if (!s1.isEmpty) => (s1, "")
  case _ => ("", "")
}

println(split)