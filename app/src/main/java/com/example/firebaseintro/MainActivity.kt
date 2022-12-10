package com.example.firebaseintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val shoppingList = mutableListOf<ShoppingItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

//        val item1 = ShoppingItem("gurka", false, "grönsak")
//        val item2 = ShoppingItem("ost", false, "kyl")
//        val item3 = ShoppingItem("tomat", false, "grönsak")
//
//        db.collection("items").add(item2)
//        db.collection("items").add(item3)


        val docRef = db.collection("items")

//        docRef.get().addOnSuccessListener { documentSnapShot ->
//            for (document in documentSnapShot.documents) {
//                val item = document.toObject<ShoppingItem>()
//                if ( item != null) {
//                    shoppingList.add(item)
//                }
//            }
//
//            printShoppingItems()
//
//
//        }

        docRef.addSnapshotListener { snapshot, e ->
            if(snapshot != null) {
                shoppingList.clear()
                for (document in snapshot.documents) {
                    val item = document.toObject<ShoppingItem>()
                    if (item != null) {
                        shoppingList.add(item)
                    }
                }
            }

            printShoppingItems()

        }


    }

    fun printShoppingItems() {
        for (item in shoppingList ) {
            Log.d("!!!", "${item.name}")
        }
    }
}