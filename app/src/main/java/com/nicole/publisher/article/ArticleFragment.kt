package com.nicole.publisher.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.nicole.publisher.Article
import com.nicole.publisher.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {

        val binding = FragmentArticleBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        binding.viewModel = viewModel

        val articleListAdapter = ArticleListAdapter()
        binding.recycleView.adapter = articleListAdapter

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

//      拿到資料
            db.collection("articles")
                .get().addOnSuccessListener {
                for (document in it) {
                    Log.d("getArticleList", "${document.id} => ${document.data}")
                }
            }
                .addOnFailureListener { exception ->
                    Log.w("GET", "Error getting documents.", exception)
        }

//      確認author是否存在
        val searchAuthor = ""
        db.collection("articles").whereEqualTo("author",searchAuthor)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("email", "${document.id} => ${document.data}")
                }
            }

//       監聽到有變化後 更新UI
        var articleList = mutableListOf<Article>()
        db.collection("articles")
            .addSnapshotListener { snapshot, error ->
            snapshot?.let {
                for (document in it.documents) {
                    Log.d("invite", "${document.id} => ${document.data}")
                    val articleList = it.toObjects(Article::class.java)
                    Log.d("list", "articleList=$articleList")
                    articleListAdapter.submitList(articleList)
                }
            }
        }

        return binding.root
    }
}