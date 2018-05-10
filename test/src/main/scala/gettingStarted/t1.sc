val list = "Hello world test string".split(" ")
list.fold("") {(z, i) => z + ":" + i }

