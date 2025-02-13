package com.example.counter.ui.theme

data class Countermodel(var count :Int){
    class counterrepo {
        private var counter = Countermodel(0)


        fun getcounter() = counter
        fun incr(){
            counter.count++
        }
        fun decr(){
            counter.count--
        }
    }
}