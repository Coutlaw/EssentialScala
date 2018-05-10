var a = Array[Int](1,2,3,4,5,6,7)
a.map(i=> i*2)

//filters apply a filter to the data
a.filter(i => i%2 ==0)
a.filter(i => i%2 ==1)
a.filter(i => i%2 ==1).map(_*2)

val list = "hello world this is a string".split(" ")
list.fold("") {(z,i) => z + ":" + i }

val list2 = List.range(0,20)
list2.fold(0) {(z,i) => z+i}
