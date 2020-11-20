package com.nicole.publisher.publish

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.nicole.publisher.Article
import com.nicole.publisher.Author
import com.nicole.publisher.databinding.FragmentPublishBinding

class PublishFragment : DialogFragment() {

    private lateinit var viewModel: PublishViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {

        val binding = FragmentPublishBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        //傳data到firebase
        binding.sendBtn.setOnClickListener {
            db.collection("articles").add(sendToFirebase(binding))
                .addOnSuccessListener { documentReference ->
                    Log.d("articles", "DocumentSnapshot added with ID: ${documentReference.id}")

                    //更新欄位的id等於document的id
                    documentReference.update("id",documentReference.id )
                }
                .addOnFailureListener { e ->
                    Log.w("post", "Error adding document", e)
                }
        }


      return binding.root
    }

    fun sendToFirebase(binding: FragmentPublishBinding) : Article {
        val article = Article(
            title = binding.titleEt.text.toString(),
            content = binding.categoryEt.text.toString(),
            category = binding.categoryEt.text.toString(),
        )
        return article
    }
}